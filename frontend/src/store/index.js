import Vue from 'vue'
import Vuex from 'vuex'
import errorMsg from './error_msg'
import geoLocation from './geolocation'
import shop from './shop'
import user from './user'
Vue.use(Vuex)

export const store = new Vuex.Store({
  modules: {
    user: user,
    shop: shop,
    errorMsg: errorMsg,
    geoLocation: geoLocation
  }
})
