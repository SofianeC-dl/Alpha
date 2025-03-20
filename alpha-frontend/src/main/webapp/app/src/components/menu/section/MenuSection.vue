<script setup lang="ts">
import {computed, onMounted, Ref, ref} from 'vue'

const props = defineProps({
  title: {
    type: String,
    default: '[NO_TITTLE]'
  }
});

const isOpen: Ref<boolean, boolean> = ref<boolean>(false);

const container = ref<HTMLElement | null>(null)
const containerHeight = ref(0);

const toggleSection = () => {
  isOpen.value = !isOpen.value;
}

onMounted(() => {
  if (container.value) {
    containerHeight.value = container.value.offsetHeight;
    console.log('container', container.value);
    console.log('px, Hauteur :', containerHeight.value, 'px');
  }
})

const bodyStyle = computed(() => {
  return isOpen.value
    ? { maxHeight: containerHeight.value + 'px', transition: 'max-height 0.5s cubic-bezier(.42,0,.58,1)' }
    : { maxHeight: '0px', transition: 'max-height 0.5s cubic-bezier(.42,0,.58,1)' }
})
</script>

<template>
  <div
    class="dropdown"
    :class="{ active: isOpen }"
  >
    <div class="container-title">
      <div class="title" @click="toggleSection">
        <h3>{{ props.title }}</h3>
      </div>
    </div>
    <div class="body" :style="bodyStyle">
      <div ref="container">
        <slot></slot>
      </div>

    </div>
  </div>
</template>



<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.dropdown {
  border: mylib.$header-border-menu-size solid mylib.$color-font-global;
  border-radius: mylib.$header-border-radius;
}

.dropdown.active .container-title {
  color: mylib.$color-background-global;
  background-color: mylib.$color-font-global;
}

.title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: mylib.$header-padding-menu;
  margin: 0 5px;
}

.title h3 {
  font-weight: 500;
  font-size: 28px;
  cursor: pointer;
  margin: 0;
}

.body {
  max-height: 0;
  overflow: hidden;
  transition: 0.4s;
  margin: 5px;
}
</style>
