<template>
  <div class="workplace__student">
    <div class="workplace__container">
      <b-card class="workplace__card" >
          <div class="workplace-chosen-tab__title text-center">
              <h2 class="text--sans text--grey main__tab-title">{{ chosenTab }}</h2>
          </div>
          <div class="tabs__container">
            <b-tabs>
              <!-- final thesis -->
              <b-tab v-on:click="changeTab(1)">
                  <template slot="title" class="tab__name">
                      <em class="text--sans">Valitud lõputöö</em>
                  </template>
                  <b-card class="chosen-thesis__card" v-if="getMyPickedThesis != null && Object.keys(getMyPickedThesis).length !== 0">
                    <h2 class="text--sans text-center">{{ getMyPickedThesis.eeTitle }}</h2>
                    <!-- <h3 class="modal-lecturer-name text--sans text-center">{{ getMyPickedThesis.fullName }}, {{ getMyPickedThesis.supervisorName }}</h3> -->
                    <hr class="hr__chosen">
                    <!-- Card with theses description -->
                    <div class="chosen-thesis-description__container" >
                      <div id="modal-description-headline-container">
                        <i id="modal-description-icon" class="fas fa-info-circle fa-2x"></i>
                        <h5  class="text--sans" id="modal-description-headline">KIRJELDUS</h5>
                      </div>
                      <div class="modal__content">
                        <h5 class="text--sans">{{ getMyPickedThesis.eeDescription }}</h5>
                      </div>
                      <div id="modal-description-tags-headline-container">
                        <i id="modal-description-icon" class="fas fa-tag fa-2x"></i>
                        <h5 class="text--sans" id="modal-description-headline">MÄRKSÕNAD</h5>
                      </div>
                      <div class="modal__content" v-for="(tag, index) in getMyPickedThesis.tags" :key="index">
                        <h5 class="text--sans">- {{ tag }}</h5>
                      </div>
                    </div>
                  </b-card>
                  <div v-else class="text-center empty-list__notification">
                      <h2  class="text--sans text--grey">Valitud lõputöö puudub</h2>
                  </div>
              </b-tab>
              <!-- My offered theses -->
              <b-tab active v-on:click="changeTab(2)">
                  <template slot="title" class="tab__name">
                      <em class="text--sans">Minu pakkumised</em>
                  </template>
                  <div class="tab__button-group text-center">
                      <b-button-group size="sm">
                          <b-button :pressed.sync="activeThesesButtonState"
                                    class="button__status"
                                    @click="chooseAbled">
                                    Aktiivsed
                          </b-button>
                          <b-button :pressed.sync="disableThesesButtonState"
                                    class="button__status"
                                    @click="chooseDisabled">
                                    Mitteaktiivsed
                          </b-button>
                      </b-button-group>
                  </div>
                  <div v-if="getMyOwnTheses != null && Object.keys(getMyOwnTheses).length !== 0">
                    <!-- Active theses -->
                    <div class="active-theses__list" v-if="activeThesesButtonState">
                      <div class="my-submitted__theses">
                        <div class="thesis-group__label">
                          <h6 class="text--sans text--blue">Kinnitatud</h6>
                        </div>
                        <b-list-group>
                            <b-list-group-item class="flex-column align-items-start"
                                              v-for="(thesis, index) in getMyOwnTheses"
                                              v-if="thesis.thesisStateCode === 3"
                                              :key="index">
                              <div class="d-flex w-100 justify-content-between">
                                <h3 class="mb-1">{{ thesis.eeTitle}}</h3>
                                <small class="text-muted"></small>
                              </div>
                              <h6 class="mb-1  text--sans short__description">
                                {{ thesis.eeDescription }}
                              </h6>
                              <small class="text-muted"></small>
                              <div class="float-right">
                                <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                  Vaata detailid
                                </b-button>
                                <b-button size="sm" class="button btn--green" @click="submitThesis(thesis)">
                                  Valida
                                  <i class="fas fa-check"></i>
                                </b-button>
                                <b-button size="sm" class="btn--red button" @click="deactivateThesis(thesis)">
                                  Loobu
                                  <i class="fas fa-times fa-md"></i>
                                </b-button>
                              </div>
                            </b-list-group-item>
                        </b-list-group>
                      </div>
                      <div class="my-not-submitted__theses">
                        <div class="thesis-group__label">
                          <h6 class="text--sans text--blue">Kinnitamata</h6>
                        </div>
                        <b-list-group>
                            <b-list-group-item class="flex-column align-items-start"
                                              v-for="(thesis, index) in getMyOwnTheses"
                                              v-if="thesis.thesisStateCode === 1"
                                              :key="index">
                              <div class="d-flex w-100 justify-content-between">
                                <h3 class="mb-1">{{ thesis.eeTitle}}</h3>
                                <small class="text-muted"></small>
                              </div>
                              <h6 class="mb-1  text--sans short__description">
                                {{ thesis.eeDescription }}
                              </h6>
                              <small class="text-muted"></small>
                              <div class="float-right">
                                <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                  Vaata detailid
                                </b-button>
                                <b-button size="sm" class="button btn--grey" @click="changeThesis(thesis)">
                                  Muuda
                                  <i class="fas fa-edit"></i>
                                </b-button>
                                <b-button size="sm"
                                          class="btn--red button"
                                          @click="deactivateThesis(thesis)">
                                  Deaktiveerida
                                  <i class="fas fa-trash-alt fa-md"></i>
                                </b-button>
                              </div>
                            </b-list-group-item>
                        </b-list-group>
                      </div>
                    </div>
                      <!-- Disabled theses -->
                      <div class="not-active-theses__list" v-if="disableThesesButtonState">
                        <div class="thesis-group__label">
                          <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                        </div>
                        <b-list-group>
                          <b-list-group-item class="flex-column align-items-start"
                                          v-for="(thesis, index) in getMyOwnTheses"
                                          v-if="thesis.thesisStateCode === 2"
                                          :key="index">
                            <div class="d-flex w-100 justify-content-between">
                              <h3 class="mb-1">{{ thesis.eeTitle}}</h3>
                              <small class="text-muted"></small>
                            </div>
                            <h6 class="mb-1  text--sans short__description">
                              {{ thesis.eeDescription }}
                            </h6>
                            <small class="text-muted"></small>
                            <div class="float-right">
                              <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                Vaata detailid
                              </b-button>
                              <b-button size="sm" class="button btn--grey" @click="changeThesis(thesis)">
                                Muuda
                                <i class="fas fa-edit"></i>
                              </b-button>
                              <b-button size="sm"
                                        class="button btn--green"
                                        @click="activateThesis(thesis)">
                                Aktiveerida
                                <i class="fas fa-plus"></i>
                              </b-button>
                            </div>
                          </b-list-group-item>
                        </b-list-group>
                      </div>
                  </div>
                  <div v-else class="text-center empty-list__notification">
                      <h2  class="text--sans text--grey">Puuduvad pakkumised</h2>
                  </div>
              </b-tab>
              <!-- My candidations -->
              <b-tab v-on:click="changeTab(3)">
                  <template slot="title">
                      <em class="text--sans">Minu kandideerimised</em>
                  </template>
                  <div class="tab__button-group text-center">
                      <b-button-group size="sm">
                          <b-button :pressed.sync="submittedThesesButtonState"
                                    class="button__status"
                                    @click="chooseSubmitted">
                                    Kinnitatud
                          </b-button>
                          <b-button :pressed.sync="notSubmittedThesesButtonState"
                                    class="button__status"
                                    @click="chooseNotSubmitted">
                                    Kinnitamata
                          </b-button>
                      </b-button-group>
                  </div>
                  <div v-if="getMyCandidateTheses != null && Object.keys(getMyCandidateTheses).length !== 0">
                      <!-- Submitted theses -->
                      <div class="active-theses__list" v-if="submittedThesesButtonState">
                          <div class="thesis-group__label">
                              <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                          </div>
                          <b-list-group>
                              <b-list-group-item class="flex-column align-items-start"
                                                v-for="(thesis, index) in getMyCandidateTheses"
                                                v-if="thesis.thesisStateCode === 3"
                                                :key="index">
                                  <div class="d-flex w-100 justify-content-between">
                                      <h3 class="mb-1">{{ thesis.eeTitle}}</h3>
                                      <small class="text-muted"></small>
                                  </div>
                                  <h6 class="mb-1  text--sans short__description">
                                    {{ thesis.eeDescription }}
                                  </h6>
                                  <small class="text-muted"></small>
                                  <div class="float-right">
                                    <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                      Vaata detailid
                                    </b-button>
                                    <b-button size="sm" class="button btn--green" @click="submitThesis(thesis)">
                                      Valida
                                      <i class="fas fa-check"></i>
                                    </b-button>
                                    <b-button size="sm" class="btn--red button" @click="deleteCandidation">
                                      Loobu
                                      <i class="fas fa-times fa-md"></i>
                                    </b-button>
                                  </div>
                              </b-list-group-item>
                          </b-list-group>
                      </div>
                      <!-- Not submitted theses -->
                      <div class="not-active-theses__list" v-if="notSubmittedThesesButtonState">
                          <div class="thesis-group__label">
                              <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                          </div>
                          <b-list-group>
                              <b-list-group-item class="flex-column align-items-start"
                                                v-for="(thesis, index) in getMyCandidateTheses"
                                                v-if="thesis.thesisStateCode === 1"
                                                :key="index">
                                  <div class="d-flex w-100 justify-content-between">
                                      <h3 class="mb-1">{{ thesis.eeTitle }}</h3>
                                      <small class="text-muted"></small>
                                  </div>
                                  <h6 class="mb-1  text--sans short__description">
                                    {{ thesis.eeDescription }}
                                  </h6>
                                  <small class="text-muted"></small>
                                  <div class="float-right">
                                    <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                      Vaata detailid
                                    </b-button>
                                    <b-button size="sm" class="btn--red button" @click="deleteCandidation">
                                      Tühista
                                      <i class="fas fa-times fa-md"></i>
                                    </b-button>
                                  </div>
                              </b-list-group-item>
                          </b-list-group>
                      </div>
                  </div>
                  <div v-else class="text-center empty-list__notification">
                      <h2 class="text--sans text--grey">Kandideerivad lõputööd puuduvad</h2>
                  </div>
              </b-tab>
            </b-tabs>
          </div>
      </b-card>
    </div>
    <!-- Modal Component -->
    <b-modal ref="myModalRef"
            id="modal-center"
            centered
            body-bg-variant="light"
            hide-footer
            hide-header
            size="lg">
    <div class="d-block text-center">
      <h2 class="text--sans">{{ modalThesisName }}</h2>
      <!-- <h4 class="modal-lecturer-name" v-if="tabNo === 3">
        {{ modal.fullName }}
      </h4> -->
    </div>
    <hr class="modal-hr__offer">
    <!-- Card with theses description -->
    <b-tabs class="modal-card-description">
        <!-- Tab with thesis information in estonian -->
      <b-tab title="Eesti Keeles" active @click="choseEstonianThesisName">
        <b-card class="card-in__tab">
          <div id="modal-description-container" >
            <div id="modal-description-headline-container">
              <i id="modal-description-icon" class="fas fa-info-circle fa-2x"></i>
              <h5  class="text--sans" id="modal-description-headline">KIRJELDUS</h5>
            </div>
            <div class="modal__content">
              <h5 class="text--sans">{{ modal.eeDescription }}</h5>
            </div>
          </div>
          <div id="modal-tags-container">
            <div id="modal-description-tags-headline-container">
              <i id="modal-description-icon" class="fas fa-tag fa-2x"></i>
              <h5 class="text--sans" id="modal-description-headline">MÄRKSÕNAD</h5>
              <div v-for="(tag, index) in modal.tags" :key="index" class="modal__content">
                <h5 class="text--sans">- {{ tag }}</h5>
              </div>
            </div>
          </div>
          <!--<div id="modal-lecturers-container" v-if="tabNo === 2">
            <div id="modal-description-lecturers-headline-container">
              <i id="modal-description-icon" class="fas fa-user fa-2x"></i>
              <h5 class="text--sans" id="modal-description-headline">ÕPPEJÕUD</h5>
            </div>
            <div class="modal__content">
              <h5 class="text--sans"></h5>
            </div>
          </div>-->
        </b-card>

      </b-tab>
      <!-- Tab with thesis information in english -->
      <b-tab title="Inglise Keeles" @click="choseEnglishThesisName">
        <b-card class="card-in__tab">
          <div id="modal-description-container" >
            <div id="modal-description-headline-container">
              <i id="modal-description-icon" class="fas fa-info-circle fa-2x"></i>
              <h5  class="text--sans" id="modal-description-headline">KIRJELDUS</h5>
            </div>
            <div class="modal__content">
              <h5 class="text--sans">{{ modal.enDescription }}</h5>
            </div>
          </div>
          <div id="modal-tags-container">
            <div id="modal-description-tags-headline-container">
              <i id="modal-description-icon" class="fas fa-tag fa-2x"></i>
              <h5 class="text--sans" id="modal-description-headline">MÄRKSÕNAD</h5>
              <div v-for="(tag, index) in modal.tags" :key="index" class="modal__content">
                <h5 class="text--sans">- {{ tag }}</h5>
              </div>
            </div>
          </div>
          <!--<div id="modal-lecturers-container" v-if="tabNo === 2">
            <div id="modal-description-lecturers-headline-container">
              <i id="modal-description-icon" class="fas fa-user fa-2x"></i>
              <h5 class="text--sans" id="modal-description-headline">ÕPPEJÕUD</h5>
            </div>
            <div class="modal__content">
              <h5 class="text--sans"></h5>
            </div>
          </div>-->
        </b-card>
      </b-tab>
    </b-tabs>
    <!-- Buttons in my offered thesis modal -->
      <div class="modal-buttons-container__offer" v-if="tabNo === 2">
        <b-btn class="modal-button-back button btn--blue"
              @click="hideModal"
              size="sm">
              Tagasi
        </b-btn>
        <b-btn class="modal-button-edit button btn--grey"
              v-if="[1, 2].includes(modal.thesisStateCode)"
              @click="changeThesis(modal)"
              size="sm">
              Muuda
        </b-btn>
        <!-- if modal not submitted by lecturer -->
        <b-btn class="modal-button-activate button btn--green"
              v-if="modal.thesisStateCode === 2"
              @click="activateThesis(modal)"
              size="sm">
              Aktiveerida
        </b-btn>
        <b-btn class="modal-button-deactivate button btn--red"
              v-if="modal.thesisStateCode === 1"
              @click="deactivateThesis(modal)"
              size="sm">
              Deaktiveerida
        </b-btn>
        <!-- if modal submitted by lecturer -->
        <b-btn class="modal-button-edit button btn--green"
              v-if="modal.thesisStateCode === 3"
              @click="submitThesis(modal)"
              size="sm">
              Valida
        </b-btn>
        <b-btn class="modal-button-deactivate button btn--red"
              v-if="modal.thesisStateCode === 3"
              @click="deactivateThesis(modal)"
              size="sm">
              Loobu
        </b-btn>
      </div>
      <div class="modal-buttons-container__candidate" v-if="tabNo === 3">
        <b-btn class="modal-button-back button btn--blue" @click="hideModal" size="sm">Tagasi</b-btn>
        <b-btn class="modal-button-edit button btn--green" size="sm">Valida</b-btn>
        <b-btn class="modal-button-deny button btn--red" @click="deleteCandidation" size="sm">Loobu</b-btn>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {

  data () {
    return {
      chosenTab: 'Minu Pakkumised',
      tabNo: 2,
      activeThesesButtonState: true,
      disableThesesButtonState: false,
      submittedThesesButtonState: true,
      notSubmittedThesesButtonState: false,

      /* values for modal form */
      modalThesisName: '',
      modal: {
        eeTitle: '',
        eeDescription: '',
        enTitle: '',
        enDescription: '',
        fullName: '',
        tags: [],
        thesisStateCode: ''
      }
    }
  },
  created: function () {
    this.$store.dispatch('fetchWorkplace')
      .then(() => {
        console.log(this.getMyOwnTheses)
      })
      .catch(err => {
        console.error(err)
      })
  },
  methods: {
    chooseAbled: function () {
      this.activeThesesButtonState = true
      this.disableThesesButtonState = false
    },
    chooseDisabled: function () {
      this.disableThesesButtonState = true
      this.activeThesesButtonState = false
    },
    chooseSubmitted: function () {
      this.submittedThesesButtonState = true
      this.notSubmittedThesesButtonState = false
    },
    chooseNotSubmitted: function () {
      this.notSubmittedThesesButtonState = true
      this.submittedThesesButtonState = false
    },
    changeTab: function (tabNo) {
      if (tabNo === 1) {
        this.chosenTab = 'Valitud lõputöö'
        this.tabNo = 1
      }
      if (tabNo === 2) {
        this.chosenTab = 'Minu Pakkumised'
        this.tabNo = 2
      }
      if (tabNo === 3) {
        this.chosenTab = 'Minu Kandideerimised'
        this.tabNo = 3
      }
    },
    choseEstonianThesisName: function () {
      if (this.modal.eeTitle === '') {
        this.modalThesisName = this.modal.enTitle
      } else {
        this.modalThesisName = this.modal.eeTitle
      }
    },
    choseEnglishThesisName: function () {
      if (this.modal.enTitle === '') {
        this.modalThesisName = this.modal.eeTitle
      } else {
        this.modalThesisName = this.modal.enTitle
      }
    },
    showModal: function (thesis) {
      this.modalThesisName = thesis.eeTitle
      this.modal.eeTitle = thesis.eeTitle
      this.modal.eeDescription = thesis.eeDescription
      this.modal.enTitle = thesis.enTitle
      this.modal.enDescription = thesis.enDescription
      this.modal.fullName = thesis.fullName
      this.modal.tags = thesis.tags
      this.modal.thesisId = thesis.thesisId
      this.modal.thesisStateCode = thesis.thesisStateCode
      this.$refs.myModalRef.show()
    },
    hideModal: function () {
      this.$refs.myModalRef.hide()
    },
    submitThesis: function (thesis) {
      // axios.post('/api/thesis/workplace/submit', {
      //   thesisId: thesis.thesisId
      // })
      //   .then(res => {
      //     console.log(res)
      //   })
      //   .catch(err => {
      //     console.error(err.response)
      //   })
    },
    deleteCandidation: function () {

    },
    activateThesis: function (thesis) {
      // this.getMyOwnTheses.splice(this.getMyOwnTheses.indexOf(thesis), 1)
      this.$store.dispatch('removeThesis', thesis, 1)
      axios.post(`/api/thesis/${thesis.thesisId}/active`)
        .then(res => {

        })
        .catch(err => {
          console.error(err.response)
        })
    },
    deactivateThesis: function (thesis) {
      // this.getMyOwnTheses.splice(this.getMyOwnTheses.indexOf(thesis), 1)
      this.$store.dispatch('removeThesis', thesis, 2)
      axios.post(`/api/thesis/${thesis.thesisId}/inactive`)
        .then(res => {

        })
        .catch(err => {
          console.error(err.response)
        })
    },
    changeThesis: function (thesis) {
      this.$store.dispatch('fetchThesis', thesis)
      this.$router.push({name: 'editThesisStudent', params: { 'thesis': thesis }})
    }
  },
  computed: {
    ...mapGetters([
      'getMyPickedThesis',
      'getMyCandidateTheses',
      'getMyOwnTheses'
    ])
  }
}
</script>

<style scoped>
  /* Text */

  footer {
    font-size: 20px;
  }

  em {
    font-size: 18px;
    font-style: normal;
    font-weight: 500;
  }

  h6 {
    font-size: 16px;
  }

  h5 {
    font-size: 18px;
  }

  h4 {
    font-size: 20px;
  }

  h3 {
    font-size: 24px;
  }

  h2 {
    font-size: 36px;
  }

  .text--sans {
    font-family: 'Source Sans Pro', sans-serif;
  }

  .line--height {
    line-height: 0.7;
  }

  .text--blue {
    color:rgb(20, 117, 189);
  }

  .text--grey {
    color:rgb(127, 128, 131);
  }

  .main__tab-title {
    text-transform: uppercase;
  }

  .empty-list__notification {
    margin-top: 100px;
  }

  .curator__name {
    font-family: 'Times New Roman', Times, serif;
    font-style: italic;
  }

  .short__description {
    width: 70%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  /* Icons */

  .icon {
    color: #fff;
  }

  /* hr */

  .hr__chosen {
    margin-left: 10%;
    margin-right: 10%;
    max-width: 600px;
    border-top: 1px solid black;
  }

  .modal-hr__offer {
    width: 350px;
    border-top: 1px solid black;
  }

  .modal-hr__candidation {
    width: 240px;
    border-top: 1px solid black;
  }

  /* Containers */

  .workplace__student {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    max-width: 1500px;
    margin-top: 290px;
    margin-bottom: 50px;
    padding-left: 5%;
    padding-right: 5%;
  }

  .workplace__container {
    position: relative;
  }

  .workplace__card {
    min-height: 800px;
    border-top: 6px solid rgb(66, 139, 202);
    border-radius: 0px;
    background-color:rgb(246, 249, 252);
  }

  .tabs__container {
    padding-left: 5%;
    padding-right: 5%;
    margin-top: 30px;
    margin-bottom: 50px;
  }

  .tab__button-group {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
    margin-bottom: 20px;
  }

  .chosen-thesis__card {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    max-width: 800px;
    margin-top: 50px;
  }

  .my-not-submitted__theses {
    margin-top: 20px;
  }

  .chosen-thesis-description__container {
    margin-left: 10%;
    margin-right: 10%;
  }

  /* Button */

    .btn--blue {
      background-color: rgb(66, 139, 202);
      border: 1px solid rgb(66, 139, 202);
    }

    .btn--blue:hover {
      background-color: rgb(46, 100, 148);
      border: 1px solid rgb(46, 100, 148);
    }

    .btn--red {
      background-color: rgb(219, 70, 70);
      border: 1px solid rgb(219, 70, 70);
    }

    .btn--red:hover {
      background-color: rgb(177, 40, 40);
      border: 1px solid rgb(177, 40, 40);
    }

    .btn--green {
      background-color: #28a745;
      border: 1px solid #28a745;
    }

    .btn--green:hover {
      background-color: #218839;
      border: 1px solid #218839;
    }

    .btn--grey {
      background-color:rgb(127, 128, 131);
      border: 1px solid rgb(127, 128, 131);
    }

    .btn--grey:hover {
      background-color:rgb(100, 101, 104);
      border: 1px solid rgb(100, 101, 104);
    }

    .button {
      box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
      font-weight: 300;
    }

  /* Button group */

  .button__status {
    font-weight: 300;
    color: rgb(66, 139, 202);
    background-color: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    border: 1px solid rgb(66, 139, 202);
    width: 120px;
  }

  .button__status:hover {
    border: 1px solid;
    background-color: rgb(66, 139, 202);
    color: #fff;
    border: 1px solid rgb(66, 139, 202);
  }

  /* Modal form with theses description */

  .modal-lecturer-name {
      font-family: 'Times New Roman', Times, serif;
      font-style: italic;
      margin-bottom: 40px;
  }

  /* Modal card with description and tags */

  .modal-card-description {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    width: 450px;
  }

    /* Description container */

    #modal-description-container {
      min-height: 200px;
    }

    #modal-description-headline-container {
      margin-bottom: 15px;
    }

    /* Tags container */

    #modal-tags-container {
      min-height: 100px;
    }

    #modal-description-tags-headline-container {
      margin-top: 20px;
      margin-bottom: 15px;
    }

    #modal-lecturers-container {
      min-height: 100px;
    }

    #modal-description-lecturers-headline-container {
      margin-top: 20px;
      margin-bottom: 15px;
    }

    .modal__content {
      margin-left: 40px;
    }

    .card-in__tab {
      border-top-left-radius:0;
      border-top-right-radius:0;
      border-top: none;
    }

    /* Modal description and tags headlines */
    #modal-description-icon {
      color: rgb(66, 139, 202);
      display: inline-block;
      vertical-align: middle;
    }

    #modal-description-headline {
      color: rgb(66, 139, 202);
      display: inline-block;
      vertical-align: top;
      margin-left: 5px;
      margin-top: 5px;
    }

  /* Modal buttons */

  .modal-buttons-container__offer {
      left:0;
      right:0;
      margin-left:auto;
      margin-right:auto;
      margin-top: 20px;
      width: 350px;
      text-align: center;
  }

  .modal-buttons-container__candidate {
      left:0;
      right:0;
      margin-left:auto;
      margin-right:auto;
      margin-top: 20px;
      width: 240px;
      text-align: center;
  }

    .modal-button-back {
      margin-bottom: 20px;
      float: left;
      width: 110px;
    }

    .modal-button-deny {
      margin-bottom: 20px;
      float: right;
      width: 110px;
    }

    .modal-button-edit {
      margin-bottom: 20px;
      width: 110px;
    }

    .modal-button-activate {
      margin-bottom: 20px;
      float: right;
      width: 110px;
    }

    .modal-button-deactivate {
      margin-bottom: 20px;
      float: right;
      width: 110px;
    }

</style>
