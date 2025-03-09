import { createRouter, createWebHistory } from 'vue-router'
import BaseLayout from "../components/layout/BaseLayout.vue";
import ApiView from "../views/apistruct/ApiView.vue";
import AdminView from "../views/admin/AdminView.vue";
import AboutView from "../views/about/AboutView.vue";
import FoldersView from "../views/folders/FoldersView.vue";
import LoginAdminView from "../views/admin/LoginAdminView.vue";
import {redirectAuthAdmin, requireAuthAdmin} from "../composables/guards/AuthGuard.js";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: BaseLayout,
      children: [
        {
          path: '',
          name: 'home',
          component: FoldersView
        },
        {
          path: '/Login',
          name: 'login',
          component: LoginAdminView,
          beforeEnter: redirectAuthAdmin
        },
        {
          path: '/Admin',
          name: 'admin',
          component: AdminView,
          beforeEnter: requireAuthAdmin
        },
        {
          path: '/About',
          name: 'about',
          component: AboutView
        },
        {
          path: '/Api',
          name: 'api',
          component: ApiView,
          beforeEnter: requireAuthAdmin
        }
      ]
    }
  ]
})

export default router
