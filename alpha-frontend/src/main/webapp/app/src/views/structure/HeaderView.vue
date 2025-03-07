<script setup lang="ts">
/** Imports **/
import {computed, nextTick, onBeforeUnmount, onMounted, ref} from 'vue'
import { useRoute } from "vue-router";
import Button from "@/components/button/Button.vue";
import LogoView from "@/components/logo/LogoView.vue";
import ButtonsContainer from "@/components/button/ButtonsContainer.vue";
import {useAuthStore} from "@/stores/auth/AuthStore.js";

defineProps({})

/** Variables **/
const route = useRoute();

const isShrunk = ref(false);

let lastScrollPosition = 0;

const rightGroupRef = ref<HTMLElement|null>(null);
const rightWidth = ref(0);

const authStore = useAuthStore();
const isAdmin = computed(() => authStore.isAdmin);

/** Methods **/
function handleScroll() {
  const currentScroll = window.scrollY || document.documentElement.scrollTop
  isShrunk.value = currentScroll > lastScrollPosition
  lastScrollPosition = currentScroll
}

onMounted(() => {
  console.log(`the Header component is now mounted.`)

  window.addEventListener('scroll', handleScroll)

  nextTick(() => {
    if(rightGroupRef.value) {
      rightWidth.value = rightGroupRef.value.offsetWidth;
    }
  });
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll)
});
const deconnect = () => {
  authStore.setToken(null);
};
const isAdminCurrentRoute = computed(() => {
  return route.path === '/Admin';
});

const isAboutCurrentRoute = computed(() => {
  return route.path === '/About';
});

</script>

<template>
  <header role="banner">

      <div class="left-group"></div>

      <div class="center-group">
        <Button id="admin" class="grid-menu-admin" type-route-active="Login" label-button="Admin" :is-button-path="true "/>

        <LogoView class="grid-logo" text="Archive.rar" />

        <Button id="about" class="grid-menu-about" type-route-active="About" label-button="About" :is-button-path="true " />
      </div>

      <div class="right-group">
        <ButtonsContainer gap="100px" v-if="isAdmin">
          <Button id="api" type-route-active="Api" label-button="Api" :is-button-path="true" />
          <Button id="api" type-route-active="" label-button="log out" @click="deconnect()" :is-button-path="true"/>
        </ButtonsContainer>
      </div>
  </header>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

header {
  position: relative;
  //@include mylib.center-block;

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

.right-group {
  display: flex;
  justify-content: flex-end;
  width: 20%;
  box-sizing: border-box;
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
