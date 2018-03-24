<template>
    <!-- List of lectors -->
    <div id="home-container">
      <div id="list_of_lecturers">
        <div role="tablist">
          <b-card  id="list-accordion" no-body class="mb-1" v-for="(lecturer, index) in lecturers" :key="index">
            <b-card-header header-tag="header" class="p-1" role="tab">
              <b-btn block href="#" v-b-toggle="'accordion-' +index" variant="light" class="text-left" >
                {{ lecturer.firstname }} {{lecturer.lastname}}</b-btn>
            </b-card-header>
            <b-collapse :id="'accordion-' + index" visible accordion="my-accordion" role="tab">
              <b-card-body>
                <!-- List of lectors theses -->
                <b-list-group v-for="(thesis, index) in JSON.parse(lecturer.theses)" :key="index">
                  <b-list-group-item button v-b-modal="'modal-' +index">
                    <em class="float-left">{{ thesis.ee_title }}</em>
                    <div class="float-right">
                      <em>VAATA</em>
                      <i class="fas fa-angle-right fa-lg"></i>
                    </div>
                  </b-list-group-item>
                  <!-- Modal Component -->
                  <b-modal :id="'modal-' + index"
                            body-bg-variant="light"
                            hide-footer
                            hide-header size="lg">
                    <div class="d-block text-center">
                      <h2>{{ thesis.ee_title }}</h2>
                      <h4 id="modal_lecturer_name">{{ lecturer.firstname }} {{lecturer.lastname}}</h4>
                    </div>
                    <hr class="my-2">
                    <!-- Card with theses description -->
                    <b-card id="modal_card_description">
                      <div class="d-block text-left">
                        <h4>Kirjeldus:</h4>
                        <p>{{ thesis.ee_description }}</p>
                      </div>
                    </b-card>
                    <!--
                    <div id="modal_buttons">
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
      <div id="pagination-container">
        <b-pagination align="center" size="md" :total-rows="100" v-model="currentPage" :per-page="10">
        </b-pagination>
        <br>
      </div>
    </div> 
</template>

<script>
export default {
  data () {
    return {
      /* list of lecturers  with list of theses */
      lecturers: [],
      /* number of chosen page */
      currentPage: 1
    }
  },
  methods: {
    getPersons: function() {
      console.log("I'm working!")
      axios.get("/api/curators")
        .then((response) => {
          console.log('Response', response)
        })
        .catch((error) => {
          console.error('Error', error)
        })
    }  
  },
  mounted: function() {
    axios.get('/api/curators')
      .then(response => {
          this.lecturers=response.data
      }).catch(e => {
          console.error(e.data)
      })
  }
}
</script>

<style scoped>
  #list_of_lecturers {
      margin-bottom: 50px;
  }

  #pagination-container {
    
  }

  #pagination {
  }

  .pagination a {
    border: 1px solid #ddd; /* Gray */
}

  #list-accordion {
    border-top: 4px solid rgb(66, 139, 202);
    border-radius: 0px;
  }

  #modal_lecturer_name {
      font-family: 'Times New Roman', Times, serif;
      font-style: italic;
  }

  #modal_card_description {
      margin-top: 3%;
      margin-bottom: 5%;
      margin-left: 15%;
      margin-right: 15%;
  }

  #modal_buttons {
      text-align: center;
  }
</style>