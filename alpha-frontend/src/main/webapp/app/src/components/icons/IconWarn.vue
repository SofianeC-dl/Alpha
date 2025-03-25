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
  <svg :id="'icon-warn-' + id"
       role="img"
       xmlns="http://www.w3.org/2000/svg"
       viewBox="0 0 50 50"
       xml:space="preserve" fill="#000000">
    <g :id="'g1-icon-warn-' + id"
       stroke-width="0">
    </g>
    <g :id="'g2-icon-warn-' + id"
       stroke-linecap="round"
       stroke-linejoin="round">
    </g>
    <g :id="'g3-icon-warn-' + id">
      <circle :id="'circle-icon-warn-' + id"
              cx="25"
              cy="25"
              r="25">
      </circle>
      <line :id="'line1-icon-warn-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-miterlimit:10;" x1="25" y1="10" x2="25" y2="32"></line>
      <line :id="'line2-icon-warn-' + id" style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-miterlimit:10;" x1="25" y1="37" x2="25" y2="39"></line>
    </g>
  </svg>
</template>

<style scoped lang="scss">
@use "@/assets/css/index" as mylib;
circle {
  fill: mylib.$color-message-warn;
}
</style>
