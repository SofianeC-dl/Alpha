<script setup lang="ts">

import {useModalStore} from "@/stores/modal/modalStore.js";
import {ModalUtils} from "@/composables/modal/ModalUtils.js";

const modalStore = useModalStore();

</script>

<template>
  <Teleport to="body">
    <Transition name="modal">
      <div v-if="modalStore.isOpen" class="modal-mask">
        <div class="modal-container" :style="{ '--size-modal': ModalUtils.convertSize(modalStore.modalOption.size)}">
          <div class="modal-header">
            {{ modalStore.modalOption.title }}
          </div>

          <div class="modal-body">
            <slot name="body"></slot>
          </div>

          <div class="modal-footer">
            <slot name="footer"></slot>
          </div>
        </div>
      </div>
    </Transition>
  </Teleport>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-container {
  width: var(--size-modal);
  margin: auto;
  padding: 20px 30px;
  background-color: mylib.$color-background-global;
  border-radius: 4px;
  //box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
