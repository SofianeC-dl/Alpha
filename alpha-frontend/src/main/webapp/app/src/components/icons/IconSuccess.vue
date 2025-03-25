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
  <svg :id="'icon-success-' + id"
       role="img"
       xmlns="http://www.w3.org/2000/svg"
       viewBox="0 0 50 50"
       xml:space="preserve"
       width="10px"
       height="10px"
       fill="#000000">
    <g :id="'g1-icon-success-' + id"
       stroke-width="0">
    </g>
    <g :id="'g2-icon-success-' + id"
       stroke-linecap="round"
       stroke-linejoin="round">
    </g>
    <g :id="'g3-icon-success-' + id">
      <circle :id="'circle-icon-success-' + id" cx="25" cy="25" r="25"></circle>
      <polyline :id="'polyline-icon-success-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;" points=" 38,15 22,33 12,25 "></polyline>
    </g>
  </svg>



</template>

<style scoped lang="scss">
@use "@/assets/css/index" as mylib;
circle {
  fill: mylib.$color-message-success;
}
</style>
