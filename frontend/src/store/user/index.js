import {AXIOS} from '../../http/http-common'

export default{
  state: {
    isAuthenticated: false
  },
  getters: {
    isAuthenticated (state) {
      return state.isAuthenticated
    }
  },
  mutations: {
    authenticate (state) {
      state.isAuthenticated = true
    },
    unauthenticate (state) {
      state.isAuthenticated = false
    },
    logout (state) {
      state.isAuthenticated = false
      state.nearby = []
      state.featuredShops = []
      state.preferred = []
      state.GEOLOCATION = null
    }
  },
  actions: {
    register ({commit}, payload) {
      commit('setLoading', true)
      commit('clearError')
      return new Promise((resolve, reject) => {
        AXIOS({
          method: 'POST',
          url: '/register',
          data: {
            username: payload.email,
            password: payload.password
          }
        }).then(({data}) => {
          commit('setLoading', false)
          resolve(data)
        }).catch((error) => {
          commit('setLoading', false)
          commit('setError', error.response.data)
          reject(error)
        })
      })
    },
    login ({commit}, payload) {
      commit('setLoading', true)
      commit('clearError')
      var params = new URLSearchParams()
      params.append('grant_type', 'password')
      params.append('username', payload.email)
      params.append('password', payload.password)
      AXIOS({
        method: 'POST',
        url: '/oauth/token',
        data: params
      }).then((response) => {
        localStorage.setItem('access_token', response.data.access_token)
        localStorage.setItem('expires_at', new Date().getTime() + JSON.parse(response.data.expires_in) * 1000)
        commit('authenticate')
        commit('setLoading', false)
      }).catch((error) => {
        commit('setLoading', false)
        commit('setError', error.response.data)
      })
    },
    logout ({commit}) {
      localStorage.removeItem('access_token')
      localStorage.removeItem('expires_at')
      commit('logout')
    }
  }
}
