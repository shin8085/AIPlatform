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
      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">应用</span>
      </el-menu-item>
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
        case '1':
          this.$router.push("/apply")
          break
        case '2':
          this.$router.push("/setting")
          break
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
</style>
