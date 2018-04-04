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
        <b-form @submit.prevent="onSubmit" v-if="show">
          <div class="cards__conteiner">
            <h3 class="text--sans main-slogan">PAKU OMA LÕPUTÖÖ IDEE</h3>
            <b-card class="name-description__card shadow" v-if="!secondLanguageCard">
              <h4 class="text--sans text--color card__title">Nimi ja kirjeldus</h4>
              <hr>
              <div class="name-description__fields">
                <b-form-group vertical
                          label="Pealkiri:"
                          label-class="text-sm"
                          label-for="add-thesis-name-ee"
                          class="mb-2 ">
                  <b-form-input id="add-thesis-name-ee"
                                type="text"
                                required
                                v-model="form.ee_title"
                                placeholder="Kirjutage oma lõputöö nimi">
                  </b-form-input>
                </b-form-group>
                <b-form-group vertical
                          label="Kirjeldus:"
                          label-class="text-sm"
                          label-for="add-thesis-description-ee"
                          class="mb-2">
                  <b-form-textarea id="add-thesis-description-ee"
                                type="text"
                                required
                                :rows="6"
                                :max-rows="12"
                                v-model="form.ee_description"
                                placeholder="Kirjeldage oma idee">
                  </b-form-textarea>
                </b-form-group>
                <b-form-checkbox id="checkbox-language"
                                v-model="status"
                                value="accepted"
                                unchecked-value="not_accepted">
                                Lisada kirjeldus inglise keeles
                </b-form-checkbox>
              </div>
              <b-button class="float-right" v-if="status=='accepted'" @click="showSecondCard">
                Inglise keeles
                <i class="fas fa-arrow-right"></i>
              </b-button>
            </b-card>
            <!-- Card for english language -->
            <b-card class="name-description__card shadow" v-if="secondLanguageCard">
              <h4 class="text--sans text--color card__title">Name and description (inglise keeles)</h4>
              <hr>
              <div class="name-description__fields">
                <b-form-group vertical
                          label="Name:"
                          label-class="text-sm"
                          label-for="add-thesis-name-en"
                          class="mb-2">
                  <b-form-input id="add-thesis-name-en"
                                type="text"
                                v-model="form.en_title"
                                placeholder="Enter thesis name">
                  </b-form-input>
                </b-form-group>
                <b-form-group vertical
                          label="Description:"
                          label-class="text-sm"
                          label-for="add-thesis-description-en"
                          class="mb-2">
                  <b-form-textarea id="add-thesis-description-en"
                                type="text"
                                :rows="6"
                                :max-rows="12"
                                v-model="form.en_description"
                                placeholder="Enter thesis description">
                  </b-form-textarea>
                </b-form-group>
              </div>
              <b-button class="float-left" @click="showFirstCard">
                <i class="fas fa-arrow-left"></i>
                Eesti keeles
              </b-button>
            </b-card>
            <!-- Card for tags and curator -->
            <b-card class="tags-curator__card shadow">
              <h4 class="text--sans text--color card__title">Märksõnad ja juhendaja</h4>
              <hr>
              <div class="row">
                <div class="col-sm-9">
                  <b-form-group vertical
                            label="Märksõnad:"
                            label-class="text-sm"
                            label-for="choose-thesis-curator"
                            class="mb-2">
                    <b-form-input id="choose-thesis-tags"
                                  class="form-control"
                                  v-model="tag"
                                  placeholder="Kirjutage märksõnad"
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
                  <b-form-group vertical
                            label="Juhendaja:"
                            label-class="text-sm"
                            label-for="choose-thesis-curator"
                            class="mb-2">
                    <b-form-select id="choose-thesis-curator"
                                  class="form-control"
                                  :options="choseOfLecturers"
                                  required
                                  v-model="form.lecturer"
                                  placeholder="Valige üks">
                    </b-form-select>
                    <small class="text-muted">Valige üks variant järgmiste hulgast</small>
                  </b-form-group>
                </div>
                <i class="fas fa-question-circle fa-lg icon"
                    v-b-popover.hover.right="'Valige `Kõik`, kui tahate pakkuda kõigile õppejõudule'"></i>
              </div>
            </b-card>
            <div class="float-right">
                <b-button class="submit-button" type="submit" variant="primary">Paku</b-button>
            </div>
          </div>
        </b-form>
      </b-card>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      show: true,
      status: 'not_accepted',
      secondLanguageCard: false,
      /* tag in input field */
      tag: '',
      /* list of theses */
      listOfTheses: [],

      /* for thesis registration */
      form: {
        ee_title: '',
        ee_description: '',
        en_title: '',
        en_description: '',
        tags: [],
        lecturer: null
      },
      choseOfLecturers: [
        { text: 'Valige üks', value: null },
        'Kõik',
        'Avo Läns'
      ]
    }
  },
  methods: {
    showSecondCard: function () {
      this.secondLanguageCard = true
    },
    showFirstCard: function () {
      this.secondLanguageCard = false
    },
    /* methods for tags adding */
    addTag: function (tag) {
      this.form.tags.push(tag)
    },
    removeTag: function (index) {
      this.form.tags.splice(index, 1)
    },
    tagExists: function (tag) {
      return this.form.tags.indexOf(tag) !== -1
    },
    handleTyping: function (e) {
      if (e.keyCode === 188) {
        let tag = this.tag.replace(/,/g, '')
        if (!this.tagExists(tag)) {
          this.addTag(tag)
          this.tag = ''
        }
      }
    },
    /* methods for adding */
    onSubmit (evt) {
      /* Reset our form values */
      alert(JSON.stringify(this.form))
      this.form.ee_title = ''
      this.form.en_title = ''
      this.form.ee_description = ''
      this.form.en_description = ''
      this.form.lecturer = null
      this.form.tags = []
      this.tag = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    }
  }
}
</script>

<style>

  h5 {
    font-size: 18px;
  }

  h4 {
    font-size: 20px;
  }

  h3 {
    font-size: 36px;
  }

  .text--sans {
    font-family: 'Source Sans Pro', sans-serif;
  }

  .line--height {
    line-height: 0.7;
  }

  .shadow {
    -webkit-box-shadow: 1px 1px 41px 0px rgba(0,0,0,0.56);
    -moz-box-shadow: 1px 1px 41px 0px rgba(0,0,0,0.56);
    box-shadow: 1px 1px 41px 0px rgba(0,0,0,0.56);
  }

  .row {
    margin-bottom: 5px;
  }

  .icon {
    color:rgb(20, 117, 189);
    cursor: pointer;
  }

  .icon:hover {
    color: rgb(46, 100, 148);
  }

  .fa-question-circle {
    margin-top: 35px;
  }

  .text--color {
    color:rgb(20, 117, 189);
  }

  .add__thesis {
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

  .offer__slogans {
    text-transform: none;
    color: rgb(246, 249, 252);
    text-align: left;
    word-wrap: break-word;
  }

  .main-slogan {
    color: rgb(161, 163, 167);
    margin-bottom: 20px;
  }

  .form__container {
    min-height: 500px;
    border-radius: 0px;
    background-color:rgb(246, 249, 252);
  }

  .cards__conteiner {
    padding-left: 15%;
    padding-right: 15%;
  }

  .name-description__card {
    height: 420px;
    margin-bottom: 30px;
  }

  .name-description__fields {
    padding-right: 15%;
  }

  .tags-curator__card {
    margin-bottom: 30px;
  }

  .submit-button {
    font-weight: 300;
    background-color: rgb(20, 117, 189);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 100px;
    border: none;
  }

  .submit-button:hover {
      background-color: rgb(46, 100, 148);
  }

  .col-form-label {
    padding-bottom: 0px;
  }

  .card__title {
    margin-bottom: 15px;
  }

  .tag__container {
    position: relative;
    display: inline-block;
  }

  .tag {
    margin: 0 10px 10px 0;
    padding: 3px 10px 3px 10px;
    color: rgb(20, 117, 189);
    background: #fff;
    border: 1px solid rgb(20, 117, 189);
    border-radius: 0.25rem;
  }

</style>
