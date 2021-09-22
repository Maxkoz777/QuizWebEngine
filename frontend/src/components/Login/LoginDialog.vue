<template>
  <q-dialog v-model="isOpenRoute">
    <q-card>
      <q-card-section>
        <div class="text-h6">Login</div>
      </q-card-section>
      <q-separator/>
      <q-form
        @submit="onSubmit"
        @reset="resetNewUserData"
      >
        <q-card-section class="login-form-container">
          <q-input filled
                   v-model="userData.username"
                   label="Username"
                   class="login-input"
                   lazy-rules
                   :rules="[ val => val && val.length > 0 || 'Please type your nickname']"
          />
          <q-input filled
                   type="password"
                   v-model="userData.password"
                   label="Password"
                   class="login-input q-pt-md"
                   lazy-rules
                   :rules="[ val => val && val.length > 0 || 'Please type your password']"
          />
        </q-card-section>
        <q-separator/>

        <q-card-actions align="right" class="q-pa-md">
          <q-btn flat label="Reset" type="Reset" class="reset-button"/>
          <q-btn flat label="Login" type="submit" class="accept-button"/>
        </q-card-actions>
      </q-form>
    </q-card>
  </q-dialog>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: "LoginDialog",
  data() {
    return {
      isOpen: true,
      userData: {
        username: '',
        password: ''
      },
    }
  },
  methods: {
    ...mapActions({
      login: 'loginModule/login'
    }),
    async onSubmit() {
      await this.login(this.userData)
    },
    resetNewUserData() {
      for (const field in this.userData) {
        this.userData[field] = null
      }
    }
  },
  computed: {
    isOpenRoute: {
      get() {
        return this.isOpen
      },
      set() {
        //todo validate
        this.$router.push('/main')
      }
    }
  }
}
</script>

<style scoped>
.login-form-container {
  max-height: 50vh;
}

.login-input {
  width: 25vw;
}
</style>
