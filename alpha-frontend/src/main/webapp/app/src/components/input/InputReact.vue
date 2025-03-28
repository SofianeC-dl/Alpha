<script setup lang="ts">
import {computed, onMounted, ref, Ref, watch} from "vue";
import {SizeBox} from "@/composables/object/SizeBox.js";
import {InputUtils} from "@/composables/input/inputUtils.js";
import {PropsInput} from "@/composables/object/PropsOption.js";
import {SizeEnum} from "@/composables/enum/sizeEnum.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import {FlexDirectionEnum} from "@/composables/enum/FlexEnum.js";
import {FormatEnum} from "@/composables/enum/FormatEnum.js";
import {IdUtils} from "@/composables/utils/id/idUtils.js";
import {JsonParseUtils} from "@/composables/utils/json/JsonParseUtils.js";


// Définition des props
const props = withDefaults(defineProps<PropsInput>(), {
  id: IdUtils.generateRandomId(),
  labelInput: 'component.input.label.default',
  ariaLabelInput: 'component.input.default',
  placeHolder: 'component.input.placeholder.default',
  size: SizeEnum.DEFAULT,
  sizeCustom: {width: 'auto', height: 'auto'},
  maxSize: 'auto'
});

const emit = defineEmits<{
  (e: 'update:modelValue', value: string): void;
}>();

const widthButton: Ref<string, string> = ref(null);
const heightButton: Ref<string, string> = ref(null);

const localInput = computed({
  get() {
    return props.modelValue;
  },
  set(value: string) {
    emit('update:modelValue', value);
  }
});

const convertLabelName = computed(() => JsonParseUtils.getTextFromTextJsonDict(props.labelInput));
const convertPlaceHolderName = computed(() => JsonParseUtils.getTextFromTextJsonDict(props.placeHolder));
const convertAriaLabel = computed(() => JsonParseUtils.getTextFromAriaJsonDict(props.ariaLabelInput));

watch(
  () => props.sizeCustom,
  (newSizeCustom) => {
    const result: SizeBox = InputUtils.convertSize(props.size, newSizeCustom);
    widthButton.value = result.width;
    heightButton.value = result.height;
  },
  { immediate: true, deep: true }
);

onMounted(() => {
  const result: SizeBox = InputUtils.convertSize(props.size, props.sizeCustom);

  widthButton.value = result.width;
  heightButton.value = result.height;
})
</script>

<template>
  <ContainerSlot :direction="FlexDirectionEnum.COLUMN" :position-item="props.positionLabel" gap="0" :width="FormatEnum.AUTO">
    {{ convertLabelName }}
    <input v-model="localInput" :placeholder="convertPlaceHolderName" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton, '--max-size-input': maxSize}" :aria-label="convertAriaLabel"/>
  </ContainerSlot>
</template>

<style scoped lang="scss">
input {
  margin: 0;
  height: var(--size-button-height);
  width: var(--size-button-width);
  flex: 1;
}
</style>
