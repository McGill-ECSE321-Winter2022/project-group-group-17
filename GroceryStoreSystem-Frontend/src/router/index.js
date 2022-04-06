import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import EmployeeLogin from '@/components/EmployeeLogin'
import OwnerLogin from '@/components/OwnerLogin'
import CustomerLogin from '@/components/CustomerLogin'
import EditCustomerProfile from '@/components/EditCustomerProfile'
import EditEmployeeProfile from '@/components/EditEmployeeProfile'
import CreateEmployee from '@/components/CreateEmployee'
import Inventory from '@/components/Inventory'
import Checkout from '@/components/Checkout'
import Cart from '@/components/Cart'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },


    {
      path: '/loginemployee',
      name: 'EmployeeLogin',
      component: EmployeeLogin
    },

    {
      path: '/loginowner',
      name: 'OwnerLogin',
      component: OwnerLogin,
    },
    {
      path: '/logincustomer',
      name: 'CustomerLogin',
      component: CustomerLogin,
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
    }


  ]
})
