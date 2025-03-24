<script setup lang="ts">

import ButtonAction from "@/components/button/ButtonAction.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
  label: {
    type: String,
    default: '[No_label]'
  },
  color: {
    type: String,
    default: '#dddddd'
  }
})

const emit = defineEmits(['removeTag']);

const removeTag = () => {
  emit('removeTag', props.label);
}
</script>

<template>
  <div :id="'tag-' + id" class="main-tag" :style="{'--color-tag': color}">
    {{ label }}
    <ButtonAction :not-selected-box="true" color-text="#222222" :function-click="removeTag"/>
  </div>
</template>

<style scoped lang="scss">

.main-tag {
  display: flex;
  height: 20px;
  width: max-content;
  padding: 5px;
  gap: 5px;
  border-radius: 5px;
  background-color: var(--color-tag);
  color: #222222;
}
</style>
