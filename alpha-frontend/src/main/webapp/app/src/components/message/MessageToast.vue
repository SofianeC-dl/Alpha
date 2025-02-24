<script setup lang="ts">
import { useGlobalMessageStore } from '@/stores/globalError/globalMessageStore.ts'
import { computed } from 'vue'

const globalMessageStore = useGlobalMessageStore()

const message = computed(() => globalMessageStore.message)
</script>

<template>
  <div class="item-main">
    <a>message </a>
    <div>{{ message }}</div>
  </div>
</template>

<style scoped>
.item-main {
  grid-area: main;
  white-space: pre-wrap;
  word-break: break-word;
}
</style>
