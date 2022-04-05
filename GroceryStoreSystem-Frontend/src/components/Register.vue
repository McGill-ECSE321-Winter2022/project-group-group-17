<template>
  <form action="action_page.php">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" required>

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
    <hr>

    <button type="submit" class="registerbtn">Register</button>
  </div>

  <div class="container signin">
    <p>Already have an account? <a href="#">Sign in</a>.</p>
  </div>
</form>
</template>

<script>
// export default {
//   name: 'hello',
//   data () {
//     return {
//       msg: 'Welcome to Your Vue.js App'
//     }
//   }
// }
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
var local = null
export default {
    name: 'SignUpCustomer',
    data() {
        return {
            errorCreating: '',
            firstname: '',
            lastname: '',
            address: '',
            is_local: '',
            local
        }
    },
    methods: {
        EnterLoginCredentialInfo: function(firstname, lastname, address){
            local = new Boolean(document.getElementById('isLocal').checked);
            console.log(document.getElementById('isLocal').checked)
            if(local == false){
                AXIOS.post('/customers/'.concat(firstname), {}, {
                    params: {
                        lastname: lastname,
                        address: address,
                        is_local: local
                    }
                })
                .then(response => {
                    this.errorCreating = ""
                    this.$router.push('/Payment')

                })
                .catch(e => {
                    var errorMsg = e
                    this.errorCreating = errorMsg.response.data
                    console.log(errorMsg.response.data)
                })
            }
            else{
                AXIOS.post('/customers/'.concat(firstname), {}, {
                    params: {
                        lastname: lastname,
                        address: address,
                        is_local: local
                    }
                })
                .then(response => {
                    this.errorCreating = ""
                    this.$router.push('/CreateLoginCredential')
                })
                .catch(e => {
                    var errorMsg = e
                    this.errorCreating = errorMsg.response.data
                    console.log(errorMsg.response.data)
                })
            }
        },
        BackToLogin(){
            this.$router.push('/LoginCustomer')
        }
    }
}
</script>

<style scoped>
    * {box-sizing: border-box}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn {
  background-color: #00C2CB;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity:1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>