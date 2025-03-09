<script setup lang="ts">

import ButtonFolderProject from "@/components/button/ButtonFolderProject.vue";
</script>

<template>
  <div class="container-folder">
    <ButtonFolderProject />
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.container-folder {
  display: flex;
  align-items: center;
  width: max-content;
  padding: 4px;
  background: mylib.$color-background-main-view;
  border-radius: mylib.$main-border-radius;
}
</style>
