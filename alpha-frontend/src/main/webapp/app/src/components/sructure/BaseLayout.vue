<script setup lang="ts">
/** Imports **/
import Header from './substruct/Header.vue'
import Footer from './substruct/Footer.vue'

import { onMounted } from 'vue'
import MainView from './substruct/MainView.vue'
import GlobalMessage from '@/components/message/GlobalMessage.vue'

defineProps({})

/** Methods **/
onMounted(() => {
  console.log(`the BaseLayout component is now mounted.`)
})
</script>

<template>
  <div class="main-layout">
    <GlobalMessage />
    <Header class="main-header" />
    <MainView class="main-view" />
    <Footer class="main-footer" />
  </div>
</template>

<style scoped>
.main-layout {
  display: grid;
  grid-template-columns: 5px auto 5px;
  grid-template-rows: auto auto auto;
  grid-template-areas:
    'header header header'
    '. main .'
    'footer footer footer';
}

.main-header {
  grid-area: header;
}

.main-view {
  grid-area: main;
}

.main-footer {
  grid-area: footer;
}
</style>
