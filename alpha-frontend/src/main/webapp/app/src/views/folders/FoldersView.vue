<script setup lang="ts">
import FolderDetail from "@/views/folders/FolderDetail.vue";
import {useModalStore} from "@/stores/modal/modalStore.js";
import MenuSection from "@/components/menu/section/MenuSection.vue";
import MenuSectionContainer from "@/components/menu/section/MenuSectionContainer.vue";

const modalStore = useModalStore();

</script>

<template>
  <MenuSectionContainer>
    <MenuSection title="Search">
      TEST
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      test
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      TEST
    </MenuSection>
  </MenuSectionContainer>
  <div class="container-folders">
    <div class="group-folder">
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
