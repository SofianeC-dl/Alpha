<script setup lang="ts">

import {FlexDirectionEnum} from "@/assets/enum/FlexEnum.js";
import {MessageGlobalToastUtils} from "@/composables/utils/message/MessageGlobalUtils.js";
import {Store} from "pinia";
import {useAuthStore} from "@/stores/auth/AuthStore.js";
import {Router, useRouter} from "vue-router";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
import {useMenuStore} from "@/stores/menu/menuStore.js";
import {markRaw} from "vue";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import MenuMobile from "@/components/menu/burger/MenuMobile.vue";
import ButtonAction from "@/components/button/ButtonAction.vue";
import ButtonPath from "@/components/button/ButtonPath.vue";
import AddTagModal from "@/views/modal/AddTagModal.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const authStore: Store = useAuthStore();
const router: Router = useRouter();
const modalCustomStore = useModalCustomStore();
const menuStore = useMenuStore();

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});

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

const openAddTagModal = () => {
  menuStore.closeMenu();
  modalCustomStore.modalSize = SizeEnum.LARGER;
  modalCustomStore.open(markRaw(AddTagModal));
}
</script>

<template>
  <Teleport :id="'teleport-menu-burger-' + id" to="body">
    <MenuMobile>
      <ContainerSlot :direction="FlexDirectionEnum.COLUMN">
        <ButtonAction label-button="component.button.addtags" :size="SizeEnum.MEDIUM" :function-click="openAddTagModal" function-click=""/>
        <ButtonPath id="api" routing-path="Api" label-button="menu.burger.button.api" :size="SizeEnum.MEDIUM" v-if="authStore.isAdmin"/>
        <ButtonAction id="logout-action" label-button="menu.burger.button.logout" :function-click="deconnect" @clicked="logoutMessage" :size="SizeEnum.MEDIUM" v-if="authStore.isAdmin"/>
      </ContainerSlot>
    </MenuMobile>
  </Teleport>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.margin-button-menu {
  margin-bottom: mylib.$menu-margin;
}
</style>
