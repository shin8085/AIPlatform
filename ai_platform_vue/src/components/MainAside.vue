<template>
  <div style="width: inherit">
    <h5>AI开放平台</h5>
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @select="onSelect"
      background-color="#333"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>应用</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1">人脸检测</el-menu-item>
          <el-menu-item index="1-2">人脸识别</el-menu-item>
          <el-menu-item index="1-3">人脸特征</el-menu-item>
          <el-menu-item index="1-4">年龄检测</el-menu-item>
          <el-menu-item index="1-5">目标检测</el-menu-item>
          <el-menu-item index="1-6">烟雾检测</el-menu-item>
          <el-menu-item index="1-7">驾驶员状态检测</el-menu-item>
          <el-menu-item index="1-8">口罩检测</el-menu-item>
          <el-menu-item index="1-9">性别检测</el-menu-item>
          <el-menu-item index="1-10">疲劳驾驶检测</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="2">
        <i class="el-icon-setting"></i>
        <span slot="title">设置</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "MainAside",
  methods: {
    onSelect(index){
      switch (index){
        case '1-4':
          if(this.$router.currentRoute.path!=="/ageEstimation"){
            this.$router.push("/ageEstimation");
          }
          break;
        case '1-9':
          if(this.$router.currentRoute.path!=="/genderDetection"){
            this.$router.push("/genderDetection");
          }
          break;
        case '2':
          if(this.$router.currentRoute.path!=="/setting"){
            this.$router.push("/setting");
          }
          break;
      }
    }
  },
  mounted() {
    this.$router.push("/apply")
  }
}
</script>

<style scoped>
.el-menu-item{
  width: 200px;
}
.el-submenu{
  width: 200px;
}
</style>
