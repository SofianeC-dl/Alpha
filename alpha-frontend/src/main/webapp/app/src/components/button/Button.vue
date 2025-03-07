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
  <div class="grid-main">
        <router-link :to="routePath" class="grid-button" v-if="isButtonPath">
          <span :class="{'selector-menu': isActiveButtonCurrentRoute, 'gradient-button': !isActiveButtonCurrentRoute}">{{ props.labelButton }}</span>
        </router-link>

        <span
          class="grid-button"
          :class="{'selector-menu': isActiveButtonCurrentRoute, 'gradient-button': !isActiveButtonCurrentRoute}"
          v-if="!isButtonPath"
        >
          {{ props.labelButton }}
        </span>
    </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.grid-main {
  display: grid;
  container-type: size;
  grid-template-columns: auto mylib.$header-button-size-width auto;
  grid-template-rows:
    auto
    mylib.$header-button-size-height
    auto;
  grid-template-areas:
  '. .      .'
  '. button .'
  '. .      .';

  place-items: center;
}

.grid-button {
  grid-area: button;
  @include mylib.link-menu;
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

</style>
