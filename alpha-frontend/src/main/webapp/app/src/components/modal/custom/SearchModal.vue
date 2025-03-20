<script setup lang="ts">

import ButtonAction from "@/components/button/ButtonAction.vue";
import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";

const modalCustom = useModalCustomStore();

const closeModal = () => {
  modalCustom.close();
}
</script>

<template>
  test modal custom

  <ButtonAction :function-click="closeModal" />
</template>

<style scoped lang="scss">

</style>
