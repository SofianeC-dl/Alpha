import {defineStore} from "pinia";
import {Ref, ref} from "vue";

export const useModalCustomStore = defineStore('modalCustomStore', () => {
  const modalComponent: Object = null

  const isOpen: Ref<boolean, boolean> = ref(false);
  const modalOption: Ref<Object, Object> = ref(modalComponent);

  function open(modalComponentEntry: Object) {
    modalOption.value = modalComponentEntry || modalComponent;
    isOpen.value = true;
  }

  function close() {
    isOpen.value = false;
    modalOption.value = modalComponent;
  }

  return {
    isOpen,
    modalOption,
    open,
    close
  }
});
