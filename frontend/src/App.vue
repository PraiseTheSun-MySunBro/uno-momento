<template>
  <div id="app" v-cloak v-if="$auth.ready()" >
    <!-- Home page background -->
    <div id="front-page-background" v-if="$auth.check()">
      <div id="main-page-background-image"></div>
    </div>
    <!-- Login page background -->
    <div id="login-page-background" v-if="!$auth.check()">
      <div id="login-page-background-image"></div>
    </div>
    <!-- Navigation bar -->
    <b-navbar toggleable="md" type="dark"  id="navbar">
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-navbar-brand :to="{ name: 'home' }" id="navbar-logo-container">
        <img src="./assets/TTU_peamine_logo_EST_Valge.png" id="navbar-logo">
      </b-navbar-brand>
      <b-collapse is-nav id="nav-collapse" v-if="$auth.check()">
        <b-navbar-nav>
          <b-nav-item :to="{ name: 'addThesis' }">Lõputöö lisamine</b-nav-item>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown  right >
            <!-- Using button-content slot -->
            <template slot="button-content">
              <em>Denis Raestas</em>
              <i class="fas fa-user-circle fa-lg"></i>
            </template>
            <b-dropdown-item :to="{ name: 'profile' }">
              <i class="far fa-user fa-lg"></i>
              <em>Kasutaja andmed</em>
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item :to="{ name: 'workplace' }">
              <i class="fas fa-briefcase fa-lg"></i>
              <em>Töölaud</em>
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item href="#" @click="logOut">
              <i class="fas fa-sign-out-alt fa-lg"></i>
              <em>Logi välja</em>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <!-- Faculty name -->
    <div class="container-faculty" v-if="$auth.check()">
      <h1 id="faculty-name">INFOTEHNOLOOGIA TEADUSKOND</h1>
    </div>
    <!-- Vue component -->
    <div class="container-fluid">
      <router-view/>
    </div>
  </div>
  <div v-else-if="!$store.getters.getConnectionState">
    <h2>No connection to server (might be you forget to start the server?)</h2>
  </div>
  <div v-else>
    <h2>Loading...</h2>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'App',
  data () {
    return {
    }
  },
  created () {
    this.$store.dispatch('fetchUser')
      .then(() => {
        console.log('Data has been fetched successfully!')
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    switchUser (value) {
      this.currentUser.roleCode = value
    },
    logOut () {
      this.$auth.logout({
        success: () => {
          // app.success = true
        },
        error: (err) => {
          // app.error = true
          // app.errors = err.response.data
          console.error(err.response)
        },
        redirect: '/login'
      })
    },
    switchCurrentTab: function (value) {
      this.currentTab = value
    }
  },
  computed: {
    fullname () {
      return `${this.currentUser.firstname} ${this.currentUser.lastname}`
    },
    ...mapGetters({
      currentUser: 'getUser'
    })
  }
}
</script>

<style>

  html,body {
    height: 100%;
    margin: 0;
    padding: 0;
    position: relative;
  }

  #app {
    min-height: 100%;
    overflow: auto;
    position: relative;
  }

  /* container for Vue components */

  .container-fluid {
    margin-top: 290px;
    margin-bottom: 50px;
    padding-left: 5%;
    padding-right: 5%;
  }

  /* container for faculty name */

  #faculty {
    max-width: 70%;
  }

  .container-faculty {
    left:0;
    right:0;
    margin-left:auto;
    margin-right:auto;
    margin-top: 80px;
    padding: 0px 10px 0px 10px;
    width: max-content;
    height: max-content;
    max-width: 70%;
    text-align: center;

    background-color: rgba(0,0,0,.5);
    position: absolute;
  }

    #faculty-name {
    font-weight: 300;
    color: rgb(246, 249, 252);
    word-wrap: break-word;
  }

  /* navigation bar */

  #navbar {
    position: relative;
    max-width:100%;
    background-color: rgba(0,0,0,.5);
  }

  #navbar-logo-container {
    padding-top: 0px;
    padding-bottom: 0px;
  }

  #navbar-logo {
    max-height: 40px;
  }

  /* Home page background (diagonal lines and image) */

  #front-page-background {
    height: 100%;
    width: 100%;
    background-image: linear-gradient(to bottom right,
                        rgb(20, 117, 189),
                        rgb(20, 117, 189) 40%,
                        rgb(66, 139, 202) 40%,
                        rgb(66, 139, 202) 65%,
                        rgb(20, 117, 189) 65%,
                        rgb(20, 117, 189) 75%,
                        rgb(246, 249, 252) 75%);
    position: absolute;
    z-index: -1;
  }

  #main-page-background-image {
    min-height: 400px;
    width: 100%;
    background-image: url(./assets/ttu4.png);
    background-position: 50% 50%;
    background-repeat: no-repeat;
    background-size: cover;
  }

  /* Login page background */

  #login-page-background {
    height: 100%;
    width: 100%;
    background-color:  rgb(246, 249, 252);
    position: absolute;
    z-index: -1;
  }

  #login-page-background-image {
    height: 100%;
    width: 100%;
    background-image: url(./assets/ttu3.jpg);
    background-position: 50% 50%;
    background-repeat: no-repeat;
    background-size: cover;
  }

  [v-cloak] > * { display: none; }
</style>
