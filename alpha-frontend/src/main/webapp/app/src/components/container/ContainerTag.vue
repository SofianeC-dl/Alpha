<script setup lang="ts">

import Tag from "@/components/tag/Tag.vue";

const props = defineProps({
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
  <div class="main-container-tag">
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
