import Vue from 'vue'
import Router from 'vue-router'
import MainView from "../views/MainView";
import Apply from "../views/Apply";
import Setting from "../views/Setting";
import App from "../App";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'mainView',
      component: MainView,
      children:[
        {path:'apply',name:'apply',component:Apply},
        {path:'setting',name:'setting',component:Setting},
      ]
    },
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
