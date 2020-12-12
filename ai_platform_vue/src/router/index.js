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
        {path:'apply',name:'apply',component:Apply},
        {path:'ageEstimation',name:'ageEstimation',component:AgeEstimation},
        {path:'genderDetection',name:'genderDetection',component:GenderDetection},
        {path:'setting',name:'setting',component:Setting},
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
