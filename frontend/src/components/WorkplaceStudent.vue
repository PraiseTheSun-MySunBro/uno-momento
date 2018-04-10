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
              <b-tab v-on:click="myChosenThesisLabel">
                  <template slot="title" class="tab__name">
                      <em class="text--sans">Valitud lõputöö</em>
                  </template>
                  <b-card class="chosen-thesis__card" v-if="Object.keys(chosenThesis).length !== 0">
                    <h2 class="text--sans text-center">{{ chosenThesis.thesisTitle }}</h2>
                    <h3 class="modal-lecturer-name text--sans text-center">{{ chosenThesis.curator }}</h3>
                    <hr class="hr__chosen">
                    <!-- Card with theses description -->
                    <div class="chosen-thesis-description__container" >
                      <div id="modal-description-headline-container">
                        <i id="modal-description-icon" class="fas fa-info-circle fa-2x"></i>
                        <h5  class="text--sans" id="modal-description-headline">KIRJELDUS</h5>
                      </div>
                      <div class="modal__content">
                        <h5 class="text--sans">{{ chosenThesis.thesisDescription }}</h5>
                      </div>
                      <div id="modal-description-tags-headline-container">
                        <i id="modal-description-icon" class="fas fa-tag fa-2x"></i>
                        <h5 class="text--sans" id="modal-description-headline">MÄRKSÕNAD</h5>
                      </div>
                      <div class="modal__content" v-for="(tag, index) in chosenThesis.tags" :key="index">
                        <h5 class="text--sans">- {{ tag.name }}</h5>
                      </div>
                    </div>
                  </b-card>
                  <div v-if="Object.keys(chosenThesis).length === 0" class="text-center empty-list__notification">
                      <h2  class="text--sans text--grey">Valitud lõputöö puudub</h2>
                  </div>
              </b-tab>
              <!-- My offered theses -->
              <b-tab active v-on:click="myOfferLabel">
                  <template slot="title" class="tab__name">
                      <em class="text--sans">Minu Pakkumised</em>
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
                                    Mitte aktiivsed
                          </b-button>
                      </b-button-group>
                  </div>
                  <div v-if="Object.keys(MyTheses).length !== 0">
                    <!-- Active theses -->
                    <div class="active-theses__list" v-if="activeThesesButtonState">
                      <div class="my-submitted__theses">
                        <div class="thesis-group__label">
                          <h6 class="text--sans text--blue">Kinnitatud</h6>
                        </div>
                        <b-list-group>
                            <b-list-group-item class="flex-column align-items-start"
                                              v-for="(thesis, index) in MyTheses"
                                              v-if="thesis.state === 'active'
                                              && thesis.curator_state === 'submitted'"
                                              :key="index">
                              <div class="d-flex w-100 justify-content-between">
                                <h3 class="mb-1">{{ thesis.ee_title}}</h3>
                                <small class="text-muted">3 days ago</small>
                              </div>
                              <h6 class="mb-1  text--sans short__description">
                                {{ thesis.ee_description }}
                              </h6>
                              <small class="text-muted">{{ thesis.curator }}</small>
                              <div class="float-right">
                                <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                  Vaadata detailid
                                </b-button>
                                <b-button size="sm" class="button btn--green" @click="submitThesis(thesis)">
                                  Valida
                                  <i class="fas fa-check"></i>
                                </b-button>
                                <b-button size="sm" class="btn--red button" @click="deactivateThesis">
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
                                              v-for="(thesis, index) in MyTheses"
                                              v-if="thesis.state === 'active'
                                              && thesis.curator_state === 'not_submitted'"
                                              :key="index">
                              <div class="d-flex w-100 justify-content-between">
                                <h3 class="mb-1">{{ thesis.ee_title}}</h3>
                                <small class="text-muted">3 days ago</small>
                              </div>
                              <h6 class="mb-1  text--sans short__description">
                                {{ thesis.ee_description }}
                              </h6>
                              <small class="text-muted">{{ thesis.curator }}</small>
                              <div class="float-right">
                                <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                  Vaadata detailid
                                </b-button>
                                <b-button size="sm" class="button btn--grey" @click="changeThesis">
                                  Muutuda
                                  <i class="fas fa-edit"></i>
                                </b-button>
                                <b-button size="sm" class="btn--red button" @click="deactivateThesis">
                                  Kustuta
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
                                          v-for="(thesis, index) in MyTheses"
                                          v-if="thesis.state === 'disable'"
                                          :key="index">
                            <div class="d-flex w-100 justify-content-between">
                              <h3 class="mb-1">{{ thesis.ee_title}}</h3>
                              <small class="text-muted">3 days ago</small>
                            </div>
                            <h6 class="mb-1  text--sans short__description">
                              {{ thesis.ee_description }}
                            </h6>
                            <small class="text-muted">{{ thesis.curator }}</small>
                            <div class="float-right">
                              <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                Vaadata detailid
                              </b-button>
                              <b-button size="sm" class="button btn--grey" @click="changeThesis">
                                Muutuda
                                <i class="fas fa-edit"></i>
                              </b-button>
                              <b-button size="sm" class="button btn--green" @click="activateThesis">
                                Aktiveerida
                                <i class="fas fa-plus"></i>
                              </b-button>
                            </div>
                          </b-list-group-item>
                        </b-list-group>
                      </div>
                  </div>
                  <div v-if="Object.keys(MyTheses).length === 0" class="text-center empty-list__notification">
                      <h2  class="text--sans text--grey">Puuduvad pakkumised</h2>
                  </div>
              </b-tab>
              <!-- My candidations -->
              <b-tab v-on:click="myCandidationLabel">
                  <template slot="title">
                      <em class="text--sans">Minu Kandideerimised</em>
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
                  <div v-if="Object.keys(MyCandidations).length !== 0">
                      <!-- Submitted theses -->
                      <div class="active-theses__list" v-if="submittedThesesButtonState">
                          <div class="thesis-group__label">
                              <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                          </div>
                          <b-list-group>
                              <b-list-group-item class="flex-column align-items-start"
                                                v-for="(thesis, index) in MyCandidations"
                                                v-if="thesis.curator_state === 'submitted'"
                                                :key="index">
                                  <div class="d-flex w-100 justify-content-between">
                                      <h3 class="mb-1">{{ thesis.ee_title}}</h3>
                                      <small class="text-muted">3 days ago</small>
                                  </div>
                                  <h6 class="mb-1  text--sans short__description">
                                    {{ thesis.ee_description }}
                                  </h6>
                                  <small class="text-muted">{{ thesis.curator }}</small>
                                  <div class="float-right">
                                    <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                      Vaadata detailid
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
                                                v-for="(thesis, index) in MyCandidations"
                                                v-if="thesis.curator_state === 'not_submitted'"
                                                :key="index">
                                  <div class="d-flex w-100 justify-content-between">
                                      <h3 class="mb-1">{{ thesis.ee_title }}</h3>
                                      <small class="text-muted">3 days ago</small>
                                  </div>
                                  <h6 class="mb-1  text--sans short__description">
                                    {{ thesis.ee_description }}
                                  </h6>
                                  <small class="text-muted">{{ thesis.curator }}</small>
                                  <div class="float-right">
                                    <b-button size="sm" class="button btn--blue" @click="showModal(thesis)">
                                      Vaadata detailid
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
                  <div v-if="Object.keys(MyCandidations).length === 0" class="text-center empty-list__notification">
                      <h2 class="text--sans text--grey">Puududvad kandideerivad lõputööd</h2>
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
        <h2 class="text--sans">{{ modal.ee_title }}</h2>
        <h4 class="modal-lecturer-name" v-if="chosenTab === 'Minu Kandideerimised'">
          {{ modal.curator }}
        </h4>
      </div>
      <hr class="modal-hr__offer" v-if="chosenTab === 'Minu Pakkumised'">
      <hr class="modal-hr__candidation" v-if="chosenTab === 'Minu Kandideerimised'">
      <!-- Card with theses description -->
      <b-card class="modal-card-description">
        <div id="modal-description-container" >
          <div id="modal-description-headline-container">
            <i id="modal-description-icon" class="fas fa-info-circle fa-2x"></i>
            <h5  class="text--sans" id="modal-description-headline">KIRJELDUS</h5>
          </div>
          <div class="modal__content">
            <h5 class="text--sans">{{ modal.ee_description }}</h5>
          </div>
        </div>
        <div id="modal-tags-container">
          <div id="modal-description-tags-headline-container">
            <i id="modal-description-icon" class="fas fa-tag fa-2x"></i>
            <h5 class="text--sans" id="modal-description-headline">MÄRKSÕNAD</h5>
          </div>
          <div class="modal__content" v-for="(tag, index) in modal.tags" :key="index">
            <h5 class="text--sans">- {{ tag.name }}</h5>
          </div>
        </div>
        <div id="modal-lecturers-container" v-if="chosenTab === 'Minu Pakkumised'">
          <div id="modal-description-lecturers-headline-container">
            <i id="modal-description-icon" class="fas fa-user fa-2x"></i>
            <h5 class="text--sans" id="modal-description-headline">ÕPPEJÕUD</h5>
          </div>
          <div class="modal__content">
            <h5 class="text--sans">- {{ modal.curator }}</h5>
          </div>
        </div>
      </b-card>
      <!-- Buttons in my offered thesis modal -->
      <div class="modal-buttons-container__offer" v-if="chosenTab === 'Minu Pakkumised'">
        <b-btn class="modal-button-back button btn--blue"
              @click="hideModal">
              Tagasi
        </b-btn>
        <b-btn class="modal-button-edit button btn--blue"
              v-if="modal.curator_state === 'not_submitted'"
              @click="changeThesis">
              Muutuda
        </b-btn>
        <!-- if modal not submitted by lecturer -->
        <b-btn class="modal-button-activate button btn--blue"
              v-if="modal.state === 'disable'
              && modal.curator_state === 'not_submitted'"
              @click="activateThesis">
              Aktiveerida
        </b-btn>
        <b-btn class="modal-button-deactivate button btn--blue"
              v-if="modal.state === 'active'
              && modal.curator_state === 'not_submitted'"
              @click="deactivateThesis">
              Kustuta
        </b-btn>
        <!-- if modal submitted by lecturer -->
        <b-btn class="modal-button-edit button btn--blue"
              v-if="modal.state === 'active'
              && modal.curator_state === 'submitted'"
              @click="submitThesis(modal)">
              Valida
        </b-btn>
        <b-btn class="modal-button-deactivate button btn--blue"
              v-if="modal.state === 'active'
              && modal.curator_state === 'submitted'"
              @click="deactivateThesis">
              Loobu
        </b-btn>
      </div>
      <div class="modal-buttons-container__candidate" v-if="chosenTab === 'Minu Kandideerimised'">
        <b-btn class="modal-button-back button btn--blue" @click="hideModal">Tagasi</b-btn>
        <b-btn class="modal-button-deny button btn--blue" @click="deleteCandidation">Tühista</b-btn>
      </div>
    </b-modal>
  </div>
</template>

<script>

export default {
  data () {
    return {
      chosenTab: 'Minu Pakkumised',
      activeThesesButtonState: true,
      disableThesesButtonState: false,
      submittedThesesButtonState: true,
      notSubmittedThesesButtonState: false,

      /* values for modal form */
      modal: {
        ee_title: '',
        ee_description: '',
        curator: '',
        tags: [],
        state: '',
        curator_state: ''
      },
      /* values for chusen thesis */
      chosenThesis: [],

      /* list of offered theses */
      MyTheses: [{
        ee_title: 'See on esimene aktiivne lõpputöö pakkumine',
        ee_description: 'Venemaa piirneb loodes Norra ja Soomega, läänes Eesti, Läti, Leedu, Poola (viimase kahega Läänemere-äärse eksklaavi Kaliningradi oblasti kaudu), Valgevene ja Ukrainaga, edelas Gruusia ja Aserbaidžaaniga, lõunas Kasahstani, Hiina ja Mongooliaga, kagus Põhja-Korea ja Jaapaniga ning idas Ameerika Ühendriikidega (viimase kahega meritsi).Venemaa on maailma suurima pindalaga riik; seal on kehtestatud 11 ajavööndit.Venemaa pikk rannajoon (37 653 km) ulatub Põhja-Jäämerest Vaikse ookeani lääneosani ning hõlmab veel Musta mere, Kaspia mere ja Läänemere ranniku. Venemaa on pikima maismaapiiriga riik (kokku 20 257 km), piirnedes 14 riigiga.Venemaa Föderatsiooni vapp, 1992–1991991. aastani oli Venemaa (Vene NFSV) Nõukogude Liidu pindalalt suurim liiduvabariik. Venemaa on Nõukogude Liidu õigusjärglane. Venemaa on endisi NSV Liidu liiduvabariike ühendava SRÜ juhtiv liige.',
        tags: [
          {
            name: 'PHP'
          },
          {
            name: 'JavaScript'
          },
          {
            name: 'HTML'
          }],
        curator: 'Avo Lans',
        state: 'active',
        curator_state: 'not_submitted'
      },
      {
        ee_title: 'See on  teine ja kinnitatud aktiivne lõpputöö pakkumine',
        ee_description: 'Some quick example text to build on the card and make up the bulk of the card',
        tags: [
          {
            name: 'PHP'
          },
          {
            name: 'JavaScript'
          },
          {
            name: 'HTML'
          }],
        curator: 'Avo Lans',
        state: 'active',
        curator_state: 'submitted'
      },
      {
        ee_title: 'See on mitte aktiivne lõpputöö pakkumine',
        ee_description: 'Kirjeldus eesti keeles',
        curator: 'Tanel Tammet',
        state: 'disable',
        curator_state: 'not_submitted'
      }],
      /* list of candidations */
      MyCandidations: [{
        ee_title: 'See on kinnitatud lõputöö',
        ee_description: 'Mo isamaa on minu arm! Mo isamaa on minu arm, kel südant annud ma,sull laulan ma, mo ülem õnn,mo õitsev Eestimaa!So valu südames mul keeb,so õnn ja rõõm mind rõõmsaks teeb,mo isamaa!Mo isamaa on minu arm,ei teda jäta ma,ja peaks sada surma maseepärast surema!Kas laimab võera kadedus,sa siiski elad südames,mo isamaa!Mo isamaa on minu armja tahan puhkada,so rüppe heidan unele,mo püha Eestimaa!So linnud und mull laulavad,mo põrmust lilled õitsetad,mo isamaa!',
        curator: 'Erik Prisjaznjuk',
        tags: [
          {
            name: 'PHP'
          },
          {
            name: 'JavaScript'
          },
          {
            name: 'HTML'
          }],
        curator_state: 'submitted'
      },
      {
        ee_title: 'See lõputöö on kinnitamata',
        ee_description: 'Kirjeldus eesti keeles',
        curator: 'Erik Prisjaznjuk',
        curator_state: 'not_submitted'
      }]
    }
  },
  methods: {
    submitThesis: function (thesis) {
      this.chosenThesis.thesisTitle = thesis.ee_title
      this.chosenThesis.thesisDescription = thesis.ee_description
      this.chosenThesis.curator = thesis.curator
      this.chosenThesis.tags = thesis.tags
    },
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
    myChosenThesisLabel: function () {
      this.chosenTab = 'Valitud lõputöö'
    },
    myCandidationLabel: function () {
      this.chosenTab = 'Minu Kandideerimised'
    },
    myOfferLabel: function () {
      this.chosenTab = 'Minu Pakkumised'
    },
    showModal: function (thesis) {
      this.modal.ee_title = thesis.ee_title
      this.modal.ee_description = thesis.ee_description
      this.modal.curator = thesis.curator
      this.modal.tags = thesis.tags
      this.modal.state = thesis.state
      this.modal.curator_state = thesis.curator_state
      this.$refs.myModalRef.show()
    },
    hideModal: function () {
      this.$refs.myModalRef.hide()
    },
    deleteCandidation: function () {

    },
    activateThesis: function () {
      for (var i = 0; i < this.MyTheses.length; i++) {
        if (this.thesisTitle === this.MyTheses[i].ee_title) {
          console.log(this.MyTheses[i].state)
        }
      }
    },
    deactivateThesis: function () {
      for (var i = 0; i < this.MyTheses.length; i++) {
        if (this.thesisTitle === this.MyTheses[i].ee_title) {
          console.log(this.MyTheses[i].state)
        }
      }
    },
    changeThesis: function () {
      this.$router.push('/thesis/edit')
    }
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
