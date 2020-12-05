<template>
  <el-container direction="horizontal">
    <!--左边-->
    <el-aside width="200px">
      <MainAside/>
    </el-aside>
    <!--右边-->
    <el-container direction="vertical">
      <!--头部-->
      <el-header>
        <MainHeader/>
      </el-header>
      <!--主体-->
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import MainHeader from "../components/MainHeader";
import MainAside from "../components/MainAside";
export default {
  name: "mainView",
  components: {MainAside, MainHeader}
}
</script>

<style scoped>
.el-header, .el-footer {
  border-bottom: 1px solid #d8dce5;
  color: #333;
  text-align: center;
  line-height: 60px;
  margin-top: 0;
}

.el-aside {
  background-color: #333;
  color: #fff;
  text-align: center;
  line-height: 200px;
}

.el-main {
  color: #333;
  text-align: center;
  line-height: 160px;
}
.el-container{
  height: inherit;
}
</style>
