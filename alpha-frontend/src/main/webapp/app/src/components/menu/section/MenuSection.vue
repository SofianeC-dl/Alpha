<script setup lang="ts">
import {computed, nextTick, onMounted, Ref, ref} from 'vue'
import {IdUtils} from "@/composables/utils/id/idUtils.js";
import {JsonParseUtils} from "@/composables/utils/json/JsonParseUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
  titleSection: {
    type: String,
    default: 'component.menu.section.title.default'
  },
  ariaLabelSection: {
    type: String,
    default: 'component.button.default'
  },
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
};

const convertTitleSectionName = computed(() => JsonParseUtils.getTextFromTextJsonDict(props.titleSection));
const convertAriaLabel = computed(() => JsonParseUtils.getTextFromAriaJsonDict(props.ariaLabelSection));

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
    role="button"
    :aria-expanded="isOpen"
    tabindex="0"
    :aria-label="convertAriaLabel"
  >
    <div :id="'container-title-menu-section-' + id" class="container-title">
      <div :id="'click-menu-section-' + id" class="title" @click="toggleSection">
        <h3 :id="'title-menu-section-' + id">{{ convertTitleSectionName }}</h3>
      </div>
    </div>
    <div :id="'body-menu-section-' + id" class="body" :style="bodyStyle">
      <div :id="'container-body-menu-section-' + id" ref="container" class="body-container" role="group">
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
