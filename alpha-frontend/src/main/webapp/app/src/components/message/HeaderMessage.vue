<script setup lang="ts">
import { computed } from 'vue'
import { useGlobalMessageStore } from '@/stores/globalMesage/globalMessageStore.ts'
import IconSuccess from "@/components/icons/IconSuccess.vue";
import IconError from "@/components/icons/IconError.vue";
import IconWarn from "@/components/icons/IconWarn.vue";
import IconInfo from "@/components/icons/IconInfo.vue";

const globalMessageStore = useGlobalMessageStore()

const type = computed(() => globalMessageStore.type)

const title = computed(() => globalMessageStore.title)

const isErrorMessage = () => {
  return type.value === 'error';
}

const isSuccessMessage = () => {
  return type.value === 'success';
}

const isInfoMessage = () => {
  return type.value === 'info';
}

const isWarnMessage = () => {
  return type.value === 'warn';
}
</script>

<template>
  <div class="main-button">
    <div class="item-icon">
      <IconError v-if="isErrorMessage()" class="item-icon" />
      <IconSuccess v-if="isSuccessMessage()" class="item-icon" />
      <IconInfo v-if="isInfoMessage()" class="item-icon" />
      <IconWarn v-if="isWarnMessage()" class="item-icon" />
    </div>
    <a class="item-title">
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
