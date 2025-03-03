<script setup lang="ts">
/** Imports **/
import {computed, onBeforeUnmount, onMounted, ref} from 'vue'
import { useRoute } from "vue-router";
import ButtonPath from "@/components/button/ButtonPath.vue";
import LogoView from "@/components/logo/LogoView.vue";

defineProps({})

/** Variables **/
const route = useRoute()

const menuOpen = ref(false)

const isShrunk = ref(false)

let lastScrollPosition = 0

const container = ref<HTMLElement | null>(null);
const textSpan = ref<HTMLElement | null>(null);

/** Methods **/
function handleScroll() {
  const currentScroll = window.scrollY || document.documentElement.scrollTop
  isShrunk.value = currentScroll > lastScrollPosition
  lastScrollPosition = currentScroll
}

onMounted(() => {
  console.log(`the Header component is now mounted.`)

  window.addEventListener('scroll', handleScroll)

  if (container.value && textSpan.value) {
    const containerRect = container.value.getBoundingClientRect();
    const textRect = textSpan.value.getBoundingClientRect();

    const scaleX = containerRect.width / textRect.width;
    const scaleY = containerRect.height / textRect.height;

    textSpan.value.style.transformOrigin = 'center';
    textSpan.value.style.transform = `scale(${scaleX}, ${scaleY})`;
  }
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll)
})

const isAdminCurrentRoute = computed(() => {
  return route.path === '/Admin';
});

const isAboutCurrentRoute = computed(() => {
  return route.path === '/About';
});
</script>

<template>
  <header role="banner">
    <ButtonPath class="grid-menu-admin" type-route-active="Admin" label-button="Admin"/>

    <LogoView class="grid-logo" text="Archive.rar" />

    <ButtonPath class="grid-menu-about" type-route-active="About" label-button="About"/>
  </header>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

header {
  display: grid;
  grid-template-columns: auto mylib.$header-container-button-size-width mylib.$header-margin-size max-content mylib.$header-margin-size mylib.$header-container-button-size-width auto;
  grid-template-rows: mylib.$header-height-size;
  grid-template-areas:
    '. buttonAdmin . logo . buttonAbout .';
}

.grid-logo {
  @include mylib.center-block;
  @include mylib.link-menu;

  grid-area: logo;
}

.gradient-button {
  @include mylib.gradient-button;
  @include mylib.gradient-button-hover;
}

.grid-menu-admin {
  position: relative;
  @include mylib.link-menu;

  font-family: 'BebasNeue', sans-serif;
  grid-area: buttonAdmin;
}

.grid-menu-about {
  position: relative;
  @include mylib.link-menu;

  font-family: 'BebasNeue', sans-serif;
  grid-area: buttonAbout;
}
</style>
