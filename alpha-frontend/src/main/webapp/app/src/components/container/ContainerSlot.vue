<script setup lang="ts">
import {PropType} from "vue";
import {FlexDirectionEnum, FlexPositionEnum} from "@/composables/enum/FlexEnum.js";
import {FormatEnum} from "@/composables/enum/FormatEnum.js";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
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
  width: {
    type: String as PropType<FormatEnum>,
    default: FormatEnum.FULL
  },
  activeFlex: {
    type: Boolean,
    default: false
  }
});
</script>

<template>
  <div
    :id="'container-slot-' + id"
    :class="`main-container-slot flex-${direction}-content-${positionContent} flex-items-${positionItem} flex-${direction}`"
    :style="{
      '--width-mode': width,
      '--flex-gap': props.gap

  }">
    <slot></slot>
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.main-container-slot {
  display: flex;
  width: var(--width-mode);
  flex-wrap: wrap;
  gap: var(--flex-gap);
}

.flex-row {
  flex-direction: row;
}

.flex-column {
  flex-direction: column;
}

.flex-row-content-flex-start {
  justify-content: flex-start;
}

.flex-row-content-center {
  justify-content: flex-start;
}

.flex-row-content-flex-end {
  justify-content: flex-end;
}

.flex-column-content-flex-start {
  align-content: flex-start;
}

.flex-column-content-center {
  align-content: flex-start;
}

.flex-column-content-flex-end {
  align-content: flex-end;
}

.flex-items-flex-start {
  align-items: flex-start;
}

.flex-items-center {
  align-items: center;
}

.flex-items-flex-end {
  align-items: flex-end;
}
</style>
