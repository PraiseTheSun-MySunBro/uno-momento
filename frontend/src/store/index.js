import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import curator from './modules/curator'
import connection from './modules/connection'
import workplace from './modules/workplace'
import thesis from './modules/thesis'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    user,
    curator,
    connection,
    workplace,
    thesis
  },
  strict: debug
})
