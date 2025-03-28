import { defineStore } from 'pinia'
import { type Ref, ref } from 'vue'
import {TypeMessageEnum} from "../../composables/enum/TypeMessageEnum.js";
import {TitleMessageEnum} from "../../composables/enum/TitleMessageEnum.js";

export const useGlobalMessageStore = defineStore('globalMessages', () => {
  const status: Ref<number, number> = ref(-1)
  const typeError: Ref<string, string> = ref('')
  const message: Ref<string, string> = ref('')
  const request: Ref<string, string> = ref('')

  const type: Ref<TypeMessageEnum, TypeMessageEnum> = ref(TypeMessageEnum.NO_TYPE)
  const title: Ref<TitleMessageEnum, TitleMessageEnum> = ref(TitleMessageEnum.NO_TITLE)

  const isOpenned: Ref<boolean, boolean> = ref(false)

  const duration: number = 3000;

  /**
   * Transforme un type en titre
   * @param typeMessage type d'entrée
   */
  function typeToTitleMessage(typeMessage: TypeMessageEnum): TitleMessageEnum {
    let titleMessage
    switch (typeMessage) {
      case TypeMessageEnum.INFO:
        titleMessage = TitleMessageEnum.INFO_TITLE
        break
      case TypeMessageEnum.ERROR:
        titleMessage = TitleMessageEnum.ERROR_TITLE
        break
      case TypeMessageEnum.WARNING:
        titleMessage = TitleMessageEnum.WARNING_TITLE
        break
      case TypeMessageEnum.SUCCESS:
        titleMessage = TitleMessageEnum.SUCCESS_TITLE
        break
      default:
        titleMessage = TitleMessageEnum.NO_TITLE
        break
    }

    return titleMessage
  }

  /**
   * Initialise un message pour la popup
   * @param newMessage message à afficher
   * @param newType type de la popup (info, error, warning, success)
   */
  function setMessageGlobal(newMessage: string, newType: TypeMessageEnum = TypeMessageEnum.INFO) {
    isOpenned.value = true
    message.value = newMessage
    type.value = newType
    title.value = typeToTitleMessage(type.value)

    setTimeout(() => {
      clearMessage();
    }, duration)
  }

  /**
   * Ferme la popup et réinitialise les paramètres
   */
  function clearMessage() {
    isOpenned.value = false
    status.value = -1
    typeError.value = ''
    message.value = ''
    request.value = ''
    type.value = TypeMessageEnum.NO_TYPE
    title.value = TitleMessageEnum.NO_TITLE
  }

  return {
    status,
    typeError,
    message,
    request,
    type,
    title,
    isOpenned,
    setMessage: setMessageGlobal,
    clearMessage,
  }
})
