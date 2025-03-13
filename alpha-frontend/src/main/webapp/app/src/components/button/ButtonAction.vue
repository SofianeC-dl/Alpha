<script setup lang="ts">
import {computed, onMounted, PropType, Ref, ref} from "vue";
import { useRoute } from "vue-router";
import {ButtonUtils} from "@/composables/utils/button/buttonUtils.js";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {SizeBox} from "@/composables/object/SizeBox.js";

const emits = defineEmits(['clicked'])

const props = defineProps({
  typeRouteActive: {
    type: String,
    default: ''
  },
  labelButton: {
    type: String,
    default: `[X]`
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
  <div class="main-button clickable selector-menu gradient-button effect" :class="{'invisibility-selector': notSelectedBox}" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}" @click="clicked">
    <span xmlns="http://www.w3.org/1999/xhtml"
         style="
            color: white;
            white-space: pre-wrap;
            overflow: hidden;">
      {{ props.labelButton }}
    </span>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.main-button {
  @include mylib.center-block;
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

.clickable {
  cursor: pointer;
}

.selector-menu {
  border: mylib.$header-border-menu-size solid mylib.$color-font-global;
  border-radius: mylib.$header-border-radius;
  padding: mylib.$header-padding-menu;
  position: relative;
}

.invisibility-selector {
  border-color: transparent;
}

.gradient-button {
  position: relative;
  z-index: 1;
  @include mylib.gradient-button;
  @include mylib.gradient-button-hover;
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
