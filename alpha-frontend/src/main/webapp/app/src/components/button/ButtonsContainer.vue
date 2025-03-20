<script setup lang="ts">
import {PropType} from "vue";
import {FlexDirectionEnum, FlexPositionEnum} from "@/assets/enum/FlexEnum.js";

const props = defineProps({
  gap: {
    type: String,
    default: '10px'
  },
  direction: {
    type: String as PropType<FlexDirectionEnum>,
    default: FlexDirectionEnum.ROW
  },
  position: {
    type: String as PropType<FlexPositionEnum>,
    default: FlexPositionEnum.CENTER
  }
});
</script>

<template>
  <div class="slot-container" :style="{'--flex-direction': direction, '--flex-position': position}">
    <slot></slot>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
.slot-container {
  display: flex;
  width: 100%;
  align-items: center;
  flex-wrap: wrap;
  flex-direction: var(--flex-direction);
  justify-content: var(--flex-position);
  gap: mylib.$header-space-between-button-right-group;
}
</style>
