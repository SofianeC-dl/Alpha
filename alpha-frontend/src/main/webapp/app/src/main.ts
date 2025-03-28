import './assets/css/base.scss'

import { type ComponentPublicInstance, createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import {useGlobalMessageStore} from "./stores/globalMesage/globalMessageStore.js";
import {TypeMessageEnum} from "./composables/enum/TypeMessageEnum.js";

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.config.errorHandler = (
  err: unknown,
  instance: ComponentPublicInstance | null,
  info: string,
) => {
  let errorMessage: string = err instanceof Error ? err.message : String(err)

  const globalMessageStore = useGlobalMessageStore()
  globalMessageStore.setMessage(`Erreur : ${errorMessage}`, TypeMessageEnum.ERROR)

  console.error(`[Global Error Handler] Erreur : ${errorMessage} | Info : ${info}`)
}

app.mount('#app')
