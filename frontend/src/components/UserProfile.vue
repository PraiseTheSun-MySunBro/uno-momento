<template>
  <div class="profile">
    <b-container class="container__profile">
      <b-row>

        <!-- User profile -->
        <b-col class="col-sm-4 first-col--min">
          <b-card header="Profiil"
                  header-class="text__header"
                  border-variant="light"
                  class="card__props">
            <div class="mt-4 mb-3">
              <b-img center
                     :src="picture"
                     fluid
                     class="image--expansion"/>
            </div>
            <div class="text__user-fullname">
              {{ fullname }}
            </div>
            <div class="div__button">
              <b-button v-if="!change"
                        variant="primary"
                        class="button--update"
                        @click="onChange">
                Muuda andmed
              </b-button>
              <b-button v-if="change"
                        variant="secondary"
                        class="button--back"
                        @click="onBack">
                {{ $lang.backButtonText }}
              </b-button>
            </div>
          </b-card>
        </b-col>

        <!-- User profile settings -->
        <b-col class="col-sm-8 second-col--min">
          <div v-if="!change">
            <b-card header="Kasutaja andmed"
                    header-class="text__header"
                    border-variant="light"
                    class="card__props">
              <b-form class="form__profile">
                <div class="label--nowrap">
                  <b-form-group label="<span class='label__text'>Eesnimi</span>"
                                label-for="profile-firstname"
                                class="mb-3 mr-1 input__name">
                    <b-form-input id="profile-firstname"
                                  class="label--margin"
                                  type="text"
                                  v-model="user.firstname"
                                  :disabled="true">
                    </b-form-input>
                  </b-form-group>
                  <b-form-group label="<span class='label__text'>Perekonnanimi</span>"
                                label-for="profile-lastname"
                                class="mb-3 ml-1 input__name">
                    <b-form-input id="profile-lastname"
                                  class="label--margin"
                                  type="text"
                                  v-model="user.lastname"
                                  :disabled="true">
                    </b-form-input>
                  </b-form-group>
                </div>
                <b-form-group label="<span class='label__text'>Uni-ID</span>"
                              label-for="profile-uniid"
                              class="mb-3">
                  <b-form-input id="profile-uniid"
                                class="label--margin"
                                type="text"
                                v-model="user.uniId"
                                :disabled="true">
                  </b-form-input>
                </b-form-group>
                <b-form-group label="<span class='label__text'>Rool</span>"
                              label-for="profile-role"
                              class="mb-3">
                  <b-form-input id="profile-role"
                                class="label--margin"
                                v-model="user.role"
                                :disabled="true">
                  </b-form-input>
                </b-form-group>
                <b-form-group label="<span class='label__text'>Kraad</span>"
                              label-for="profile-degree"
                              class="mb-3">
                  <b-form-input id="profile-degree"
                                class="label--margin"
                                type="text"
                                v-model="user.degree"
                                :disabled="true">
                  </b-form-input>
                </b-form-group>
                <b-form-group label="<span class='label__text'>Teaduskond</span>"
                              label-for="profile-faculty"
                              class="mb-4">
                  <b-form-input id="profile-faculty"
                                class="label--margin"
                                type="text"
                                v-model="user.faculty"
                                :disabled="true">
                  </b-form-input>
                </b-form-group>
              </b-form>
            </b-card>
          </div>

          <!-- Edit profile settings -->
          <!-- Edit email -->
          <div v-if="change">
            <b-card header="Muuda e-posti"
                    header-class="text__header"
                    border-variant="light"
                    class="card__props">
              <b-form class="form__profile">
                <b-form-group label="<span class='label__text'>E-post</span>"
                              label-for="profile-email"
                              class="mb-3">
                  <b-form-input id="profile-email"
                                class="label--margin"
                                type="email"
                                v-model="user.email">
                  </b-form-input>
                </b-form-group>
              </b-form>

              <div class="div__button">
                <b-button type="submit"
                          variant="primary"
                          class="button--update"
                          :disabled="$v.user.email.$invalid">
                  Salvesta
                </b-button>
              </div>
            </b-card>

            <!-- Edit username -->
            <b-card header="Muuda kasutajanime"
                    header-class="text__header"
                    border-variant="light"
                    class="card__props">
              <b-form class="form__profile">
                <b-form-group label="<span class='label__text'>Kasutajanimi</span>"
                              label-for="profile-username"
                              class="mb-3">
                  <b-form-input id="profile-usernmae"
                                class="label--margin"
                                type="text"
                                v-model="user.username">
                  </b-form-input>
                </b-form-group>

                <div class="div__button">
                  <b-button type="submit"
                            variant="primary"
                            class="button--update"
                            :disabled="$v.user.username.$invalid">
                    Salvesta
                  </b-button>
                </div>
              </b-form>
            </b-card>

            <!-- Edit password -->
            <b-card header="Muuda parooli"
                    header-class="text__header"
                    border-variant="light"
                    class="card__props">
              <b-form class="form__profile">
                <b-form-group label="<span class='label__text'>Uus parool</span>"
                              label-for="profile-password"
                              class="mb-3">
                  <b-form-input id="profile-password"
                                class="label--margin"
                                type="password"
                                v-model="user.password">
                  </b-form-input>
                </b-form-group>
                <b-form-group label="<span class='label__text'>Uus parool (uuesti)</span>"
                              label-for="profile-password-repeat"
                              class="mb-3">
                  <b-form-input id="profile-password-repeat"
                                class="label--margin"
                                type="password"
                                v-model="user.repeatPassword">
                  </b-form-input>
                </b-form-group>

                <div class="div__button">
                  <b-button type="submit"
                            variant="primary"
                            class="button--update"
                            :disabled="$v.user.password.$invalid
                                    || $v.user.repeatPassword.$invalid">
                    Salvesta
                  </b-button>
                </div>
              </b-form>
            </b-card>
          </div>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { required, minLength, maxLength, email, sameAs } from 'vuelidate/lib/validators'

export default {
  name: 'UserProfile',
  props: {
    currentUser: {}
  },
  data () {
    return {
      user: {
        firstname: '',
        lastname: '',
        username: '',
        password: '',
        repeatPassword: '',
        email: '',
        role: '',
        uniId: '',
        degree: '',
        faculty: ''
      },
      picture: require('../assets/user.svg'),
      change: false
    }
  },
  methods: {
    onChange () {
      this.change = true
    },
    onBack () {
      this.change = false
    }
  },
  mounted () {
    console.log(this.$langs)
    this.user.firstname = this.currentUser.firstname
    this.user.lastname = this.currentUser.lastname
    this.user.email = this.currentUser.email
    this.user.username = this.currentUser.username
    this.user.uniId = this.currentUser.uniId
    this.user.role = this.currentUser.roleEeName
    this.user.degree = this.currentUser.degreeEeName
    this.user.faculty = this.currentUser.facultyEeName
  },
  computed: {
    fullname () {
      return `${this.currentUser.firstname} ${this.currentUser.lastname}`
    }
  },
  validations: {
    user: {
      email: {
        required,
        email,
        maxLength: maxLength(256)
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
      repeatPassword: {
        sameAsPassword: sameAs('password')
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

.profile {
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
  padding-left: 10%;
  padding-right: 10%;
}

.container__profile {
  overflow: hidden;
  position: relative;
  width: 100%;
  height: 100%;
  margin: 0 auto;
  background: #fff;
  border-radius: 1%;
}

.first-col--min {
  min-width: 200px;
  width: 100%;
}
.second-col--min {
  min-width: 340px;
  width: 100%;
}
.card__props {
  border-bottom: 0;
  width: 100%;
}

.image--expansion {
  height: 55%;
  width: 55%;
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

.button--update {
  -webkit-appearance: button;
  background: rgb(2, 136, 209);
  text-transform: uppercase;
}
.button--update:hover {
  background: rgb(1, 87, 155);
}
.button--back {
  -webkit-appearance: button;
  background: rgb(158, 158, 158);
  text-transform: uppercase;
}
.button--back:hover {
  background: rgb(97, 97, 97);
}

.input__name {
  width: 100%;
}

.text__user-fullname {
  font-family: 'Roboto', sans-serif;
  font-size: 20px;
  color: grey;
  text-transform: uppercase;
  text-align: center;
}

.text__header {
  font-family: 'Roboto', sans-serif;
  font-size: 20px;
  color: grey;
  padding-top: 10px;
  padding-bottom: 10px;
  text-transform: uppercase;
  text-align: center;
}

.form__profile {
  position: relative;
  padding: 30px 139px 0;
}

.label--nowrap {
  display: flex;
  display: -webkit-flex;
  flex-wrap: nowrap;
  -webkit-flex-wrap: nowrap;
}

@media screen and (max-width: 1237px) {
  .form__profile {
    padding: 30px 80px 0;
  }
}

@media screen and (max-width: 991px) {
  button {
    width: 90%;
  }

  .form__profile {
    padding: 30px 47px 0;
  }
}

@media screen and (max-width: 767px) {
  button {
    width: 90%;
  }

  .profile {
    margin-top: 76px;
  }

  .form__profile {
    padding: 30px 20px 0;
  }

  .col-sm-4 {
    max-width: 100%;
    flex: 0 0 100%;
  }
  .col-sm-8 {
    max-width: 100%;
    flex: 0 0 100%;
  }
}

@media screen and (max-width: 663px) {
  .form__profile {
    padding: 30px 20px 0;
  }
}
</style>
