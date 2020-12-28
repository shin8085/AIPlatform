
applyNameTran={
  nameMap:{
    face_recognize:"人脸识别",
    age_estimation:"年龄检测",
    object_detection:"目标检测",
    smoke_detection:"烟雾检测",
    distracted_driver_detection:"驾驶员状态检测",
    mask_detection:"口罩检测",
    gender_detection:"性别检测"
  },
  EnglishToChinese:function(name){
    return this.nameMap[name];
  },
  ChineseToEnglish:function(name){
    for(let key in this.nameMap){
      if(this.nameMap[key]===name){
        return key;
      }
    }
  }
}
