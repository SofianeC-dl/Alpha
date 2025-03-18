<script setup lang="ts">
import {computed, onMounted, PropType, Ref, ref} from "vue";
import { useRoute } from "vue-router";
import {ButtonUtils} from "@/composables/utils/button/buttonUtils.js";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {SizeBox} from "@/composables/object/SizeBox.js";

const emits = defineEmits(['clicked'])

const props = defineProps({
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
  <div :class="{'invisibility-selector': !isActiveButtonCurrentRoute || notSelectedBox, 'gradient-button': !isActiveButtonCurrentRoute}" class="main-button selector-menu effect" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}" @click="clicked">
    <router-link :to="routePath" class="button-style" v-if="isButtonPath">
      <span>{{ props.labelButton }}</span>
    </router-link>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.main-button {
  display: inline-flex;
  align-items: center;
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
  @include mylib.center-block
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
