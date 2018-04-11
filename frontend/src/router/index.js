import Vue from 'vue'
import Router from 'vue-router'

import EntryPoint from '@/components/EntryPoint'
import Home from '@/components/Home'
import AddThesisStudent from '@/components/AddThesisStudent'
import AddThesisLecturer from '@/components/AddThesisLecturer'
import WorkplaceStudent from '@/components/WorkplaceStudent'
import WorkplaceLecturer from '@/components/WorkplaceLecturer'
import UserProfile from '@/components/UserProfile'
import PageNotFound from '@/components/PageNotFound'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '*',
      component: PageNotFound,
      name: 'PageNotFound'
    },
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
      path: '/thesis/add/student',
      alias: '/thesis/edit',
      name: 'addThesisStudent',
      component: AddThesisStudent,
      meta: {
        auth: true
      }
    },
    {
      path: '/thesis/add/lecturer',
      alias: '/thesis/edit',
      name: 'addThesisLecturer',
      component: AddThesisLecturer,
      meta: {
        auth: true
      }
    },
    {
      path: '/thesis/workplace/student',
      name: 'WorkplaceStudent',
      component: WorkplaceStudent,
      meta: {
        auth: true
      }
    },
    {
      path: '/thesis/workplace/lecturer',
      name: 'WorkplaceLecturer',
      component: WorkplaceLecturer,
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
    }
  ]
})
