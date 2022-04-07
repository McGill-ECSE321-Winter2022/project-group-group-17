<template>

<div class="container-fluid">

<div class="container">
  <!-- Title -->
  <div class="d-flex justify-content-between align-items-center py-3">
    <h2 class="h5 mb-0"><a href="#" class="text-muted"></a> Order #2</h2>
  </div>

  <!-- Main content -->
  <div class="row">
    <div class="col-lg-8">
      <!-- Details -->
      <div class="card mb-4">
        <div class="card-body">
          <div class="mb-3 d-flex justify-content-between">
            <div>
              <span class="me-3">Ordered on: March 1, 2022</span>
              <span class="badge rounded-pill bg-info">SHIPPING</span>
            </div>
            <div class="d-flex">
              <div class="dropdown">
                <button class="btn btn-link p-0 text-muted" type="button" data-bs-toggle="dropdown">
                  <i class="bi bi-three-dots-vertical"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#"><i class="bi bi-pencil"></i> Edit</a></li>
                  <li><a class="dropdown-item" href="#"><i class="bi bi-printer"></i> Print</a></li>
                </ul>
              </div>
            </div>
          </div>
          <table class="table table-borderless">
            <tbody>
              <tr>
                <td>
                  <div class="d-flex mb-2">
                    <div class="flex-lg-grow-1 ms-3">
                      <h6 class="medium mb-0"><a href="#" class="text-reset">Chocolate Bars</a></h6>
                      <span class="small">Quantity: 20 units @ 500g/unit</span>
                    </div>
                  </div>
                </td>
                <td>1</td>
                <td class="text-end">$120.23</td>
              </tr>
              <tr>
                <td>
                  <div class="d-flex mb-2">
                    <div class="flex-lg-grow-1 ms-3">
                      <h6 class="medium"><a href="#" class="text-reset">Cheese</a></h6>
                      <span class="small">Quantity: 30 units @ 300g/unit</span>
                    </div>
                  </div>
                </td>
                <td>1</td>
                <td class="text-end">$204.00</td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td colspan="2">Subtotal</td>
                <td class="text-end">$324.23</td>
              </tr>
              <tr>
                <td colspan="2">Shipping</td>
                <td class="text-end">$0.00</td>
              </tr>
              <tr class="fw-bold">
                <td colspan="2">TOTAL</td>
                <td class="text-end">$324.23</td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
      <!-- Payment -->
      <div class="card mb-4">
        <div class="card-body">
          <div class="row">
            <div class="col-lg-6">
              <h3 class="h6">Payment Details</h3>

              <p><span class="badge bg-success rounded-pill">PAID</span></p>
            </div>
            <div class="col-lg-6">
              <h3 class="h6">Billing address</h3>
              <address>
                <strong>Rui Du</strong><br>
                1234 Sherbrooke St, Suite 900<br>
                Montreal, QC H2D 3E4<br>
              </address>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-4">
        <!-- Shipping information -->
        <div class="card-body">
          <h3 class="h6">Shipping Information</h3>
          <hr>
          <h3 class="h6">Address</h3>
          <address>
            <strong>Rui Du</strong><br>
            1234 Sherbrooke St, Suite 900<br>
            Montreal, QC H2D 3E4<br>
          </address>
        </div>
      </div>
    </div>
  </div>
</div>

</template>

<style>

body{
    background:#eee;
}
.card {
    box-shadow: 0 20px 27px 0 rgb(0 0 0 / 5%);
}
.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid rgba(0,0,0,.125);
    border-radius: 1rem;
}
.text-reset {
    --bs-text-opacity: 1;
    color: inherit!important;
}
a {
    color: #5465ff;
    text-decoration: none;
}
</style>