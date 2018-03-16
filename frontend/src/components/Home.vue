<template>
  <div class="container-fluid" v-if="$auth.check()">
    <div class="container">
      <!-- Student workplace where he can find own offered tesises -->
      <div class="work-place" v-if="currentTab === 2">
        <b-tabs>
          <b-tab title="Minu pakkumised" active>
            <b-list-group>
              <b-list-group-item href="#" class="flex-column align-items-start" v-for="thesis in studentTheses">
                <div class="d-flex w-100 justify-content-between">
                  <h5 class="mb-1">{{ thesis.en_title}}</h5>
                </div>
                <p class="mb-1">
                  {{ thesis.en_description }}
                </p>
                <small class="text-muted">{{ thesis.lecturer }}</small>
              </b-list-group-item>
            </b-list-group>
          </b-tab>
          <b-tab title="Minu kandideerimised" >
            <b-list-group>
              <b-list-group-item href="#" class="flex-column align-items-start" v-for="candidate in candidateTheses">
                <div class="d-flex w-100 justify-content-between">
                  <h5 class="mb-1">{{ candidate.en_title}}</h5>
                </div>
                <p class="mb-1">
                  {{ candidate.en_description }}
                </p>
                <small class="text-muted">{{ candidate.curator_name }}</small>
              </b-list-group-item>
            </b-list-group>
          </b-tab>
        </b-tabs>
      </div>
      <!-- Page with lists of lecturers -->
      <div class="faculty-name" v-if="currentTab === 0">
        <h1>Informaatika teaduskond</h1>
      </div>
      <div id="list-of-lecturers" v-if="currentTab === 0">
        <div role="tablist">
          <b-card no-body class="mb-1" v-for="(lecturer, index) in lecturers">
            <b-card-header header-tag="header" class="p-1" role="tab">
              <b-btn block href="#" v-b-toggle="'accordion-' +index" variant="light" class="text-left" >
                {{ lecturer.firstname }} {{lecturer.lastname}}</b-btn>
              </b-card-header>
              <b-collapse :id="'accordion-' + index" visible accordion="my-accordion" role="tab">
                <b-card-body>
                  <b-list-group v-for="(thesis, index) in JSON.parse(lecturer.theses)">
                    <b-list-group-item button v-b-modal="'modal-' +index">
                      <em class="float-left">{{ thesis.en_title }}</em>
                        <div class="float-right">
                          <em>VAATA</em>
                          <i id="thesisButtonAngle" class="fas fa-angle-right fa-lg"></i>
                        </div>
                      </b-list-group-item>
                      <!-- Modal Component -->
                      <b-modal
                        :id="'modal-' + index"
                        body-bg-variant="light"
                        hide-footer
                        hide-header size="lg">
                      <div class="d-block text-center">
                        <h2>{{ thesis.en_title }}</h2>
                        <h4 id="modalLecturerName">{{ lecturer.firstname }} {{lecturer.lastname}}</h4>
                      </div>
                      <hr class="my-2">
                      <b-card id="modalCardDescription">
                        <div class="d-block text-left">
                          <h4>Kirjeldus:</h4>
                          <p>{{ thesis.en_description }}</p>
                        </div>
                      </b-card>
                      <!--
                      <div id="modalButtons">
                        <b-btn class="mt-3" variant="primary" v-on:click="modalCandidate">Kandideeri</b-btn>
                      </div>
                      -->
                    </b-modal>
                  </b-list-group>
                </b-card-body>
              </b-collapse>
            </b-card>
        </div>
      </div>
      <!-- Page with registration of thesis -->
      <div v-if="currentTab === 1">
        <div class="faculty-name">
          <h1>Informaatika teaduskond</h1>
        </div>
        <div id="thesis-registration">
          <b-card  border-variant="primary bg-light">
            <h2 class="card-title">Lisa lõputöö</h2>
            <hr class="my-2">
            <b-form @submit.prevent="onSubmit" @reset.prevent="onReset" v-if="show">
              <div class="row">
                <div class="col-sm-9">
                  <b-form-group class="b-form-group">
                    <label for="inputfield">Teema:</label>
                    <input class="form-control " type="text" required
                        id="inputfield" v-model="form.en_title" placeholder="Kirjutage oma lõputöö nimi">
                  </b-form-group>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-9">
                  <div class="b-form-group">
                    <label for="inputfield">Kirjeldus:</label>
                    <b-form-textarea
                        id="inputfield"
                        placeholder="Kirjeldage oma idee"
                        v-model="form.en_description"
                        required
                        :rows="6"
                        :max-rows="12">
                    </b-form-textarea>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-9">
                  <div class="b-form-group">
                    <label for="inputfield">Juhendaja:</label>
                    <b-form-select
                        class="form-control"
                        :options="choseOfLecturers"
                        required
                        v-model="form.lecturer"
                        id="inputfield"
                        placeholder="Kirjutage oma isikukood">
                    </b-form-select>
                    <small class="text-muted">Valige üks variant järgmiste hulgast</small>
                  </div>
                </div>
              </div>
              <div id="thesisFormButtons" class="float-right">
                  <b-button type="reset"  variant="secondary">Tühista</b-button>
                  <b-button type="submit"  variant="primary">Paku</b-button>
              </div>
            </b-form>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      currentUser: {
        id: 1,
        /* if you want to change profile from student to lecturer, change this parameter on the 1 */
        firstname: '',
        lastname: '',
      },
      theses: {

      },
      currentTab: 0,
      show: true,
      /* list of lecturers  with list of theses */
      lecturers: [],
      /* list of offered theses */
      studentTheses: [],
      /* list of lecturer theses */
      lecturerTheses: [],
      /* list of candidate theses */
      candidateTheses: [],

      /* for thesis registration */
      form: {
        en_title: '',
        en_description: '',
        lecturer: null
      },
      choseOfLecturers: [
        { text: 'Valige üks', value: null },
        'Mart Vein',
        'Avo Läns'
      ],
      formLecturer: {
        en_title: '',
        en_description: '',
      },
    }
  },
  methods: {
    getPersons: function() {
      console.log("I'm working!")
      axios.get("/curators/page/0")
        .then((response) => {
          console.log('Response', response)
        })
        .catch((error) => {
          console.error('Error', error)
        })
    },
    /* methods for student */
    onSubmit (evt) {
      this.studentTheses.push(
        {lecturer: this.form.lecturer, en_title: this.form.en_title, en_description: this.form.en_description})
      /* Reset our form values */
      // axios.post(`/curator/${this.currentUser}/thesis`, {eeTitle: this.form.en_title, enTitle: this.form.en_title, eeDescription: this.form.en_description, enDescription: this.form.en_description, facultyCode: 3})
      //     .then(response => {
      //         console.log("Post: ", response.data);
      //     })
      //     .catch(e => {
      //         console.error(e);
      //     });
      this.form.en_title = ''
      this.form.en_description = ''
      this.form.lecturer = null
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    },
    onReset (evt) {
      /* Reset our form values */
      this.form.en_title = ''
      this.form.en_description = ''
      this.form.lecturer = null
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    },
    switchCurrentTab: function(value) {
      this.currentTab = value
    } ,
    modalCandidate: function() {
      this.candidateTheses.push(
        {curator_name: this.curator_name, en_title: this.en_title, en_description: this.en_description})
    },
    /* methods for lecturer */
    onSubmitLecturer (evt) {
      this.lecturerTheses.push(
        {en_title: this.formLecturer.en_title, en_description: this.formLecturer.en_description})
      axios.post(`/curator/${this.currentUser}/thesis`,
          {eeTitle: this.formLecturer.en_title, enTitle: this.formLecturer.en_title,
           eeDescription: this.formLecturer.en_description, enDescription: this.formLecturer.en_description,
           facultyCode: 3})
        .then(response => {
            console.log("Post: ", response.data)
            window.location.reload()
        })
        .catch(e => {
            console.error(e)
        })
      /* Reset our form values */
      this.formLecturer.en_title = ''
      this.formLecturer.en_description = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    },
    onResetLecturer (evt) {
      /* Reset our form values */
      this.formLecturer.en_title = ''
      this.formLecturer.en_description = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    },
    switchUser: function(value) {
      this.currentUser.id = value
    },
  }
}
</script>

<style>
  #app {
      height: 100vh;
  }
  .container-fluid {
      width: 100%;
      padding-right: 0px;
      padding-left: 0px;
      margin-right: auto;
      margin-left: auto;
  }

  .container {
      margin-top: 10%;
      margin-bottom: 5%;
  }
  .container-registration {
      margin-top: 5%;
      padding-right: 10%;
      padding-left: 10%;
  }
  #list-of-lecturers {
      border-top: 4px solid rgb(66, 139, 202);
  }

  #thesis-registration {
      border-top: 4px solid rgb(66, 139, 202);
  }
  .row {
      margin-bottom: 3%;
  }
  #offerSlogan {
      margin-bottom: 3%;

  }

  #modalLecturerName {
      font-family: 'Times New Roman', Times, serif;
      font-style: italic;
  }

  #modalCardDescription {
      margin-top: 3%;
      margin-bottom: 5%;
      margin-left: 15%;
      margin-right: 15%;
  }

  #modalButtons {
      text-align: center;
  }
</style>
