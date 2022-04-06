<template>
  <div class="container h-100">
    <div class="row h-100">
      <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
        <div class="d-table-cell align-middle">

          <div class="text-center mt-4">
            <h1 class="h2">Customer Registration</h1>
          </div>

          <div class="card">
            <div class="card-body">
              <div class="m-sm-4">
                  <div class="form-group">
                    <label>First Name</label>
                    <input class="form-control form-control-lg" type="text" name="name" id="firstname" v-model="firstname">
                  </div>
                  <div class="form-group">
                    <label>Last Name</label>
                    <input class="form-control form-control-lg" type="text" name="lastname" id="lastname" v-model="lastname">
                  </div>
                  <div class="form-group">
                    <label>Email</label>
                    <input class="form-control form-control-lg" type="email" name="email" id="email" v-model="email">
                  </div>
                  <div class="form-group">
                    <label>Password</label>
                    <input class="form-control form-control-lg" type="password" name="password" id="password" v-model="password">
                  </div>
                  <div class="text-center mt-3">
                    <button type="button" class="btn btn-lg btn-primary" @click="register_user(firstname, lastname, email, password)">Sign up</button>
                  </div>
              </div>
            </div>
          </div>

          <h3 v-if="error">{{error}}</h3>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
const config = require('../../config')

let frontendUrl;
let backendUrl;

if (process.env.NODE_ENV === "production") {
  console.log("prod env")
  frontendUrl = 'https://' + config.build.host + ':' + config.build.port
  backendUrl = 'https://' + config.build.backendHost + ':' + config.build.backendPort

} else if (process.env.NODE_ENV === "development") {
  console.log("dev env")
  frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
  backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
}

const AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: "register",

  data() {
    return {
      response: [],
      success: '',
      error: ''
    }
  },

  methods: {
    register_user: function(firstname, lastname, email, password) {
      if (document.getElementById('firstname').value === '') {
        this.error = 'Please provide a valid first name'
      } else if (document.getElementById('lastname').value === '') {
        this.error = 'Please provide a valid last name'
      } else if (document.getElementById('email').value === '') {
        this.error = 'Please provide a valid email'
      } else if (document.getElementById('password').value === '') {
        this.error = 'Please provide a valid password'
      } else {
        AXIOS.post(backendUrl + '/customer/create?firstName=' + firstname + '&lastName=' + lastname + '&email=' + email + '&password='
          + password).then(response => {
          this.response = response.data
          this.success = response.data.id
        })
          .catch(msg => {
            console.log(msg.response.data)
            console.log(msg.response.status)
            this.error = msg.response.data;
          })

        this.$router.push('/login')
      }
    }
  }
}



</script>

<style scoped>
body{
  margin-top:20px;
  background-color: #f2f3f8;
}
.card {
  margin-bottom: 1.5rem;
  box-shadow: 0 1px 15px 1px rgba(52,40,104,.08);
}

.card {
  position: relative;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border: 1px solid #e5e9f2;
  border-radius: .2rem;
}
</style>
