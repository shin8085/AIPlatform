<template>
  <div style="height: inherit;">
    <span style="float: left;margin-top: 25px">
      <el-breadcrumb separator="/">
      <el-breadcrumb-item v-for="title in titles" :key="title">{{ title }}</el-breadcrumb-item>
    </el-breadcrumb>
    </span>
    <span style="float: right">
      <el-dropdown trigger="click" @command="handleCommand">
        <span class="el-dropdown-link">
          {{this.username}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="changePassword">修改密码</el-dropdown-item>
          <el-dropdown-item command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </span>
    <!--修改密码对话框-->
    <el-dialog title="修改密码" :visible.sync="dialogForChangePassword" width="500px">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
        <el-form-item label="旧密码" prop="oldPass">
          <el-input prefix-icon="el-icon-unlock" type="password" v-model="ruleForm.oldPass" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPass">
          <el-input prefix-icon="el-icon-unlock" type="password" v-model="ruleForm.newPass" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input prefix-icon="el-icon-unlock" type="password" v-model="ruleForm.checkPass" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">修改密码</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import '../utils/common'
export default {
  name: "MainHeader",
  data(){
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
      } else if (value !== this.ruleForm.newPass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      username: '',
      dialogForChangePassword:false,
      titles:[],
      ruleForm: {
        username: '',
        oldPass:'',
        newPass: '',
        checkPass: '',
      },
      rules: {
        oldPass:[
          { required: true, message: "请输入旧密码", trigger: 'blur'},
        ],
        newPass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ],
      }
    }
  },
  mounted() {
    this.username=sessionStorage.getItem("username");
    let identity=sessionStorage.getItem("identity");
    // if(identity==='user'){
      this.titles=common.getPagePath(this.$route.path.split('/')[1]);
    // }else if(identity==="administrator"){
    //   this.titles=common.getPagePath(this.$route.path.split('/')[1]);
    // }
    let _this=this;
    dataTran.$on('pagePath',function(data){
      _this.titles=data;
    })
  },
  methods: {
    handleCommand(command) {
      switch (command){
        case 'changePassword':
          this.dialogForChangePassword=true;
          break;
        case 'logout':
          this.$confirm('即将退出系统, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.get('/api/logout').then(res=>{
              this.$router.push('/login')
            })
          });
          break;
      }
    },
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.username=sessionStorage.getItem('username');
          console.log(this.ruleForm)
          this.$axios.post("/api/changePassword",{
            username:this.ruleForm.username,
            oldPassword:this.ruleForm.oldPass,
            newPassword:this.ruleForm.newPass
          }).then(res => {
            if (res.data.code === 200) {
              this.$alert(res.data.message, '提示', {
                confirmButtonText: '确认',
                callback: action => {
                  this.$router.push("/homePage");
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
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
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
