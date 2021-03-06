const state = {
  myOwnTheses: [],
  myCandidateTheses: [],
  myPickedThesis: {}
}

const getters = {
  getMyOwnTheses (state) {
    return state.myOwnTheses
  },
  getMyCandidateTheses (state) {
    return state.myCandidateTheses
  },
  getMyPickedThesis (state) {
    return state.myPickedThesis
  }
}

const actions = {
  fetchWorkplace ({ commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get('/api/thesis/workplace')
        .then(res => {
          commit('fetchWorkplace', res.data)
          resolve()
        })
        .catch(err => {
          reject(err)
        })
    })
  },
  removeThesis ({commit}, thesis, thesisState) {
    commit('removeThesis', thesis, thesisState)
  },
  resetWorkplace ({ commit }) {
    commit('resetWorkplace')
  }
}

const mutations = {
  fetchWorkplace (state, data) {
    state.myOwnTheses = data.myOwnTheses
    state.myCandidateTheses = data.myCandidates
    state.myPickedThesis = data.pickedThesis
  },
  removeThesis (state, thesis, thesisState) {
    // state.myOwnTheses.splice(state.myOwnTheses.indexOf(thesis), 1);
    console.log(state.myOwnTheses[state.myOwnTheses.indexOf(thesis)])
    state.myOwnTheses[state.myOwnTheses.indexOf(thesis)].thesisStateCode = thesisState
  },
  resetWorkplace (state) {
    state.myOwnTheses = []
    state.myCandidateTheses = []
    state.myPickedThesis = {}
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
