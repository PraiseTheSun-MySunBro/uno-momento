const state = {
  user: {
    id: -1,
    firstname: '',
    lastname: '',
    state: 0,
    roleCode: -1
  },
  connectionState: true
}

const getters = {
  getUser(state) {
    return state.user
  },
  getConnectionState(state) {
    return state.connectionState
  }
}

const actions = {
  fetchUser({ commit }) {
    return new Promise((resolve, reject) => {
      if (state.user.id !== -1) {
        resolve()
        return
      }

      axios.get('/auth/user')
        .then(res => {
          commit('fetchUser', res.data)
          resolve()
        })
        .catch(err => {
          commit('connectionState', !!err.response)
          reject(err)
        })
    })
  }
}

const mutations = {
  fetchUser(state, user) {
    state.user = user
  },
  connectionState(state, connectionState) {
    state.connectionState = connectionState
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
