<template>
    <!-- List of lectors -->
    <div id="home-container">
      <div id="list_of_lecturers">
        <div role="tablist">
          <b-card  id="list-accordion" no-body class="mb-1" v-for="(lecturer, index) in lecturers" :key="index">
            <b-card-header header-tag="header" class="p-1" role="tab">
              <b-btn block href="#" v-b-toggle="'accordion-' +index" variant="light" class="text-left">
                <span class="when-opened float-right">
                  <i class="fas fa-caret-down"></i>
                </span>
                  <span class="when-closed float-right">
                    <i class="fas fa-caret-left"></i>
                </span>
                {{ lecturer.firstname }} {{lecturer.lastname}}</b-btn>
            </b-card-header>
            <b-collapse :id="'accordion-' + index" visible accordion="my-accordion" role="tab">
              <b-card-body>
                <!-- List of lectors theses -->
                <b-list-group class="theses-list">
                  <b-list-group-item v-for="(thesis, index) in JSON.parse(lecturer.theses)"
                                    :key="index"
                                    href="#"
                                    id="theses-group-item"
                                    @click="showModal(thesis,lecturer)">
                    {{ thesis.ee_title }}
                    <div class="float-right">
                      <b-badge variant="dark" pill id="graduade-pill">MAGISTRI</b-badge>
                      VAATA
                      <i class="fas fa-angle-right fa-lg"></i>
                    </div>
                  </b-list-group-item>
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
      <!-- Modal Component -->
      <b-modal ref="myModalRef"
              id="modal-center"
              centered
              body-bg-variant="light"
              hide-footer
              hide-header
              size="lg">
      <div class="d-block text-center">
        <b-badge variant="dark" pill id="graduade-pill-modal">MAGISTRI</b-badge>
        <h2>{{ thesisTitle }}</h2>
        <h4 id="modal-lecturer-name">
          {{ thesisCuratorFirstName }} {{ thesisCuratorLastName }}
        </h4>
      </div>
      <hr id="modal-hr">
      <!-- Card with theses description -->
      <b-card id="modal-card-description">
        <div id="modal-description-container" >
          <div id="modal-description-headline-container">
            <i id="modal-description-icon" class="fas fa-info-circle fa-2x"></i>
            <h6 id="modal-description-headline">KIRJELDUS</h6>
          </div>
          <p>{{ thesisDescription }}</p>
        </div>
        <div id="modal-tags-container">
          <div id="modal-description-tags-headline-container">
            <i id="modal-description-icon" class="fas fa-tag fa-2x"></i>
            <h6 id="modal-description-headline">MÄRKSÕNAD</h6>
          </div>
        </div>
      </b-card>
      <div id="modal-buttons-container">
        <b-btn class="modal-button-back" @click="hideModal">Tagasi</b-btn>
        <b-btn class="modal-button-candidate" v-if="candidate" @click="candidateThesis">Kandideeri</b-btn>
        <b-btn class="modal-button-deny" v-if="!candidate" @click="denyThesisCandidation">Tühista</b-btn>
      </div>
    </b-modal>
    </div>
</template>

<script>
export default {
  name: 'HomeStudent',
  data () {
    return {
      /* test values */
      candidate: true,
      subscription: false,
      /* list of lecturers  with list of theses */
      lecturers: [],
      /* values for modal form */
      thesisTitle: '',
      thesisDescription: '',
      thesisCuratorFirstName: '',
      thesisCuratorLastName: '',
      /* number of chosen page */
      currentPage: 1
    }
  },
  methods: {
    showModal (thesis, lecturer) {
      this.thesisTitle = thesis.ee_title
      this.thesisDescription = thesis.ee_description
      this.thesisCuratorFirstName = lecturer.firstname
      this.thesisCuratorLastName = lecturer.lastname
      this.$refs.myModalRef.show()
    },
    hideModal: function () {
      this.$refs.myModalRef.hide()
    },
    candidateThesis: function () {
      this.candidate = false
    },
    denyThesisCandidation: function () {
      this.candidate = true
    },
    acceptSubscription: function () {
      this.subscription = true
    },
    denySubscription: function () {
      this.subscription = false
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
  created () {
    console.log('test')
    this.lecturers = this.$store.getters.getCurators
    this.$store.dispatch('fetchCurators')
      .then((data) => {
        console.log('Curators data has been fetched successfully!')
        this.lecturers = data
      })
      .catch(err => {
        console.error(err)
      })
  }
}
</script>

<style scoped>
#home-container {
  margin-top: 290px;
  margin-bottom: 50px;
  padding-left: 5%;
  padding-right: 5%;
}

#list_of_lecturers {
    margin-bottom: 50px;
}

#list-accordion {
  border-top: 4px solid rgb(66, 139, 202);
  border-radius: 0px;
}

/* Each theses group item */

#theses-group-item {
  border-radius: 0px;
}

#graduade-pill {
  margin-right: 40px;
  min-width: 120px;
  background-color:rgb(66, 139, 202);
}

.card-body {
  padding-left: 5%;
  padding-right: 5%;
}

/* Pagination */

#pagination-container {}

#pagination {}

/* Modal form with theses description */

#graduade-pill-modal {
  margin-bottom: 10px;
  min-width: 120px;
  background-color:rgb(66, 139, 202);
}

#modal-lecturer-name {
    font-family: 'Times New Roman', Times, serif;
    font-style: italic;
    margin-bottom: 40px;
}

#modal-hr {
  width: 220px;
  border-top: 1px solid black;
}

/* Modal card with description and tags */

#modal-card-description {
    min-height: 450px;
    margin-top: 30px;
    margin-left: 15%;
    margin-right: 15%;
}

  /* Description container */

  #modal-description-container {
    min-height: 300px;
  }

  #modal-description-headline-container {
    margin-bottom: 15px;
  }

  /* Tags container */

  #modal-tags-container {
    min-height: 50px;
  }

  #modal-description-tags-headline-container {
    margin-bottom: 15px;
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

#modal-buttons-container {
    left:0;
    right:0;
    margin-left:auto;
    margin-right:auto;
    margin-top: 40px;
    width: 220px;
    text-align: center;
}

  .modal-button-back {
    margin-bottom: 20px;
    float: left;
    font-weight: 300;
    background-color: rgb(66, 139, 202);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 100px;
    border: none;
  }

  .modal-button-back:hover {
    background-color: rgb(46, 100, 148);
  }

  .modal-button-candidate {
    margin-bottom: 20px;
    float: right;
    font-weight: 300;
    background-color: rgb(66, 139, 202);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 100px;
    border: none;
  }

  .modal-button-candidate:hover {
    background-color: rgb(46, 100, 148);
  }

  .modal-button-deny {
    margin-bottom: 20px;
    float: right;
    background-color: rgb(219, 70, 70);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    width: 100px;
    border: none;
  }

  .modal-button-deny:hover {
    background-color: rgb(177, 40, 40);
  }

/* Animation for caret in list of lecturers */

.collapsed > .when-opened,
:not(.collapsed) > .when-closed {
  display: none;
}
</style>
