<script setup lang="ts">

import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
const modalCustomStore = useModalCustomStore();

</script>

<template>
  <Teleport to="body">
    <Transition name="modal">
      <div v-if="modalCustomStore.isOpen" class="modal-mask">
        <div class="modal-container">
          <component :is="modalCustomStore.modalOption" />
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
  width: max-content;
  margin: auto;
  padding: 20px 30px;
  background-color: mylib.$color-background-global;
  border-radius: 4px;
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
