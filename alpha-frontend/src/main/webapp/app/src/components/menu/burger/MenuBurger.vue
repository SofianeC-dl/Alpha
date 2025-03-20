<script setup lang="ts">

import MenuMobile from "@/components/menu/burger/MenuMobile.vue";
import ButtonAction from "@/components/button/ButtonAction.vue";
import ButtonsContainer from "@/components/button/ButtonsContainer.vue";
import {FlexDirectionEnum} from "@/assets/enum/FlexEnum.js";
import ButtonPath from "@/components/button/ButtonPath.vue";
import {MessageGlobalToastUtils} from "@/composables/utils/message/MessageGlobalUtils.js";
import {Store} from "pinia";
import {useAuthStore} from "@/stores/auth/AuthStore.js";
import {Router, useRouter} from "vue-router";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import SearchModal from "@/components/modal/custom/SearchModal.vue";
import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
import {useMenuStore} from "@/stores/menu/menuStore.js";
import {markRaw} from "vue";

const authStore: Store = useAuthStore();
const router: Router = useRouter();
const modalCustomStore = useModalCustomStore();
const menuStore = useMenuStore();

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

const openSearchModal = () => {
  menuStore.closeMenu();
  modalCustomStore.open(markRaw(SearchModal));
}

</script>

<template>
  <Teleport to="body">
    <MenuMobile>
      <ButtonsContainer :direction="FlexDirectionEnum.COLUMN">
        <ButtonAction id="search" label-button="Search" :size="SizeEnum.MEDIUM" :function-click="openSearchModal"/>
        <ButtonPath id="api" routing-path="Api" label-button="Api" :size="SizeEnum.MEDIUM" v-if="authStore.isAdmin"/>
        <ButtonAction id="logout-action" label-button="log out" :function-click="deconnect" @clicked="logoutMessage" :size="SizeEnum.MEDIUM" v-if="authStore.isAdmin"/>
      </ButtonsContainer>
    </MenuMobile>
  </Teleport>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.margin-button-menu {
  margin-bottom: mylib.$menu-margin;
}
</style>
