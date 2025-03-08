<script setup lang="ts">
import { computed } from 'vue'
import { useGlobalMessageStore } from '@/stores/globalError/globalMessageStore.ts'

const globalMessageStore = useGlobalMessageStore()

const title = computed(() => globalMessageStore.title)
</script>

<template>
  <div class="main-button">
    <div class="item-icon">
    </div>
    <a class="item-title">
      {{ title }}
    </a>
  </div>
</template>

<style scoped>

.main-button {
  display: grid;
  grid-template-rows: 2em;
  grid-template-columns: 2em 2px auto 60px;
  grid-template-areas:
    'icon . title title';
}
.item-title {
  grid-area: title;
  font-size: 2em;
}

.item-icon {
  grid-area: icon;
}
</style>
