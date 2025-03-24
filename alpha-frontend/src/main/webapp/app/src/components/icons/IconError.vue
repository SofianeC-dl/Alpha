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
  <svg :id="'icon-error-' + id"
       xmlns="http://www.w3.org/2000/svg"
       viewBox="0 0 50 50"
       xml:space="preserve"
       fill="#000000">
    <g :id="'g1-icon-error-' + id"
       stroke-width="0">
    </g>
    <g :id="'g2-icon-error-' + id"
       stroke-linecap="round"
       stroke-linejoin="round">
    </g>
    <g :id="'g3-icon-error-' + id">
      <circle :id="'circle-icon-error-' + id" cx="25" cy="25" r="25"></circle>
      <polyline :id="'polyline1-icon-error-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-miterlimit:10;" points="16,34 25,25 34,16 "></polyline>
      <polyline :id="'polyline2-icon-error-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-miterlimit:10;" points="16,16 25,25 34,34 "></polyline>
    </g>
  </svg>
</template>

<style scoped lang="scss">
@use "@/assets/css/index" as mylib;
circle {
  fill: mylib.$color-message-error;
}
</style>
