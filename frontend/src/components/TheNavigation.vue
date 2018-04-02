<template>
  <b-navbar class="navbar"
            toggleable="md"
            type="dark"
            v-if="$auth.check()">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-navbar-brand class="navbar__logo-container"
                    :to="{ name: 'home' }" >
      <img class="navbar__logo"
           :src="logo">
    </b-navbar-brand>
    <b-collapse id="nav-collapse"
                is-nav>
      <b-navbar-nav>
        <b-nav-item :to="{ name: 'home' }">
          <em>Pealeht</em>
        </b-nav-item>
        <b-nav-item :to="{ name: 'addThesisStudent' }">
          <em>Lõputöö lisamine</em>
        </b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right>

          <!-- Using button-content slot -->
          <template slot="button-content">
            <em class="username">{{ fullname }}</em>
            <i class="fas fa-user-circle fa-lg navbar-icon"></i>
          </template>

          <b-dropdown-item :to="{ name: 'profile' }">
            <i class="far fa-user fa-lg navbar-icon"></i>
            <em>Kasutaja andmed</em>
          </b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>

          <b-dropdown-item :to="{ name: 'workplace' }">
            <i class="fas fa-briefcase fa-lg navbar-icon"></i>
            <em>Töölaud</em>
          </b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>

          <b-dropdown-item href="#"
                           @click="logout">
            <i class="fas fa-sign-out-alt fa-lg navbar-icon"></i>
            <em>Logi välja</em>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
export default {
  name: 'TheNavigation',
  data () {
    return {
      logo: require('../assets/TTU_peamine_logo_EST_Valge.png')
    }
  },
  methods: {
    logout () {
      this.$auth.logout({
        success: () => {
          // app.success = true
        },
        error: (err) => {
          // app.error = true
          // app.errors = err.response.data
          console.error(err.response)
        },
        redirect: '/'
      })
    }
  },
  computed: {
    fullname () {
      return `${this.currentUser.person.firstname} ${this.currentUser.person.lastname}`
    }
  },
  props: {
    currentUser: {}
  }
}
</script>

<style scoped>

em {
  font-size: 16px;
  font-style: normal;
  font-weight: 300;
}

.navbar {
  position: relative;
  max-width:100%;
  background-color: rgba(0,0,0,.5);
}
.navbar__logo-container {
  padding-top: 0px;
  padding-bottom: 0px;
}
.navbar__logo {
  max-height: 40px;
}

.username {
  margin-right: 5px;
}

.navbar-icon {
  margin-right: 5px;
}

@media screen and (max-width: 767px) {
  /* adaptive for mobile */
  .navbar {
    position: absolute;
    min-width:100%;
    background-color: rgba(0,0,0,.7);
    z-index: 2;
  }

  em {
    font-size: 14px;
    font-style: normal;
    font-weight: 300;
  }
}

</style>
