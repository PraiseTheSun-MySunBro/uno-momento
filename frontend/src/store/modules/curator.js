const state = {
  curators: {}
}

const getters = {
  getCurators (state) {
    return state.curators
  }
}

const actions = {
  fetchCurators ({ state, commit }) {
    return new Promise((resolve, reject) => {
      axios.get('/api/curators')
        .then(res => {
          commit('fetchCurators', res.data)
          resolve(res.data)
        })
        .catch(err => {
          reject(err)
        })
    })
  }
}

const mutations = {
  fetchCurators (state, curators) {
    state.curators = curators
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
