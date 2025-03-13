<script setup lang="ts">
/** Imports **/
import {computed, onMounted, onUnmounted, Ref, ref} from 'vue'
import {Router, useRouter} from "vue-router";
import ButtonPath from "@/components/button/ButtonPath.vue";
import LogoView from "@/components/logo/LogoView.vue";
import ButtonsContainer from "@/components/button/ButtonsContainer.vue";
import {useAuthStore} from "@/stores/auth/AuthStore.js";
import {MessageGlobalToastUtils} from "@/composables/utils/message/MessageGlobalUtils.js";
import ButtonMenu from "@/components/button/ButtonMenu.vue";
import ButtonAction from "@/components/button/ButtonAction.vue";
import {Store} from "pinia";
import {FlexDirectionEnum} from "@/assets/enum/FlexEnum.js";

defineProps({})

/** Variables **/
const router: Router = useRouter();
const authStore: Store = useAuthStore();

const isAdmin = computed(() => authStore.isAdmin);

const mediaQuery: MediaQueryList = window.matchMedia('(max-width: 598px)');
const isMediaPhone: Ref<boolean, boolean> = ref<boolean>(false);

/** Methods **/
const handleResize = () => {
  isMediaPhone.value = mediaQuery.matches;
}

onMounted(() => {
  console.log(`the Header component is now mounted.`)

  window.addEventListener('resize', handleResize)
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
</script>

<template>
  <header role="banner">

      <div class="left-group"></div>

      <div v-if="!isMediaPhone" class="center-group">
        <ButtonPath id="admin" class="grid-menu-admin" routing-path="Login" label-button="Admin" :is-button-path="true" :active-path="['Admin']"/>

        <LogoView class="grid-logo" text="Archive.rar" />

        <ButtonPath id="about" class="grid-menu-about" routing-path="About" label-button="About" :is-button-path="true " />
      </div>

      <div v-if="isMediaPhone" class="center-group">
        <LogoView class="grid-logo" text="Archive.rar" />

        <ButtonsContainer style="justify-content: space-between" :direction="FlexDirectionEnum.ROW">
          <ButtonPath id="admin" class="grid-menu-admin" routing-path="Login" label-button="Admin" :is-button-path="true" :active-path="['Admin']"/>
          <ButtonPath id="about" class="grid-menu-about" routing-path="About" label-button="About" :is-button-path="true " />
        </ButtonsContainer>
      </div>

      <div class="right-group">
        <ButtonsContainer gap="100px" v-if="isAdmin">
          <ButtonPath id="api" routing-path="Api" label-button="Api" :is-button-path="true" />
          <ButtonAction id="logout-action" label-button="log out" :function-click="deconnect" @clicked="logoutMessage"/>
        </ButtonsContainer>
      </div>

      <div class="right-group-menu-burger">
        <ButtonMenu />
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
.space-container-header {
  display: flex;
  align-items: center;
  width: 100%;
  box-sizing: border-box;
  justify-content: space-between;
  position: relative;
  height: mylib.$header-height-size;
}

.center-group {
  justify-content: center;
  display: flex;
  width: 60%;
  justify-items: center;
  grid-template-columns: auto max-content auto;
  align-items: center;
  column-gap: mylib.$header-margin-size;
  height: mylib.$header-height-size;
}

@media (max-width: mylib.$media-size-logo) {
  .center-group {
    flex-direction: column;
  }
}

.right-group {
  display: flex;
  justify-content: flex-end;
  width: 20%;
  box-sizing: border-box;
}

@media (max-width: mylib.$media-size-menu) {
  .right-group {
    display: none;
  }
}

.right-group-menu-burger {
  display: none;
}

@media (max-width: mylib.$media-size-menu) {
  .right-group-menu-burger {
    display: flex;
    justify-content: flex-end;
    width: 20%;
    box-sizing: border-box;
  }
}

.gradient-button {
  @include mylib.gradient-button;
  @include mylib.gradient-button-hover;
}

.grid-menu-admin {
  position: relative;
  @include mylib.link-menu;
}

.grid-menu-about {
  position: relative;
  @include mylib.link-menu;
}
</style>
