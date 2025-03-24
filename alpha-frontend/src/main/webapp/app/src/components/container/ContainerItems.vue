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
  <div :id="'container-items-' + id" class="main-container-item">
    <slot style="gap: 50px"></slot>
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.main-container-item {
  @include mylib.border-style-unpadding;
  width: auto;
  min-height: 20px;
  height: max-content;
  display: flex;
  flex-wrap: wrap;
  gap: inherit;
  padding: 5px;
}
</style>
