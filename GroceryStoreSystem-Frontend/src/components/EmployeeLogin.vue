<template>
  <div id="LoginEmployee">
    <h2>Employee Login</h2>
    <div>
        <input style="margin-top:15px" type="text" v-model="email"  placeholder=" Email" required>
    </div>
        <input  style="margin-top:15px" type="password"  v-model="password"  placeholder=" Password" required>
    <div>
    </div>
    <div>
        <button style="margin-top:15px" @click="LoginEmployee(email,password)" class="btn btn-primary">Log in</button>
    </div>
    <div>
      <span v-if="errorEmployee" style="color:red">Error: {{errorEmployee}} </span>
    </div>
    <div div style="margin-top:20px">
      <strong> Don't have an account? Sign up!</strong>
    </div>
    <div style="margin-top:20px">
      <!-- <button @click="SignUpEmployee()" style="margin:25px; margin-left: 550px;" class="btn btn-primary"> Sign up </button> -->
      <a style="margin-left:10px"><router-link to="/">Main Page</router-link></a>
      <a style="margin-left:10px" @click="SignUpEmployee()"><router-link to="/">Sign Up</router-link></a>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
var config = require('../../config')

var backendConfigurer = function () {
    switch (process.env.NODE_ENV) {
      case 'development':
        return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
      case 'production':
        return 'https://' + config.build.backendHost + ':' + config.build.backendPort
    }
  }
  
  var frontendConfigurer = function () {
    switch (process.env.NODE_ENV) {
      case 'development':
        return 'http://' + config.dev.host + ':' + config.dev.port
      case 'production':
        return 'https://' + config.build.host + ':' + config.build.port
    }
  }
  
  var backendUrl = backendConfigurer()
  var frontendUrl = frontendConfigurer()

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'EmployeeLogin',

    data() {
        return {
          employee: '',
          email: '',
          password: '',
          errorEmployee: ''
        }
    },
    methods: {
        EmployeeLogin: function(email, password){
            AXIOS.post('/employee/login'.concat(email), {}, {
                params: {
                    password: password
                }
            })
            .then(response => {
                AXIOS.get('/employee/login')
                .then(response2 =>{
                    if(response2.data.head == false){
                        this.errorEmployee = ""
                        this.$router.push('/')
                    }
                    else {
                        console.log(response2.data)
                        this.errorEmployee = "According to our data you are the owner, please log in in the owner page"
                        AXIOS.post('/employee/logout')
                    }
                })
                
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg.response.data)
                this.errorEmployee = errorMsg.response.data
            })
        }
    }
}
</script>


<style scoped>
  #LoginEmployee {
    font-family: 'Varela Round', Helvetica, Arial, sans-serif;
    background: #9fdfcb;
    height: 100vh;
    margin: auto;
    width: 95vw;
    border: 3px solid rgb(78, 184, 78);
    padding: 10px;
  }
  input {
    text-align: center;
  }
</style>