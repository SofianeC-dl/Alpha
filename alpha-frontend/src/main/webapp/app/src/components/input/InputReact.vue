<script setup lang="ts">
import {computed, onMounted, ref, Ref} from "vue";
import {SizeBox} from "@/composables/object/SizeBox.js";
import {InputUtils} from "@/composables/input/inputUtils.js";
import {PropsInput} from "@/assets/enum/PropsEnum.js";
import {SizeEnum} from "@/assets/enum/sizeEnum.js";
import ContainerSlot from "@/components/container/ContainerSlot.vue";
import {FlexDirectionEnum} from "@/assets/enum/FlexEnum.js";
import {FormatEnum} from "@/assets/enum/FormatEnum.js";


// Définition des props
const props = withDefaults(defineProps<PropsInput>(), {
  labelInput: '[NO_LABEL]',
  placeHolder: '',
  size: SizeEnum.DEFAULT
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

onMounted(() => {
  const result: SizeBox = InputUtils.convertSize(props.size);

  widthButton.value = result.width;
  heightButton.value = result.height;
})
</script>

<template>
  <ContainerSlot :direction="FlexDirectionEnum.COLUMN" :position-item="props.positionLabel" gap="0" :width="FormatEnum.AUTO">
    {{ props.labelInput }}
    <input v-model="localInput" :placeholder="placeHolder" :style="{'--size-button-width': widthButton, '--size-button-height': heightButton}"/>
  </ContainerSlot>
</template>

<style scoped lang="scss">
input {
  margin: 0;
  height: var(--size-button-height);
  width: var(--size-button-width);
}
</style>
