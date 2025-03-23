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
  positionContent: {
    type: String as PropType<FlexPositionEnum>
  },
  positionItem: {
    type: String as PropType<FlexPositionEnum>
  },
});
</script>

<template>
  <div :class="{
    'slot-container-row': direction === FlexDirectionEnum.ROW,
    'slot-container-column': direction === FlexDirectionEnum.COLUMN
  }"
       :style="{
    '--flex-direction':         direction,
    '--flex-position-content':  positionContent,
    '--flex-position-item':     positionItem,
    '--flex-gap': props.gap
  }">
    <slot></slot>
  </div>
</template>

<style lang="scss">
@use '@/assets/css/index' as mylib;

.slot-container-row {
  @include mylib.flex-row;
}

.slot-container-column {
  @include mylib.flex-column;
}
</style>
