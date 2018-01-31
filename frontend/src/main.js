// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'
import { store } from './store/index'
import App from './App'
import router from './router'
import Alert from './components/utils/Alert.vue'

Vue.use(Vuetify)
Vue.component('app-alert', Alert)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App },

  // locate user before creation
  beforeCreate () {
    this.$store.dispatch('initGeolocation')
    .then((position) => {
      localStorage.setItem('coords-lat', position.coords.latitude)
      localStorage.setItem('coords-lon', position.coords.longitude)
      this.$store.commit('setGeolocation')
    })
    .catch((err) => {
      console.error(err.message)
    })
  },
  mounted () {
    // check if token is valid (implies logged in user)
    if (new Date().getTime() < JSON.parse(localStorage.getItem('expires_at'))) {
      this.$store.commit('authenticate')
    } else {
      this.$store.commit('unauthenticate')
    }
  }
})
