<template>
  
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Inventory Management</h1>
          <a href="#" class="btn btn-primary my-2">Update Item</a>
          <a href="#" class="btn btn-secondary my-2">Create New Item</a>
        
      </div>
    </div>
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