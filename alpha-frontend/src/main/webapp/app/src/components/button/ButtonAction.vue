<script setup lang="ts">
import {computed, onMounted, PropType, Ref, ref} from "vue";
import { useRoute } from "vue-router";
import {ButtonUtils} from "@/composables/utils/button/buttonUtils.js";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {SizeBox} from "@/composables/object/SizeBox.js";

const emits = defineEmits(['clicked']);

const props = defineProps({
  typeRouteActive: {
    type: String,
    default: ''
  },
  labelButton: {
    type: String,
    default: `[X]`
  },
  isIconButton: {
    type: Boolean,
    default: false
  },
  isButtonPath: {
    type: Boolean,
    default: false
  },
  notSelectedBox: {
    type: Boolean,
    default: false
  },
  functionClick: {
    type: Function,
    default: () => {
      console.log('Not function click');
    }
  },
  size: {
    type: String as PropType<SizeEnum>,
    default: SizeEnum.DEFAULT
  }
})

/** Variables **/
const route = useRoute();

const routePath: string = '/' + props.typeRouteActive;

const widthButton: Ref<string, string> = ref(null);
const heightButton: Ref<string, string> = ref(null);

/** Méthodes **/
const isActiveButtonCurrentRoute = computed(() => {
  return route.path === routePath;
});

const clicked = () => {
  if (props.functionClick) props.functionClick();
  emits('clicked');
}

onMounted(() => {
  const result: SizeBox = ButtonUtils.convertSize(props.size);

  widthButton.value = result.width;
  heightButton.value = result.height;
})


</script>

<template>
  <div class="button-container">
    <div class="main-button clickable selector-menu effect" :class="{'invisibility-selector': notSelectedBox}" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}" >
      <div @click="clicked">
        <span v-if="!isIconButton" xmlns="http://www.w3.org/1999/xhtml"
             style="
                color: white;
                white-space: pre-wrap;
                overflow: hidden;">
          {{ props.labelButton }}
        </span>
        <div v-if="isIconButton" class="icon-center" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}">
          <slot ></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
.button-container {
  width: max-content;
}
.main-button {
  @include mylib.row-center-block;
  flex-wrap: wrap;

  width: var(--size-button-width);
}

.main-button span {
  display: flex;
  overflow-wrap: break-word;
  font-size: var(--size-button-height);

  justify-content: center;
  align-content: center;
}

.icon-center {
  display: flex;
  justify-content: center;
  align-content: center;
  width: var(--size-button-width);
  height: var(--size-button-height);
}

.clickable {
  cursor: pointer;
}

.selector-menu {
  @include mylib.border-style;
  position: relative;
}

.invisibility-selector {
  border-color: transparent;
}

.effect {
  transition: fill 30s ease;
}

.effect:hover {
  transform: scale(1.1);
}

span {
  display: block;    /* ou inline-block */
  width: 100%;
}
</style>
