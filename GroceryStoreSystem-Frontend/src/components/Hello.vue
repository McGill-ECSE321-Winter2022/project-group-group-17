<template>
  <div id="hello">
    <h2 style="font-size:70px;color:#00C2CB;padding:275px" class="font-weight-bold">Welcome to the Super Grocery Store!</h2>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#hello {
  background-image: url("../assets/background.jpg");
  background-size: 100%;
  width: 100%;
  height: 1000px;
  background-repeat: no-repeat;

  top: 0;
  left: 0;

  text-align-all: center;
}
</style>
