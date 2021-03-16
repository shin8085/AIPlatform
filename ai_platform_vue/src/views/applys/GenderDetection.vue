<template>
  <div>
    <div class="apply_description">
      <div class="apply_description_container">
        <div class="apply_description_name">性别检测</div>
        <div class="apply_description_detail">检测人的性别</div>
      </div>
    </div>
    <el-upload
      class="upload-demo"
      action="/api/apply/gender_detection"
      accept="image/png, image/jpeg"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :on-change="onChange"
      multiple
      :limit="1"
      :on-exceed="handleExceed"
      :file-list="fileList"
      :auto-upload="false">
      <el-button slot="trigger" size="small" type="primary">上传图片</el-button>
      <el-button size="small" type="primary" @click="takeVideo">{{openOrClose}}</el-button>
      <div slot="tip" class="el-upload__tip">支持上传jpg/png图片,大小小于10MB</div>
    </el-upload>

    <div class="demo-image">
      <el-container v-loading="loading">
        <img
          :src="url"
          v-show="isShow"
          alt=""
          style="max-height: 400px;margin-left: auto;margin-right: auto">
      </el-container>
    </div>
  </div>
</template>

<script>
import "../../assets/css/apply_page.css"
export default {
  name: "GenderDetection",
  data() {
    return {
      fileList: [],
      fit: 'contain',
      url: '/api/images/initImage.jpg',
      isShow: false,
      loading: false,
      stop:false, //是否停止上传
      isTakeVideo:false, //是否正在拍摄视频
      openOrClose:"开启摄像头"
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.isShow=false;
      this.url='/api/images/initImage.jpg';
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`只能同时上传一张图片`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    onChange(file){
      if(this.isTakeVideo===true){
        this.$alert("请先关闭摄像头", '提示', {
          confirmButtonText: '确定',
        });
        this.fileList=[];
        return;
      }
      this.loading=true;
      this.isShow=true;
      let _this=this;
      let reader = new FileReader()
      reader.readAsDataURL(file.raw)
      reader.onload  = function(event){
        let img_base64;
        if(file.name.split('.')[1]==='png'){
          img_base64 = this.result.substr(22);
        }else{
          img_base64 = this.result.substr(23)
        }
        _this.$axios.post("/api/apply/gender_detection",{base64Data:img_base64}).then(res=>{
          let data=res.data;
          if(data.code===200){
            //检测成功
            _this.loading=false;
            _this.url="data:image/jpeg;base64,"+data.data.base64Data;
          }else{
            //检测失败
            _this.loading=false;
            _this.isShow=false;
            _this.$alert(data.message, '提示', {
              confirmButtonText: '确定',
            });
          }
        })
      }
    },
    takeVideo(){
      if(this.isTakeVideo===false){
        this.openCamera();
        this.isTakeVideo=true;
        this.openOrClose="关闭摄像头"
      }else{
        this.closeCamera();
        this.isTakeVideo=false;
        this.openOrClose="开启摄像头"
      }
    },
    openCamera(){
      // this.video = document.getElementById('video');
      this.video = document.createElement('video');
      if (navigator.mediaDevices.getUserMedia) {
        //最新的标准API
        navigator.mediaDevices.getUserMedia({video : {width: 640, height: 360}}).then(this.success).catch(this.error);
      } else if (navigator.webkitGetUserMedia) {
        //webkit核心浏览器
        navigator.webkitGetUserMedia({video : {width: 640, height: 360}},this.success, this.error)
      } else if (navigator.mozGetUserMedia) {
        //firefox浏览器
        navigator.mozGetUserMedia({video : {width: 640, height: 360}}, this.success, this.error);
      } else if (navigator.getUserMedia) {
        //旧版API
        navigator.getUserMedia({video : {width: 640, height: 360}}, this.success, this.error);
      }
    },
    closeCamera(){
      clearInterval(this.intervalId); //清楚定时器
      this.stop=true;
      let tracks=this.video.srcObject.getTracks();
      tracks.forEach(function(track){
        track.stop();
      })
      this.video.srcObject=null;
    },
    success(stream) {
      //兼容webkit核心浏览器
      // let CompatibleURL = window.URL || window.webkitURL;

      //将视频流设置为video元素的源
      console.log(stream);

      //video.src = CompatibleURL.createObjectURL(stream);
      this.video.srcObject = stream;
      this.video.play();
      this.isShow=true;
      this.fileList=[];
      this.intervalId=setInterval(()=>{
        if(this.stop===false){
          this.stop=true; //等待请求完成再进行下一次拍摄上传
          this.takePhoto();
        }
      },100);
    },
    error(error) {
      console.log(`访问用户媒体设备失败${error.name}, ${error.message}`);
    },
    takePhoto(){
      let canvas=document.createElement("canvas");
      canvas.width=640;
      canvas.height=360;
      let context = canvas.getContext("2d");
      context.drawImage(this.video, 0, 0, 640, 360);
      let base64Data=canvas.toDataURL().substr(22); //转为base64并截取后22位
      this.$axios.post('/api/apply/gender_detection',{base64Data:base64Data}).then(res=>{
        this.stop=false; //开始下一次拍摄上传
        this.url="data:image/png;base64,"+res.data.data.base64Data;
      })
    }
  },
  beforeDestroy() {
    if(this.isTakeVideo===true){
      this.closeCamera();
    }
  }
}
</script>

<style scoped>
.upload-demo{
  line-height: 60px;
  width: 300px;
  margin-right: auto;
  margin-left: auto;
  margin-bottom: 5px;
}
.el-upload__tip{
  line-height: 20px;
}
</style>
