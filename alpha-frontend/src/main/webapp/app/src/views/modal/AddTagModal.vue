<script setup lang="ts">

import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import {FlexDirectionEnum, FlexPositionEnum} from "@/assets/enum/FlexEnum.js";
import ButtonAction from "@/components/button/ButtonAction.vue";
import InputReact from "@/components/input/InputReact.vue";
import ContainerItems from "@/components/container/ContainerItems.vue";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import {FormatEnum} from "@/assets/enum/FormatEnum.js";
import ContainerTag from "@/components/container/ContainerTag.vue";
import {TagDto} from "@/generated/index.js";
import {ref, Ref} from "vue";
import {useTagStore} from "@/stores/tag/tagStore.js";

const modalCustom = useModalCustomStore();
const tagStore = useTagStore();

const tagList: Ref<Array<TagDto>, Array<TagDto>> = ref<Array<TagDto>>([]);

const inputTag: Ref<string, string> = ref<string>('');

const closeModal = () => {
  modalCustom.close();
}

const addTag = () => {
  const newTag: TagDto = {
    label: inputTag.value,
    color: '#dddddd'
  }

  tagList.value.push(newTag);

  inputTag.value = '';
  console.log(tagList.value);
};

const addTagAndExit = () => {
  tagStore.addTagList(tagList.value);

  closeModal();
}
</script>

<template>
  <ContainerSlot :direction="FlexDirectionEnum.COLUMN">
    <ContainerSlot :position-content="FlexPositionEnum.LEFT" :position-item="FlexPositionEnum.RIGHT" :width="FormatEnum.AUTO">
      <InputReact place-holder="component.input.placeholder.newtag" label-input="component.input.label.newtag" v-model="inputTag"/>
      <ButtonAction label-button="component.button.add" :size="SizeEnum.DEFAULT" :function-click="addTag"/>
    </ContainerSlot>
    <ContainerItems>
      <ContainerTag v-model:tag-list="tagList" @update:tagList="val => tagList = val"/>
    </ContainerItems>
    <ContainerSlot :position-content="FlexPositionEnum.RIGHT" :position-item="FlexPositionEnum.RIGHT">
      <ButtonAction :function-click="addTagAndExit" label-button="component.button.addtags" />
      <ButtonAction :function-click="closeModal" label-button="component.button.exit" />
    </ContainerSlot>
  </ContainerSlot>
</template>

<style scoped lang="scss">

</style>
