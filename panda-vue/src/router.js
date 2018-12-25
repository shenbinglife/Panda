import Vue from 'vue'
import Router from 'vue-router'
import User from './views/user/User.vue'
import Template from './views/Template.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/users',
      name:'user',
      component: User
    },
    {
      path:'/templates',
      name:'template',
      component: Template
    }
  ]
})
