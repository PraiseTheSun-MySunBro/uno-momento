<template>
  <b-container fluid>
    <b-container class="auth">
      <b-form @submit.prevent="onSubmit">
        <b-card bg-variant="light">
          <b-form-group horizontal
                        label="Email:"
                        label-class="text-sm"
                        label-for="auth_email">
            <b-form-input id="auth_email" type="email" v-model="credentials.email"></b-form-input>
          </b-form-group>
          <b-form-group horizontal
                        label="Password:"
                        label-class="text-sm"
                        label-for="auth_password">
            <b-form-input id="auth_password" type="password" v-model="credentials.password"></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-button variant="primary">Login</b-button>
          </b-form-group>
        </b-card>
      </b-form>
    </b-container>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      credentials: {
        email: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit () {
      // let app = this
      // this.$auth.login({
        
      // })
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
