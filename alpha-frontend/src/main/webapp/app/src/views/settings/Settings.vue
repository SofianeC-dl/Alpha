<script setup lang="ts">

import MenuTabs from "@/components/menu/tabs/MenuTabs.vue";
import ProjectSettings from "@/views/menu/tabs/ProjectSettings.vue";
import TagSettings from "@/views/menu/tabs/TagSettings.vue";
import GlobalSettings from "@/views/menu/tabs/GlobalSettings.vue";

const tabs = [
  { name: 'projectSettings', label: 'Projects', component: ProjectSettings },
  { name: 'tagSettings', label: 'Tags', component: TagSettings },
  { name: 'globalSettings', label: 'Global', component: GlobalSettings }
]

</script>

<template>
  <MenuTabs :tabs="tabs" default-open-tabs="projectSettings"/>
</template>

<style scoped lang="scss">

</style>
