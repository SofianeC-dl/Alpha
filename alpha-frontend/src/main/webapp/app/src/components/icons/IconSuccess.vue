<script setup lang="ts">

</script>

<template>
  <svg id="success"
       xmlns="http://www.w3.org/2000/svg"
       viewBox="0 0 50 50"
       xml:space="preserve"
       width="10px"
       height="10px"
       fill="#000000">
    <g id="SVGRepo_bgCarrier"
       stroke-width="0">
    </g>
    <g id="SVGRepo_tracerCarrier"
       stroke-linecap="round"
       stroke-linejoin="round">
    </g>
    <g id="SVGRepo_iconCarrier">
      <circle cx="25" cy="25" r="25"></circle>
      <polyline style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;" points=" 38,15 22,33 12,25 "></polyline>
    </g>
  </svg>



</template>

<style scoped lang="scss">
@use "@/assets/css/index" as mylib;
circle {
  fill: mylib.$color-message-success;
}
</style>
