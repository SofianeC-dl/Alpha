<script setup lang="ts">
import { useGlobalMessageStore } from '@/stores/globalMesage/globalMessageStore'
import { computed } from 'vue'
import HeaderMessage from '@/components/message/HeaderMessage.vue'
import MessageToastToast from '@/components/message/MessageToast.vue'
import ButtonAction from "@/components/button/ButtonAction.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const globalMessageStore = useGlobalMessageStore();

const type = computed(() => globalMessageStore.type);
const isOpen = computed(() => globalMessageStore.isOpenned);

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  }
});

const roleMessage = computed(() => {
  return type.value === TypeMessageEnum.ERROR ? 'alert' : 'status';
});
</script>

<template>
  <Teleport :id="'teleport-global-message-' + id" to="body">
    <div :id="'global-message-' + id" v-if="isOpen" :class="['global-message-params', type]" :role="roleMessage">
      <ButtonAction :function-click="globalMessageStore.clearMessage" class="item-button" :not-selected-box="true" aria-label-button="component.button.closedtoast"/>
      <HeaderMessage class="item-header" :class="type"/>
      <MessageToastToast />
    </div>
  </Teleport>
</template>

<style lang="scss">
@use '@/assets/css/index' as mylib;

.global-message-params {
  position: fixed;
  z-index: mylib.$message-z-index;

  bottom: 0;
  right: 5%;
  width: 25%;
  min-width:  mylib.$message-min-width;
  min-height: mylib.$message-min-height;
  box-sizing: border-box;

  border-left: mylib.$message-border-size solid mylib.$color-font-global;
  border-right: mylib.$message-border-size solid mylib.$color-font-global;
  border-top: mylib.$message-border-size solid mylib.$color-font-global;

  border-radius: mylib.$message-border-radius mylib.$message-border-radius 0 0;
  background-color: rgba(mylib.$color-background-global, mylib.$color-message-opacity);

  display: grid;
  grid-template-rows:
    mylib.$message-margin
    mylib.$message-header-size
    mylib.$message-space-between
    auto
    mylib.$message-margin;
  grid-template-columns: mylib.$message-margin mylib.$message-header-size mylib.$message-space-between  auto min-content mylib.$message-margin ;
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
  width: mylib.$message-header-item-size;
}

.item-main {
  grid-area: main;
  white-space: pre-wrap;
  word-break: break-word;
}

.item-icon {
  grid-area: icon;
  width: mylib.$message-icon-item-width;
  height: mylib.$message-icon-item-height;
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
