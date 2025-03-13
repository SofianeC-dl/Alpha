import {defineStore} from "pinia";
import {Ref, ref} from "vue";
import {ModalOption} from "../../composables/object/ModalOption.js";
import {SizeEnum} from "../../assets/enum/sizeEnum.js";

export const useModalStore = defineStore('modalStore', () => {
  const defaultModalOption: ModalOption = {
    title: '[no_title]',
    size: SizeEnum.MEDIUM,
    body: '[no_body]'
  };

  const isOpen: Ref<boolean, boolean> = ref(false);
  const modalOption: Ref<ModalOption, ModalOption> = ref(defaultModalOption);

  function open(modalOptionCurrent?: ModalOption) {
    modalOption.value = modalOptionCurrent || defaultModalOption;
    isOpen.value = true;
  }

  function close() {
    isOpen.value = false;
    modalOption.value = defaultModalOption;
  }

  return {
    isOpen,
    modalOption,
    open,
    close
  }
});
