<script setup lang="ts">
import { useGlobalMessageStore } from '@/stores/globalMesage/globalMessageStore'
import { computed } from 'vue'
import HeaderMessage from '@/components/message/HeaderMessage.vue'
import MessageToastToast from '@/components/message/MessageToast.vue'
import Button from "@/components/button/Button.vue";

const globalMessageStore = useGlobalMessageStore()

const type = computed(() => globalMessageStore.type)
const isOpen = computed(() => globalMessageStore.isOpenned)
</script>

<template>
  <Teleport to="body">
    <div v-if="isOpen" :class="['global-message-params', type]">
      <Button :function-click="globalMessageStore.clearMessage" class="item-button" label-button="[X]" />
      <HeaderMessage class="item-header" :class="type"/>
      <MessageToastToast />
    </div>
  </Teleport>
</template>

<style lang="scss">
@use '@/assets/css/index' as mylib;

.global-message-params {
  position: fixed;
  z-index: 9999;

  bottom: 0;
  right: 5%;
  width: 25%;
  min-width:  19rem;
  min-height: 10vh;
  box-sizing: border-box;

  border-left: 1px solid mylib.$color-font-global;
  border-right: 1px solid mylib.$color-font-global;
  border-top: 1px solid mylib.$color-font-global;

  border-radius: 3px 3px 0 0;
  background-color: rgba(mylib.$color-background-global, 0.95);

  display: grid;
  grid-template-rows: 5px 2em 2px auto 5px;
  grid-template-columns: 5px 2em 2px auto min-content 5px;
  grid-template-areas:
    '. .      .       .       .       .'
    '. header header  .       button  .'
    '. .      .       .       .       .'
    '. main   main    main    main    .'
    '. .      .       .       .       .';
}

.item-button {
  place-items: flex-end;
  grid-area: button;
}

.item-header {
  place-items:center;
  grid-area: header;
  width: 100%;
}

.item-main {
  grid-area: main;
  white-space: pre-wrap;
  word-break: break-word;
}

.item-icon {
  grid-area: icon;
  width: 100%;
  height: 100%;
}
.global-message-params.error {
  border-color: mylib.$color-message-error;
}

.global-message-params.error a {
  color: mylib.$color-message-error;
}

.global-message-params.success {
  border-color: mylib.$color-message-success;
}

.global-message-params.success a {
  color: mylib.$color-message-success;
}

.global-message-params.info {
  border-color: mylib.$color-message-info;
}

.global-message-params.info a {
  color: mylib.$color-message-info;
}

.global-message-params.warn {
  border-color: mylib.$color-message-warn;
}

.global-message-params.warn a {
  color: mylib.$color-message-warn;
}
</style>
