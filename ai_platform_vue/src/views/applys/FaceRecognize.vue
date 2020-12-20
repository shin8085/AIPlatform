<template>
  <div>
    <el-upload
      class="upload-demo"
      action="/api/apply/face_recognize"
      accept="image/png, image/jpeg"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :on-success="onSuccess"
      :on-progress="onProgress"
      :on-change="onChange"
      multiple
      :limit="1"
      :on-exceed="handleExceed"
      :file-list="fileList"
      :auto-upload="false">
      <el-button size="small" type="primary">上传图片</el-button>
      <div slot="tip" class="el-upload__tip">支持上传jpg/png图片,大小小于10MB</div>
    </el-upload>

    <div class="demo-image">
      <el-image
        style="width: 400px; height: 400px"
        :src="url"
        :fit="fit"
        v-show="isShow"
        v-loading="loading"></el-image>
    </div>
  </div>
</template>

<script>
export default {
  name: "FaceRecognize",
  data() {
    return {
      fileList: [],
      fit: 'contain',
      url: '/api/images/initImage.jpg',
      isShow: false,
      loading: false
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
    onSuccess(data){
      if(data.code===200){
        //检测成功
        this.loading=false;
        this.isShow=true;
        this.url='http://localhost:8081'+data.message;
      }else{
        //检测失败
        this.loading=false;
        this.isShow=false;
        this.$alert(data.message, '提示', {
          confirmButtonText: '确定',
        });
      }
    },
    onProgress(){
      this.loading=true;
      this.isShow=true;
    },
    onChange(file){
      let reader = new FileReader()
      reader.readAsDataURL(file.raw)
      reader.onload  = function(event){
        let img_base64 = this.result
        console.log(img_base64)
        
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
