<script setup lang="ts">

import MenuMobile from "@/components/menu/MenuMobile.vue";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import ButtonAction from "@/components/button/ButtonAction.vue";
import ButtonsContainer from "@/components/button/ButtonsContainer.vue";
import {FlexDirectionEnum} from "@/assets/enum/FlexEnum.js";
</script>

<template>
  <Teleport to="body">
    <MenuMobile>
      <ButtonsContainer :direction="FlexDirectionEnum.COLUMN">
        <ButtonAction class="margin-button-menu" label-button="larger" :size="SizeEnum.MEDIUM"/>
        <ButtonAction class="margin-button-menu" label-button="medium" :size="SizeEnum.MEDIUM"/>
        <ButtonAction class="margin-button-menu" label-button="small" :size="SizeEnum.MEDIUM"/>
      </ButtonsContainer>
    </MenuMobile>
  </Teleport>
</template>

<style scoped lang="scss">
.margin-button-menu {
  margin-bottom: 10px;
}
</style>
