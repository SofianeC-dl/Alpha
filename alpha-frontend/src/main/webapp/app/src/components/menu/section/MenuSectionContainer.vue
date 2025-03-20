<script setup lang="ts">

</script>

<template>
 <div class="container">
   <slot></slot>
 </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.container {
  margin: mylib.$margin-global 0;
}
</style>
