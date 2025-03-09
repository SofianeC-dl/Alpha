import {useAuthStore} from "../../stores/auth/AuthStore.js";

export function requireAuthAdmin(to, from, next) {
  const authStore = useAuthStore();

  if (!authStore.isAdmin) {
    next({name: 'login'})
  } else {
    next();
  }
}

export function redirectAuthAdmin(to, from, next) {
  const authStore = useAuthStore();

  if (authStore.isAdmin) {
    next({name: 'admin'})
  } else {
    next();
  }
}
