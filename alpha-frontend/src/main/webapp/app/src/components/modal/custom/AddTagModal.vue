<script setup lang="ts">

import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import {FlexDirectionEnum, FlexPositionEnum} from "@/assets/enum/FlexEnum.js";
import ButtonAction from "@/components/button/ButtonAction.vue";
import InputReact from "@/components/input/InputReact.vue";
import ContainerItems from "@/components/container/ContainerItems.vue";

const modalCustom = useModalCustomStore();

const closeModal = () => {
  modalCustom.close();
}
</script>

<template>
  <ContainerSlot :direction="FlexDirectionEnum.COLUMN">
    <ContainerSlot :position-content="FlexPositionEnum.LEFT" :position-item="FlexPositionEnum.LEFT">
      <InputReact place-holder="Nature" label-input="Tag search" model-value=""/>
      <ContainerItems>
        <InputReact place-holder="Nature" label-input="Tag search" model-value=""/>
        <InputReact place-holder="Nature" label-input="Tag search" model-value=""/>
        <InputReact place-holder="Nature" label-input="Tag search" model-value=""/>
      </ContainerItems>
    </ContainerSlot>

    <ContainerSlot :position-content="FlexPositionEnum.RIGHT" :position-item="FlexPositionEnum.RIGHT">
      <ButtonAction :function-click="closeModal" label-button="exit" />
    </ContainerSlot>
  </ContainerSlot>
</template>

<style scoped lang="scss">

</style>
