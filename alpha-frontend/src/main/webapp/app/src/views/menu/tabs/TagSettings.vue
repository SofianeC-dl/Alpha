<script setup lang="ts">

import ButtonAction from "@/components/button/ButtonAction.vue";
import {SizeEnum} from "@/composables/enum/sizeEnum.js";
import {markRaw} from "vue";
import AddTagModal from "@/views/modal/AddTagModal.vue";
import {useModalCustomStore} from "@/stores/modal/modalCustomStore.js";
import TableData from "@/components/table/TableData.vue";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import {FlexPositionEnum} from "@/composables/enum/FlexEnum.js";
import {DataRow, TableHeader} from "@/composables/object/TableObject.js";
import {TableEnumTextAlign} from "@/composables/enum/TableEnum.js";
import {useTagStore} from "@/stores/tag/tagStore.js";
import {ColorUtils} from "@/composables/utils/color/ColorUtils.js";

const modalCustomStore = useModalCustomStore();
const tagStore = useTagStore();

const openAddTagModal = () => {
  modalCustomStore.modalSize = SizeEnum.MEDIUM;
  modalCustomStore.open(markRaw(AddTagModal));
}

const data: DataRow[] = [
  { label: 1, nom: 'Jean Dupont', email: 'jean.dupont@example.com', age: 32 },
  { tagname: 2, nom: 'Marie Curie', email: 'marie.curie@example.com', age: 45 },
  { tagname: 3, nom: 'Pierre Martin', email: 'pierre.martin@example.compierre.martin@example.compierre.martin@example.compierre.martin@example.compierre.martin@example.com', age: 28 }
]

const header: TableHeader[] = [
  { key: 'label', label: 'Tag Name', size: 'auto' },
  { key: 'color', label: 'Color', size: '40%'}
]
</script>

<template>
  <ContainerSlot>
    <ContainerSlot style="width: 50%" :position-item="FlexPositionEnum.CENTER" >
      <ButtonAction label-button="component.button.addtags" :size="SizeEnum.SMALL" :function-click="openAddTagModal" aria-label-button="component.button.addtagsheader" />
    </ContainerSlot>
    <TableData :header="header" :data="tagStore.tagList.tagList" style="flex: 1" :text-align-header="TableEnumTextAlign.CENTER" >
      <template #cell-color="{ row }" >
        <div class="container-cell-color" :style="{ '--color-tag': row.color, '--color-font': ColorUtils.contrastColor(row.color) }">
          {{ row.color }}
        </div>
      </template>
    </TableData>
  </ContainerSlot>
</template>

<style scoped lang="scss">

.container-cell-color {
  background-color: var(--color-tag);
  color: var(--color-font);
  width: 100%;
  height: 100%;
  text-align: center;
}
</style>
