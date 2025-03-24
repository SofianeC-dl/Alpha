<script setup lang="ts">
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});
</script>
<template>
  <svg
    :id="'icon-folder-project-' + id"
    viewBox="0 0 64 48"
    xmlns="http://www.w3.org/2000/svg"
  >
    <path
      :id="'path-icon-folder-project-' + id"
      d="
      M 8,44
      H 58
      V 14
      H 44
      L 34,6
      H 8
      V 44
      Z
    "
      fill="none"
      stroke="#ffffff"
      stroke-width="3"
      stroke-linejoin="miter"
    />

    <line
      :id="'line-icon-folder-project-' + id"
      x1="8"
      y1="14"
      x2="58"
      y2="14"
      stroke="#ffffff"
      stroke-width="3"
      stroke-linecap="square"
    />
  </svg>

</template>

<style lang="scss" scoped>
  svg {
    width: 50px;
    height: 100%;
  }
</style>
