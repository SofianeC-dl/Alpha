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
    :id="'icon-menu-' + id"
    role="img"
    xmlns="http://www.w3.org/2000/svg"
    x="0px" y="0px"
    viewBox="0 0 50 50">
    <path :id="'path-icon-menu-' + id" d="M 0 7.5 L 0 12.5 L 50 12.5 L 50 7.5 Z M 0 22.5 L 0 27.5 L 50 27.5 L 50 22.5 Z M 0 37.5 L 0 42.5 L 50 42.5 L 50 37.5 Z"></path>
  </svg>

</template>

<style scoped lang="scss">
@use '@/assets/css/index'as mylib;

svg {
  width: 45px;
  height: 45px;
  display: block;

  path {
    stroke: #dddddd;
    fill: #dddddd;
  }
}


</style>
