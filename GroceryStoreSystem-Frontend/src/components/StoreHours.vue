<template>
  <div id="Store_Hours">
    <div id="header">
      <h2 style="margin-top:20px">Store Information: </h2>
    </div>

    <div id="page-body">
      <div id="storehours-body">
        <h3>Store Hours: </h3>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Start Time</th>
                <th scope="col">End Time</th>
                <th scope="col">Weekday</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="event in storehours">
                <td scope="col">{{event.storeHourID}}</td>
                <td scope="col">{{event.startTime}}</td>
                <td scope="col">{{event.endTime}}</td>
                <td scope="col">{{event.weekday}}</td>
              </tr>
            </tbody>
          </table>

          <button type="button" class="btn btn-primary" @click="redirect_create()">Add Store Hour</button>
          <button type="button" class="btn btn-primary" @click="redirect_update()">Update Store Hour</button>
      </div>
      <div id="specialday-body">
        <h3>Special Days</h3>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Start Date</th>
                <th scope="col">Start Time</th>
                <th scope="col">End Date</th>
                <th scope="col">End Time</th>
              </tr>
            </thead>
            <tbody>
            <tr v-for="sday in specialdays">
              <td scope="col">{{sday.id}}</td>
              <td scope="col">{{split_date(sday.startTimestamp)}}</td>
              <td scope="col">{{split_time(sday.startTimestamp)}}</td>
              <td scope="col">{{split_date(sday.endTimestamp)}}</td>
              <td scope="col">{{split_time(sday.endTimestamp)}}</td>
            </tr>
            </tbody>
          </table>

        <button type="button" class="btn btn-primary" @click="redirect_create_sday()">Add Special Day</button>
        <button type="button" class="btn btn-primary" @click="redirect_upadte_sday()">Update Special Day</button>
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
  name: "store_hour",

  data() {
    return {
      error: '',
      response: [],
      storehours: [],
      specialdays: [],

    }
  },

  methods: {
    redirect_create: function() {
      this.$router.push('/addstorehour')
    },
    redirect_update: function() {
      this.$router.push('/updatestorehour')
    },

    redirect_create_sday: function() {
      this.$router.push('/addspecialday')
    },

    redirect_upadte_sday: function() {
      this.$router.push('/updatespecialday')
    },

    split_date: function(str) {
      let date = str.split("T")

      return date[0]
    },

    split_time: function(str) {
      let time = str.split("T")

      return time[1]
    }
  },

  beforeMount() {
    AXIOS.get(backendUrl + '/storehours').then(response => {
        this.storehours = response.data
      }
    )
    .catch(msg => {
      this.error = msg.response.data;
      console.log(this.error)
    })

    AXIOS.get(backendUrl + '/specialdays').then(response => {
        this.specialdays  = response.data
      }
    )
    .catch(msg => {
      this.error = msg.response.data;
      console.log(this.error)
    })
  }
}
</script>

<style scoped>
  #storehours-body {
    margin-top: 50px;
  }
  #specialday-body {
    margin-top: 50px;
  }
</style>
