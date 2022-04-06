import Vue from 'vue'
import Router from 'vue-router'

import Hello from '@/components/Hello'
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
