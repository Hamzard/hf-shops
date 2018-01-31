import Vue from 'vue'
import Router from 'vue-router'
import PreferredShops from '@/components/shop/PreferredShops'
import NearbyShops from '@/components/shop/NearbyShops'
import Login from '@/components/user/Login'
import Home from '@/components/Home'
import Register from '@/components/user/Register'
import AuthGuard from './auth-guard'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/nearby',
      name: 'NearbyShops',
      component: NearbyShops,
      beforeEnter: AuthGuard
    },
    {
      path: '/preferred',
      name: 'PreferredShops',
      component: PreferredShops,
      beforeEnter: AuthGuard
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/',
      name: 'home',
      component: Home
    }
  ]
})
