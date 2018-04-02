<template>
  <b-container class="add__thesis">
    <div id="offer-slogan-container">
      <div id="offer-slogans">
        <p>Peas on head mõtted millest saab hea lõpputöö idee?</p>
        <p>Jaga neid õppejõududega või vali ühe konkreetse ja paku talle oma idee!</p>
      </div>
    </div>
    <div id="thesis-registration">
      <b-card id="form-card">
        <h2 class="card-title">PAKU OMA LÕPUTÖÖ IDEE</h2>
        <b-form @submit.prevent="onSubmit"
                v-if="show">
          <div class="row">
            <div class="col-sm-6">
              <b-form-group vertical
                        label="Teema:"
                        label-class="text-sm"
                        label-for="add-thesis-name">
                <b-form-input id="add-thesis-name"
                              type="text"
                              required
                              v-model="form.ee_title"
                              placeholder="Kirjutage oma lõputöö nimi">
                </b-form-input>
              </b-form-group>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6">
              <b-form-group vertical
                        label="Kirjeldus:"
                        label-class="text-sm"
                        label-for="add-thesis-description">
                <b-form-textarea id="add-thesis-description"
                              type="text"
                              required
                              :rows="6"
                              :max-rows="12"
                              v-model="form.ee_description"
                              placeholder="Kirjeldage oma idee">
                </b-form-textarea>
              </b-form-group>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6">
              <label class="mr-sm-2" for="inlineFormCustomSelectPref">Preference</label>
              <b-form-select class="mb-2 mr-sm-2 mb-sm-0"
                            :value="null"
                            :options="{ '1': 'One', '2': 'Two', '3': 'Three' }"
                            id="inlineFormCustomSelectPref">
                <option slot="first" :value="null">Choose...</option>
              </b-form-select>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6">
              <b-form-group vertical
                        label="Juhendaja:"
                        label-class="text-sm"
                        label-for="choose-thesis-curator">
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
          </div>
          <div id="thesis-form-buttons" class="float-right">
              <b-button class="submit-button" type="submit" variant="primary">Paku</b-button>
          </div>
        </b-form>
      </b-card>
    </div>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      show: true,
      /* list of theses */
      listOfTheses: [],

      /* for thesis registration */
      form: {
        ee_title: '',
        ee_description: '',
        lecturer: null
      },
      choseOfLecturers: [
        { text: 'Valige üks', value: null },
        'Mart Vein',
        'Avo Läns'
      ]
    }
  },
  methods: {
    /* methods for student */
    onSubmit (evt) {
      this.listOfTheses.push(
        {ee_title: this.form.ee_title, ee_description: this.form.ee_description})
      axios.post(`api/curator/2/thesis`, {
        ee_title: this.form.ee_title,
        en_title: this.form.en_title,
        ee_description: this.form.ee_description,
        en_description: this.form.en_description,
        aculty_code: 3})
        .then(response => {
          console.log('Post:', response.data)
          window.location.reload()
        })
        .catch(e => {
          console.error(e)
        })
      /* Reset our form values */
      this.form.ee_title = ''
      this.form.ee_description = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    }
  },
  mounted: function () {
    axios.get('/api/curators')
      .then(response => {
        this.lecturers = response.data
      }).catch(e => {
        console.error(e.data)
      })
  }
}
</script>

<style>

  p {
    font-size: 16px;
    line-height: 0.3;
  }

  .add__thesis {
    margin-top: 265px;
    margin-bottom: 50px;
    max-width: 800px;
  }

  .card-body {
    background-color: rgb(246, 249, 252);
  }

  .card-title {
    color: rgb(66, 139, 202);
  }
  .row {
    margin-bottom: 3%;
  }

  #offer-slogan-container {
    left:0;
    right:0;
    margin-left:auto;
    margin-right:auto;
    height: max-content;
    background-color:rgb(20, 117, 189);
    padding: 30px 21px 21px 21px;
  }

  #offer-slogans {
    text-transform: none;
    color: rgb(246, 249, 252);
    text-align: left;
    word-wrap: break-word;
  }

  #form-card {
    border-radius: 0px;
  }

  .submit-button {
    font-weight: 300;
    background-color: rgb(66, 139, 202);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 100px;
    border: none;
  }

  .submit-button:hover {
      background-color: rgb(46, 100, 148);
    }

</style>
