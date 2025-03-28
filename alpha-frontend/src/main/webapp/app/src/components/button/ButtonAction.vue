<script setup lang="ts">
import {computed, onMounted, PropType, Ref, ref} from "vue";
import { useRoute } from "vue-router";
import {ButtonUtils} from "@/composables/utils/button/buttonUtils.js";
import {SizeEnum} from "@/composables/enum/sizeEnum.js";
import {SizeBox} from "@/composables/object/SizeBox.js";
import {IdUtils} from "@/composables/utils/id/idUtils.js";
import {JsonParseUtils} from "@/composables/utils/json/JsonParseUtils.js";

const emits = defineEmits(['clicked']);

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
  typeRouteActive: {
    type: String,
    default: ''
  },
  labelButton: {
    type: String,
    default: 'component.button.default'
  },
  ariaLabelButton: {
    type: String,
    default: 'component.button.default'
  },
  isIconButton: {
    type: Boolean,
    default: false
  },
  isButtonPath: {
    type: Boolean,
    default: false
  },
  invisibleSelectedBox: {
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
  },
  colorText: {
    type: String,
    default: '#dddddd'
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
};

const convertLabel = computed(() => JsonParseUtils.getTextFromTextJsonDict(props.labelButton));
const convertAriaLabel = computed(() => JsonParseUtils.getTextFromAriaJsonDict(props.ariaLabelButton));

onMounted(() => {
  const result: SizeBox = ButtonUtils.convertSize(props.size);

  widthButton.value = result.width;
  heightButton.value = result.height;
})


</script>

<template>
  <div :id="'button-action-' + id" class="button-container" role="button" tabindex="0" :aria-label="convertAriaLabel">
    <div :id="'sub-button-action-' + id" class="main-button clickable selector-menu effect" :class="{'invisibility-selector': invisibleSelectedBox, 'not-selector': notSelectedBox}" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}" >
      <div :id="'click-button-action-' + id" @click="clicked" @keydown.enter="clicked">
        <span :id="'span-button-action-' + id" v-if="!isIconButton" xmlns="http://www.w3.org/1999/xhtml" :style="{'--color-text': colorText}">
          {{ convertLabel }}
        </span>
        <div :id="'icon-button-action-' + id" v-if="isIconButton" class="icon-center" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}">
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

  color: var(--color-text);
  white-space: pre-wrap;
  overflow: hidden;
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

.not-selector {
  border: none;
  padding: 0;
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
