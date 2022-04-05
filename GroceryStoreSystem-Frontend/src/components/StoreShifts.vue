<template>
  <div id="Store_Shits">
    <div id="header">
      <h2 style="margin-top:20px">Store Shifts: </h2>
    </div>
    <div id="page-body">
      <div id="shifts-body">

        <h3>Shift List: </h3>

        <table class="table">
          <thead>
            <tr>
              <th scope="col">Employee ID</th>
              <th scope="col">Shift ID</th>
              <th scope="col">Shift Date</th>
              <th scope="col">Shift Start Time</th>
              <th scope="col">Shift End Time</th>
              <th scope="col">Shift Status</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="shift in shifts">

              <td v-if="shift.employeeDto !== null">{{shift.employeeDto.id}}</td>
              <td v-else> - </td>

              <td>{{shift.id}}</td>
              <td>{{shift.date}}</td>
              <td>{{shift.startTime}}</td>
              <td>{{shift.endTime}}</td>
              <td>{{shift.shiftStatus}}</td>
              <td></td>
            </tr>
          </tbody>
        </table>

        <button type="button" class="btn btn-primary" @click="redirect_create()">Add Shift</button>
        <button type="button" class="btn btn-primary" @click="redirect_update()">Update Shift</button>
        <button type="button" class="btn btn-primary" @click="redirect_update_status()">Update Shift Status</button>

      </div>



      <div id="employees-body" style="margin-top: 50px">

        <h3>Employees List: </h3>

        <table class="table">
          <thead>
          <tr>
            <th scope="col">Employee ID</th>
            <th scope="col">Employee First Name</th>
            <th scope="col">Employee Last Name</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="employee in employees">
            <td>{{employee.id}}</td>
            <td>{{employee.firstName}}</td>
            <td>{{employee.lastName}}</td>
          </tr>
          </tbody>
        </table>
      </div>



      <h3 v-if="error" style="color:red;">{{error}}</h3>

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
  name: "store_shifts",

  data() {
    return {
      shifts: [],
      employees: [],
      response: [],
      error: ''
    }
  },

  methods: {
    redirect_create: function() {
      this.$router.push('/addshift')
    },

    redirect_update: function() {
      this.$router.push('/updateshiftemp')
    },

    redirect_update_status: function() {
      this.$router.push('/updateshiftstatus')
    },

    load_shifts: function() {
      AXIOS.get(backendUrl + '/shifts').then(response => {
          this.shifts = response.data
        }
      )
      .catch(msg => {
        this.error = msg.response.data;
        console.log(this.error)
      })
    },

    load_employees: function() {
      AXIOS.get(backendUrl + '/employees').then(response => {
          this.employees  = response.data
        }
      )
        .catch(msg => {
          this.error = msg.response.data;
          console.log(this.error)
        })
    }
  },

   beforeMount() {
    this.load_shifts()
    this.load_employees()
  }
}

</script>

<style scoped>

</style>
