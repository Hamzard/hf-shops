import {AXIOS} from '../../http/http-common'

export default {
  state: {
    PAGE_SIZE: 12,
    PAGE: 0,
    featuredShops: [],
    nearby: [],
    preferred: []
  },
  getters: {
    preferredShops (state) {
      return state.preferred
    },
    nearbyShops (state) {
      return state.nearby
    },
    featuredShops (state) {
      return state.featuredShops
    }
  },
  mutations: {
    like (state, payload) {
      const index = state.nearby.indexOf(payload)
      if (index !== -1) {
        state.nearby.splice(index, 1)
        state.preferred.push(payload)
      }
    },
    dislike (state, payload) {
      const index = state.nearby.indexOf(payload)
      if (index !== -1) {
        state.nearby.splice(index, 1)
      }
    },
    remove (state, payload) {
      const index = state.preferred.indexOf(payload)
      if (index !== -1) {
        state.preferred.splice(index, 1)
      }
    },
    setPreferredShops (state, payload) {
      state.preferred = payload
    },
    setNearbyShops (state, payload) {
      state.nearby = state.nearby.concat(payload)
      state.PAGE += 1
    },
    setFeaturedShops (state, payload) {
      state.featuredShops = payload
    }
  },
  actions: {
    loadPreferredShops ({commit, state}) {
      let token = localStorage.getItem('access_token')
      AXIOS.get('/preferred', {
        params: {
          access_token: token
        }
      }).then(({data}) => {
        if (data.length > 0) {
          commit('setPreferredShops', data)
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    loadNearbyShops ({commit, state}) {
      let token = localStorage.getItem('access_token')
      let lon = localStorage.getItem('coords-lon')
      let lat = localStorage.getItem('coords-lat')
      return new Promise((resolve, reject) => {
        AXIOS.get('/nearby', {
          params: {
            lat: lat,
            lon: lon,
            s: state.PAGE_SIZE,
            p: state.PAGE,
            access_token: token
          }
        }).then(({data}) => {
          if (data.length > 0) {
            commit('setNearbyShops', data)
          }
          resolve(data)
        }).catch((error) => {
          reject(error)
        })
      })
    },
    loadFeaturedShops ({commit, state}) {
      AXIOS.get('/public').then(({data}) => {
        commit('setFeaturedShops', data)
      }).catch((error) => {
        console.log('No shops are liked at the moment - ' + error)
      })
    },
    like: ({commit}, payload) => {
      let token = localStorage.getItem('access_token')
      AXIOS({
        method: 'put',
        url: '/preferred/' + payload.id,
        params: {
          access_token: token
        }
      }).then((response) => {
        commit('like', payload)
      }).catch((error) => {
        console.log(error)
      })
    },
    dislike: ({commit}, payload) => {
      let token = localStorage.getItem('access_token')
      AXIOS({
        method: 'delete',
        url: '/nearby/' + payload.id,
        params: {
          access_token: token
        }
      }).then((response) => {
        commit('dislike', payload)
      }).catch((error) => {
        console.log(error)
      })
    },
    remove: ({commit}, payload) => {
      let token = localStorage.getItem('access_token')
      AXIOS({
        method: 'delete',
        url: '/preferred/' + payload.id,
        params: {
          access_token: token
        }
      }).then((response) => {
        commit('remove', payload)
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
