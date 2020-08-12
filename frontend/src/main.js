import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//编辑器和element的引用与注册
import mavonEditor from 'mavon-editor'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'mavon-editor/dist/css/index.css';
Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(ElementUI)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
