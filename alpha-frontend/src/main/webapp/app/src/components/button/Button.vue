<script setup lang="ts">
import { computed } from "vue";
import { useRoute } from "vue-router";

const props = defineProps({
  typeRouteActive: {
    type: String,
    default: ''
  },
  labelButton: {
    type: String,
    default: `[no_label_button]`
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
  }
})

/** Variables **/
const route = useRoute();

const routePath: string = '/' + props.typeRouteActive;

/** Méthodes **/
const isActiveButtonCurrentRoute = computed(() => {
  return route.path === routePath;
});
</script>

<template>
  <div class="main-button">
    <router-link :to="routePath" class="button-style effect" v-if="isButtonPath">
      <span :class="{'selector-menu': isActiveButtonCurrentRoute && !notSelectedBox, 'gradient-button': !isActiveButtonCurrentRoute}">{{ props.labelButton }}</span>
    </router-link>

    <span
      class="button-style clickable gradient-button effect"
      @click="functionClick"
      v-if="!isButtonPath"
    >
      {{ props.labelButton }}
    </span>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.main-button {
  display: flex;
}

.button-style {
  @include mylib.link-menu;
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
</style>
