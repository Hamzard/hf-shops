export default {
  state: {
    GEOLOCATION: null
  },
  getters: {
    geolocation (state) {
      return state.GEOLOCATION
    }
  },
  mutations: {
    setGeolocation (state) {
      state.GEOLOCATION = {
        lon: localStorage.getItem('coords-lon'),
        lat: localStorage.getItem('coords-lat')
      }
    }
  },
  actions: {
    initGeolocation ({commit}) {
      var options = {
        enableHighAccuracy: true,
        timeout: 10 * 1000 * 1000
      }
      return new Promise(function (resolve, reject) {
        navigator.geolocation.watchPosition(resolve, reject, options)
      })
    }
  }
}
