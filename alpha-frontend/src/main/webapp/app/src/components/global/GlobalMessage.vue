<script setup lang="ts">
import {useGlobalMessageStore} from "@/stores/globalError/globalMessageStore";
import {computed} from "vue";

const globalMessageStore = useGlobalMessageStore()

const message = computed(() => globalMessageStore.message)
const type = computed(() => globalMessageStore.type)
const isOpen = computed(() => globalMessageStore.isOpenned)

</script>

<template>
  <Teleport to="body">
      <div v-if="isOpen" :class="['global-message-params', type]">
        <div class="item-a">
          {{ message }}
        </div>
        <button @click="globalMessageStore.clearMessage()" class="item-b">Close</button>
      </div>
  </Teleport>
</template>

<style>
.global-message-params {
  position: fixed;
  top: 0;
  right: 5px;
  width: 250px;
  min-height: 100px;
  border-left: 1px solid #dddddd;
  border-right: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
  border-radius: 0 0 5px 5px;
  font-weight: bold;
  box-sizing: border-box;
  background-color: #dddddd;
  z-index: 9999;
  display: grid;
  grid-template-rows: 2px auto 2px 20px 2px;
  grid-template-columns: 2px auto 60px 2px;
  grid-template-areas:
    ". . . ."
    ". main main ."
    ". . . ."
    ". . button ."
    ". . . .";
}

.global-message-params.error {
  border-color: #e74c3c;
  color: #e74c3c;
}

.global-message-params.success {
  border-color: #2ecc71;
  color: #2ecc71;
}

.global-message-params.info {
  border-color: #3498db;
  color: #3498db;
}

.global-message-params.warn {
  border-color: #e1be2b;
  color: #e1be2b;
}

.item-a {
  grid-area: main;
}

.item-b {
  grid-area: button;
}

</style>
