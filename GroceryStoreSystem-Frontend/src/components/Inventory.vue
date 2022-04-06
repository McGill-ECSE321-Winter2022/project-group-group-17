<template>
  
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Inventory Management</h1>
          <a href="#" class="btn btn-primary my-2">Update Item</a>
          <a href="#" class="btn btn-secondary my-2">Create New Item</a>
        
      </div>
    </div>

    <table class="table">
  <thead>
    <tr>
      <th scope="col">#ID</th>
      <th scope="col">Product</th>
      <th scope="col">Category</th>
      <th scope="col">Amount</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">001</th>
      <td>Apples</td>
      <td>Fruits</td>
      <td>100</td>
    </tr>
    <tr>
      <th scope="row">002</th>
      <td>Carrots</td>
      <td>Vegetables</td>
      <td>100</td>
    </tr>
    <tr>
      <th scope="row">003</th>
      <td>Fruit Loops Cereal</td>
      <td>Non-Perishables</td>
      <td>50</td>
    </tr>
    <tr>
      <th scope="row">004</th>
      <td>Bananas</td>
      <td>Fruits</td>
      <td>100</td>
    </tr>
    
  </tbody>
</table>
  </section>
  
</template>

<script>
export default {
  name: "inventory",
  data() {
    return {
      msg: "Manage Inventory",
    };
  },
};
</script>

<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
</style>