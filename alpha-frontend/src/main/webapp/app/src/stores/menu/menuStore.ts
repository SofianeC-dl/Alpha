import {defineStore} from "pinia";
import {ref, Ref} from "vue";

export const useMenuStore = defineStore('menuStore', () => {
  const isMenuOpen: Ref<boolean, boolean> = ref(false);

  function toggleMenu() {
    isMenuOpen.value = !isMenuOpen.value
  }

  function closeMenu() {
    isMenuOpen.value = false;
  }

  return {
    isMenuOpen,
    toggleMenu,
    closeMenu
  }
});
