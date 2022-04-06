<template>
  <div id="Add_Shift">
    <div class="mainFrame">

      <div>
        <h2><b>Add Employee Shift</b></h2>
      </div>

      <br>

      <div class="names">
        <label><b>Shift Information: </b></label>
        <br>
        <br>
        <input type="date" v-model='date' id="date" placeholder="Shift Date" class="form-control" required>
        <br>
        <br>
        <input type="time" v-model="starttime" id="starttime" placeholder="Start Time" class="form-control" required>
        <br>
        <br>
        <input type="time" v-model="endtime" id="endtime" placeholder="End Time" class="form-control" required>
        <br>
        <br>
        <input type="text" v-model="shiftstatus" id="shiftstatus" placeholder="Shift Status" class="form-control" required>
        <br>
        <br>
      </div>

      <div class="buttonPersonalFrame inputPersLabel">
        <button class="btn-cancel" @click="cancel">Back
        </button>
        <button class="btn-success" @click="addShift(date, starttime, endtime, shiftstatus)">Create Shift
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
  name: 'add_shift',

  data () {
    return {
      error: '',
      success: '',
      response: [],
    }
  },

  methods : {
    addShift: function(date, starttime, endtime, shiftstatus) {
      if (document.getElementById('date').value === '') {
        this.error = "Please enter a valid date"
      }
      else if(document.getElementById('starttime').value === '') {
        this.error = "Please enter a valid start time"
      }
      else if(document.getElementById('endtime').value === '') {
        this.error = "Please enter a valid end time"
      }
      else if(document.getElementById('shiftstatus').value === '') {
        this.error = "Please enter a valid shift status"
      }
      else {
        AXIOS.post(backendUrl + '/shift/create?date=' + date + "&starttime=" + starttime + '&endtime=' + endtime +
          '&status=' + shiftstatus).then(response => {
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

    //Push to URL
    cancel: function() {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
  #Add_Shift {
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
