<template>
  <div>
    <div>
      <span style="font-size: 40px;font-weight: bold">欢迎使用AI开放平台</span>
    </div>
    <div>
      <el-row :gutter="40">
        <el-col :span="8">
          <router-link to="/faceRecognize" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">人脸识别</div>
                <div class="explain">识别人脸，并且使用方框进行标识</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="8">
          <router-link to="/ageEstimation" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">年龄检测</div>
                <div class="explain">检测人的年龄信息</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="8">
          <router-link to="/objectDetection" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">目标检测</div>
                <div class="explain">检测物体的类别，目前可检测80种物体</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
      </el-row>
      <el-row :gutter="40">
        <el-col :span="8">
          <router-link to="/smokeDetection" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">烟雾检测</div>
                <div class="explain">检测是否有烟雾，返回有烟雾的概率</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="8">
          <router-link to="/distractedDriverDetection" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">驾驶员状态检测</div>
                <div class="explain">检测驾驶员的驾驶状态，目前可检测9种状态</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="8">
          <router-link to="/maskDetection" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">口罩检测</div>
                <div class="explain">检测是否佩戴口罩</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
      </el-row>
      <el-row :gutter="40">
        <el-col :span="8">
          <router-link to="/genderDetection" class="r_link">
            <el-card shadow="hover">
              <div class="grid-content">
                <div class="title">性别检测</div>
                <div class="explain">检测人的性别</div>
              </div>
            </el-card>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomePage"
}
</script>

<style scoped>
.el-row{
  margin-top: 10px;
  margin-bottom: 50px;
}
.grid-content{
  line-height: 50px;
}
.title{
  font-size: 22px;
  font-weight: bold;
}
.r_link {
  text-decoration: none;
}
</style>
