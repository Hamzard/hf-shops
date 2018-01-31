import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: `http://localhost:8088/api`,
  auth: {
    username: 'my-trusted-client',
    password: 'secret'
  }
})
