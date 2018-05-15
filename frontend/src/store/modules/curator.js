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
  },
  joinAsCandidate ({ commit }, data) {
    commit('joinAsCandidate', data)
  },
  leaveAsCandidate ({ commit }, data) {
    commit('leaveAsCandidate', data)
  }
}

const mutations = {
  fetchCurators (state, curators) {
    state.curators = curators
  },
  joinAsCandidate (state, data) {
    let theses = JSON.parse(state.curators[data.lectureIndex].theses)
    if (theses[data.thesisIndex].candidates == null) {
      theses[data.thesisIndex].candidates = []
    }
    theses[data.thesisIndex].candidates.push(data.personId)
    state.curators[data.lectureIndex].theses = JSON.stringify(theses)
  },
  leaveAsCandidate (state, data) {
    let theses = JSON.parse(state.curators[data.lectureIndex].theses)
    let idx = theses[data.thesisIndex].candidates.indexOf(data.personId)
    theses[data.thesisIndex].candidates.splice(idx, 1)
    state.curators[data.lectureIndex].theses = JSON.stringify(theses)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
