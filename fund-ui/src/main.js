import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/font/iconfont.css'
// 导入全局样式表
import './assets/css/global.css'
import axios from 'axios'

// 导入Nprogress对应的JS和CSS
import Nprogress from 'nprogress'
import 'nprogress/nprogress.css'

// 配置请求根路径
axios.defaults.baseURL = 'http://192.168.1.125:7001/'
// 在request拦截器中,展示进度条
axios.interceptors.request.use(config => {
  Nprogress.start()
  return config
})
// 在response拦截器中,隐藏进度条
axios.interceptors.response.use(config => {
  Nprogress.done()
  return config
})
Vue.prototype.$http = axios

Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
