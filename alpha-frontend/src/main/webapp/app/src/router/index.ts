import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "@/components/security/Login.vue";
import Admin from "@/components/security/Admin.vue";
import BaseLayout from "../components/sructure/BaseLayout.vue";
import ApiStruct from "../components/sructure/apistruct/ApiStruct.vue";

function requireAuth(to: any, from: any, next: any) {
  const token = localStorage.getItem('token');
  if (!token) {
    next('/login');
  } else {
    next();
  }
}

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
        }
      ]
    },
  ],
})

export default router
