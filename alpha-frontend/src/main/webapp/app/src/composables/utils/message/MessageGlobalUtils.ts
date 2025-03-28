import {useGlobalMessageStore} from "../../../stores/globalMesage/globalMessageStore.js";
import {TypeMessageEnum} from "../../enum/TypeMessageEnum.js";

export class MessageGlobalToastUtils {
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

  static successMessage(message: string) {
    const globalMessageStore = useGlobalMessageStore()
    globalMessageStore.setMessage(message, TypeMessageEnum.SUCCESS)
  }
}
