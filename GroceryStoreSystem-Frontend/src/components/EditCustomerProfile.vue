<template>
  <div class="container">
    <div class="row gutters">
      <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
        <div class="card h-100">
          <div class="card-body">
            <div class="account-settings">

              </div>
              <div class="about">
                <h5>Your Profile</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
        <div class="card h-100">
          <div class="card-body">
            <div class="row gutters">
              <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <h6 class="mb-2 text-primary">Personal Details</h6>
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">

              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="password">New Password</label>
                  <input
                    type="text"
                    class="form-control"
                    id="password"
                    placeholder="Enter new password"
                  />
                </div>
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="password">Confirm password</label>
                  <input
                    type="url"
                    class="form-control"
                    id="password"
                    placeholder="Re enter password"
                  />
                </div>
              </div>
            </div>
            <div class="row gutters">
              <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <h6 class="mt-3 mb-2 text-primary">Update Address</h6>
              </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="Street Number">Address Number</label>
                  <input
                    type="number"
                    class="form-control"
                    id="AddressNum"
                    placeholder="Enter Address Number"
                  />
                </div>
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="Street">Street</label>
                  <input
                    type="name"
                    class="form-control"
                    id="Street"
                    placeholder="Enter Street"
                  />
                </div>
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="ciTy">City</label>
                  <input
                    type="name"
                    class="form-control"
                    id="ciTy"
                    placeholder="Enter City"
                  />
                </div>
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="sTate">Province</label>
                  <input
                    type="text"
                    class="form-control"
                    id="sTate"
                    placeholder="Enter Province"
                  />
                </div>
              </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="cOuntry">Country</label>
                  <input
                    type="text"
                    class="form-control"
                    id="cOuntry"
                    placeholder="Enter Country"
                  />
                </div>
              </div>
              <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                <div class="form-group">
                  <label for="zIp">Postal</label>
                  <input
                    type="text"
                    class="form-control"
                    id="zIp"
                    placeholder="Postal Code"
                  />
                </div>
              </div>
            </div>
            <div class="row gutters">
              <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="text-right">
                  <button
                    type="button"
                    id="submit"
                    name="submit"
                    class="btn btn-secondary"
                  >
                    Cancel
                  </button>
                  <button
                    type="button"
                    id="submit"
                    name="submit"
                    class="btn btn-primary"
                  >
                    Update
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  
</template>

<script>
export default {
  name: "editCustprofile",
  data() {
    return {
      msg: "Your Customer Profile",
    };
  },
};
</script>

<style>
body {
  margin: 0;
  padding-top: 40px;
  color: #2e323c;
  background: #f5f6fa;
  position: relative;
  height: 100%;
}
.account-settings .user-profile {
  margin: 0 0 1rem 0;
  padding-bottom: 1rem;
  text-align: center;
}
.account-settings .user-profile .user-avatar {
  margin: 0 0 1rem 0;
}
 .account-settings .user-profile .user-avatar img {
  width: 90px;
  height: 90px;
  -webkit-border-radius: 100px;
  -moz-border-radius: 100px;
  border-radius: 100px;
} 
.account-settings .user-profile h5.user-name {
  margin: 0 0 0.5rem 0;
}
.account-settings .user-profile h6.user-email {
  margin: 0;
  font-size: 0.8rem;
  font-weight: 400;
  color: #9fa8b9;
} 
.account-settings .about {
  margin: 2rem 0 0 0;
  text-align: center;
}
.account-settings .about h5 {
  margin: 0 0 15px 0;
  color: #00C2CB;
}
.account-settings .about p {
  font-size: 0.825rem;
}
.form-control {
  border: 1px solid #cfd1d8;
  -webkit-border-radius: 2px;
  -moz-border-radius: 2px;
  border-radius: 2px;
  font-size: 0.825rem;
  background: #ffffff;
  color: #2e323c;
}

.card {
  background: #ffffff;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  border: 0;
  margin-bottom: 1rem;
}
</style>