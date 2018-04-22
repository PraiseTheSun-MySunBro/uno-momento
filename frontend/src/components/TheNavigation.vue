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
        <b-nav-item  href="#" @click="addThesis">
          <em>Lõputöö lisamine</em>
        </b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown>
          <template slot="button-content">
            <em class="lang">{{ language }}</em>
          </template>
          <b-dropdown-item href="#" @click="chooseEesti">
            <img src="/static/ee.svg" class="navbar-flag">
            <em>Eesti</em>
          </b-dropdown-item>
          <b-dropdown-item href="#" @click="chooseEnglish">
            <img src="/static/gb.svg" class="navbar-flag">
            <em>English</em>
          </b-dropdown-item>
        </b-nav-item-dropdown>
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

          <b-dropdown-item href="#" @click="chooseWorkplace">
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
      language: 'EESTI',
      logo: require('../assets/TTU_peamine_logo_EST_Valge.png')
    }
  },
  methods: {
    chooseEesti: function () {
      this.language = 'EESTI'
    },
    chooseEnglish: function () {
      this.language = 'ENGLISH'
    },
    addThesis: function () {
      if (this.currentUser.roleCode === 1) {
        this.$router.push('/thesis/add/student')
      } else if (this.currentUser.roleCode === 2) {
        this.$router.push('/thesis/add/lecturer')
      }
    },
    chooseWorkplace: function () {
      if (this.currentUser.roleCode === 1) {
        this.$router.push('/thesis/workplace/student')
      } if (this.currentUser.roleCode === 2) {
        this.$router.push('/thesis/workplace/lecturer')
      }
    },
    logout () {
      this.$auth.logout({
        success: () => {
          // app.success = true
          this.$store.dispatch('resetUser')
          this.$store.dispatch('resetWorkplace')
        },
        error: (err) => {
          // app.error = true
          // app.errors = err.response.data
          console.error(err.response)
        },
        redirect: '/login'
      })
    }
  },
  computed: {
    fullname () {
      return `${this.currentUser.firstname} ${this.currentUser.lastname}`
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
  max-width: 100%;
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

.lang {
  margin-right: 5px;
}

.navbar-icon {
  margin-right: 5px;
}

.navbar-flag {
  margin-right: 5px;
  width: 20px;
  height: 15px;
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
