import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css.styl'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.prototype.$success = function() {
  this.$message({
    duration:1000,
    showClose: true,
    type: 'success',
    message: '操作成功'
  });
}
Vue.prototype.$failed = function () {
  this.$message({
    type:'error',
    duration:1000,
    showClose: true,
    message: '操作失败'
  });
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

