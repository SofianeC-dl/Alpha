<script setup lang="ts">
/** Imports **/
import { onMounted } from 'vue'

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
}
</style>
