<script setup lang="ts">
import IconLogo from "@/components/icons/IconLogo.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";

const props = withDefaults(defineProps<{
  id?: string,
  text?: string
}>(), {
  id: IdUtils.generateRandomId(),
  text: 'Archive.rar'
});
</script>

<template>
      <router-link to="/" role="button">
        <IconLogo :text-input="text" />
      </router-link>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
</style>
