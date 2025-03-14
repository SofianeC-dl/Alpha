<script setup lang="ts">

import MenuMobile from "@/components/menu/MenuMobile.vue";
import ButtonAction from "@/components/button/ButtonAction.vue";
import ButtonsContainer from "@/components/button/ButtonsContainer.vue";
import {FlexDirectionEnum} from "@/assets/enum/FlexEnum.js";
import ButtonPath from "@/components/button/ButtonPath.vue";
import {MessageGlobalToastUtils} from "@/composables/utils/message/MessageGlobalUtils.js";
import {Store} from "pinia";
import {useAuthStore} from "@/stores/auth/AuthStore.js";
import {Router, useRouter} from "vue-router";

const authStore: Store = useAuthStore();
const router: Router = useRouter();

const deconnect = () => {
  authStore.setToken(null);
  router.push('/');
};

const logoutMessage = () => {
  if (authStore.isAdmin) {
    MessageGlobalToastUtils.errorMessage('Log out error');
  } else {
    router.push('/');
    MessageGlobalToastUtils.successMessage('Log out success');
  }
};
</script>

<template>
  <Teleport to="body">
    <MenuMobile>
      <ButtonsContainer :direction="FlexDirectionEnum.COLUMN">
        <ButtonPath id="api" routing-path="Api" label-button="Api" :is-button-path="true" />
        <ButtonAction id="logout-action" label-button="log out" :function-click="deconnect" @clicked="logoutMessage"/>
      </ButtonsContainer>
    </MenuMobile>
  </Teleport>
</template>

<style scoped lang="scss">
.margin-button-menu {
  margin-bottom: 10px;
}
</style>
