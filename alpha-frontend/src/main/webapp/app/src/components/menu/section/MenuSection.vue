<script setup lang="ts">
import {computed, nextTick, onMounted, Ref, ref} from 'vue'
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
  title: {
    type: String,
    default: '[NO_TITTLE]'
  }
});

const isOpen: Ref<boolean, boolean> = ref<boolean>(false);

const container = ref<HTMLElement | null>(null)
const containerHeight = ref(0);

const toggleSection = async () => {
  if (!isOpen.value && container.value) {
    await nextTick();
    containerHeight.value = container.value.offsetHeight;
  }
  isOpen.value = !isOpen.value;
}

onMounted(() => {
  nextTick(() => {
    if (container.value) {
      containerHeight.value = container.value.offsetHeight;
    }
  });
});



const bodyStyle = computed(() => {
  return isOpen.value
    ? { maxHeight: containerHeight.value + 'px', transition: 'max-height 0.5s cubic-bezier(.42,0,.58,1)' }
    : { maxHeight: '0px', transition: 'max-height 0.5s cubic-bezier(.42,0,.58,1)' }
})
</script>

<template>
  <div
    :id="'menu-section-' + id"
    class="dropdown"
    :class="{ active: isOpen }"
  >
    <div :id="'container-title-menu-section-' + id" class="container-title">
      <div :id="'click-menu-section-' + id" class="title" @click="toggleSection">
        <h3 :id="'title-menu-section-' + id">{{ props.title }}</h3>
      </div>
    </div>
    <div :id="'body-menu-section-' + id" class="body" :style="bodyStyle">
      <div :id="'container-body-menu-section-' + id" ref="container" class="body-container">
        <slot></slot>
      </div>
    </div>
  </div>
</template>



<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.dropdown {
  @include mylib.border-style-unpadding;
}

.dropdown.active .container-title {
  @include mylib.selected-style;
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
}

.body-container {
  padding: 5px;
}
</style>
