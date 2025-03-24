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
 <div :id="'menu-section-container-' + id" class="container">
   <slot></slot>
 </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.container {
  margin: mylib.$margin-global 0;
}
</style>
