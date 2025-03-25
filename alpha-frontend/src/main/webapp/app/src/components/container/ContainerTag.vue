<script setup lang="ts">

import Tag from "@/components/tag/Tag.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = defineProps({
  id: {
    type: String,
    default: IdUtils.generateRandomId()
  },
  tagList: {
    type: Array,
    default: () => []
  }
});

const emits = defineEmits(['update:tagList']);

const removeTag = (tagEmit: string) => {
  const newList = props.tagList.filter(tag => tag.label !== tagEmit);
  emits('update:tagList', newList);
}
</script>

<template>
  <div :id="'container-tag-' + id" class="main-container-tag" role="list">
    <Tag v-for="tag of tagList" :label="tag.label" :color="tag.color" @remove-tag="removeTag"/>
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.main-container-tag {
  width: 100%;
  height: max-content;
  display: flex;
  flex-wrap: wrap;
  gap: inherit;
}
</style>
