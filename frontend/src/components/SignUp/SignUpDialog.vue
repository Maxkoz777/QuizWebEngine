<template>
  <q-dialog v-model="isOpen">
    <q-card>
      <q-card-section>
        <div class="text-h6">Registration</div>
      </q-card-section>

      <q-separator/>

      <q-form
        @submit="onSubmit"
        @reset="resetNewUserData"
      >
        <q-card-section class="registration-form-container">
          <div class="row q-gutter-md q-pt-md">
            <q-input filled
                     v-model="newUserData.firstname"
                     label="First name"
                     class="registration-input"
                     lazy-rules
                     :rules="[ val => val && val.length > 0 || 'Please type your name']"
            />
            <q-input filled
                     v-model="newUserData.lastname"
                     label="Last name"
                     class="registration-input"
                     lazy-rules
                     :rules="[ val => val && val.length > 0 || 'Please type your surname']"
            />
          </div>
          <div class="row q-gutter-md q-pt-md">

            <q-input filled
                     v-model="newUserData.email"
                     label="Email"
                     type="email"
                     class="registration-input"
                     lazy-rules
                     :rules="[ val => val && val.length > 0 || 'Please type your email']"
            />
            <q-input filled
                     v-model="newUserData.username"
                     label="Username"
                     class="registration-input"
                     lazy-rules
                     :rules="[ val => val && val.length > 0 || 'Please type username']"
            />
          </div>
          <div class="row q-gutter-md q-pt-md">
            <q-input filled
                     v-model="newUserData.password"
                     label="Password"
                     type="password"
                     class="registration-input"
                     lazy-rules
                     :rules="[ val => val && val.length > 0 || 'Please type the password']"
            />
            <q-input filled
                     v-model="newUserData.confirmPassword"
                     label="Confirm Password"
                     type="password"
                     class="registration-input"
                     lazy-rules
                     :rules="[ val => val ===this.newUserData.password  || 'Passwords do no match']"
            />
          </div>
        </q-card-section>

        <q-separator/>

        <q-card-actions align="right" class="q-pa-md">
          <q-btn flat label="Reset" type="Reset" class="reset-button"/>
          <q-btn flat label="Sign Up" type="submit" class="signup-button"/>
        </q-card-actions>
      </q-form>

    </q-card>
  </q-dialog>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: "SignUpDialog",
  props: ['isDialogOpen'],
  data() {
    return {
      newUserData: {
        email: '',
        username: '',
        firstname: '',
        lastname: '',
        password: '',
        confirmPassword: ''
      }
    }
  },
  computed: {
    isOpen: {
      get() {
        return this.isDialogOpen
      },
      set(val) {
        this.$emit("update:isDialogOpen", val)
      }
    }
  },
  methods: {
    ...mapActions({
      registerUser: 'signingUpModule/resisterUser'
    }),
    async onSubmit() {
      await this.registerUser(this.newUserData)
    },
    resetNewUserData() {
      for (const regField in this.newUserData) {
        this.newUserData[regField] = null
      }
    }
  }
}
</script>

<style scoped>
.registration-form-container {
  max-height: 50vh;
}

.registration-input {
  width: 15vw;
}
</style>
