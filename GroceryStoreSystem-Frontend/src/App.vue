
<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #6BE138">
      <div class="container-fluid">
        <a class="navbar-brand font-weight-bold" href="#" style="font-size: 16px">SUPER GROCERY STORE</a>
      </div>
    </nav>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #00C2CB">
      <div class="container-fluid">
        <a class="navbar-brand" href="#" @click="home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">

            <li class="nav-item">
              <a class="nav-link" @click="browse">Browse</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="storehours">Store Hours</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="shifts">Shifts</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="orders">Orders</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="profile">Profile</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="inventory">Inventory</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="checkout">Checkout</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="login">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="register_customer">Register</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div id="body-content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import JQuery from "jquery";
const $ = JQuery();


export default {
  name: 'app',

  methods: {
    home: function() {
      this.$router.push('/')
    },

    shifts: function() {
      this.$router.push('/shifts')
    },

    storehours: function() {
      this.$router.push('/storehours')
    },

    browse: function() {
      this.$router.push('/browse')
    },

    orders: function() {
      this.$router.push('/myorders')
    },

    profile: function() {
      this.$router.push('/profile')
    },

    inventory: function() {
      this.$router.push('/inventory')
    },

    checkout: function() {
      this.$router.push('/checkout')
    },


    login: function() {
      this.$router.push('/login')
    },

    register_customer: function() {
      this.$router.push('/register')
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}

body {
  margin: 0;
  padding: 0;
}
</style>
