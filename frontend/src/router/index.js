import Vue from 'vue'
import Router from 'vue-router'

import EntryPoint from '@/components/EntryPoint'
import HomeStudent from '@/components/HomeStudent'
import AddThesis from '@/components/AddThesis'
import UserProfile from '@/components/UserProfile'
import UserWorkplace from '@/components/UserWorkplace'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'entry',
      component: EntryPoint,
      meta: {
        auth: false
      }
    },
    {
      path: '/home',
      name: 'home',
      component: HomeStudent,
      meta: {
        auth: true
      }
    },
    {
      path: '/thesis/add',
      name: 'addThesis',
      component: AddThesis,
      meta: {
        auth: true
      }
    },
    {
      path: '/profile',
      name: 'profile',
      component: UserProfile,
      meta: {
        auth: true
      }
    },
    {
      path: '/workplace',
      name: 'workplace',
      component: UserWorkplace,
      meta: {
        auth: true
      }
    }
  ]
})
