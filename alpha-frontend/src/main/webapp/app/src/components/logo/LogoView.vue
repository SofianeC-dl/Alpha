<script setup lang="ts">
import IconLogo from "@/components/icons/IconLogo.vue";

const props = defineProps<{
  text?: string
}>()
</script>

<template>
      <router-link to="/" >
        <IconLogo text-input="Archive.rar" />
      </router-link>
</template>

<style lang="scss" scoped>
@use '@/assets/css/index' as mylib;
</style>
