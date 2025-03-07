<script setup lang="ts">
const props = defineProps({
  gap: {
    type: String,
    default: '10px'
  }
});
</script>

<template>
  <div class="slot-container" >
    <slot></slot>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
.slot-container {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: mylib.$header-space-between-button-right-group;
}
</style>
