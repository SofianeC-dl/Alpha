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
  <main>
        <router-view  />
  </main>
</template>

<style lang="scss" scoped>
@use "@/assets/css/index" as mylib;

</style>
