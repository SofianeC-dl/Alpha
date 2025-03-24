<script setup lang="ts">
import { useGlobalMessageStore } from '@/stores/globalMesage/globalMessageStore.ts'
import { computed } from 'vue'
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});

const globalMessageStore = useGlobalMessageStore()

const message = computed(() => globalMessageStore.message)
</script>

<template>
  <div :id="'message-toast-' + id" class="item-main">
    <a :id="'text-message-toast-' + id">message </a>
    <div :id="'body-message-toast-' + id">{{ message }}</div>
  </div>
</template>

<style scoped>
.item-main {
  grid-area: main;
  white-space: pre-wrap;
  word-break: break-word;
}
</style>
