<script setup lang="ts">
/** Imports **/
import HeaderView from '../../views/structure/HeaderView.vue'
import FooterView from '../../views/structure/FooterView.vue'

import { onMounted } from 'vue'
import MainView from '../../views/structure/MainView.vue'
import GlobalMessage from '@/components/message/GlobalMessage.vue'
import Modal from "@/components/modal/Modal.vue";
import MenuBurger from "@/components/menu/burger/MenuBurger.vue";
import Loading from "@/components/loading/Loading.vue";
import ModalCustom from "@/components/modal/ModalCustom.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
})

/** Methods **/
onMounted(() => {
  console.log(`the BaseLayout component is now mounted.`)
})
</script>

<template>
    <div :id="'base-layout-' + id" class="main-layout">
      <Loading />
      <GlobalMessage />
      <Modal />
      <ModalCustom />
      <MenuBurger />
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
  grid-template-columns: mylib.$margin-global auto mylib.$margin-global;
  grid-template-rows:
    mylib.$margin-global
    mylib.$header-height-size
    mylib.$margin-global
    auto
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
