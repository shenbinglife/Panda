import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import User from './components/User.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path:'/users',
      name:'user',
      component: User
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }, {
      path: '/index',
      name: 'index',
      component: () => import('./views/Index.vue'),
      children: [
        {
          path: '/index/list',
          name: 'list',
          component: () => import('./components/HelloWorld.vue')
        }
      ]
    }
  ]
})
