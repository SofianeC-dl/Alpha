<script setup lang="ts">
/** Imports **/
import {computed, markRaw, onMounted, onUnmounted, Ref, ref} from 'vue'
import {Router, useRouter} from "vue-router";
import ButtonPath from "@/components/button/ButtonPath.vue";
import LogoView from "@/components/logo/LogoView.vue";
import {useAuthStore} from "@/stores/auth/AuthStore.js";
import {MessageGlobalToastUtils} from "@/composables/utils/message/MessageGlobalUtils.js";
import ButtonAction from "@/components/button/ButtonAction.vue";
import {Store} from "pinia";
import {FlexDirectionEnum, FlexPositionEnum} from "@/assets/enum/FlexEnum.js";
import {useMenuStore} from "@/stores/menu/menuStore.js";
import IconMenu from "@/components/icons/IconMenu.vue";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import AddTagModal from "@/components/modal/custom/AddTagModal.vue";
import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";

defineProps({})

/** Variables **/
const router: Router = useRouter();
const authStore: Store = useAuthStore();
const menuStore = useMenuStore();
const modalCustomStore = useModalCustomStore();

const isAdmin = computed(() => {
  menuStore.closeMenu();
  return authStore.isAdmin;
});

const mediaQuery: MediaQueryList = window.matchMedia('(max-width: 1600px)');
const isMediaPhone: Ref<boolean, boolean> = ref<boolean>(false);

/** Methods **/
const handleResize = () => {
  isMediaPhone.value = mediaQuery.matches;
}

onMounted(() => {
  console.log(`the Header component is now mounted.`)

  window.addEventListener('resize', handleResize)
  handleResize();
})

onUnmounted(() => {
  window.removeEventListener('resize', handleResize)
})

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
  modalCustomStore.modalSize = SizeEnum.MEDIUM;
  modalCustomStore.open(markRaw(AddTagModal));
}
</script>

<template>
  <header role="banner">

    <div class="border-group" >
      <ContainerSlot v-if="isAdmin" :position-item="FlexPositionEnum.CENTER">
        <ButtonAction label-button="Add Tag" :size="SizeEnum.SMALL" :function-click="openAddTagModal" />
      </ContainerSlot>
    </div>

      <div v-if="!isMediaPhone" class="center-group">
        <ButtonPath id="admin" class="grid-menu-admin" routing-path="Login" label-button="Admin" :active-path="['Admin']" :size="SizeEnum.SMALL"/>

        <LogoView class="grid-logo" text="Archive.rar" />

        <ButtonPath id="about" class="grid-menu-about" routing-path="About" label-button="About"  :size="SizeEnum.SMALL"/>
      </div>

    <div v-if="isMediaPhone" class="center-group">
        <LogoView class="grid-logo" text="Archive.rar" />

        <ContainerSlot class="button-container" :direction="FlexDirectionEnum.ROW">
          <ButtonPath id="admin" class="grid-menu-admin" routing-path="Login" label-button="Admin"  :active-path="['Admin']" :size="SizeEnum.SMALL"/>

          <div class="border-group-menu-burger">
            <ButtonAction :function-click="menuStore.toggleMenu" :is-icon-button="true" :not-selected-box="true">
              <IconMenu />
            </ButtonAction>
          </div>

          <ButtonPath id="about" class="grid-menu-about" routing-path="About" label-button="About"  :size="SizeEnum.SMALL"/>
        </ContainerSlot>
      </div>

      <div class="border-group">
        <ContainerSlot gap="10px" v-if="isAdmin" :direction="FlexDirectionEnum.ROW" :position-item="FlexPositionEnum.CENTER">
          <ButtonPath id="api" routing-path="Api" label-button="Api" :size="SizeEnum.SMALL"/>
          <ButtonAction id="logout-action" label-button="log out" :function-click="deconnect" @clicked="logoutMessage" :size="SizeEnum.SMALL"/>
        </ContainerSlot>
      </div>
  </header>


</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

header {
  position: relative;

  display: flex;
  justify-content: space-between;
  height: mylib.$header-height-size;
  font-family: 'BebasNeue', sans-serif;
}
.left-group {
  width: 20%;
}

.button-container {
  justify-content: space-between;
}

.space-container-header {
  @include mylib.space-between-block;
  width: 100%;
  box-sizing: border-box;
  position: relative;
  height: mylib.$header-height-size;
}

.center-group {
  justify-content: center;
  display: flex;
  justify-items: center;
  grid-template-columns: auto max-content auto;
  align-items: center;
  column-gap: mylib.$header-margin-size;
}

.border-group {
  display: flex;
  justify-content: flex-end;
  width: 20%;
  box-sizing: border-box;
}

.border-group-menu-burger {
  display: none;
}

.grid-menu-admin {
  position: relative;
  @include mylib.link-menu;
}

.grid-menu-about {
  position: relative;
  @include mylib.link-menu;
}

@media (max-width: mylib.$media-size-menu) {
  .border-group {
    display: none;
    width: 10%;
  }

  .border-group-menu-burger {
    display: flex;
    justify-content: flex-end;
    width: 10%;
    box-sizing: border-box;
  }

  .left-group {
    width: 10%;
  }

  .button-container {
    justify-content: space-around;
  }

  .main-header {
    justify-content: center;
  }
}

@media (max-width: mylib.$media-size-logo) {
  .center-group {
    flex-direction: column;
    justify-content: space-around;
  }
}
</style>
