import axios from 'axios'
import JQuery from 'jquery'

let $ = JQuery
const config = require('../../config')

const frontendUrl = 'http://' + config.build.host
const backendUrl = 'http://' + config.build.backendHost

const AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: "store_shifts",

  data() {
    return {

    }
  },

  methods: {

  }
}
