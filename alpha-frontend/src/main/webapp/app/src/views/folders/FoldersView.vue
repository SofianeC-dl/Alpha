<script setup lang="ts">
import FolderDetail from "@/views/folders/FolderDetail.vue";
import MenuSection from "@/components/menu/section/MenuSection.vue";
import MenuSectionContainer from "@/components/menu/section/MenuSectionContainer.vue";
import InputReact from "@/components/input/InputReact.vue";
import {Ref, ref} from "vue";
import {FlexDirectionEnum, FlexPositionEnum} from "@/composables/enum/FlexEnum.js";
import {SizeEnum} from "@/composables/enum/sizeEnum.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";

const inputText : Ref<string, string> = ref<string>('');
</script>

<template>
  <MenuSectionContainer>
    <MenuSection title-section="component.menu.section.title.search" aria-label-section="component.menu.section.title.search">
        <ContainerSlot :direction="FlexDirectionEnum.COLUMN" :position-content="FlexPositionEnum.LEFT" gap="10px">
          <InputReact v-model="inputText" :size="SizeEnum.SMALL"/>
          <InputReact v-model="inputText" :size="SizeEnum.SMALL"/>
          <InputReact v-model="inputText" :size="SizeEnum.SMALL"/>
          <InputReact v-model="inputText" :size="SizeEnum.SMALL"/>
        </ContainerSlot>
    </MenuSection>
  </MenuSectionContainer>
  <div class="container-folders" role="group">
    <div class="group-folder">
      {{ inputText }}
      <FolderDetail />
      <FolderDetail />
      <FolderDetail />
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;

.container-folders {
  display: flex;
  flex-direction: row;
  align-content: space-between;
  flex-wrap: wrap;

  @media(max-width: mylib.$media-size-menu) {
    justify-content: space-between;
  }

  @media(max-width: mylib.$media-size-minimal-folders) {
    justify-content: center;
  }
}

.group-folder {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 1rem;
}
</style>
