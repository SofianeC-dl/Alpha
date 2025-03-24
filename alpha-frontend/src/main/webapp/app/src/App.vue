<script setup lang="ts">
import {useTagStore} from "@/stores/tag/tagStore.js";
import {onMounted} from "vue";

const tagStore = useTagStore();

onMounted(() => {
  tagStore.reloadTag();
})
</script>

<template>
  <router-view></router-view>
</template>
