import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
