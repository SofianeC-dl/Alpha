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
  <svg :id="'icon-info-' + id"
       role="img"
       xmlns="http://www.w3.org/2000/svg"
       viewBox="0 0 50 50"
       xml:space="preserve"
       fill="#000000">
    <g :id="'g1-icon-info-' + id"
       stroke-width="0">
    </g>
    <g :id="'g2-icon-info-' + id"
       stroke-linecap="round"
       stroke-linejoin="round">
    </g>
    <g :id="'g3-icon-info-' + id">
      <circle :id="'circle-icon-info-' + id"
              cx="25"
              cy="25"
              r="25">
      </circle>
      <line :id="'line1-icon-info-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;" x1="25" y1="13" x2="25" y2="38"></line>
      <line :id="'line2-icon-info-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;" x1="37.5" y1="25" x2="12.5" y2="25"></line>
    </g>
  </svg>
</template>

<style scoped lang="scss">
@use "@/assets/css/index" as mylib;
circle {
  fill: mylib.$color-message-info;
}
</style>
