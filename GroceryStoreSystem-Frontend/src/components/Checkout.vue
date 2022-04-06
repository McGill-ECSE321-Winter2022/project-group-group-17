<template>
  <section class="h-100 h-custom" style="background-color: #FFFFFF">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12">
          <div
            class="card card-registration card-registration-2"
            style="border-radius: 15px"
          >
            <div class="card-body p-0">
              <div class="row g-0">
                <div class="col-lg-8">
                  <div class="p-5">
                    <div
                      class="
                        d-flex
                        justify-content-between
                        align-items-center
                        mb-5
                      "
                    >
                      <h1 class="fw-bold mb-0 text-black">Checkout</h1>
                      <h6 class="mb-0 text-muted">3 items</h6>
                    </div>
                    <hr class="my-4" />

                    <div
                      class="
                        row
                        mb-4
                        d-flex
                        justify-content-between
                        align-items-center
                      "
                    >
                      <div class="col-md-2 col-lg-2 col-xl-2">
                        <!-- <img
                          src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img5.webp"
                          class="img-fluid rounded-3"
                          alt="Cotton T-shirt"
                        /> -->
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-3">
                        <h6 class="text-muted">Vegetables</h6>
                        <h6 class="text-black mb-0">Carrots</h6>
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                        <button
                          class="btn btn-link px-2"
                          onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
                        >
                          <i class="fas fa-minus"></i>
                        </button>

                        <input
                          id="form1"
                          min="0"
                          name="quantity"
                          value="8"
                          type="number"
                          class="form-control form-control-sm"
                        />

                        <button
                          class="btn btn-link px-2"
                          onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
                        >
                          <i class="fas fa-plus"></i>
                        </button>
                      </div>
                      <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                        <h6 class="mb-0">$ 4.00</h6>
                      </div>
                      <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                        <a href="#!" class="text-muted"
                          ><i class="fas fa-times"></i
                        ></a>
                      </div>
                    </div>

                    <hr class="my-4" />

                    <div
                      class="
                        row
                        mb-4
                        d-flex
                        justify-content-between
                        align-items-center
                      "
                    >
                      <div class="col-md-2 col-lg-2 col-xl-2">
                        <!-- <img
                          src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img6.webp"
                          class="img-fluid rounded-3"
                          alt="Cotton T-shirt"
                        /> -->
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-3">
                        <h6 class="text-muted">Fruits</h6>
                        <h6 class="text-black mb-0">Apples</h6>
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                        <button
                          class="btn btn-link px-2"
                          onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
                        >
                          <i class="fas fa-minus"></i>
                        </button>

                        <input
                          id="form1"
                          min="0"
                          name="quantity"
                          value="6"
                          type="number"
                          class="form-control form-control-sm"
                        />

                        <button
                          class="btn btn-link px-2"
                          onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
                        >
                          <i class="fas fa-plus"></i>
                        </button>
                      </div>
                      <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                        <h6 class="mb-0">$ 4.00</h6>
                      </div>
                      <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                        <a href="#!" class="text-muted"
                          ><i class="fas fa-times"></i
                        ></a>
                      </div>
                    </div>

                    <hr class="my-4" />

                    <div
                      class="
                        row
                        mb-4
                        d-flex
                        justify-content-between
                        align-items-center
                      "
                    >
                      <div class="col-md-2 col-lg-2 col-xl-2">
                        <!-- <img
                          src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img7.webp"
                          class="img-fluid rounded-3"
                          alt="Cotton T-shirt"
                        /> -->
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-3">
                        <h6 class="text-muted">Fruits</h6>
                        <h6 class="text-black mb-0">Bananas</h6>
                      </div>
                      <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                        <button
                          class="btn btn-link px-2"
                          onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
                        >
                          <i class="fas fa-minus"></i>
                        </button>

                        <input
                          id="form1"
                          min="0"
                          name="quantity"
                          value="10"
                          type="number"
                          class="form-control form-control-sm"
                        />

                        <button
                          class="btn btn-link px-2"
                          onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
                        >
                          <i class="fas fa-plus"></i>
                        </button>
                      </div>
                      <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                        <h6 class="mb-0">$ 5.00</h6>
                      </div>
                      <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                        <a href="#!" class="text-muted"
                          ><i class="fas fa-times"></i
                        ></a>
                      </div>
                    </div>

                    <hr class="my-4" />

                    <div class="pt-5">
                      <h6 class="mb-0">
                        <a href="#!" class="text-body"
                          ><i class="fas fa-long-arrow-alt-left me-2"></i>Back
                          to shop</a
                        >
                      </h6>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 bg-grey">
                  <div class="p-5">
                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                    <hr class="my-4" />

                    <div class="d-flex justify-content-between mb-4">
                      <h5 class="text-uppercase">items 3</h5>
                      <h5>$ 13.00</h5>
                    </div>

                    <h5 class="text-uppercase mb-3">Shipping</h5>

                    <div class="mb-4 pb-2">
                      <select class="select">
                        <option value="1">Standard Delivery</option>
                        <option value="2">Pick-Up</option>
                        <!-- <option value="3">Three</option>
                        <option value="4">Four</option> -->
                      </select>
                    </div>



                    <hr class="my-4" />

                    <div class="d-flex justify-content-between mb-5">
                      <h5 class="text-uppercase">Total price</h5>
                      <h5>$ 13.00</h5>
                    </div>

                    <button
                      type="button"
                      class="btn btn-dark btn-block btn-lg"
                      data-mdb-ripple-color="dark"
                    >
                      Purchase
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "checkout",
  data() {
    return {
      msg: "Checkout",
    };
  },
};
</script>

<style>
@media (min-width: 1025px) {
  .h-custom {
    height: 100vh !important;
  }
}

.card-registration .select-input.form-control[readonly]:not([disabled]) {
  font-size: 1rem;
  line-height: 2.15;
  padding-left: 0.75em;
  padding-right: 0.75em;
}

.card-registration .select-arrow {
  top: 13px;
}

.bg-grey {
  background-color: #6BE138;
}

@media (min-width: 992px) {
  .card-registration-2 .bg-grey {
    border-top-right-radius: 16px;
    border-bottom-right-radius: 16px;
  }
}

@media (max-width: 991px) {
  .card-registration-2 .bg-grey {
    border-bottom-left-radius: 16px;
    border-bottom-right-radius: 16px;
  }
}
</style>