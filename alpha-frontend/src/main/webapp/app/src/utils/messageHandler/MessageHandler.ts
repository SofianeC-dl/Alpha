import GlobalMessage from "@/components/global/GlobalMessage.vue";
import {useGlobalMessageStore} from "@/stores/globalError/globalMessageStore.ts";
import {TypeMessageEnum} from "@/assets/enum/typeMessageEnum.ts";

export class MessageHandler {

  static errorMessage(message: string) {
    const globalMessageStore = useGlobalMessageStore()
    globalMessageStore.setMessage(message, TypeMessageEnum.ERROR)
  }

  static infoMessage(message: string) {
    const globalMessageStore = useGlobalMessageStore()
    globalMessageStore.setMessage(message, TypeMessageEnum.INFO)
  }

  static warningMessage(message: string) {
    const globalMessageStore = useGlobalMessageStore()
    globalMessageStore.setMessage(message, TypeMessageEnum.WARNING)
  }
}
