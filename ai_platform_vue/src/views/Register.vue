<template>
  <div class="login_back">
    <div  class="loginMain">
      <div class="login_title">注册</div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="register-ruleForm">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input prefix-icon="el-icon-unlock" type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input prefix-icon="el-icon-unlock" type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
          <el-button @click="toLoginView">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: '',
        pass: '',
        checkPass: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/api/register",{
            username:this.ruleForm.username,
            password:this.ruleForm.pass
          }).then(res => {
            if (res.data.code === 200) {
              this.$alert(res.data.message, '提示', {
                confirmButtonText: '去登陆',
                callback: action => {
                  this.$router.push("/");
                }
              });
            }else{
              this.$message({
                showClose: true,
                message: res.data.message,
                type: 'error'
              });
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    toLoginView(){
      this.$router.push("/login")
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
