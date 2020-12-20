<template>
<!--
    摄像头调用测试
-->
  <div>
    <div>
      <el-button @click="openCamera">打开摄像头</el-button>
      <el-button @click="closeCamera">关闭摄像头</el-button>
      <el-button @click="takePhoto">拍照</el-button>
    </div>
    <img :src="base64_src" alt=""/>
  </div>
</template>

<script>
export default {
  name: "Setting",
  data(){
    return{
      video:'',
      fit: 'contain',
      base64_src:'/api/images/initImage.jpg',
      intervalId:null
    }
  },
  methods:{
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
      clearInterval(this.intervalId);
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
      this.intervalId=setInterval(this.takePhoto,1000);
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
      this.$axios.post('/api/apply/test',{base64Data:base64Data}).then(res=>{
        this.base64_src="data:image/png;base64,"+res.data.message;
      })
    }
  },
}
</script>

<style scoped>

</style>
