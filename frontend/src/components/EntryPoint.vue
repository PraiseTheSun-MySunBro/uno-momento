<template>
  <div class="entrypoint">
    <b-container class="container__entrypoint"
                 v-if="!$auth.check()"
                 v-bind:class="{ '--signup': isActive }">
      <!-- Sign in form -->
      <b-form class="form__signin sign-in__transaction"
              @submit.prevent="onLoginSubmit">
          <b-form-group :label="`<span class='label__text'>${$lang.usernameLabel}</span>`"
                        label-for="auth-username"
                        class="mb-2">
            <b-form-input id="auth-username"
                          type="text"
                          v-model="credentials.username">
            </b-form-input>
          </b-form-group>
          <b-form-group :label="`<span class='label__text'>${$lang.passwordLabel}</span>`"
                        label-for="auth-password">
            <b-form-input id="auth-password"
                          type="password"
                          v-model="credentials.password">
            </b-form-input>
          </b-form-group>
          <b-form-group>
            <div class="div__button">
              <b-button type="submit"
                        variant="primary"
                        class="button--signin"
                        :disabled="$v.credentials.username.$invalid
                                || $v.credentials.password.$invalid">
                {{ $lang.signinButtonText }}
              </b-button>
              <p class="text forgot-password">{{ $lang.forgottenPasswordText }}</p>
            </div>
          </b-form-group>
      </b-form>

      <!-- Image, switches and text -->
      <div id="entrypoint__transaction" class="entrypoint__transaction">
        <div class="image">
          <div class="image__text text--up">
            <h2 class="text">{{ $lang.firstTimeHereHeader }}</h2>
            <p class="text">{{ $lang.firstTimeHereText }}</p>
          </div>
          <div class="image__text text--in">
            <h2 class="text">{{ $lang.commonHereHeader }}</h2>
            <p class="text">{{ $lang.commonHereText }}</p>
          </div>
          <div class="image__button"
               @click="isActive = !isActive">
            <span class="text text--up">{{ $lang.createNewText }}</span>
            <span class="text text--in">{{ $lang.loginText }}</span>
          </div>
        </div>

        <!-- Registration form -->
        <b-form class="form__registration sign-up__transaction"
                @submit.prevent="onRegisterSubmit">

          <div  class="alert alert-danger"
                v-if="error && !success">
            <p>There was an error, unable to complete registration.</p>
            <p>{{ errors }}</p>
          </div>
          <div class="alert alert-success"
               v-if="success">
            <p>Registration completed. You can now sign in.</p>
          </div>

          <div v-if="!back">
            <div class="row label--nowrap">
              <b-form-group :label="`<span class='label__text'>${$lang.firstnameLabel}</span>`"
                            label-for="reg-firstname"
                            class="mb-3 mr-1">
                <b-form-input id="reg-firstname"
                              class="label--margin"
                              type="text"
                              v-model="credentials.firstname">
                </b-form-input>
              </b-form-group>
              <b-form-group :label="`<span class='label__text'>${$lang.lastnameLabel}</span>`"
                            label-for="reg-lastname"
                            class="mb-3 ml-1">
                <b-form-input id="reg-lastname"
                              class="label--margin"
                              type="text"
                              v-model="credentials.lastname">
                </b-form-input>
              </b-form-group>
            </div>
            <b-form-group :label="`<span class='label__text'>${$lang.usernameLabel}</span>`"
                          label-for="reg-username"
                          class="mb-3">
              <b-form-input id="reg-username"
                            class="label--margin"
                            type="text"
                            v-model="credentials.username">
              </b-form-input>
            </b-form-group>
            <b-form-group :label="`<span class='label__text'>${$lang.passwordLabel}</span>`"
                          label-for="reg-password"
                          class="mb-3">
              <b-form-input id="reg-password"
                            class="label--margin"
                            type="password"
                            v-model="credentials.password">
              </b-form-input>
            </b-form-group>
            <b-form-group :label="`<span class='label__text'>${$lang.emailLabel}</span>`"
                          label-for="reg-email"
                          class="mb-3">
              <b-form-input id="reg-email"
                            class="label--margin"
                            type="email"
                            v-model="credentials.email">
              </b-form-input>
            </b-form-group>
          </div>

          <div v-if="back">
            <b-form-group :label="`<span class='label__text'>${$lang.uniIdLabel}</span>`"
                          label-for="reg-uniid"
                          class="mb-3">
              <b-form-input id="reg-uniid"
                            class="label--margin"
                            type="text"
                            v-model="credentials.uniId">
              </b-form-input>
            </b-form-group>
            <b-form-group class="div__roles mb-3">
              <b-form-radio-group id="reg-role"
                                  class="label--margin"
                                  v-model="credentials.role"
                                  :options="roles">
              </b-form-radio-group>
            </b-form-group>
            <b-form-group :label="`<span class='label__text'>${$lang.degreeLabel}</span>`"
                          label-for="reg-degree"
                          class="mb-3">
              <b-form-select id="reg-degree"
                             class="label--margin"
                             type="text"
                             v-model="credentials.degree"
                             :options="degrees"/>
            </b-form-group>
            <b-form-group :label="`<span class='label__text'>${$lang.facultyLabel}</span>`"
                          label-for="reg-faculty"
                          class="mb-3">
              <b-form-select id="reg-faculty"
                             class="label--margin"
                             type="text"
                             v-model="credentials.faculty"
                             :options="faculties"/>
            </b-form-group>
          </div>

          <div class="div__button">
            <b-button v-if="!back"
                      variant="primary"
                      class="button--continue"
                      @click="onContinue"
                      :disabled="$v.credentials.firstname.$invalid
                              || $v.credentials.lastname.$invalid
                              || $v.credentials.username.$invalid
                              || $v.credentials.password.$invalid
                              || $v.credentials.email.$invalid">
              {{ $lang.continueButtonText }}
            </b-button>
            <b-button v-if="back"
                      variant="secondary"
                      class="button--back"
                      @click="onBack">
              {{ $lang.backButtonText }}
            </b-button>
            <b-button v-if="back"
                      type="submit"
                      variant="success"
                      class="button--signup"
                      :disabled="$v.credentials.uniId.$invalid
                              || $v.credentials.role.$invalid
                              || $v.credentials.degree.$invalid
                              || $v.credentials.faculty.$invalid
                              ">
              {{ $lang.signupButtonText }}
            </b-button>
          </div>

        </b-form>
      </div>
    </b-container>
  </div>
</template>

<script>
import { required, minLength, maxLength, email } from 'vuelidate/lib/validators'

const touchMap = new WeakMap()

export default {
  name: 'EntryPoint',
  data () {
    return {
      credentials: {
        firstname: '',
        lastname: '',
        username: '',
        password: '',
        email: '',
        role: 1,
        uniId: '',
        degree: '',
        faculty: ''
      },
      roles: [
        { text: '<span class="label__text">Tudeng</span>', value: 1 },
        { text: '<span class="label__text">Õppejõud</span>', value: 2 }
      ],
      degrees: [
        // { text: '<span class="label__text">Vali</span>', value: null, disabled: true },
        { text: '<span class="label__text">Puudub</span>', value: 1 },
        { text: '<span class="label__text">Bakalaureus</span>', value: 2 },
        { text: '<span class="label__text">Magister</span>', value: 3 },
        { text: '<span class="label__text">Doktor</span>', value: 4 },
        { text: '<span class="label__text">Rakendus</span>', value: 5 },
        { text: '<span class="label__text">Inseneeri</span>', value: 6 }
      ],
      faculties: [
        // { text: '<span class="label__text">Vali</span>', value: null, disabled: true },
        { text: '<span class="label__text">Infotehnoloogia teaduskond</span>', value: 3 },
        { text: '<span class="label__text">Loodusteaduskond</span>', value: 4 },
        { text: '<span class="label__text">Eesti Mereakadeemia</span>', value: 5 },
        { text: '<span class="label__text">Inseneriteaduskond</span>', value: 2 },
        { text: '<span class="label__text">Majandusteaduskond</span>', value: 1 }
      ],
      isActive: false,
      back: false,
      success: false,
      error: false,
      errors: []
    }
  },
  methods: {
    delayTouch ($v) {
      $v.$reset()
      if (touchMap.has($v)) {
        clearTimeout(touchMap.get($v))
      }
      touchMap.set($v, setTimeout($v.$touch, 1000))
    },
    onLoginSubmit () {
      let app = this
      this.$auth.login({
        data: {
          username: app.credentials.username,
          password: app.credentials.password
        },
        success: () => {
          app.success = true
          this.$store.dispatch('fetchUser')
            .then(() => {
              console.log('Data has been fetched successfully!')
            })
        },
        error: (err) => {
          app.error = true
          app.errors = err.response
          console.error(err)
        },
        rememberMe: true,
        redirect: '/',
        fetchUser: null
      })
    },
    onContinue () {
      this.back = true
    },
    onBack () {
      this.back = false
    },
    onRegisterSubmit () {
      let app = this
      this.$auth.register({
        data: {
          username: app.credentials.username,
          password: app.credentials.password,
          email: app.credentials.email,
          firstname: app.credentials.firstname,
          lastname: app.credentials.lastname,
          uniId: app.credentials.uniId,
          roleCode: app.credentials.role,
          facultyCode: app.credentials.faculty,
          degreeCode: app.credentials.degree
        },
        success: () => {
          app.success = true
        },
        error: (err) => {
          app.error = true
          app.errors = `${err.response.data.status} ${err.response.data.error}`
          console.error(err.response)
        },
        redirect: null
      })
    }
  },
  validations: {
    credentials: {
      firstname: {
        required,
        maxLength: maxLength(1000)
      },
      lastname: {
        required,
        maxLength: maxLength(1000)
      },
      username: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(32)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(72)
      },
      email: {
        required,
        email,
        maxLength: maxLength(256)
      },
      role: {
        required
      },
      uniId: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(6)
      },
      degree: {
        required
      },
      faculty: {
        required
      }
    }
  }
}
</script>

<style scoped>
*, *:before, *:after {
  -webkit-box-sizing: border-box;
          box-sizing: border-box;
  margin: 0;
  padding: 0;
}

h2 {
  width: 100%;
  font-size: 26px;
  text-align: center;
}

input {
  -webkit-appearance: textfield;
  cursor: text;
  display: block;
  margin-top: -5px;
  width: 100%;
  padding: 7.5px 7.5px 7.5px 7.5px;
  font-size: 18px;
  border: 1px solid #e6ecf0;
  font-family: 'Roboto', sans-serif;
}
input:focus {
  outline: 0;
  color: #14171a;
  border-color: rgba(0,132,180,0.5);
}

select {
  cursor:pointer;
  display: block;
  margin-top: -5px;
  padding: 7.5px 7.5px 7.5px 7.5px;
  font-size: 18px;
  border: 1px solid #e6ecf0;
  font-family: 'Roboto', sans-serif;
}

button {
  display: block;
  margin: 0 auto;
  width: 260px;
  height: 36px;
  border-radius: 30px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
  border: none;
  outline: none;
  background: none;
  text-transform: uppercase;
  font-family: 'Roboto', sans-serif;
}

.entrypoint {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.container__entrypoint {
  overflow: hidden;
  position: relative;
  max-width: 1050px;
  width: 900px;
  height: 550px;
  margin: 0 auto;
  background: #fff;
  border-radius: 1%;
}

.form__signin {
  position: relative;
  width: 640px;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
  padding: 167px 120px 0;
}

.form__registration {
  position: relative;
  width: 640px;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
  padding: 30px 120px 0;
}

.entrypoint__transaction {
  overflow: hidden;
  position: absolute;
  left: 640px;
  top: 0;
  width: 900px;
  height: 100%;
  padding-left: 260px;
  background: #fff;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}
.container__entrypoint.--signup .entrypoint__transaction {
  -webkit-transform: translate3d(-640px, 0, 0);
          transform: translate3d(-640px, 0, 0);
}

.sign-in__transaction {
  -webkit-transition-timing-function: ease-out;
          transition-timing-function: ease-out;
}
.container__entrypoint.--signup .sign-in__transaction {
  -webkit-transition-timing-function: ease-in-out;
          transition-timing-function: ease-in-out;
  -webkit-transition-duration: 1.2s;
          transition-duration: 1.2s;
  -webkit-transform: translate3d(640px, 0, 0);
          transform: translate3d(640px, 0, 0);
}

.sign-up__transaction {
  -webkit-transform: translate3d(-900px, 0, 0);
          transform: translate3d(-900px, 0, 0);
}
.container__entrypoint.--signup .sign-up__transaction {
  -webkit-transform: translate3d(0, 0, 0);
          transform: translate3d(0, 0, 0);
}

.image {
  overflow: hidden;
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 260px;
  height: 100%;
  padding-top: 360px;
}
.image:before {
  content: '';
  position: absolute;
  right: 0;
  top: 0;
  width: 900px;
  height: 100%;
  background-image: url("../assets/ttu2.png");
  background-size: cover;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}
.image:after {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  -webkit-transform: translate3d(0, 0, 0);
}
.container__entrypoint.--signup .image:before {
  -webkit-transform: translate3d(640px, 0, 0);
          transform: translate3d(640px, 0, 0);
}
.image__text {
  z-index: 2;
  position: absolute;
  left: 0;
  top: 50px;
  width: 100%;
  padding: 0 20px;
  text-align: center;
  color: #fff;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}
.image__text h2 {
  margin-bottom: 10px;
  font-weight: normal;
}
.image__text p {
  font-size: 14px;
  line-height: 1.5;
}
.container__entrypoint.--signup .image__text.text--up {
  -webkit-transform: translateX(520px);
          transform: translateX(520px);
}
.image__text.text--in {
  -webkit-transform: translateX(-520px);
          transform: translateX(-520px);
}
.container__entrypoint.--signup .image__text.text--in {
  -webkit-transform: translateX(0);
          transform: translateX(0);
  -webkit-transform: translate3d(0, 0, 0);
}
.image__button {
  overflow: hidden;
  z-index: 2;
  position: relative;
  width: 150px;
  height: 36px;
  margin: 0 auto;
  background: transparent;
  color: #fff;
  text-transform: uppercase;
  font-size: 15px;
  cursor: pointer;
}
.image__button:after {
  content: '';
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  border: 1px solid #fff;
  border-radius: 30px;
  -webkit-transform: translate3d(0, 0, 0);
}
.image__button:hover:after {
  border: 1px solid #fff;
  background-color: rgba(255, 255, 255, 0.15);
  border-radius: 30px;
  -webkit-transform: translate3d(0, 0, 0);
}
.image__button span {
  position: absolute;
  left: 0;
  top: 0;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  width: 100%;
  height: 100%;
  -webkit-transition: -webkit-transform 1.2s;
  transition: -webkit-transform 1.2s;
  transition: transform 1.2s;
  transition: transform 1.2s, -webkit-transform 1.2s;
}
.image__button span.text--in {
  -webkit-transform: translateY(-72px);
          transform: translateY(-72px);
}
.container__entrypoint.--signup .image__button span.text--in {
  -webkit-transform: translateY(0);
          transform: translateY(0);
  -webkit-transform: translate3d(0, 0, 0);
}
.container__entrypoint.--signup .image__button span.text--up {
  -webkit-transform: translateY(72px);
          transform: translateY(72px);
}

.button--signin {
  -webkit-appearance: button;
  margin-left: 0px;
  background: rgb(2, 136, 209);
  text-transform: uppercase;
}
.button--signin:hover {
  background: rgb(1, 87, 155);
}
.button--signup {
  margin-left: 20px;
  width: 100%;
  -webkit-appearance: button;
  background: rgb(0, 183, 76);
  text-transform: uppercase;
}
.button--signup:hover {
  background: rgb(0, 130, 54);
}
.button--continue {
  -webkit-appearance: button;
  background: rgb(2, 136, 209);
  text-transform: uppercase;
}
.button--continue:hover {
  background: rgb(1, 87, 155);
}
.button--back {
  margin-right: 20px;
  -webkit-appearance: button;
  background: rgb(158, 158, 158);
  text-transform: uppercase;
}
.button--back:hover {
  background: rgb(97, 97, 97);
}
.div__button {
  -moz-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  -moz-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  margin: 20px auto;
}

.forgot-password {
  cursor: pointer;
  font-size: 12px;
  color: rgb(2, 136, 209);
}
.forgot-password:hover {
  color: rgb(1, 87, 155);
  text-decoration: underline;
}

.label--nowrap {
  display: -webkit-flex;
  -webkit-flex-wrap: nowrap;
  display: flex;
  flex-wrap: nowrap;
}

.div__roles {
  margin-left: 25%;
}

@media screen and (max-width: 991px) {
  button {
    width: 160px;
  }

  .entrypoint__transaction {
    left: 460px;
    padding-left: 180px;
  }
  .container__entrypoint.--signup .entrypoint__transaction {
    -webkit-transform: translate3d(-460px, 0, 0);
            transform: translate3d(-460px, 0, 0);
  }

  .form__signin {
    width: 460px;
    padding: 167px 50px 0;
  }

  .form__registration {
    width: 460px;
    padding: 30px 50px 0;
    margin-left: 80px;
  }
}

@media screen and (max-width: 767px) {
  /* adaptive for mobile */
  #entrypoint__transaction {
    display: none;
  }
}
</style>
