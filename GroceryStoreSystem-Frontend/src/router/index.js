import Vue from 'vue'
import Router from 'vue-router'

import AddShift from '../components/AddShift';
import AddSpecialDay from '../components/AddSpecialDay';
import AddStoreHour from "../components/AddStoreHour";
import StoreHours from "../components/StoreHours";
import StoreShifts from "../components/StoreShifts";
import UpdateShift from "../components/UpdateShift";
import UpdateShiftStatus from "../components/UpdateShiftStatus";
import UpdateSpecialDay from "../components/UpdateSpecialDay";
import UpdateStoreHour from "../components/UpdateStoreHour";
import Hello from "../components/Hello";

Vue.use(Router)


const ourRouter  = new Router( {
  routes: [
    {
      path: '/',
      name: 'home',
      component: Hello
    },
    {
      path: '/addshift',
      name: 'add_shift',
      component: AddShift
    },
    {
      path: '/addspecialday',
      name: 'add_special_day',
      component: AddSpecialDay
    },
    {
      path: '/addstorehour',
      name: 'add_store_hour',
      component: AddStoreHour
    },
    {
      path: '/shifts',
      name: 'store_shifts',
      component: StoreShifts,
    },
    {
      path: '/storehours',
      name: 'store_hours',
      component: StoreHours
    },
    {
      path: '/updateshiftemp',
      name: 'update_shift',
      component: UpdateShift
    },
    {
      path: '/updateshiftstatus',
      name: 'update_shift_status',
      component: UpdateShiftStatus
    },
    {
      path: '/updatespecialday',
      name: 'update_special_day',
      component: UpdateSpecialDay
    },
    {
      path: '/updatestorehour',
      name: 'update_store_hour',
      component: UpdateStoreHour
    }
  ]
})

export default ourRouter;
