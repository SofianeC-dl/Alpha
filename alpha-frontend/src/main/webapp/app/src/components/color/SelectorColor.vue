<script setup lang="ts">
import { computed, nextTick, onMounted, ref, watch, PropType } from 'vue';
import { ColorUtils } from "@/composables/utils/color/ColorUtils.js";
import { RGB } from "@/composables/object/Colors.js";
import InputReact from "@/components/input/InputReact.vue";
import { SizeEnum } from "@/composables/enum/sizeEnum.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import { FlexDirectionEnum } from "@/composables/enum/FlexEnum.js";
import { SizeBox } from "@/composables/object/SizeBox.js";
import { HexColorSquareUtils } from "@/composables/utils/color/HexColorSquareUtils.js";

// Props (y compris modelValue pour v-model)
const props = defineProps({
  size: {
    type: String as PropType<SizeEnum>,
    default: SizeEnum.DEFAULT
  },
  modelValue: {
    type: String,
    default: '#FFFFFFFF'
  }
});

// Émission pour v-model
const emit = defineEmits<{
  (e: 'update:modelValue', newHex: string): void;
}>();

// Taille du composant
const widthColorSquare = ref('');
const heightColorSquare = ref('');

// Références DOM
const svRef = ref<HTMLDivElement | null>(null);
const hueRef = ref<HTMLDivElement | null>(null);
const alphaRef = ref<HTMLDivElement | null>(null);
const previewBoxRef = ref<HTMLDivElement | null>(null);

// État HSV + alpha
const hue = ref(0);        // 0 à 360°
const saturation = ref(1); // 0 à 1
const value = ref(1);      // 0 à 1
const alpha = ref(1);      // 0 à 1

// Valeur hexadécimale interne initialisée à partir du modelValue
const hexValue = ref(props.modelValue);

// Calcul de la couleur RGB (renommé en internalRgb pour éviter tout conflit)
const internalRgb = computed<RGB>(() =>
  ColorUtils.hsvToRgb(hue.value, saturation.value, value.value)
);

// Dès que hue, saturation, value ou alpha changent, on recalcule hexValue
watch([hue, saturation, value, alpha], () => {
  const { r, g, b } = internalRgb.value;
  hexValue.value = ColorUtils.rgbAtoHex8({ r, g, b, a: alpha.value });
}, { immediate: true });

// Dès que hexValue change, on émet l'événement pour v-model
watch(hexValue, (newVal) => {
  emit('update:modelValue', newVal);
});

// Si la valeur externe change, on met à jour hexValue
watch(() => props.modelValue, (newVal) => {
  if (newVal !== hexValue.value) {
    hexValue.value = newVal;
  }
});

// Couleur CSS finale
const rgbaColor = computed(() => {
  const { r, g, b } = ColorUtils.hsvToRgb(hue.value, saturation.value, value.value);
  return `rgba(${r}, ${g}, ${b}, ${alpha.value})`;
});

// Mise à jour depuis la saisie manuelle
function onHexInput() {
  let cleaned = hexValue.value.trim();
  if (!cleaned.startsWith('#')) cleaned = '#' + cleaned;
  const rgba = ColorUtils.hex8ToRgba(cleaned);
  if (rgba) {
    ColorUtils.rgbToHsv(rgba, hue, saturation, value);
    alpha.value = rgba.a;
    hexValue.value = cleaned.toUpperCase();
  }
}

/* --- Position des pointeurs --- */
// Tailles (en pixels) des pointeurs
const pointerSizeSV = 12;
const pointerSizeHue = 10;
const pointerSizeAlpha = 10;

const svPointerLeft = ref(0);
const svPointerTop = ref(0);
const huePointerLeft = ref(0);
const alphaPointerLeft = ref(0);

// Carré Saturation/Value : on place le pointeur de manière à ce que son centre corresponde aux coordonnées calculées
function updateSVPointer() {
  const box = svRef.value?.getBoundingClientRect();
  if (!box) return;
  svPointerLeft.value = saturation.value * box.width;
  svPointerTop.value  = (1 - value.value) * box.height;
}
watch([saturation, value], updateSVPointer, { immediate: true });

function startDragSV(e: MouseEvent | TouchEvent) {
  const moveFn = (event: MouseEvent | TouchEvent) => {
    const { clientX, clientY } = getClientXY(event);
    const box = svRef.value?.getBoundingClientRect();
    if (!box) return;
    const x = Math.max(0, Math.min(box.width, clientX - box.left));
    const y = Math.max(0, Math.min(box.height, clientY - box.top));
    saturation.value = x / box.width;
    value.value = 1 - y / box.height;
  };
  const upFn = () => {
    window.removeEventListener('mousemove', moveFn as any);
    window.removeEventListener('touchmove', moveFn as any);
    window.removeEventListener('mouseup', upFn);
    window.removeEventListener('touchend', upFn);
  };
  moveFn(e);
  window.addEventListener('mousemove', moveFn as any);
  window.addEventListener('touchmove', moveFn as any);
  window.addEventListener('mouseup', upFn);
  window.addEventListener('touchend', upFn);
}

// Slider Hue
function updateHuePointer() {
  const box = hueRef.value?.getBoundingClientRect();
  if (!box) return;
  huePointerLeft.value = (hue.value / 360) * box.width;
}
watch(hue, updateHuePointer);

function startDragHue(e: MouseEvent | TouchEvent) {
  const moveFn = (event: MouseEvent | TouchEvent) => {
    const { clientX } = getClientXY(event);
    const box = hueRef.value?.getBoundingClientRect();
    if (!box) return;
    const x = Math.max(0, Math.min(box.width, clientX - box.left));
    hue.value = (x / box.width) * 360;
  };
  const upFn = () => {
    window.removeEventListener('mousemove', moveFn as any);
    window.removeEventListener('touchmove', moveFn as any);
    window.removeEventListener('mouseup', upFn);
    window.removeEventListener('touchend', upFn);
  };
  moveFn(e);
  window.addEventListener('mousemove', moveFn as any);
  window.addEventListener('touchmove', moveFn as any);
  window.addEventListener('mouseup', upFn);
  window.addEventListener('touchend', upFn);
}

// Slider Alpha
// On calcule la position du centre du pointeur alpha en le clampant pour qu'il reste à l'intérieur
function updateAlphaPointer() {
  const box = alphaRef.value?.getBoundingClientRect();
  if (!box) return;
  alphaPointerLeft.value = pointerSizeAlpha / 2 + alpha.value * (box.width - pointerSizeAlpha);
}
watch(alpha, updateAlphaPointer);

function startDragAlpha(e: MouseEvent | TouchEvent) {
  const moveFn = (event: MouseEvent | TouchEvent) => {
    const { clientX } = getClientXY(event);
    const box = alphaRef.value?.getBoundingClientRect();
    if (!box) return;
    const x = Math.max(pointerSizeAlpha / 2, Math.min(box.width - pointerSizeAlpha / 2, clientX - box.left));
    alpha.value = (x - pointerSizeAlpha / 2) / (box.width - pointerSizeAlpha);
  };
  const upFn = () => {
    window.removeEventListener('mousemove', moveFn as any);
    window.removeEventListener('touchmove', moveFn as any);
    window.removeEventListener('mouseup', upFn);
    window.removeEventListener('touchend', upFn);
  };
  moveFn(e);
  window.addEventListener('mousemove', moveFn as any);
  window.addEventListener('touchmove', moveFn as any);
  window.addEventListener('mouseup', upFn);
  window.addEventListener('touchend', upFn);
}

// Dégradés
const alphaBackgroundGradient = computed(() => {
  const { r, g, b } = internalRgb.value;
  return `linear-gradient(to right, rgba(${r}, ${g}, ${b}, 0) 0%, rgba(${r}, ${g}, ${b}, 1) 100%)`;
});
const hueBackgroundColor = computed(() => {
  const { r, g, b } = ColorUtils.hsvToRgb(hue.value, 1, 1);
  return `rgb(${r}, ${g}, ${b})`;
});

// Utilitaire pour récupérer les coordonnées client
function getClientXY(e: MouseEvent | TouchEvent) {
  if ('touches' in e && e.touches.length) {
    return { clientX: e.touches[0].clientX, clientY: e.touches[0].clientY };
  }
  return { clientX: (e as MouseEvent).clientX, clientY: (e as MouseEvent).clientY };
}

// Taille de l'input de saisie
const widthInputSize = computed(() => {
  if (svRef.value && previewBoxRef.value) {
    const containerWidth = svRef.value.getBoundingClientRect().width;
    const previewWidth = previewBoxRef.value.getBoundingClientRect().width;
    return { width: (containerWidth - previewWidth - 18) + 'px', height: '1rem' };
  }
  return { width: '0', height: '1rem' };
});

// Initialisation des tailles et positions
onMounted(() => {
  const result: SizeBox = HexColorSquareUtils.convertSize(props.size);
  widthColorSquare.value = result.width;
  heightColorSquare.value = result.height;
  nextTick(() => {
    updateSVPointer();
    updateHuePointer();
    updateAlphaPointer();
  });
});
</script>

<template>
  <div class="color-picker-container">
    <div class="container-color">
      <!-- Carré Saturation/Value -->
      <div class="saturation-value"
           :style="{
             '--size-width-color-container': widthColorSquare,
             '--size-height-color-container': heightColorSquare,
             backgroundColor: hueBackgroundColor
           }"
           @mousedown="startDragSV"
           @touchstart.prevent="startDragSV"
           ref="svRef">
        <div class="value-gradient"></div>
        <div class="saturation-gradient"></div>
        <div class="sv-pointer"
             :style="{ left: svPointerLeft + 'px', top: svPointerTop + 'px' }">
        </div>
      </div>

      <!-- Slider Hue -->
      <div class="hue-slider"
           :style="{ '--size-color-width-slider-hue': widthColorSquare }"
           @mousedown="startDragHue"
           @touchstart.prevent="startDragHue"
           ref="hueRef">
        <div class="hue-pointer" :style="{ left: huePointerLeft + 'px' }"></div>
      </div>

      <!-- Slider Alpha -->
      <div class="alpha-slider"
           :style="{ '--size-color-width-slider-alpha': widthColorSquare }"
           @mousedown="startDragAlpha"
           @touchstart.prevent="startDragAlpha"
           ref="alphaRef">
        <div class="alpha-background" :style="{ background: alphaBackgroundGradient }"></div>
        <div class="alpha-pointer" :style="{ left: alphaPointerLeft + 'px' }"></div>
      </div>

      <!-- Aperçu + saisie -->
      <div class="color-preview">
        <div class="preview-box" :style="{ background: rgbaColor }" ref="previewBoxRef"></div>
        <ContainerSlot :direction="FlexDirectionEnum.ROW">
          <!-- v-model pour la valeur hexadécimale -->
          <InputReact class="hex-input" :size="SizeEnum.CUSTOM" :size-custom="widthInputSize"
                      v-model="hexValue" label-input="component.hexcolor.inputhex"/>
        </ContainerSlot>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.color-picker-container {
  width: max-content;
  max-width: 100%;
}
.container-color {
  padding: 10px;
  @include mylib.border-style-unpadding;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

/* Carré Saturation/Value */
.saturation-value {
  position: relative;
  width: var(--size-width-color-container);
  height: var(--size-height-color-container);
  cursor: crosshair;
  border-radius: 4px;
  overflow: hidden;
}
.value-gradient, .saturation-gradient {
  position: absolute;
  inset: 0;
}
.value-gradient {
  background: linear-gradient(to top, #000, transparent);
}
.saturation-gradient {
  background: linear-gradient(to right, #fff, transparent);
}
.sv-pointer {
  position: absolute;
  width: 12px;
  height: 12px;
  box-sizing: border-box;
  border: 2px solid #fff;
  border-radius: 50%;
  transform: translate(-50%, -50%);
}

/* Slider Hue */
.hue-slider {
  position: relative;
  height: 14px;
  width: var(--size-color-width-slider-hue);
  background: linear-gradient(to right, #ff0000 0%, #ffff00 17%, #00ff00 33%, #00ffff 50%, #0000ff 67%, #ff00ff 83%, #ff0000 100%);
  border-radius: 4px;
  cursor: pointer;
}
.hue-pointer {
  position: absolute;
  top: 50%;
  width: 10px;
  height: 10px;
  background: #fff;
  border: 2px solid #000;
  border-radius: 50%;
  transform: translate(-50%, -50%);
}

/* Slider Alpha */
.alpha-slider {
  position: relative;
  height: 14px;
  width: var(--size-color-width-slider-alpha);
  border-radius: 4px;
  cursor: pointer;
  background-size: 8px 8px;
  background-image:
    linear-gradient(45deg, #ccc 25%, transparent 25%, transparent 75%, #ccc 75%, #ccc),
    linear-gradient(45deg, #ccc 25%, transparent 25%, transparent 75%, #ccc 75%, #ccc);
  background-position: 0 0, 4px 4px;
}
.alpha-background {
  position: absolute;
  inset: 0;
  border-radius: 4px;
}
.alpha-pointer {
  position: absolute;
  top: 50%;
  width: 10px;
  height: 10px;
  background: #fff;
  border: 2px solid #000;
  border-radius: 50%;
  transform: translate(-50%, -50%);
}

/* Aperçu + Input */
.color-preview {
  display: flex;
  align-items: center;
  gap: 8px;
}
.preview-box {
  width: 32px;
  min-width: 15px;
  height: 32px;
  border-radius: 4px;
  border: 1px solid #aaa;
}
.hex-input {
  width: 100%;
  flex: 1;
}
</style>
