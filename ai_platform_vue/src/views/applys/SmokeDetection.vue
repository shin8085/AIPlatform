<template>
  <div>
    <el-upload
      class="upload-demo"
      action="/api/apply/smoke_detection"
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
      <el-button size="small" type="primary">上传图片</el-button>
      <div slot="tip" class="el-upload__tip">支持上传jpg/png图片,大小小于10MB</div>
    </el-upload>
    <div class="demo-image" v-show="isShow">
      <span class="demonstration" style="display: block;line-height: 10px">{{ confidences }}</span>
      <el-container v-loading="loading">
        <img
          :src="url"
          alt=""
          style="max-height: 400px;margin-left: auto;margin-right: auto;margin-top: 10px">
      </el-container>
    </div>
  </div>
</template>

<script>
export default {
  name: "SmokeDetection",
  data() {
    return {
      fileList: [],
      fit: 'contain',
      url: '/api/images/initImage.jpg',
      confidences:'',
      isShow: false,
      loading: false
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.isShow=false;
      this.confidences=''
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
    onSuccess(data){
      if(data.code===200){
        //检测成功
        this.loading=false;
        this.isShow=true;
        this.url='http://localhost:8081'+data.data.path;
        this.confidences='有烟雾的概率：'+data.data.confidences+'%';
      }else{
        //检测失败
        this.loading=false;
        this.isShow=false;
        this.$alert(data.message, '提示', {
          confirmButtonText: '确定',
        });
      }
    },
    onChange(file){
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
        _this.$axios.post("/api/apply/smoke_detection",{base64Data:img_base64}).then(res=>{
          let data=res.data;
          if(data.code===200){
            //检测成功
            _this.loading=false;
            _this.url="data:image/jpeg;base64,"+data.data.base64Data;
            _this.confidences='有烟雾的概率：'+data.data.confidences+'%';
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
