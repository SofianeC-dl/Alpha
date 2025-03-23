<script setup lang="ts">

</script>

<template>
  <div class="main-container-item">
    <slot style="gap: 50px"></slot>
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;
.main-container-item {
  @include mylib.border-style-unpadding;
  width: max-content;
  height: max-content;
  display: flex;
  gap: 5px;
  padding: 5px;
}
</style>
