import {defineStore} from "pinia";
import {ref, Ref} from "vue";

export const useLoadingStore = defineStore('loadingStore', () => {
  const isLoading: Ref<boolean, boolean> = ref(false);

  function activeLoading() {
    isLoading.value = true;
  }

  function disabledLoading() {
    isLoading.value = false;
  }

  return {
    isLoading,
    activeLoading,
    disabledLoading
  }
});
