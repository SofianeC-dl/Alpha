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

main {
  background: mylib.$color-background-main-view;
  border-radius: mylib.$main-border-radius;
  padding: mylib.$margin-global;

  box-shadow: -4px -4px 4px rgba(17, 17, 17, 0.5);
}
</style>
