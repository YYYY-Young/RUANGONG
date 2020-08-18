import Vue from 'vue'
import VueRouter from 'vue-router'
import Workbench from '../components/Workbench.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import User from '../components/User.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        redirect:'/login'
    },
    {
        path: "/teameditor",
        name: "Teameditor",
        component: () =>
            import ('../components/TeamEditor.vue'),
    },
    {
        path: "/myteam",
        name: "MyTeam",
        component: () =>
            import ('../components/MyTeam.vue'),
    },
    {
        path: "/teamdetail",
        name: "TeamDetail",
        component: () =>
            import ('../components/TeamDetail.vue'),
    },
  
    {
        path: "/messagecenter",
        name: "MessageCenter",
        component: () =>
            import ('../components/MessageCenter.vue'),
    },

    {
        path: '/testbench',
        name: 'TestBench',
        component: () =>
        import ('../components/TestBench.vue'),

    },
    {
        path: '/recyclebin',
        name: 'RecycleBin',
        component: () =>
        import ('../components/RecycleBin.vue'),
    },
    {
        path: '/mycreate',
        name: 'MyCreate',
        component: () =>
        import ('../components/MyCreate.vue'),
    },
    {
        path: '/recenteditor',
        name: 'RecentEditor',
        component: () =>
        import ('../components/RecentEditor.vue'),
    },
    {
        path: '/comments',
        name: 'Comments',
        component: () =>
        import ('../components/Comments.vue'),
    },
    {
        path: '/recentreview',
        name: 'RecentReview',
        component: () =>
        import ('../components/RecentReview.vue'),
    },
    {
        path: '/collections',
        name: 'Collections',
        component: () =>
        import ('../components/Collections.vue'),
    },
    {
        path: '/menu',
        name: 'Menu',
        component: () =>
        import ('../components/menu.vue'),
    },
    {
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