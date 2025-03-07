import {defineStore} from "pinia";
import {jwtDecode} from "jwt-decode";
import {SecurityEnum} from "../../composables/enum/SecurityEnum.js";

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('token') || null
  }),
  getters: {
    isAdmin(state): boolean {
      if (!state.token) return false;

      try {
        const decoded: any = jwtDecode(state.token);

        return decoded.role === SecurityEnum.ADMIN;
      } catch (error) {
        console.error('Erreur lors du décodage du token', error);
        return false;
      }
    }
  },
  actions: {
    setToken(newToken: string | null) {
      this.token = newToken;
      if (newToken) {
        localStorage.setItem('token', newToken);
      } else {
        localStorage.removeItem('token');
      }
    },
  },
})
