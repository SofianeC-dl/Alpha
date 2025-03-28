<script setup lang="ts">
import {ref, computed, onMounted} from 'vue'
import {IdUtils} from "@/composables/utils/id/idUtils.js";
import {JsonParseUtils} from "@/composables/utils/json/JsonParseUtils.js";
import type {MenuSectionTabs} from "@/composables/object/Menu.js";

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
  tabs: {
    type: Array as PropType<MenuSectionTabs[]>,
    required: true
  },
  defaultOpenTabs: {
    type: String,
    required: true
  }
});

const defaultTabs: Ref<string, string> = ref<string>('');

const convertTitleSectionName = computed(() => JsonParseUtils.getTextFromTextJsonDict(props.titleSection));
const convertAriaLabel = computed(() => JsonParseUtils.getTextFromAriaJsonDict(props.ariaLabelSection));

// Composant actif calculé à partir de l'onglet sélectionné
const activeComponent = computed(() => {
  const currentTab = props.tabs.find(tab => tab.name === defaultTabs.value)
  return currentTab ? currentTab.component : null
})

onMounted(() => {
  defaultTabs.value = props.defaultOpenTabs;
})
</script>

<template>
  <div class="container-menu-tabs">
    <div class="tabs">
        <div
          :id="'menu-section-' + id"
          class="menu-tabs"
          v-for="tab in tabs"
          :key="tab.name"
          :class="{ active: defaultTabs === tab.name }"
          @click="() => defaultTabs = tab.name"
          tabindex="0"
          aria-label="test"
        >
          <div :id="'container-title-menu-section-' + id" class="container-title">
            <div :id="'click-menu-section-' + id" class="title">
              <h1 :id="'title-menu-section-' + id">{{ tab.label }}</h1>
            </div>
          </div>
          <div :id="'body-menu-section-' + id">
            <div :id="'container-body-menu-section-' + id" ref="container" class="body-container" role="group">
              <slot></slot>
            </div>
          </div>
        </div>
    </div>
    <!-- Contenu de l'onglet actif -->
    <div class="content">
      <component :is="activeComponent" />
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.container-menu-tabs {
  @include mylib.border-style-unpadding;
}
.menu-tabs {
  cursor: pointer;
  flex: 1;
  text-align: center;
  &:not(:last-child) {
    border-right: 2px solid #fff;
  }
}

.tabs {
  border-bottom: mylib.$header-border-menu-size solid mylib.$color-font-global;
  width: 100%;
  display: flex;
  flex-wrap: nowrap;
}

.tabs button {
  margin-right: 0.5rem;
  padding: 0.5rem 1rem;
  border: none;
  background-color: #eee;
}

.menu-tabs.active {
  @include mylib.selected-style;
}

.content {
  padding: 5px;
}
</style>
