import router from '../router'
import Axios from "axios";
import '../utils/common'
import MainHeader from "../components/MainHeader";
import {Main} from "element-ui";
router.beforeEach((to,from,next)=>{
  document.title="AI开放平台"; //网页标题
  // sessionStorage.setItem("pagePath",common.getPagePath(to.name))
  dataTran.$emit('pagePath',common.getPagePath(to.name));
  if(to.matched.some(res=>res.meta.requireAuth)){ //判断是否需要权限
    let loginSessionId=sessionStorage.getItem("loginSessionId");
    if(loginSessionId){
      //存在session id
      Axios.get("/api/checkLoginSession",{
        params:{'loginSessionId':loginSessionId}
      }).then(res=>{
        if(res.data.code===200){
          //session id正确
          // next();
          toView(to,next);
        }else{
          next({
            path:"/login"
          })
        }
      })
    }else{
      next({
        path:"/login"
      })
    }
  }else{
    next();
  }
})
function toView(to,next){
  Axios.get("/api/applyManage/getApplyInfo").then(res=>{
    let ai_status=res.data.data;
    let ai_opened=[];
    let ai_apply=[];
    for(let i=0;i<ai_status.length;i++){
      ai_apply.push(ai_status[i].apply_name)
      if(ai_status[i].is_open===true){
        ai_opened.push(ai_status[i].apply_name)
      }
    }
    let url=to.name.replace(/\B([A-Z])/g,'_$1').toLowerCase();
    if(ai_opened.includes(url)||!ai_apply.includes(url)){
      //ai应用已开放
      next();
    }else{
      //ai应用未开放
      next({
        path:"/applyUnOpen"
      })
    }
  })
}
