import router from '../router'
import Axios from "axios";
router.beforeEach((to,from,next)=>{
  if(to.matched.some(res=>res.meta.requireAuth)){ //判断是否需要权限
    let loginSessionId=sessionStorage.getItem("loginSessionId");
    if(loginSessionId){
      //存在session id
      Axios.get("/api/checkLoginSession",{
        params:{'loginSessionId':loginSessionId}
      }).then(res=>{
        if(res.data.code===200){
          //session id正确
          next();
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