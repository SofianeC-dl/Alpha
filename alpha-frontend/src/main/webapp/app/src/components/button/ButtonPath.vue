<script setup lang="ts">
import {computed, onMounted, PropType, Ref, ref} from "vue";
import { useRoute } from "vue-router";
import {ButtonUtils} from "@/composables/utils/button/buttonUtils.js";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {SizeBox} from "@/composables/object/SizeBox.js";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const emits = defineEmits(['clicked'])

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
  routingPath: {
    type: String,
    default: ''
  },
  activePath: {
    type: Array<String>,
    default: [],
  },
  labelButton: {
    type: String,
    default: `[X]`
  },
  isButtonPath: {
    type: Boolean,
    default: true
  },
  notSelectedBox: {
    type: Boolean,
    default: false
  },
  size: {
    type: String as PropType<SizeEnum>,
    default: SizeEnum.DEFAULT
  }
})

/** Variables **/
const route = useRoute();

const routePath: string = '/' + props.routingPath;

const widthButton: Ref<string, string> = ref(null);
const heightButton: Ref<string, string> = ref(null);

/** Méthodes **/
const normalizedActivePath = () => {
  return props.activePath.map((activePath: string) => '/' + activePath);
};

const isActiveButtonCurrentRoute = computed(() => {
  const normalizedPathArray: String[] = normalizedActivePath();
  return route.path === routePath || normalizedPathArray.includes(route.path);
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
  <router-link :id="'router-button-path-' + id" :to="routePath" class="button-style" v-if="isButtonPath" role="link">
    <div :id="'button-path-' + id" class="main-button selector-menu effect" :class="{'invisibility-selector': isActiveButtonCurrentRoute && !notSelectedBox}" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}">
      <div :id="'click-button-path-' + id" @click="clicked">
          <span :id="'span-button-path-' + id">{{ props.labelButton }}</span>
      </div>
    </div>
  </router-link>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.main-button {
  flex-wrap: nowrap;
  justify-content: center;
  width: var(--size-button-width);
}

.main-button span {
  display: flex;
  overflow-wrap: break-word;
  font-size: var(--size-button-height);

  justify-content: center;
  align-content: center;
}

.button-style {
  width: max-content;
  @include mylib.link-menu;
}

.selector-menu {
  @include mylib.border-style;
  position: relative;
}

.invisibility-selector {
  @include mylib.selected-style;
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
