import {defineStore} from "pinia";
import {ref, Ref} from "vue";

export const useMenuStore = defineStore('menuStore', () => {
  const isMenuOpen: Ref<boolean, boolean> = ref(false);

  function toggleMenu() {
    console.log('menu open : ',isMenuOpen.value);
    isMenuOpen.value = !isMenuOpen.value
  }

  return {
    isMenuOpen,
    toggleMenu
  }
});
