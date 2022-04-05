import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import EmployeeLogin from '@/components/EmployeeLogin'
import OwnerLogin from '@/components/OwnerLogin'
import CustomerLogin from '@/components/CustomerLogin'
import Register from '@/components/Register'
import EditCustomerProfile from '@/components/EditCustomerProfile'
import CreateEmployee from '@/components/CreateEmployee'


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
      path: '/register',
      name: 'Register',
      component: Register,
    },
    {
      path: '/customer/update',
      name: 'EditCustomerProfile',
      component: EditCustomerProfile,
    }, 
    {
      path: '/employee/create',
      name: 'CreateEmployee',
      component: CreateEmployee,
    }
  ]
})
