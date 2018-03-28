import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/Home'
import Login from '@/components/Login'
import AddThesis from '@/components/AddThesis'
import Workplace from '@/components/Workplace'
import Profile from '@/components/Profile'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        auth: true
      }
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        auth: false
      }
    },
    {
      path: '/theses/add',
      name: 'addThesis',
      component: AddThesis,
      meta: {
        auth: true
      }
    },
    {
      path: '/workplace',
      name: 'workplace',
      component: Workplace,
      meta: {
        auth: true
      }
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile,
      meta: {
        auth: true
      }
    }
  ]
})
