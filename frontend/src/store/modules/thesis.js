const state = {
  thesis: {}
}

const getters = {
  getThesis (state) {
    return state.thesis
  }
}

const actions = {
  fetchThesis ({ commit }, data) {
    return new Promise((resolve, reject) => {
      commit('fetchThesis', data)
    })
  },
  resetThesis ({ commit }) {
    commit('resetThesis')
  }
}

const mutations = {
  fetchThesis (state, data) {
    if (data.tags == null || data.tags.length === 0 || data.tags[0] == null) data.tags = []
    state.thesis = data
  },
  resetThesis (state) {
    state.thesis = {}
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
