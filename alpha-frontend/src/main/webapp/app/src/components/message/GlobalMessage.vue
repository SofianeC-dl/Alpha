<script setup lang="ts">
import { useGlobalMessageStore } from '@/stores/globalError/globalMessageStore'
import { computed } from 'vue'
import HeaderMessage from '@/components/message/HeaderMessage.vue'
import MessageToastToast from '@/components/message/MessageToast.vue'

const globalMessageStore = useGlobalMessageStore()

const type = computed(() => globalMessageStore.type)
const isOpen = computed(() => globalMessageStore.isOpenned)
</script>

<template>
  <Teleport to="body">
    <div v-if="isOpen" :class="['global-message-params', type]">
      <HeaderMessage class="item-header" :class="type" />
      <MessageToastToast />
      <button @click="globalMessageStore.clearMessage()" class="item-button">Close</button>
    </div>
  </Teleport>
</template>

<style lang="scss">
@use '@/assets/css/index' as mylib;
.global-message-params {
  position: fixed;
  z-index: 9999;

  top: 0;
  right: 5px;
  width: 350px;
  min-height: 10vh;
  box-sizing: border-box;

  border-left: 1px solid #dddddd;
  border-right: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
  border-radius: 0 0 5px 5px;

  font-weight: bold;

  background-color: mylib.$color-background-global;

  display: grid;
  grid-template-rows: 5px 2em 2px auto 2px 20px 5px;
  grid-template-columns: 5px 2em 2px auto 60px 5px;
  grid-template-areas:
    '. .      .       .       .       .'
    '. header header  header  header   .'
    '. .      .       .       .       .'
    '. main   main    main    main    .'
    '. .      .       .       .       .'
    '. .      .       .       button  .'
    '. .      .       .       .       .';
}

.item-button {
  grid-area: button;
}

.item-header {
  grid-area: header;
}

.item-main {
  grid-area: main;
  white-space: pre-wrap;
  word-break: break-word;
}

.global-message-params.error {
  border-color: #e74c3c;
}

.global-message-params.error a {
  color: #e74c3c;
}

.global-message-params.success {
  border-color: #2ecc71;
}

.global-message-params.success a {
  color: #2ecc71;
}

.global-message-params.info {
  border-color: #3498db;
}

.global-message-params.info a {
  color: #3498db;
}

.global-message-params.warn {
  border-color: #e1be2b;
}

.global-message-params.warn a {
  color: #e1be2b;
}
</style>
