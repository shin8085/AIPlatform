<template>
  <div style="width: inherit">
    <h5 style="line-height: 120px">AI开放平台</h5>
    <el-menu
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      background-color="#333"
      text-color="#fff"
      router
      active-text-color="#ffd04b">
      <!--普通用户侧边栏-->
      <el-menu-item index="/homePage" v-show="user_show">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-submenu index="/apply" v-show="user_show">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>应用</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/faceRecognize">人脸识别</el-menu-item>
          <el-menu-item index="/ageEstimation">年龄检测</el-menu-item>
          <el-menu-item index="/objectDetection">目标检测</el-menu-item>
          <el-menu-item index="/smokeDetection">烟雾检测</el-menu-item>
          <el-menu-item index="/distractedDriverDetection">驾驶员状态检测</el-menu-item>
          <el-menu-item index="/maskDetection">口罩检测</el-menu-item>
          <el-menu-item index="/genderDetection">性别检测</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/personalCenter" v-show="user_show">
        <i class="el-icon-s-home"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
<!--      <el-menu-item index="/setting" v-show="user_show">-->
<!--        <i class="el-icon-setting"></i>-->
<!--        <span slot="title">设置</span>-->
<!--      </el-menu-item>-->
      <!--管理员侧边栏-->
      <el-menu-item index="/userManage" v-show="admin_show">
        <i class="el-icon-user"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/applyManage" v-show="admin_show">
        <i class="el-icon-user"></i>
        <span slot="title">应用管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "MainAside",
  data() {
    return {
      menu_active:'/homePage',
      admin_show:false,
      user_show:false
    }
  },
  mounted() {
    let identity=sessionStorage.getItem("identity");
    if(identity==='user'){
      this.menu_active='/homePage'
      this.user_show=true;
    }else if(identity==="administrator"){
      this.menu_active='/userManage'
      this.admin_show=true;
    }
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
