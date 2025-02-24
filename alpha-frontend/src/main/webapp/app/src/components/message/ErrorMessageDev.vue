<script setup lang="ts">
import { computed } from 'vue'
import { useGlobalMessageStore } from '@/stores/globalError/globalMessageStore.ts'

const globalMessageStore = useGlobalMessageStore()

const status = computed(() => globalMessageStore.status)
const typeError = computed(() => globalMessageStore.typeError)
const message = computed(() => globalMessageStore.message)
const request = computed(() => globalMessageStore.request)
const title = computed(() => globalMessageStore.title)
</script>

<template>
  <div class="item-main">
    <a>status </a>
    <div>"{{ status }}"</div>
    <br />
    <a>typeError </a>
    <div>"{{ typeError }}"</div>
    <br />
    <a>message </a>
    <div>"{{ message }}"</div>
    <br />
    <a>request </a>
    <div>"{{ request }}"</div>
  </div>
</template>

<style scoped>
.item-main {
  grid-area: main;
  white-space: pre-wrap;
  word-break: break-word;
}
</style>
