<script setup lang="ts">
import {useMenuStore} from "@/stores/menu/menuStore.js";

const menuStore = useMenuStore();
</script>

<template>
  <div class="navbar">
    <button class="burger-btn" @click="menuStore.toggleMenu">
      <span class="bar"></span>
      <span class="bar"></span>
      <span class="bar"></span>
    </button>
  </div>
</template>

<style scoped lang="scss">
.navbar {
  display: flex;
  align-items: center;
}
</style>
