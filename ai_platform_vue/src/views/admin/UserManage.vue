<template>
  <div>
    <el-table
      :data="tableData"
      border
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      style="width: 100%;line-height: 40px;">
      <el-table-column
        prop="username"
        label="用户名"
        width="120px">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码"
        width="120px">
      </el-table-column>
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
      <el-table-column label="操作" width="240px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--编辑对话框-->
    <el-dialog
      :close-on-click-modal=false
      :visible.sync="editDialogVisible"
      width="25%">
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="120px"
        class="edit-ruleForm"
        size="small">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="人脸识别" prop="face_recognize">
          <el-input v-model.number="ruleForm.face_recognize"></el-input>
        </el-form-item>
        <el-form-item label="年龄检测" prop="age_estimation">
          <el-input v-model.number="ruleForm.age_estimation"></el-input>
        </el-form-item>
        <el-form-item label="目标检测" prop="object_detection">
          <el-input v-model.number="ruleForm.object_detection"></el-input>
        </el-form-item>
        <el-form-item label="烟雾检测" prop="smoke_detection">
          <el-input v-model.number="ruleForm.smoke_detection"></el-input>
        </el-form-item>
        <el-form-item label="驾驶员状态检测" prop="distracted_driver_detection">
          <el-input v-model.number="ruleForm.distracted_driver_detection"></el-input>
        </el-form-item>
        <el-form-item label="口罩检测" prop="mask_detection">
          <el-input v-model.number="ruleForm.mask_detection"></el-input>
        </el-form-item>
        <el-form-item label="性别检测" prop="gender_detection">
          <el-input v-model.number="ruleForm.gender_detection"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="commit">确认</el-button>
          <el-button type="primary" @click="editDialogVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserManage",
  data() {
    return {
      tableData: [],
      editDialogVisible:false,
      row:{},
      ruleForm: {
        username: '',
        password: '',
        face_recognize:'',
        age_estimation:'',
        object_detection:'',
        smoke_detection:'',
        distracted_driver_detection:'',
        mask_detection:'',
        gender_detection:''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        face_recognize: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
        age_estimation: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
        object_detection: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
        smoke_detection: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
        distracted_driver_detection: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
        mask_detection: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
        gender_detection: [
          { required: true, message: '该字段不能为空', trigger: 'blur' },
          { type: 'number', message: '该字段必须为数字'}
        ],
      }
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
      this.row=row;
      this.ruleForm=JSON.parse(JSON.stringify(row)); // 深拷贝
      this.ruleForm.oldUsername=row.username;
      this.editDialogVisible=true;
    },
    handleDelete(index, row) {
      console.log(index, row);
      this.$confirm('是否删除该用户的所有信息？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/api/userManage/deleteUserInfo",{username:row.username}).then(res=>{
          if(res.data.code===200){
            this.tableData.splice(index,1);
            this.$message({
              showClose: true,
              type: 'success',
              message: '删除成功!'
            });
          }else{
            this.$message({
              showClose: true,
              message: res.data.message,
              type: 'error'
            });
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    commit(){
      this.$axios.post("/api/userManage/editUserInfo",this.ruleForm).then(res=>{
        if(res.data.code===200){
          for(let key in this.row){
            this.row[key]=this.ruleForm[key];
          }
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
    this.$axios.get("/api/userManage/getUserInfo").then(res=>{
      this.tableData=res.data.data;
    })
  }
}
</script>

<style scoped>

.el-form-item{
  margin-bottom: 15px;
}
</style>
