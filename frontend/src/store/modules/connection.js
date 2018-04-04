const state = {
  connectionState: true
}

const getters = {
  getConnectionState (state) {
    return state.connectionState
  }
}

const actions = {
  setConnectionState ({ state, commit }, data) {
    commit('setConnectionState', data)
  }
}

const mutations = {
  setConnectionState (state, data) {
    state.connectionState = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
