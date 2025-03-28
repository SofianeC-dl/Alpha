<script setup lang="ts">

import {useModalStore} from "@/stores/modal/modalStore.js";
import {ModalUtils} from "@/composables/utils/modal/ModalUtils.js";
import ButtonAction from "@/components/button/ButtonAction.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";
import {nextTick, watch} from "vue";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});

const modalStore = useModalStore();

watch(() => modalStore.isOpen, async (newVal) => {
  if (newVal) {
    await nextTick()
    firstElement.value.focus()
  }
});

const handleTabKey = (event) => {
  const focusableElements = modal.value.querySelectorAll(
    'ButtonAction'
  )
  const focusable = Array.from(focusableElements).filter(el => !el.disabled)
  const first = focusable[0]
  const last = focusable[focusable.length - 1]

  if (!event.shiftKey && document.activeElement === last) {
    event.preventDefault()
    first.focus()
  }

  else if (event.shiftKey && document.activeElement === first) {
    event.preventDefault()
    last.focus()
  }
}
</script>

<template>
  <Teleport :id="'teleport-modal-' + id" to="body" aria-modal="true">
    <Transition :id="'transition-modal-' + id" name="modal" role="dialog" aria-modal="true">
      <div :id="'modal-' + id" v-if="modalStore.isOpen" class="modal-mask" @keydown.tab="handleTabKey">
        <div :id="'container-modal-' + id" class="modal-container" :style="{ '--size-modal': ModalUtils.convertSize(modalStore.modalOption.size)}">
          <div :id="'header-modal-' + id" class="modal-header">
            {{ modalStore.modalOption.title }}
          </div>

          <div :id="'body-modal-' + id" class="modal-body">
           {{ modalStore.modalOption.body }}
          </div>

          <div :id="'footer-modal-' + id" class="modal-footer">
              <ButtonAction class="modal-default-button" :function-click="modalStore.close" label-button="component.button.ok" aria-label-button="component.button.okmodal"/>
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
