<template>
  <div>
    <span style="float: right">
      <el-dropdown trigger="click" @command="handleCommand">
        <span class="el-dropdown-link">
          {{this.username}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </span>
  </div>
</template>

<script>
export default {
  name: "MainHeader",
  data(){
    return {
      username: ''
    }
  },
  mounted() {
    this.username=sessionStorage.getItem("username");
  },
  methods: {
    handleCommand(command) {
      switch (command){
        case 'logout':
          this.$confirm('即将退出系统, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.get('/api/logout').then(res=>{
              this.$router.push('/login')
            })
          }).catch(()=>{

          })
      }
    }
  }
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #333;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
