<template>
  <div id="catalogue">
    <h2 style="margin-top:20px;" class="font-weight-bold">Store Products: </h2>

    <div class="card-group">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Apple</h5>
          <p class="card-text">Category: Fruit</p>
          <p class="card-text">Price: 3.1$/KG</p>
          <p class="card-text"><small class="text-muted">Number of available items: 15</small></p>
          <button type="button" class="btn btn-info">Add to cart</button>
        </div>
      </div>
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Pear</h5>
          <p class="card-text">Category: Fruit</p>
          <p class="card-text">Price: 2.2$/KG</p>
          <p class="card-text"><small class="text-muted">Number of available items: 25</small></p>
          <button type="button" class="btn btn-info">Add to cart</button>
        </div>
      </div>
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Peach</h5>
          <p class="card-text">Category: Fruit</p>
          <p class="card-text">Price: 1.1$/KG</p>
          <p class="card-text"><small class="text-muted">Number of available items: 4</small></p>
          <button type="button" class="btn btn-info">Add to cart</button>
        </div>
      </div>
    </div>

    <div class="card-group">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Meat</h5>
          <p class="card-text">Category: Perishables</p>
          <p class="card-text">Price: 23.1$/KG</p>
          <p class="card-text"><small class="text-muted">Number of available items: 2.5KG</small></p>
          <button type="button" class="btn btn-info">Add to cart</button>
        </div>
      </div>
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Chicken</h5>
          <p class="card-text">Category: Fruit</p>
          <p class="card-text">Price: 17.2$/KG</p>
          <p class="card-text"><small class="text-muted">Number of available items: 4.5KG</small></p>
          <button type="button" class="btn btn-info">Add to cart</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "browse"
}
</script>

<style scoped>

</style>
