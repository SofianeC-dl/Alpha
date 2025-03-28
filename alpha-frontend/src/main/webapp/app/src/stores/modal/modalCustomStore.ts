import {defineStore} from "pinia";
import {Ref, ref} from "vue";
import {SizeEnum} from "../../composables/enum/sizeEnum.js";

export const useModalCustomStore = defineStore('modalCustomStore', () => {
  const modalComponent: Object = null

  let modalSize: SizeEnum = SizeEnum.MEDIUM;
  const isOpen: Ref<boolean, boolean> = ref(false);
  const modalOption: Ref<Object, Object> = ref(modalComponent);

  function open(modalComponentEntry: Object) {
    modalOption.value = modalComponentEntry || modalComponent;
    isOpen.value = true;
  }

  function close() {
    modalSize = SizeEnum.MEDIUM;
    isOpen.value = false;
    modalOption.value = modalComponent;
  }

  return {
    isOpen,
    modalOption,
    modalSize,
    open,
    close
  }
});
