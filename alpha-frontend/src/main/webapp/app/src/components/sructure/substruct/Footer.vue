<script setup lang="ts">
/** Imports **/
import { onMounted } from 'vue'

defineProps({})

/** Methods **/
onMounted(() => {
  console.log(`the Header component is now mounted.`)
})
</script>

<template>
  <footer class="main-footer">
    <p>Here's some contact info</p>
  </footer>
</template>

<style scoped>
.main-footer {
  background-color: #242424;
  color: #ddd;
  text-align: center;
}
</style>
