<template>
  <div id="app"
       v-cloak
       v-if="$auth.ready()">

    <!-- Entrypoint page background -->
    <div class="entrypoint__background"
         v-if="!$auth.check()">
    </div>

    <!-- Home page background -->
    <div class="home-page__background"
         v-else>
      <div class="home-page__background__image"></div>
    </div>

    <div v-if="$auth.check()">
      <!-- Navigation bar -->
      <the-navigation :currentUser="currentUser"/>
      <!-- Faculty name -->
      <the-faculty-name :faculty="faculty"/>
    </div>

    <!-- Vue component -->
    <router-view :currentUser="currentUser"/>
  </div>
  <div v-else-if="!$store.getters.getConnectionState">
    <h2>No connection to server (might be you forget to start the server?)</h2>
  </div>
  <div v-else>
    <div style="text-align: center">
      <p style="font-size: 1.5rem; color: white;">Loading...</p>
      <i class="fa fa-spinner fa-spin" style="font-size: 2.5rem;"></i>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import TheNavigation from './components/TheNavigation'
import TheFacultyName from './components/TheFacultyName'
export default {
  name: 'App',
  components: {
    'the-navigation': TheNavigation,
    'the-faculty-name': TheFacultyName
  },
  data () {
    return {
    }
  },
  methods: {
    switchUser (value) {
      this.currentUser.roleCode = value
    },
    switchCurrentTab: function (value) {
      this.currentTab = value
    }
  },
  created () {
    this.$store.dispatch('fetchUser')
      .then(() => {
        console.log('User data has been fetched successfully!')
      })
      .catch(err => {
        if (err.response.data.exception === 'io.jsonwebtoken.ExpiredJwtException') {
          this.$auth.logout({
            success: () => {},
            error: (err) => {
              console.error('Logout error', err.response)
            }
          })
        }
        console.error(err.response)
      })
  },
  computed: {
    ...mapGetters({
      currentUser: 'getUser'
    }),
    faculty () {
      return {
        eeName: this.currentUser.facultyEeName,
        enName: this.currentUser.facultyEnName
      }
    }
  }
}
</script>

<style>
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
  position: relative;
}
button:hover {
  box-shadow: 0 7px 14px rgba(50, 50, 93, 0.1),
              0 3px 6px  rgba(0, 0, 0, 0.08);
}
#app {
  min-height: 100%;
  overflow: auto;
  position: relative;
}
/* Entrypoint page background */
.entrypoint__background {
  overflow: auto;
  position: relative;
}
.entrypoint__background:before {
  content: "";
  position: fixed;
  top: -5%;
  left: -5%;
  z-index: -1;
  display: block;
  background-image: url(./assets/ttu2.png);
  background-size:cover;
  width: 110%;
  height: 110%;
  -webkit-filter: blur(10px);
  -moz-filter: blur(10px);
  -o-filter: blur(10px);
  -ms-filter: blur(10px);
  filter: blur(10px);
  transform: translate3d(0, 0, 0);
  -webkit-transform: translate3d(0, 0, 0);
}
.text {
  font-family: 'Roboto', sans-serif;
}
.label__text {
  font-family: 'Roboto', sans-serif;
  margin-left: 13px;
}
.label--margin {
  margin-top: -4px;
}
/* Home page background (diagonal lines and image) */
.home-page__background {
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
.home-page__background__image {
  min-height: 400px;
  width: 100%;
  background-image: url(./assets/ttu4.png);
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: cover;
  filter: brightness(55%);
  -webkit-filter: brightness(55%);
  -moz-filter: brightness(55%);
  -o-filter: brightness(55%);
  -ms-filter: brightness(55%);
}
/* for Workplace components */
  /* for Tabs */
  .nav-tabs .nav-link.active, .nav-tabs .nav-item.show .nav-link {
    background-color: rgb(246, 249, 252);
    border-bottom-color: rgb(246, 249, 252);
  }
  a {
      color: rgb(66, 139, 202);
      text-decoration: none;
      background-color: transparent;
      -webkit-text-decoration-skip: objects;
  }
  .btn:focus, .btn:active:focus, .btn.active:focus {
    outline: 0 none;
  }
  .btn-secondary:not(:disabled):not(.disabled):active,
  .btn-secondary:not(:disabled):not(.disabled).active,
  .show > .btn-secondary.dropdown-toggle {
    border: 1px solid;
    background-color: rgb(66, 139, 202);
    color: #fff;
    border: 1px solid rgb(66, 139, 202);
  }
  .btn-secondary:not(:disabled):not(.disabled):active:focus,
  .btn-secondary:not(:disabled):not(.disabled).active:focus,
  .show > .btn-secondary.dropdown-toggle:focus {
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0);
  }
@media screen and (max-width: 767px) {
  /* adaptive for mobile */
  .home-page__background__image {
    min-height: 240px;
    width: 100%;
    background-image: url(./assets/ttu4.png);
    background-position: 50% 50%;
    background-repeat: no-repeat;
    background-size: cover;
    filter: brightness(55%);
    -webkit-filter: brightness(55%);
    -moz-filter: brightness(55%);
    -o-filter: brightness(55%);
    -ms-filter: brightness(55%);
  }
}
[v-cloak] > * { display: none; }
</style>
