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
        class="info_column">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码"
        class="info_column">
      </el-table-column>
      <el-table-column
        prop="face_recognize"
        label="人脸识别"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column
        prop="age_estimation"
        label="年龄检测"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column
        prop="object_detection"
        label="目标检测"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column
        prop="smoke_detection"
        label="烟雾检测"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column
        prop="distracted_driver_detection"
        label="驾驶员状态检测"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column
        prop="mask_detection"
        label="口罩检测"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column
        prop="gender_detection"
        label="性别检测"
        class="ai_apply_column">
      </el-table-column>
      <el-table-column label="操作" width="180px">
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
  </div>
</template>

<script>
export default {
  name: "UserManage",
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
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
.ai_apply_column{
  width: 40px;
}
.info_column{
  width: 100px;
}
</style>
