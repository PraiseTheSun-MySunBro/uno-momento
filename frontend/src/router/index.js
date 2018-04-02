import Vue from 'vue'
import Router from 'vue-router'

import EntryPoint from '@/components/EntryPoint'
import Home from '@/components/Home'
import AddThesisStudent from '@/components/AddThesisStudent'
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
      component: Home,
      meta: {
        auth: true
      }
    },
    {
      path: '/thesis/add',
      name: 'addThesisStudent',
      component: AddThesisStudent,
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
