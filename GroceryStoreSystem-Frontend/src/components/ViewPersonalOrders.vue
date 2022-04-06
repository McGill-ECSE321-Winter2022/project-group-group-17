<template>


    <div class="container mt-5">
        <div class="d-flex justify-content-center row">
            <div class="col-md-10">
                <div class="rounded">
                    <div class="table-responsive table-borderless">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th class="text-center">
                                        <div class="toggle-btn">
                                            <div class="inner-circle"></div>
                                        </div>
                                    </th>
                                    <th>Order #</th>
                                    <th>Order Type</th>
                                    <th>Status</th>
                                    <th>Total Cost</th>
                                    <th>Ordered On</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody class="table-body">
                                <tr class="cell-1">
                                    <td class="text-center">
                                        <div class="toggle-btn">
                                            <div class="inner-circle"></div>
                                        </div>
                                    </td>
                                    <td>
                                        <button class="btn btn-link d-none d-lg-block btn-icon-text"> <span class="text">1</span></button>
                                    </td>
                                    <td>In-Store</td>
                                    <td><span class="badge badge-success">Completed</span></td>
                                    <td>$274.43</td>
                                    <td>March 23, 2022</td>
                                    <td><i class="fa fa-ellipsis-h text-black-50"></i></td>
                                </tr>
                                <tr class="cell-1">
                                    <td class="text-center">
                                        <div class="toggle-btn">
                                            <div class="inner-circle"></div>
                                        </div>
                                    </td>
                                    <td>
                                        <button class="btn btn-link d-none d-lg-block btn-icon-text"> <span class="text">2</span></button>
                                    </td>
                                    <td>Delivery</td>
                                    <td><span class="badge badge-info">Shipped</span></td>
                                    <td>$324.23</td>
                                    <td>March 1, 2022</td>
                                    <td><i class="fa fa-ellipsis-h text-black-50"></i></td>
                                </tr>
                                <tr class="cell-1">
                                    <td class="text-center">
                                        <div class="toggle-btn">
                                            <div class="inner-circle"></div>
                                        </div>
                                    </td>
                                    <td>
                                        <button class="btn btn-link d-none d-lg-block btn-icon-text"> <span class="text">3</span></button>
                                    </td>
                                    <td>Pickup</td>
                                    <td><span class="badge badge-danger">Ready for Pickup</span></td>
                                    <td>$123.00</td>
                                    <td>February 23, 2022</td>
                                    <td><i class="fa fa-ellipsis-h text-black-50"></i></td>
                                </tr>
                                <tr class="cell-1">
                                    <td class="text-center">
                                        <div class="toggle-btn">
                                            <div class="inner-circle"></div>
                                        </div>
                                    </td>
                                    <td>
                                        <button class="btn btn-link d-none d-lg-block btn-icon-text"> <span class="text">4</span></button>
                                    </td>
                                    <td>In-Store</td>
                                    <td><span class="badge badge-success">Completed</span></td>
                                    <td>$434.20</td>
                                    <td>January 11, 2022</td>
                                    <td><i class="fa fa-ellipsis-h text-black-50"></i></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>



export default {

    name: "PersonalOrders",

    data() {
        return {
            user: "Rui",
            orders: [
                {
                    orderNum: 1,
                    orderDate: "March 11, 2022",
                    orderType: "Delivery",
                    totalCost: 123.34,
                },
                {
                    orderNum: 2,
                    orderDate: "March 3, 2022",
                    orderType: "Pickup",
                    totalCost: 123.34,
                },
                {
                    orderNum: 3,
                    orderDate: "March 3, 2022",
                    orderType: "In-Store",
                    totalCost: 324.34,
                }
            ]
        }
    }

}



</script>
