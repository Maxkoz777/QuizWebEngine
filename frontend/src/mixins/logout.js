import {mapActions} from 'vuex'

export const logout = {
  methods: {
    ...mapActions({
      logout: 'homeModule/logout'
    })
  }
}
