<script setup lang="ts">
/** Imports **/
import HeaderView from '../../views/structure/HeaderView.vue'
import FooterView from '../../views/structure/FooterView.vue'

import { onMounted } from 'vue'
import MainView from '../../views/structure/MainView.vue'
import GlobalMessage from '@/components/message/GlobalMessage.vue'
import Modal from "@/components/modal/Modal.vue";

defineProps({})

/** Methods **/
onMounted(() => {
  console.log(`the BaseLayout component is now mounted.`)
})
</script>

<template>
  <div class="main-layout">
    <GlobalMessage />
    <Modal />
    <HeaderView class="main-header" />
    <MainView class="main-view" />
    <FooterView class="main-footer" />
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.main-layout {
  min-height: mylib.$main-height-size;
  display: grid;
  grid-template-columns: mylib.$margin-global 1fr mylib.$margin-global;
  grid-template-rows:
    mylib.$margin-global
    mylib.$header-height-size
    mylib.$margin-global
    1fr
    mylib.$margin-global
    mylib.$footer-height-size
    mylib.$margin-global;
  grid-template-areas:
    '.      .       .'
    '.      header  .'
    '.      .       .'
    '.      main    .'
    '.      .       .'
    'footer footer  footer'
    '.      .       .';
}

.main-header {
  height: mylib.$header-height-size;
  grid-area: header;
}

.main-view {
  height: max-content;
  grid-area: main;
  box-sizing: border-box;
}

.main-footer {
  height: mylib.$footer-height-size;
  grid-area: footer;
}
</style>
