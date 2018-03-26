<template>
  <div id="app" v-cloak v-if="$auth.ready() && $store.getters.getConnectionState">
    <b-navbar toggleable="md" type="dark" variant="secondary" style="max-width:100%">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand :to="{ name: 'home' }" @click="switchCurrentTab(0)">Tallinna Tehnikaülikool</b-navbar-brand>
      <b-collapse is-nav id="nav_collapse">
        <b-navbar-nav>
          <b-nav-item :to="{ name: 'addThesis' }">Lõputöö lisamine</b-nav-item>
          <b-nav-item href="#" v-on:click="switchUser(2)" v-if="currentUser.role_code === 1">Juhendaja vaade</b-nav-item>
          <b-nav-item href="#" v-on:click="switchUser(1)" v-else-if="currentUser.role_code === 2">Tudengi vaade</b-nav-item>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right no-caret>
            <!-- Using button-content slot -->
            <div slot="button-content">
              <em>{{ fullname }}</em>
              <i class="fas fa-user-circle fa-lg"></i>
            </div>
            <b-dropdown-item href="#">
              <i class="far fa-user fa-lg"></i>
              <em>Kasutaja andmed</em>
            </b-dropdown-item>
            <b-dropdown-item href="#" v-on:click="switchCurrentTab(2)">
              <i class="fas fa-briefcase fa-lg"></i>
              <em>Töölaud</em>
            </b-dropdown-item>
            <b-dropdown-item href="#" @click="logOut">
              <i class="fas fa-sign-out-alt fa-lg"></i>
              <em>Logi välja</em>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view/>
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
          app.success = true
        },
        error: (err) => {
          app.error = true
          app.errors = err.response.data;
          console.error(err.response);
        },
        redirect: '/login'
      })
    },
    switchCurrentTab: function(value) {
      this.currentTab = value
    } ,
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
  [v-cloak] > * { display: none; }
</style>
