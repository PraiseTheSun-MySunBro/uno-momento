<template>
  <div class="workplace__student">
    <div class="workplace__container">
      <b-card class="workplace__card" >
          <div class="workplace-chosen-tab__title text-center">
              <h2 class="text--sans text--grey main__tab-title">{{ chosenTab }}</h2>
          </div>
          <div class="tabs__container">
            <b-tabs>
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
                            <div class="thesis-group__label">
                                <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                            </div>
                            <b-list-group>
                                <b-list-group-item href="#"
                                                    class="flex-column align-items-start"
                                                    v-for="(thesis, index) in MyTheses"
                                                    v-if="thesis.state === 'active'"
                                                    :key="index">
                                    <div class="d-flex w-100 justify-content-between">
                                        <em class="text--sans">{{ thesis.ee_title}}</em>
                                    </div>
                                </b-list-group-item>
                            </b-list-group>
                        </div>
                        <!-- Disabled theses -->
                        <div class="not-active-theses__list" v-if="disableThesesButtonState">
                            <div class="thesis-group__label">
                                <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                            </div>
                            <b-list-group>
                                <b-list-group-item href="#"
                                                    class="flex-column align-items-start"
                                                    v-for="(thesis, index) in MyTheses"
                                                    v-if="thesis.state === 'disable'"
                                                    :key="index">
                                    <div class="d-flex w-100 justify-content-between">
                                        <em class="thesis-name text--sans">{{ thesis.ee_title}}</em>
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
                                <b-list-group-item href="#"
                                                    class="flex-column align-items-start"
                                                    v-for="(candidation, index) in MyCandidations"
                                                    v-if="candidation.state === 'submit'"
                                                    :key="index">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">{{ candidation.ee_title}}</h5>
                                    </div>
                                    <p class="mb-1">{{ candidation.ee_description }}</p>
                                </b-list-group-item>
                            </b-list-group>
                        </div>
                        <!-- Not submitted theses -->
                        <div class="not-active-theses__list" v-if="notSubmittedThesesButtonState">
                            <div class="thesis-group__label">
                                <h6 class="text--sans text--blue">Kuupäeva järgi</h6>
                            </div>
                            <b-list-group>
                                <b-list-group-item href="#"
                                                    class="flex-column align-items-start"
                                                    v-for="(candidation, index) in MyCandidations"
                                                    v-if="candidation.state === 'not_submit'"
                                                    :key="index">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">{{ candidation.ee_title}}</h5>
                                    </div>
                                    <p class="mb-1">{{ candidation.ee_description }}</p>
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
      /* list of offered theses */
      MyTheses: [{
        ee_title: 'See on aktiivne lõpputöö pakkumine',
        ee_description: 'Kirjeldus eesti keeles',
        curator: 'Avo Läns',
        state: 'active'
      },
      {
        ee_title: 'See on mitte aktiivne lõpputöö pakkumine',
        ee_description: 'Kirjeldus eesti keeles',
        curator: 'Avo Läns',
        state: 'disable'
      }],
      /* list of candidations */
      MyCandidations: [{
        ee_title: 'See on kinnitatud lõputöö',
        ee_description: 'Kirjeldus eesti keeles',
        curator: 'Erik Prisjaznjuk',
        state: 'submit'
      },
      {
        ee_title: 'See lõputöö on kinnitamata',
        ee_description: 'Kirjeldus eesti keeles',
        curator: 'Erik Prisjaznjuk',
        state: 'not_submit'
      }]
    }
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
    myCandidationLabel: function () {
      this.chosenTab = 'Minu Kandideerimised'
    },
    myOfferLabel: function () {
      this.chosenTab = 'Minu Pakkumised'
    }
  }
}
</script>

<style scoped>
  /* Text */
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
    font-size: 22px;
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
    min-height: 500px;
    border-top: 6px solid rgb(66, 139, 202);
    border-radius: 0px;
    background-color:rgb(246, 249, 252);
  }

  .tabs__container {
    padding-left: 5%;
    padding-right: 5%;
    margin-top: 30px;
  }

  .tab__button-group {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
    margin-bottom: 20px;
  }

  /* Buttons */

  .button__status {
    font-weight: 300;
    color: rgb(66, 139, 202);
    background-color: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    border: 1px solid rgb(66, 139, 202);
    width: 120px;
  }

  .button__status:active {
    border: 1px solid;
    background-color: #fff;
    color: rgb(66, 139, 202);
    border: 1px solid rgb(66, 139, 202);
  }

</style>
