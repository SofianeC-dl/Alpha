<script setup lang="ts">

import IconFolderProject from "@/components/icons/IconFolderProject.vue";
</script>

<template>
  <button class="icon-button" aria-label="Mon bouton">
    <IconFolderProject />
  </button>
</template>

<style scoped>
.icon-button {
  background: none;    /* Retire le style par défaut du bouton */
  border: none;        /* Retire la bordure par défaut */
  cursor: pointer;     /* Curseur “pointeur” au survol */
  padding: 0.5rem;
  transition: transform 0.2s ease;
}

/* On applique la couleur de base via currentColor ou en fixant fill sur l’élément SVG */
.icon-button svg {
  width: 100%;
  height: 100%;
  stroke: #000; /* Couleur de base */
  transition: fill 0.2s ease;
}

/* Au survol */
.icon-button:hover svg {
  fill: #f00; /* Changement de couleur au hover */
}

/* Effet d’agrandissement du bouton lui-même au survol (optionnel) */
.icon-button:hover {
  transform: scale(1.1);
}
</style>
