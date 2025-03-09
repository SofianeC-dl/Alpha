<script setup lang="ts">

</script>

<template>
  <svg id="warn"
       xmlns="http://www.w3.org/2000/svg"
       viewBox="0 0 50 50"
       xml:space="preserve" fill="#000000">
    <g id="SVGRepo_bgCarrier"
       stroke-width="0">
    </g>
    <g id="SVGRepo_tracerCarrier"
       stroke-linecap="round"
       stroke-linejoin="round">
    </g>
    <g id="SVGRepo_iconCarrier">
      <circle cx="25"
              cy="25"
              r="25">
      </circle>
      <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-miterlimit:10;" x1="25" y1="10" x2="25" y2="32"></line>
      <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-miterlimit:10;" x1="25" y1="37" x2="25" y2="39"></line>
    </g>
  </svg>
</template>

<style scoped lang="scss">
@use "@/assets/css/index" as mylib;
circle {
  fill: mylib.$color-message-warn;
}
</style>
