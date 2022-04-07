<template>
  <div id="Update_StoreHour">
    <div class="mainFrame">

      <div>
        <h2><b>Update Store Hour</b></h2>
      </div>

      <br>

      <div class="names">
        <label><b>Store Hour Information: </b></label>
        <br>
        <br>
        <input type="text" v-model='storeHourID' id="storeHourID" placeholder="Store Hour ID" class="form-control" required>
        <br>
        <br>
        <input type="text" v-model='starttime' id="starttime" placeholder="Start Time" class="form-control" required>
        <br>
        <br>
        <input type="text" v-model="endtime" id="endtime" placeholder="End Time" class="form-control" required>
        <br>
        <br>
      </div>

      <div class="buttonPersonalFrame inputPersLabel">
        <button class="btn btn-cancel" @click="cancel">Back
        </button>
        <button class="btn btn-success" @click="updateStoreHour(storeHourID, starttime, endtime)">Update Store Hour</button>
      </div>


      <span v-if="error" style="color: red; margin-top:20px; ">Error: {{error}}</span>
      <span v-if="success" style="color: green">Updated Store Hour: {{success}}</span>
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
  name: "update_store_hour",

  data() {
    return {
      error: '',
      success: '',
      response: [],
    }
  },

  methods: {
    updateStoreHour: function(storeHourID, starttime, endtime) {
      if(document.getElementById("storeHourID").value === '') {
        this.error = 'Please provide a valid Store Hour ID'
      }
      else if(document.getElementById("starttime").value === '') {
        this.error = 'Please provide a valid start time'
      }
      else if(document.getElementById("endtime").value === '') {
        this.error = 'Please provide a valid end time'
      }
      else {
        AXIOS.post(backendUrl + '/storehour/update?storeHourID=' + storeHourID + "&startTime=" + starttime
          + '&endTime=' + endtime).then(response => {
          this.response = response.data
          this.success = response.data.storeHourID
          console.log(response);
        }).catch(msg => {
          console.log(msg.response.data)
          console.log(msg.response.status)
          this.error = msg.response.data;
        })

      }
    },

    cancel: function() {
      this.$router.push('/storehours')
    }

  }
}
</script>

<style scoped>
#Update_StoreHour {
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
</style>
