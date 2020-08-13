import Vue from 'vue'
import VueRouter from 'vue-router'
import Workbench from '../components/Workbench.vue'
import Login1 from '../components/Login1.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Workbench',
    component: Workbench
  },
  {
    path: '/login1',
    name: 'Login1',
    component: Login1
  },
  {
    path: "/editor",
    name: "ArticleEditor",
    component: () => import('../components/ArticleEditor.vue'), 

  },
  {
    path: "/articles",
    name: "Articles",
    component: () => import('../components/Articles.vue'),     
},
{
  path: "/articledetail",
    name: "ArticleDetail",
    component: () => import('../components/ArticleDetail.vue'), 
}
]

const router = new VueRouter({
  routes
})

export default router
