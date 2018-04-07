<template>
  <div class="add__thesis">
    <div class="add-thesis__container">
      <div class="offer-slogan__container">
        <div class="offer__slogans">
          <h5 class="text--sans line--height">Peas on head mõtted millest saab hea lõpputöö idee?</h5>
          <h5 class="text--sans line--height">Jaga neid õppejõududega või vali ühe konkreetse ja paku talle oma idee!</h5>
        </div>
      </div>
      <b-card class="form__container">
        <b-form>
          <div class="card__conteiner">
            <h2 class="text--sans text--grey main-slogan">PAKU OMA LÕPUTÖÖ IDEE</h2>
            <b-card class="name-description__card shadow">
              <h4 class="text--sans text--blue card__title">Nimi ja kirjeldus</h4>
              <hr>
              <!-- Estonian language card -->
              <b-list-group class="estonian__card">
                <b-list-group-item v-if="!estonianCard">
                  <em class="text--sans">Lisada eesti keeles</em>
                  <b-button size="sm" class="float-right add__button" @click="openEstonianCard">
                    <i class="fas fa-plus"></i>
                  </b-button>
                </b-list-group-item>
                <b-list-group-item v-if="estonianCard">
                  <div class="card-language__container">
                    <em class="text--sans text--grey card__language">Eesti keeles</em>
                    <b-button class="float-right close__button" size="sm" @click="closeEstonianCard">
                        <i class="fas fa-times"></i>
                    </b-button>
                  </div>
                  <hr>
                  <div class="name-description__fields">
                    <div class="row">
                      <div class="col-sm-12">
                        <b-form-group label="Pealkiri:"
                                      label-class="text-sm"
                                      label-for="add-thesis-name-ee">
                          <b-form-input id="add-thesis-name-ee"
                                        type="text"
                                        v-model="form.ee_title"
                                        placeholder="Kirjutage oma lõputöö nimi">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-sm-12">
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-sm-12">
                        <b-form-group label="Kirjeldus:"
                                      label-class="text-sm"
                                      label-for="add-thesis-description-ee">
                          <b-form-textarea id="add-thesis-description-ee"
                                          type="text"
                                          :rows="6"
                                          :max-rows="12"
                                          v-model="form.ee_description"
                                          placeholder="Kirjeldage oma idee">
                          </b-form-textarea>
                        </b-form-group>
                      </div>
                    </div>
                  </div>
                </b-list-group-item>
              </b-list-group>
              <!-- English language card -->
              <b-list-group class="english__card">
                <b-list-group-item v-if="!englishCard">
                  <em class="text--sans">Lisada inglise keeles</em>
                  <b-button size="sm" class="float-right add__button" @click="openEnglishCard">
                    <i class="fas fa-plus"></i>
                  </b-button>
                </b-list-group-item>
                <b-list-group-item v-if="englishCard">
                  <div class="card-language__container">
                    <em class="text--sans text--grey card__language">Inglise keeles</em>
                    <b-button class="float-right close__button" size="sm" @click="closeEnglishCard">
                      <i class="fas fa-times"></i>
                    </b-button>
                  </div>
                  <hr>
                  <div class="name-description__fields">
                    <div class="row">
                      <div class="col-sm-12">
                        <b-form-group label="Pealkiri:"
                                      label-class="text-sm"
                                      label-for="add-thesis-name-en">
                          <b-form-input id="add-thesis-name-en"
                                        type="text"
                                        v-model="form.en_title"
                                        placeholder="Kirjutage oma lõputöö nimi">
                          </b-form-input>
                        </b-form-group>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-sm-9">
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-sm-12">
                        <b-form-group label="Kirjeldus:"
                                      label-class="text-sm"
                                      label-for="add-thesis-description-en">
                          <b-form-textarea id="add-thesis-description-en"
                                          type="text"
                                          :rows="6"
                                          :max-rows="12"
                                          v-model="form.en_description"
                                          placeholder="Kirjeldage oma idee">
                          </b-form-textarea>
                        </b-form-group>
                      </div>
                    </div>
                  </div>
                </b-list-group-item>
              </b-list-group>
              <!-- Märksõnad ja juhendaja -->
              <h4 class="text--sans text--blue card__title">Märksõnad ja juhendaja</h4>
              <hr>
              <div class="row">
                <div class="col-sm-9">
                  <b-form-group label="Märksõnad:"
                                label-class="text-sm"
                                label-for="choose-thesis-curator">
                    <b-form-input id="choose-thesis-tags"
                                  class="form-control"
                                  v-model="tag"
                                  placeholder="Kirjutage märksõnad"
                                  autocomplete="off"
                                  @keyup.native="handleTyping">
                    </b-form-input>
                  </b-form-group>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-9">
                  <div class="tags">
                    <div v-for="(tag,index) in form.tags"
                        :key="index"
                        class="tag__container">
                      <div class="tag">
                        {{ tag }}
                        <i class="fas fa-times fa-xs icon" @click="removeTag(index)"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <b-form-group label="Juhendaja:"
                                label-class="text-sm"
                                label-for="choose-thesis-curator">
                    <b-form-select id="choose-thesis-curator"
                                  class="form-control"
                                  :options="listOfLecturers"
                                  v-model="form.lecturer"
                                  placeholder="Valige üks">
                    </b-form-select>
                    <small class="text-muted">Valige üks variant järgmiste hulgast</small>
                  </b-form-group>
                </div>
                <i class="fas fa-question-circle fa-lg icon"
                    v-b-popover.hover.right="popoverText"></i>
              </div>
              <div class="float-right">
                <b-button class="submit__button"
                          variant="primary"
                          @click="showSubmitModal"
                          :disabled="$v.form.$invalid">
                          Paku
                </b-button>
              </div>
            </b-card>
          </div>
        </b-form>
      </b-card>
      <!-- Modal card for submision -->
      <b-modal ref="myModalRef"
              centered
              hide-footer
              title="Kas olete kindel, et tahate saada lõputöö?"
              class="submit__modal"
              hide-header-close>
        <div class="float-right">
          <b-btn class="modal-button__cancel" size="sm" @click="hideSubmitModal">Ei</b-btn>
          <b-btn class="modal-button__submit" size="sm" @click="onThesisSubmit">Jah</b-btn>
        </div>
    </b-modal>
    </div>
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'

export default {
  data () {
    return {
      show: true,
      estonianCard: false,
      englishCard: false,
      popoverText: 'Jätta seda "Valige üks", kui tahate pakkuda kõigile õppejõudule',

      /* list of lecturers  with list of theses */
      lecturers: [],

      /* for thesis registration */
      form: {
        ee_title: '',
        ee_description: '',
        en_title: '',
        en_description: '',
        tags: [],
        lecturer: null
      },

      /* List of lecturers for input-select form */
      listOfLecturers: [{
        text: 'Valige üks',
        value: null
      }],

      /* tag in input field */
      tag: '',

      /* list of theses */
      listOfTheses: []
    }
  },
  methods: {
    /* methods for open-close Language Cards */
    openEstonianCard: function () {
      this.estonianCard = true
    },
    openEnglishCard: function () {
      this.englishCard = true
    },
    closeEstonianCard: function () {
      this.estonianCard = false
      this.form.ee_title = ''
      this.form.ee_description = ''
    },
    closeEnglishCard: function () {
      this.englishCard = false
      this.form.en_title = ''
      this.form.en_description = ''
    },
    /* methods for tags */
    addTag: function (tag) {
      if (this.form.tags.length < 5) {
        this.form.tags.push(tag)
      }
    },
    removeTag: function (index) {
      this.form.tags.splice(index, 1)
    },
    tagExists: function (tag) {
      return this.form.tags.indexOf(tag) !== -1
    },
    handleTyping: function (e) {
      if (e.keyCode === 13) {
        let tag = this.tag.replace(/,/g, '')
        if (!this.tagExists(tag)) {
          this.addTag(tag)
          this.tag = ''
        }
      }
    },
    /* modal submition form */
    hideSubmitModal: function () {
      this.$refs.myModalRef.hide()
    },
    showSubmitModal: function () {
      this.$refs.myModalRef.show()
    },
    /* methods for adding */
    onThesisSubmit () {
      /* Reset our form values */
      this.form.ee_title = ''
      this.form.en_title = ''
      this.form.ee_description = ''
      this.form.en_description = ''
      this.form.lecturer = null
      this.form.tags = []
      this.tag = ''
      this.estonianCard = false
      this.englishCard = false
      this.$refs.myModalRef.hide()
    },
    getPersons: function () {
      console.log("I'm working!")
      axios.get('/api/curators')
        .then((response) => {
          console.log('Response', response)
        })
        .catch((error) => {
          console.error('Error', error)
        })
    }
  },
  mounted () {
    this.lecturers = this.$store.getters.getCurators
    this.$store.dispatch('fetchCurators')
      .then((data) => {
        console.log('Curators data has been fetched successfully!')
        this.lecturers = data
        for (var i = 0, each = this.lecturers.length; i < each; i++) {
          const lecturer = this.lecturers[i].firstname + ' ' + this.lecturers[i].lastname
          this.listOfLecturers.push(lecturer)
        }
      })
      .catch(err => {
        console.error(err)
      })
  },
  validations () {
    if (this.estonianCard && this.englishCard) {
      return {
        form: {
          ee_title: {
            required
          },
          ee_description: {
            required
          },
          en_title: {
            required
          },
          en_description: {
            required
          }
        }
      }
    } if (this.estonianCard && !this.englishCard) {
      return {
        form: {
          ee_title: {
            required
          },
          ee_description: {
            required
          },
          en_title: {},
          en_description: {}
        }
      }
    } if (!this.estonianCard && this.englishCard) {
      return {
        form: {
          ee_title: {},
          ee_description: {},
          en_title: {
            required
          },
          en_description: {
            required
          }
        }
      }
    } else {
      return {
        form: {
          ee_title: {
            required
          },
          ee_description: {
            required
          },
          en_title: {
            required
          },
          en_description: {
            required
          }
        }
      }
    }
  },
  mounted () {
    this.lecturers = this.$store.getters.getCurators
    this.$store.dispatch('fetchCurators')
      .then((data) => {
        console.log('Curators data has been fetched successfully!')
        this.lecturers = data
        for (var i = 0, each = this.lecturers.length; i < each; i++) {
          const lecturer = this.lecturers[i].firstname + ' ' + this.lecturers[i].lastname
          this.listOfLecturers.push(lecturer)
        }
      })
      .catch(err => {
        console.error(err)
      })
  }
}
</script>

<style scoped>

  .shadow {
    -webkit-box-shadow: 0px 0px 6px -1px rgba(0,0,0,0.75);
    -moz-box-shadow: 0px 0px 6px -1px rgba(0,0,0,0.75);
    box-shadow: 0px 0px 6px -1px rgba(0,0,0,0.75);
  }

  .row {
    margin-bottom: 5px;
  }

  /* Icons */

  .icon {
    color:#fff;
    cursor: pointer;
  }

  .icon:hover {
    color: rgb(46, 100, 148);
  }

  .fa-question-circle {
    color: rgb(66, 139, 202);
    margin-top: 42px;
  }

  /* Text */

  em {
    font-size: 18px;
    font-style: normal;
    font-weight: 500;
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

  .card__language {
      text-transform: uppercase;
  }

  .offer__slogans {
    text-transform: none;
    color: rgb(246, 249, 252);
    text-align: left;
    word-wrap: break-word;
  }

  /* Containers */

  .add__thesis {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    max-width: 1500px;
    margin-top: 290px;
    margin-bottom: 50px;
    padding-left: 10%;
    padding-right: 10%;
  }

  .add-thesis__container {
    position: relative;
  }

  .offer-slogan__container {
    left:0;
    right:0;
    margin-left:auto;
    margin-right:auto;
    height: max-content;
    background-color:rgb(20, 117, 189);
    color: rgb(246, 249, 252);
    padding: 20px 0px 15px 30px;
  }

  .form__container {
    min-height: 500px;
    border-radius: 0px;
    background-color:rgb(246, 249, 252);
  }

  .card__conteiner {
    left: 0;
    right: 0;
    margin-left: auto;
    margin-right: auto;
    max-width: 950px;
    padding-left: 10%;
    padding-right: 10%;
  }

  .name-description__card {
    margin-bottom: 30px;
  }

  .name-description__fields {
    padding-right: 80px;
  }

  .main-slogan {
    margin-bottom: 20px;
  }

  .card__title {
    margin-bottom: 15px;
  }

  /* Language cards */
  .estonian__card {
      margin-bottom: 10px;
  }

  .english__card {
    margin-bottom: 20px;
  }

  /* Tags */
  .tag__container {
    position: relative;
    display: inline-block;
  }

  .tag {
    margin: 0 10px 10px 0;
    padding: 3px 10px 3px 10px;
    color: #fff;
    font-weight: rgb(66, 139, 202);
    background: rgb(66, 139, 202);
    border: 1px solid #fff;
    border-radius: 0.25rem;
  }

  /* Buttons */
  .add__button {
      background-color: #28a745;
      border:none;
  }

  .add__button:hover {
      background-color: #218839;
      border:none;
  }

  .close__button {
    background-color: rgb(219, 70, 70);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    border: none;
  }

  .close__button:hover {
      background-color: rgb(177, 40, 40);
      box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
      border: none;
  }

  .submit__button {
    font-weight: 300;
    background-color: rgb(66, 139, 202);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 100px;
    border: none;
  }

  .submit__button:hover {
      background-color: rgb(46, 100, 148);
  }

  .modal-button__submit {
    font-weight: 300;
    background-color: rgb(66, 139, 202);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 50px;
    border: none;
  }

  .modal-button__submit:hover {
      background-color: rgb(46, 100, 148);
  }

  .modal-button__cancel {
    font-weight: 300;
    background-color: rgb(127, 128, 131);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 50px;
    border: none;
  }

  .modal-button__cancel:hover {
    background-color: rgb(104, 102, 102);
  }

  @media screen and (max-width: 767px) {
    /* adaptive for mobile */

    /* Main container */
    .add__thesis {
      left: 0;
      right: 0;
      margin-left: auto;
      margin-right: auto;
      max-width: 1500px;
      margin-top: 200px;
      margin-bottom: 50px;
      padding-left: 0;
      padding-right: 0;
    }

    h5 {
      font-size: 14px;
    }

    h4 {
      font-size: 18px;
    }

    h3 {
      font-size: 28px;
    }
    .line--height {
      line-height: 1;
    }

    .offer-slogan__container {
      left:0;
      right:0;
      margin-left:auto;
      margin-right:auto;
      height: max-content;
      background-color:rgb(20, 117, 189);
      color: rgb(246, 249, 252);
      padding: 10px 0px 5px 15px;
    }

    .card__conteiner {
      left: 0;
      right: 0;
      margin-left: auto;
      margin-right: auto;
      max-width: 800px;
      padding-left: 0;
      padding-right: 0;
    }

    .name-description__fields {
      padding-right: 0;
    }
    .fa-question-circle {
      display: none;
    }
  }

</style>
