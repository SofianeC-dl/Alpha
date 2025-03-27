<script setup lang="ts">
import IconLogo from "@/components/icons/IconLogo.vue";
import {IdUtils} from "@/composables/utils/id/idUtils.js";
import {computed} from "vue";
import {JsonParseUtils} from "@/composables/utils/json/JsonParseUtils.js";

const props = withDefaults(defineProps<{
  id?: string,
  text?: string
}>(), {
  id: IdUtils.generateRandomId(),
  text: 'logo.default'
});

const convertLabelName = computed(() => JsonParseUtils.getTextFromTextJsonDict(props.text));

</script>

<template>
    <router-link to="/" role="button">
      <IconLogo :text-input="convertLabelName" />
    </router-link>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
</style>
