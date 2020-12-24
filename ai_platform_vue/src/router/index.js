import Vue from 'vue'
import Router from 'vue-router'
import MainView from "../views/MainView";
import Apply from "../views/Apply";
import Setting from "../views/Setting";
import App from "../App";
import Login from "../views/Login";
import Register from "../views/Register";
import AgeEstimation from "../views/applys/AgeEstimation";
import GenderDetection from "../views/applys/GenderDetection";
import FaceRecognize from "../views/applys/FaceRecognize";
import SmokeDetection from "../views/applys/SmokeDetection";
import DistractedDriverDetection from "../views/applys/DistractedDriverDetection";
import MaskDetection from "../views/applys/MaskDetection";
import ObjectDetection from "../views/applys/ObjectDetection";
import HomePage from "../views/HomePage";
import UserManage from "../views/admin/UserManage";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'mainView',
      component: MainView,
      meta:{
        requireAuth:true
      },
      children:[
        {path:'/homePage',name:'homePage',component:HomePage},
        {path:'/apply',name:'apply',component:Apply},
        {path:'/faceRecognize',name:'faceRecognize',component:FaceRecognize},
        {path:'/ageEstimation',name:'ageEstimation',component:AgeEstimation},
        {path:'/objectDetection',name:'objectDetection',component:ObjectDetection},
        {path:'/smokeDetection',name:'smokeDetection',component:SmokeDetection},
        {path:'/distractedDriverDetection',name:'distractedDriverDetection',component:DistractedDriverDetection},
        {path:'/MaskDetection',name:'maskDetection',component:MaskDetection},
        {path:'/genderDetection',name:'genderDetection',component:GenderDetection},
        {path:'/setting',name:'setting',component:Setting},
        {path:'/userManage',name:'userManage',component:UserManage},
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    }
    // {
    //   path: '/apply',
    //   name: 'apply',
    //   component: {
    //     mainContent:Apply
    //   }
    // },
    // {
    //   path: '/setting',
    //   name: 'setting',
    //   component: {
    //     mainContent: Setting
    //   }
    // }
  ]
})
