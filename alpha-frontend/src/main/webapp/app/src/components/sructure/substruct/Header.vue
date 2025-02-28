<script setup lang="ts">
/** Imports **/
import { onBeforeUnmount, onMounted, ref } from 'vue'

defineProps({})

/** Variables **/
const menuOpen = ref(false)

const isShrunk = ref(false)

let lastScrollPosition = 0

/** Methods **/
function handleScroll() {
  const currentScroll = window.scrollY || document.documentElement.scrollTop
  isShrunk.value = currentScroll > lastScrollPosition
  lastScrollPosition = currentScroll
}

onMounted(() => {
  console.log(`the Header component is now mounted.`)

  window.addEventListener('scroll', handleScroll)
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll)
})

const toggleMenu = () => {
  menuOpen.value = !menuOpen.value
}
</script>

<template>
  <header role="banner" :class="['navbar', { 'hide-header': isShrunk }]">
    <div class="main-layout">
      <div class="logo item-logo">
        <img src="../../../assets/logo.svg" alt="Vite logo" />
      </div>
      <div class="item-menu">
        <nav class="navigation" aria-label="Menu de navigation">
          <ul>
            <li><router-link to="/">Accueil</router-link></li>
            <li><router-link to="/about">À propos</router-link></li>
            <li><router-link to="/services">Services</router-link></li>
            <li><router-link to="/contact">Contact</router-link></li>
          </ul>
        </nav>
        <div class="menu-mobile" @click="toggleMenu">
          <span>Menu</span>
        </div>
        <transition name="fade">
          <nav v-if="menuOpen" class="mobile-navigation" aria-label="Menu de navigation">
            <ul>
              <li><router-link to="/">Accueil</router-link></li>
              <li><router-link to="/about">À propos</router-link></li>
              <li><router-link to="/services">Services</router-link></li>
              <li><router-link to="/contact">Contact</router-link></li>
            </ul>
          </nav>
        </transition>
      </div>
    </div>
  </header>
</template>

<style scoped>
.main-layout {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 5px 80px 5px auto;
  grid-template-rows: auto;
  grid-template-areas:
    '. logo . menu';
}

.item-logo {
  grid-area: logo;
}

.item-menu {
  grid-area: menu;
}

.navbar {
  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #242424;
  color: #fff;
  display: flex;
  align-items: center;
  transition: all 0.3s ease;
  height: 80px;
  z-index: 999;
}

.navbar .logo img {
  height: 50px;
  margin-right: 20px;
}

.navbar.logo.shrinkLogo img {
  height: 0;
}

.navbar nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #fff;
}

.navbar.hide-header {
  opacity: 0;
  pointer-events: none;
}

.navigation ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.navigation li {
  margin: 0 15px;
}

.menu-mobile {
  display: none;
  cursor: pointer;
}

@media (max-width: 768px) {
  .navigation {
    display: none;
  }
  .menu-mobile {
    display: block;
  }
  .mobile-navigation {
    position: absolute;
    top: 70px;
    left: 0;
    width: 100%;
    background-color: #242424;
    border-top: 1px solid #ddd;
    color: #ddd;
    z-index: 10;
    border-radius: 7px;
  }
  .mobile-navigation ul {
    list-style: none;
    padding: 10px;
    margin: 0;
  }
  .mobile-navigation li {
    margin: 10px 0;
  }
}

/* Transition pour le menu mobile */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
