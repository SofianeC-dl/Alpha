<script setup lang="ts">
import { computed } from 'vue'
import { useGlobalMessageStore } from '@/stores/globalMesage/globalMessageStore.ts'
import IconSuccess from "@/components/icons/IconSuccess.vue";
import IconError from "@/components/icons/IconError.vue";
import IconWarn from "@/components/icons/IconWarn.vue";
import IconInfo from "@/components/icons/IconInfo.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});

const globalMessageStore = useGlobalMessageStore();

const type = computed(() => globalMessageStore.type);

const title = computed(() => globalMessageStore.title);

const isErrorMessage = (): string => {
  return type.value === 'error';
};

const isSuccessMessage = (): string => {
  return type.value === 'success';
};

const isInfoMessage = (): string => {
  return type.value === 'info';
};

const isWarnMessage = (): string => {
  return type.value === 'warn';
};
</script>

<template>
  <div :id="'header-message-' + id" class="main-button">
    <div :id="'icon-header-message-' + id" class="item-icon">
      <IconError v-if="isErrorMessage()" class="item-icon" />
      <IconSuccess v-if="isSuccessMessage()" class="item-icon" />
      <IconInfo v-if="isInfoMessage()" class="item-icon" />
      <IconWarn v-if="isWarnMessage()" class="item-icon" />
    </div>
    <a :id="'title-header-message-' + id" class="item-title">
      {{ title }}
    </a>
  </div>
</template>

<style scoped>

.main-button {
  display: grid;
  grid-template-rows: 2rem;
  grid-template-columns: 1rem 5px max-content;
  grid-template-areas:
    'icon . title';
  align-items: center;
}
.item-title {
  grid-area: title;
  font-size: large;
}

.item-icon {
  justify-content: center;
  grid-area: icon;
}
</style>
