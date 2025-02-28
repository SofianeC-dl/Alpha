<script setup lang="ts">
/** Imports **/
import { onMounted } from 'vue'
import ApiStruct from '@/components/sructure/apistruct/ApiStruct.vue'

defineProps({})

/** Methods **/
onMounted(() => {
  console.log(`the BaseLayout component is now mounted.`)
})
</script>

<template>
  <main class="site-main">
    <router-view />
  </main>
</template>

<style scoped>
.site-main {
  flex: 1;
  background-color: var(--vt-background-color);
  color: var(--vt-c-white);
}
</style>
