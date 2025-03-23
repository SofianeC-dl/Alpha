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
<div :class="{'input-container-row': direction === FlexDirectionEnum.ROW, 'input-container-column': direction === FlexDirectionEnum.COLUMN}" :style="{'--flex-direction': direction, '--flex-position': position, '--flex-gap': gap}">
  <slot></slot>
</div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.input-container-row {
  @include mylib.flex-row;
}

.input-container-column {
  @include mylib.flex-column;
}
</style>
