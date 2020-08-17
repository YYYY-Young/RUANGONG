import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: {
            id: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).id,
            username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username,
            password: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).password,
            salt: '',
            phone: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).phone,
            email: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).email,
            enabled: '',
            code: '',
            roles: []
        },

    },
    mutations: {
        initAdminMenu(state, menus) {
            state.adminMenus = menus
        },
        login(state, data) {
            state.user = data
            window.localStorage.setItem('user', JSON.stringify(data))

        },
        setuid(state, data) {
            state.uid = data
            window.localStorage.setItem('uid', JSON.stringify(data))
        },
        logout(state) {
            // 注意不能用 null 清除，否则将无法判断 user 里具体的内容
            state.username = ''
            window.localStorage.removeItem('username')
            state.adminMenus = []
        },

    },
    actions: {}
})