const state = {
  user: {
    id: -1,
    firstname: '',
    lastname: '',
    state: 0,
    roleCode: -1
  }
}

const getters = {
  getUser (state) {
    return state.user
  }
}

const actions = {
  fetchUser ({ commit }) {
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
          commit('setConnectionState', !!err.response)
          reject(err)
        })
    })
  }
}

const mutations = {
  fetchUser (state, data) {
    state.user = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
