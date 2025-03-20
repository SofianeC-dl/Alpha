<script setup lang="ts">

import {useMenuStore} from "@/stores/menu/menuStore.js";
import ButtonAction from "@/components/button/ButtonAction.vue";

const menuStore = useMenuStore();
</script>

<template>
    <nav v-if="menuStore.isMenuOpen" class="menu-navbar">
      <div class="button-close-menu">
        <ButtonAction  :function-click="menuStore.toggleMenu" :not-selected-box="true" />
      </div>

      <div class="button-menu">
        <slot class="button"></slot>
      </div>
    </nav>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.menu-navbar {
  position: fixed;
  z-index: 10000;

  right: 0;
  top: 0;
  width: max-content;
  min-width: 25%;
  height: 100%;
  box-sizing: border-box;
  padding: 1rem;

  border-left: 1px solid mylib.$color-font-global;
  background-color: mylib.$color-background-global;
}

.button-close-menu {
  display: flex;
  justify-content: flex-end;
  width: 100%;
}

.button-menu {
  display: flex;
  flex-direction: column;
  height: 100%;

  justify-items: center;
  align-items: center;
}

.burger-btn {
  background: none;
  border: none;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  padding: 0;
  .bar {
    width: 25px;
    height: 3px;
    background-color: mylib.$color-font-global;
    margin: 4px 0;
    transition: 0.3s;
  }
}

/* MenuMobile caché ou visible en fonction de la taille de l'écran */
.nav-links {
  display: block;
  /* ... */
}

@media (min-width: mylib.$media-size-menu) {
  .burger-btn {
    display: none;
  }
  .nav-links {
    display: flex;
  }
}
</style>
