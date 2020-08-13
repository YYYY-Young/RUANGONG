import Vue from 'vue'
import VueRouter from 'vue-router'
import Workbench from '../components/Workbench.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import User from '../components/User.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/workbench',
        name: 'Workbench',
        component: Workbench
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/User',
        name: 'User',
        component: User
    },
    {
        path: '/Register',
        name: 'Register',
        component: Register
    },
    {
        path: "/editor",
        name: "ArticleEditor",
        component: () =>
            import ('../components/ArticleEditor.vue'),

    },
    {
        path: "/articles",
        name: "Articles",
        component: () =>
            import ('../components/Articles.vue'),
    },
    {
        path: "/articledetail",
        name: "ArticleDetail",
        component: () =>
            import ('../components/ArticleDetail.vue'),
    },
    {
        path: "/articlechange",
        name: "ArticleChange",
        component: () =>
            import ('../components/ArticleChange.vue'),
    }

]

const router = new VueRouter({
    routes
})

export default router