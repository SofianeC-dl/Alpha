<script setup lang="ts">

import {
  AuthentificationApi,
  AuthentificationApiCreateAuthenticationTokenAndLoginRequest
} from "@/generated/index.js";
import {useCatch} from "@/composables/utils/api/ApiUtils.js";
import {MessageGlobalToastUtils} from "@/composables/utils/message/MessageGlobalUtils.js";
import {ref} from "vue";
import { useRouter } from 'vue-router';
import {useAuthStore} from "@/stores/auth/AuthStore.js";

const router = useRouter();
const authentificationApi: AuthentificationApi = new AuthentificationApi();

const username:  Ref<string, string> = ref('');
const password:  Ref<string, string> =  ref('');

const login = (() => {
  const authentificationApiCreateAuthenticationTokenAndLoginRequest: AuthentificationApiCreateAuthenticationTokenAndLoginRequest = {
    authRequest: {
      username: username.value,
      password: password.value
    }
  }
  useCatch(authentificationApi.createAuthenticationTokenAndLogin(authentificationApiCreateAuthenticationTokenAndLoginRequest).then((axiosData) => {
    MessageGlobalToastUtils.successMessage(`Connexion Réussi`, axiosData.data.token);
    const authStore: useAuthStore = useAuthStore();
    authStore.setToken(axiosData.data.token);
    router.push('/Admin');
  }));
});
</script>

<template>
  <div>
    <h2>Connexion</h2>
    <form @submit.prevent="login">
      <div>
        <label>Nom d'utilisateur:</label>
        <input type="text" v-model="username" />
      </div>
      <div>
        <label>Mot de passe:</label>
        <input type="password" v-model="password" />
      </div>
      <button type="submit">Se connecter</button>
    </form>
  </div>
</template>

<style scoped>

</style>
