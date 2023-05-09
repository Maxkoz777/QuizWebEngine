<template>
  <q-dialog v-model="isOpenRoute" data-test-id="dialogSignUp"
  >
    <q-card>
      <q-card-section class="row justify-between">
        <div class="text-h6">Registration</div>
        <div class="text-red-4 notify-text-size">{{ passwordMatched }}</div>
      </q-card-section>

      <q-separator />

      <q-form @submit="onSubmit" @reset="resetNewUserData">
        <q-card-section class="registration-form-container">
          <div class="row q-gutter-md q-pt-md">
            <q-input
              data-test-id="firstnameSignUp"
              filled
              v-model="newUserData.firstname"
              label="First name"
              class="registration-input"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type your name'
              ]"
            />
            <q-input
              data-test-id="lastnameSignUp"
              filled
              v-model="newUserData.lastname"
              label="Last name"
              class="registration-input"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type your surname'
              ]"
            />
          </div>
          <div class="row q-gutter-md q-pt-md">
            <q-input
              data-test-id="emailSignUp"
              filled
              v-model="newUserData.email"
              label="Email"
              type="email"
              class="registration-input"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type your email'
              ]"
            />
            <q-input
              data-test-id="usernameSignUp"
              filled
              v-model="newUserData.username"
              label="Username"
              class="registration-input"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type username'
              ]"
            />
          </div>
          <div class="row q-gutter-md q-pt-md">
            <q-input
              data-test-id="passwordSignUp"
              filled
              v-model="newUserData.password"
              label="Password"
              type="password"
              class="registration-input"
              lazy-rules
              :rules="[
                val => (val && val.length > 0) || 'Please type the password'
              ]"
            />
            <q-input
              data-test-id="confirmPasswordSignUp"
              filled
              v-model="newUserData.confirmPassword"
              label="Confirm Password"
              type="password"
              class="registration-input"
              lazy-rules
              :rules="[
                val =>
                  val === this.newUserData.password || 'Passwords do no match'
              ]"
            />
          </div>
        </q-card-section>

        <q-separator />

        <q-card-actions align="right" class="q-pa-md">
          <q-btn flat label="Reset" type="Reset" class="reset-button" />
          <q-btn
            data-test-id="confirmSignUp"
            flat
            label="Sign Up"
            type="submit"
            class="accept-button"/>
        </q-card-actions>
      </q-form>
    </q-card>
  </q-dialog>
</template>

<script>
import { mapActions } from "vuex";
import { validatePasswordsSimilarity } from "app/helpers";

export default {
  name: "SignUpDialog",
  data() {
    return {
      isOpen: true,
      newUserData: {
        email: "",
        username: "",
        firstname: "",
        lastname: "",
        password: "",
        confirmPassword: ""
      }
    };
  },
  methods: {
    ...mapActions({
      registerUser: "signingUpModule/resisterUser"
    }),
    async onSubmit() {
      const responseMessage = await this.registerUser(this.newUserData);
      if (responseMessage) {
        this.showSignUpNotification(
          "Registration done! Please sign in.",
          "purple"
        );
        this.$router.push("/main");
      } else {
        this.showSignUpNotification("Error in server, please try later", "red");
      }
    },
    resetNewUserData() {
      for (const regField in this.newUserData) {
        this.newUserData[regField] = "";
      }
    },
    showSignUpNotification(message, color) {
      this.$q.notify({
        message: message,
        color: color
      });
    }
  },
  computed: {
    isOpenRoute: {
      get() {
        return this.isOpen;
      },
      set() {
        this.$router.push("/main");
      }
    },
    passwordMatched: function() {
      return validatePasswordsSimilarity(
        this.newUserData.password,
        this.newUserData.confirmPassword
      )
        ? ""
        : "Passwords do not match";
    }
  }
};
</script>

<style scoped>
.registration-form-container {
  max-height: 50vh;
}

.registration-input {
  width: 15vw;
}
</style>
