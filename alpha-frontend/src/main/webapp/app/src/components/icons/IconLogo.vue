<script setup lang="ts">
import {nextTick, onMounted, ref, watch} from "vue";

const props = defineProps<{
  textInput?: string
}>();

const svgRef = ref<SVGSVGElement | null>(null);
const textRef = ref<SVGTextElement | null>(null);

// Dimensions réactives pour le viewBox
const svgWidth:  Ref<number, number> = ref<number>(0);
const svgHeight:  Ref<number, number> = ref<number>(0);

onMounted(() => {
  // Quand le composant est monté, on attend la mise à jour du DOM
  nextTick(measureText);
});

watch(() => props.textInput, () => {
  nextTick(measureText);
});

function measureText() {
  if (textRef.value) {
    // getBBox() renvoie un objet { x, y, width, height }
    const bbox = textRef.value.getBBox();

    svgWidth.value = 450;
    svgHeight.value = bbox.height || 50;
  }
}
</script>

<template>
  <div class="svg-wrapper">
    <svg
      ref="svgRef"
      :viewBox="`0 0 ${svgWidth} ${svgHeight}`"
      preserveAspectRatio="xMidYMid meet"
      xmlns="http://www.w3.org/2000/svg"
    >

      <text
        ref="textRef"
        x="50%"
        y="50%"
        fill="#ffffff"
        dominant-baseline="middle"
        text-anchor="middle"
        letter-spacing="0.05em"
      >
        {{ textInput }}
      </text>
    </svg>
  </div>

</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.svg-wrapper {
  display: contents;
  width: 100%;
  /* Donnez une hauteur ou un ratio pour que l’SVG ait un espace vertical */
  aspect-ratio: 2 / 1; // par exemple
  background: #222;
}

svg {
  width: 100%;
  height: mylib.$header-height-size;;
}
text {
  font-size: 100px;
  width: 100%;
  font-family: BebasNeue, sans-serif
}
</style>
