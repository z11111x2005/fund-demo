import Vue from 'vue'
import VueRouter from 'vue-router'
import layout from '@/views/layout'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: layout,
    redirect: '/index',
    name: '基金信息',
    children: [
      {
        name: '首页',
        path: '/index',
        icon: 'icon-shouye',
        component: () => import('@/views/Home/index.vue')
      },
      {
        name: '实时数据',
        path: '/fund-real-time',
        icon: 'icon-jijin',
        component: () => import('@/views/fund/FundRealTimeList.vue')
      },
      {
        name: '基金分组',
        path: '/fund-group',
        icon: 'icon-fenzu',
        component: () => import('@/views/fund/FundGroup.vue')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/Login.vue')
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    const user = window.localStorage.getItem('user')
    if (!user) {
      next('/login')
    } else {
      next()
    }
  }
})

export default router
