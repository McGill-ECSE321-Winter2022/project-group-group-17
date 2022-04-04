<template>
  <div id="Update_Status">
    <div class="mainFrame">

      <div>
        <h2><b>Update Shift Status</b></h2>
      </div>

      <br>

      <div class="names">
        <label><b>Shift Information: </b></label>
        <br>
        <br>
        <input type="text" v-model='shiftID' id="shiftID" placeholder="Shift ID" class="form-control" required>
        <br>
        <br>
        <input type="text" v-model="shiftstatus" id="status" placeholder="Employee ID" class="form-control" required>
        <br>
        <br>
      </div>

      <div class="buttonPersonalFrame inputPersLabel">
        <button class="btn-cancel" @click="cancel">Back
        </button>
        <button class="btn-success" @click="updateShiftStatus(shiftID, shiftstatus)">Create Shift
        </button>
      </div>


      <span v-if="error" style="color: red">Error: {{error}}</span>
      <span v-if="success" style="color: green">Added New Author: {{success}}</span>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
const config = require('../../config')

let frontendUrl;
let backendUrl;

if (process.env.NODE_ENV === 'production') {
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
  name: "update_shift_status",

  data() {
    return {
      error: '',
      success: '',
      response: [],
    }
  },

  methods: {
    updateShiftStatus: function(status, shift_id) {
      if(document.getElementById("shiftID").value === '') {
        this.error = 'Please provide a valid Shift ID'
      }
      else if(document.getElementById("status").value === '') {
        this.error = 'Please provide a valid status'
      }
      else {
        AXIOS.post(backendUrl + '/shift/update/status?shiftID=' + shift_id + "&status=" + status).then(response => {
          this.response = response.data
          this.success = response.data.id
          console.log(response)
        }).catch(msg => {
          console.log(msg.response.data)
          console.log(msg.response.status)
          this.error = msg.response.data;
        })
      }
    },

    cancel: function() {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
#Update_Status {
  background-image: linear-gradient(to bottom right, #3eadcf, #abe9cd);
  padding-bottom: 200px;
}
.names {
  width: 24.75%;
  min-width: 400px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  padding-top: 2vh;
}
.btn-success {
  border-radius: 60px;
  border: None;
  width: 150px;
  height: 55px;
  color: #FDEDEC;
  background-color: #03a634;
}
.btn-cancel {
  border-radius: 60px;
  border: None;
  width: 150px;
  height: 55px;
  color: #FDEDEC;
  background-color: #ab0303;
}
</style>
