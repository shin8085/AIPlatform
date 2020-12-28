<template>
  <div>
    <el-table
      :data="tableData"
      border
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      style="width: 48%;line-height: 40px;margin-right: auto;margin-left: auto">
      <el-table-column
        prop="apply_name"
        label="应用名称"
        width="250px">
      </el-table-column>
      <el-table-column
        prop="invoking_count"
        label="调用次数"
        width="251px">
      </el-table-column>
      <el-table-column label="操作" width="121px">
        <template slot-scope="scope">
          <el-switch
            v-model="tableData[scope.$index].is_open"
            @change="onSwitchChange(tableData[scope.$index])">
          </el-switch>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import "../../utils/applyNameTran"
export default {
  name: "ApplyManage",
  data() {
    return {
      value:true,
      tableData: []
    }
  },
  methods:{
    onSwitchChange(row){
      let apply={};
      apply.is_open=row.is_open;
      apply.apply_name=applyNameTran.ChineseToEnglish(row.apply_name);
      this.$axios.post('/api/applyManage/updateApplyInfo',apply).then(res=>{
        console.log(res)
      })
    }
  },
  mounted() {
    this.$axios.get('/api/applyManage/getApplyInfo').then(res=>{
      for (let i=0;i<res.data.data.length;i++){
        res.data.data[i].apply_name=applyNameTran.EnglishToChinese(res.data.data[i].apply_name);
      }
      this.tableData=res.data.data;
    })
  }
}
</script>

<style scoped>

</style>
