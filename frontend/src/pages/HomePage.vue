<template>
  <div class="fullscreen main-container" v-if="!pending">
    <home-app-bar-layout/>
    <router-view/>
  </div>
  <div v-else>
    <loading-frame/>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import HomeAppBarLayout from "app/layouts/UserPage/HomeAppBarLayout";
import LoadingFrame from "components/Loading/LoadingFrameFullSize";

export default {
  name: "HomePage",
  data() {
    return {
      pending: true
    }
  },
  components: {LoadingFrame, HomeAppBarLayout},
  async created() {
    this.user = await this.fetchUserData()
    this.pending = false
    !this.user ? this.$router.push('/main') : ''
  },
  methods: {
    ...mapActions({
      fetchUserData: 'homeModule/fetchUserData'
    })
  },
  computed: {
    ...mapGetters({
      userData: 'homeModule/userData'
    })
  }
}
</script>

<style scoped>
.main-container{
  display: grid;
  grid-template-rows: 8% 92%;
}
</style>
