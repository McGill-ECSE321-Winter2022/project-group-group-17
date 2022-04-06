import Vue from 'vue'
import Router from 'vue-router'

import Hello from '@/components/Hello'
import EditCustomerProfile from '@/components/EditCustomerProfile'
import EditEmployeeProfile from '@/components/EditEmployeeProfile'
import CreateEmployee from '@/components/CreateEmployee'
import Inventory from '@/components/Inventory'
import Checkout from '@/components/Checkout'
import Cart from '@/components/Cart'
import Order from '@/components/Order'
import ViewPersonalOrders from '@/components/ViewPersonalOrders'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },

    {
      path: '/customer/update',
      name: 'EditCustomerProfile',
      component: EditCustomerProfile,
    }, 
    {
      path: '/employee/update',
      name: 'EditEmployeeProfile',
      component: EditEmployeeProfile,
    },
    {
      path: '/employee/create',
      name: 'CreateEmployee',
      component: CreateEmployee,
    }, 
    {
      path: '/inventory',
      name: 'Inventory',
      component: Inventory,
    }, 
    {
      path: '/checkout',
      name: 'Checkout',
      component: Checkout,
    },

    {
      path: '/cart',
      name: 'Cart',
      component: Cart,
    },
    {
      path: '/myorders',
      name: 'PersonalOrders',
      component: ViewPersonalOrders
    },
    {
      path: '/myorders/2',
      name: 'order',
      component: Order
    }


  ]
})
