<template>
  <div class="add-thesis">
    <div id="thesis_registration">
      <b-card  border-variant="primary bg-light">
        <h2 class="card-title">Lisa lõputöö</h2>
        <hr class="my-2">
        <div id="offer_slogan">
            <p>Peas on head mõtted millest saab hea lõpputöö idee?</p>
            <p>Jaga neid õppejõududega või vali ühe konkreetse ja paku talle oma idee!</p>
        </div>
        <b-form @submit.prevent="onSubmit"
                @reset.prevent="onReset"
                v-if="show">
          <div class="row">
            <div class="col-sm-9">
              <b-form-group vertical
                        label="Teema:"
                        label-class="text-sm"
                        label-for="add_thesis_name">
                <b-form-input id="add_thesis_name"
                              type="text"
                              required
                              v-model="form.ee_title"
                              placeholder="Kirjutage oma lõputöö nimi">
                </b-form-input>
              </b-form-group>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-9">
              <b-form-group vertical
                        label="Kirjeldus:"
                        label-class="text-sm"
                        label-for="add_thesis_description">
                <b-form-textarea id="add_thesis_description"
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
            <div class="col-sm-9">
              <b-form-group vertical
                        label="Juhendaja:"
                        label-class="text-sm"
                        label-for="choose_thesis_curator">
                <b-form-select id="choose_thesis_curator"
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
          <div id="thesisFormButtons" class="float-right">
              <b-button type="reset"  variant="secondary">Tühista</b-button>
              <b-button type="submit"  variant="primary">Paku</b-button>
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
    },
    onReset (evt) {
      /* Reset our form values */
      this.form.ee_title = ''
      this.form.ee_description = ''
      this.form.lecturer = null
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
.add-thesis {
  margin-top: 50%;
  padding-right: 15%;
  padding-left: 15%;
}

#thesis_registration {
  border-top: 4px solid rgb(66, 139, 202);
}
.row {
  margin-bottom: 3%;
}

#offer_slogan {
  margin-bottom: 50px;
}
</style>
