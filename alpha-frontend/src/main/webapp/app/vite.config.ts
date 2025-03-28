import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
    host: true,
    port: 3000,
    proxy: {
      '/alpha-web': {
        target: 'http://192.168.1.179:3000',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/alpha-web/, '')
      }
    }
  },
})
