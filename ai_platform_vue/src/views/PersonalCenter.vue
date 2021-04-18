<template>
  <div>
    <div class="info_list">
      <el-row :gutter="10">
        <el-col :span="6"><span>用户名:</span></el-col>
        <el-col :span="6"><span>{{userInfo.username}}</span></el-col>
        <el-col :span="6">
          <el-button type="primary" style="margin-left: 300px;line-height: 8px;font-size: 5px" @click="editUserInfo">编辑</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6"><span>性别:</span></el-col>
        <el-col :span="6"><span>{{userInfo.sex}}</span></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6"><span>生日:</span></el-col>
        <el-col :span="6"><span>{{userInfo.birthday}}</span></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6"><span>邮箱:</span></el-col>
        <el-col :span="6"><span>{{userInfo.mail}}</span></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6"><span>注册时间:</span></el-col>
        <el-col :span="6"><span>{{userInfo.join_date}}</span></el-col>
      </el-row>
    </div>
    <!--编辑对话框-->
    <el-dialog
      :close-on-click-modal=false
      :visible.sync="editDialogVisible"
      width="25%">
      <el-form
        :model="userInfo"
        ref="ruleForm"
        label-width="120px"
        class="edit-ruleForm"
        size="small">
        <el-form-item label="用户名" prop="username">
          <el-input :disabled="true" v-model="userInfo.username"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="userInfo.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="userInfo.birthday"
            value-format="yyyy-MM-dd"
            style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="邮箱" prop="mail">
          <el-input v-model="userInfo.mail"></el-input>
        </el-form-item>
        <el-form-item label="注册时间" prop="join_date">
          <el-input :disabled="true" v-model="userInfo.join_date"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="commit">确认</el-button>
          <el-button type="primary" @click="editDialogVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-divider></el-divider>
    <el-table
      :data="tableData"
      border
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      style="width: 100%;line-height: 40px;">
      <el-table-column
        prop="face_recognize"
        label="人脸识别">
      </el-table-column>
      <el-table-column
        prop="age_estimation"
        label="年龄检测">
      </el-table-column>
      <el-table-column
        prop="object_detection"
        label="目标检测">
      </el-table-column>
      <el-table-column
        prop="smoke_detection"
        label="烟雾检测">
      </el-table-column>
      <el-table-column
        prop="distracted_driver_detection"
        label="驾驶员状态检测">
      </el-table-column>
      <el-table-column
        prop="mask_detection"
        label="口罩检测">
      </el-table-column>
      <el-table-column
        prop="gender_detection"
        label="性别检测">
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
export default {
  name: "PersonalCenter",
  data(){
    return {
      userInfo:{
        username:null,
        sex:null,
        birthday:null,
        mail:null,
        join_date:null
      },
      tableData: [],
      editDialogVisible:false,

    }
  },
  methods:{
    editUserInfo(){
      this.editDialogVisible=true;
    },
    commit(){
      console.log(this.userInfo)
      this.$axios.post("/api/userInfo/updateUserInfo",this.userInfo).then(res=>{
        if(res.data.code===200){
          this.editDialogVisible=false;
          this.$message({
            showClose: true,
            type: 'success',
            message: res.data.message
          });
        }else{
          this.$message({
            showClose: true,
            message: res.data.message,
            type: 'error'
          });
        }
      }).catch(res=>{
        this.$message({
          showClose: true,
          message: "更新失败",
          type: 'error'
        });
      })
    }
  },
  mounted() {
    this.$axios.get("/api/userInfo/getUserInfo",{
      params:{'username':sessionStorage.getItem('username')}
    }).then(res=>{
      this.userInfo=res.data.data
    })
    this.$axios.get("/api/userInfo/getUserInvokingCount",{
      params:{'username':sessionStorage.getItem('username')}
    }).then(res=>{
      this.tableData=res.data.data;
    })
  }
}
</script>

<style scoped>
.info_list{
  margin-left: 350px;
}
.el-row{
  line-height: 50px;
}
</style>
