<script setup lang="ts">

import IconFolderProject from "@/components/icons/IconFolderProject.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
})
</script>

<template>
  <button :id="'button-folder-' + id" class="icon-button" aria-label="Mon bouton">
    <IconFolderProject />
  </button>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
.icon-button {
  background: none;
  border: none;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.icon-button svg {
  width: mylib.$main-height-folder;
  min-width: mylib.$main-min-height-folder;
  stroke: #000;
  transition: fill 0.2s ease;
}

/* Au survol */
.icon-button:hover svg {
  fill: #f00; /* Changement de couleur au hover */
}

.icon-button:hover {
  transform: scale(1.1);
}
</style>
