import Vue from 'vue'
import Vuex from 'vuex'

import signingUpModule from "src/store/modules/signingUpModule";
import loginModule from "src/store/modules/loginModule";
import homeModule from "src/store/modules/homeModule";
import quizEditorModule from "src/store/modules/quizEditorModule";

Vue.use(Vuex)

/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default function (/* { ssrContext } */) {
  const Store = new Vuex.Store({
    modules: {
      signingUpModule,
      loginModule,
      homeModule,
      quizEditorModule
    },

    // enable strict mode (adds overhead!)
    // for dev mode only
    strict: process.env.DEBUGGING
  })

  return Store
}
