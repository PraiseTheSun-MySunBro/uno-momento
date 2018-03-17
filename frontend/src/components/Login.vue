<template>
  <b-container fluid v-if="!$auth.check()">
    <b-container class="auth">
      <b-tabs>
        <b-tab title="Login">
          <b-form @submit.prevent="onLoginSubmit">
            <b-card bg-variant="light">
              <b-form-group horizontal
                            label="Username:"
                            label-class="text-sm"
                            label-for="auth_username">
                <b-form-input id="auth_username" type="text" v-model="credentials.username"></b-form-input>
              </b-form-group>
              <b-form-group horizontal
                            label="Password:"
                            label-class="text-sm"
                            label-for="auth_password">
                <b-form-input id="auth_password" type="password" v-model="credentials.password"></b-form-input>
              </b-form-group>
              <b-form-group>
                <b-button variant="primary" type="submit">Login</b-button>
              </b-form-group>
            </b-card>
          </b-form>
        </b-tab>
        <b-tab title="Register">
          <div class="alert alert-danger" v-if="error && !success">
            <p>There was an error, unable to complete registration.</p>
            <p>{{ errors }}</p>
          </div>
          <div class="alert alert-success" v-if="success">
            <p>Registration completed. You can now sign in.</p>
          </div>
          <b-form @submit.prevent="onRegisterSubmit">
            <b-card bg-variant="light">
              <b-form-group horizontal
                            label="Email:"
                            label-class="text-sm"
                            label-for="reg_email">
                <b-form-input id="reg_email" type="email" v-model="credentials.email"></b-form-input>
              </b-form-group>
              <b-form-group horizontal
                            label="Username:"
                            label-class="text-sm"
                            label-for="reg_username">
                <b-form-input id="reg_username" type="text" v-model="credentials.username"></b-form-input>
              </b-form-group>
              <b-form-group horizontal
                            label="Password:"
                            label-class="text-sm"
                            label-for="reg_password">
                <b-form-input id="reg_password" type="password" v-model="credentials.password"></b-form-input>
              </b-form-group>
              <b-button variant="primary" type="submit">Register</b-button>
            </b-card>
          </b-form>
        </b-tab>
      </b-tabs>
    </b-container>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      credentials: {
        email: '',
        password: '',
        username: ''
      },
      success: false,
      error: false,
      errors: []
    }
  },
  methods: {
    onLoginSubmit () {
      let app = this

      this.$auth.login({
        data: {
          username: app.credentials.username,
          password: app.credentials.password
        },
        success: () => {
          app.success = true
          axios.get(`/auth/user/${app.credentials.username}`)
            .then((res) => {
              console.log("Loaded data: " + res.data);
            })
            .catch((err) => {
              console.error(err);
            })
        },
        error: (err) => {
          app.error = true
          app.errors = err.response.data;
          console.error(err.response);
        },
        redirect: null,
        fetchUser: null
      })
    },
    onRegisterSubmit () {
      let app = this

      this.$auth.register({
        data: {
          email: app.credentials.email,
          username: app.credentials.username,
          password: app.credentials.password
        },
        success: () => {
          app.success = true
        },
        error: (err) => {
          app.error = true
          app.errors = err.response.data;
          console.error(err.response);
        },
        redirect: null
      })
    }
  }
}
</script>

<style>
  .auth {
    margin-top: 150px;
    max-width: 600px;
  }
</style>
