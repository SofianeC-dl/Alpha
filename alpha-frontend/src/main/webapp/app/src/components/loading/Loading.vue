<script setup lang="ts">

import {useLoadingStore} from "@/stores/loading/loadingStore.js";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const loadingStore = useLoadingStore();

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});
</script>

<template>
  <Teleport :id="'teleport-loading-' + id" to="body">
    <Transition :id="'transition-loading-' + id" name="loading">
      <div :id="'loading-' + id" v-if="loadingStore.isLoading" class="modal-mask" role="status">
        <div :id="'container-loading-' + id" class="modal-container" :style="{ '--size-modal': SizeEnum.SMALL}">
          <div :id="'overlay-loading-' + id" class="loading-overlay">
            <div :id="'spinner-loading-' + id" class="spinner">
              Chargement...
            </div>
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
  z-index: 10001;
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
