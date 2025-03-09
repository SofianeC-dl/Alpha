<script setup lang="ts">
import FolderDetail from "@/views/folders/FolderDetail.vue";
import {useModalStore} from "@/stores/modal/modalStore.js";

const modalStore = useModalStore();

</script>

<template>
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


  @media(max-width: 572px) {
    justify-content: space-between;
  }
}

.group-folder {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 1rem;
}



</style>
