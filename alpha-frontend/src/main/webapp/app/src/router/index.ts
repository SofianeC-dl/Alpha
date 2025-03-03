import { createRouter, createWebHistory } from 'vue-router'
import BaseLayout from "../components/layout/BaseLayout.vue";
import ApiStruct from "../components/layout/apistruct/ApiStruct.vue";
import AdminView from "../views/admin/AdminView.vue";
import AboutView from "../views/about/AboutView.vue";

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
          component: ApiStruct
        },
        {
          path: '/Admin',
          name: 'admin',
          component: AdminView
        },
        {
          path: '/About',
          name: 'about',
          component: AboutView
        }
      ]
    }
  ]
})

export default router
