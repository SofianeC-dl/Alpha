<script setup lang="ts">

</script>

<template>
  <svg
    xmlns="http://www.w3.org/2000/svg"
    viewBox="130 70 280 210"
    style="width: 100%; height: auto;"
  >
    <g class="layer">
      <line
        stroke-width="20"
        x1="130"
        x2="400"
        y1="75"
        y2="75"
      />
      <line
        stroke-width="20"
        x1="130"
        x2="400"
        y1="150"
        y2="150"
      />
      <line
        stroke-width="20"
        x1="130"
        x2="400"
        y1="225"
        y2="225"
      />
    </g>
  </svg>

</template>

<style scoped lang="scss">
@use '@/assets/css/index'as mylib;

svg {
  width: 100%;
  height: auto;
  display: block; /* pour éviter les espaces blancs indésirables */
}


line {
  stroke: mylib.$color-font-global;
  stroke-linecap: butt;
}

</style>
