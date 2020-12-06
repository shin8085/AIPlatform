<template>
  <div class="login_back">
    <div class="loginMain">
      <div class="login_title">登录系统</div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login-ruleForm">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-unlock" v-model="ruleForm.password" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loginSystem('ruleForm')">登录</el-button>
          <el-button type="primary" @click="toRegisterView">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      }
    };
  },
  methods: {
    loginSystem(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/api/login",this.ruleForm).then(res => {
            if(res.data.code===200){
              //校验通过
              sessionStorage.setItem("loginSessionId",res.data.data.id);
              sessionStorage.setItem('username',this.ruleForm.username);
              this.$router.push("/");
            }else{
              this.$message({
                showClose: true,
                message: res.data.message,
                type: 'error'
              });
            }
          })
        }
      });
    },
    toRegisterView(){
      this.$router.push("/register")
    }
  }
}
</script>

<style scoped>
.login_back{
  height: inherit;
  background-color: #606266;
}
.loginMain{
  width: 400px;
  margin-left: auto;
  margin-right: auto;
  padding-top: 200px;
  padding-right: 50px;
}
.login_title{
  margin-bottom: 50px;
  margin-left: 80px;
  font-size: 30px;
  color:white;
}
</style>
