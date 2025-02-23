import {defineStore} from "pinia";
import {type Ref, ref} from "vue";

export const useGlobalMessageStore  = defineStore('globalMessages', () => {
  const message:Ref<string, string> = ref('')
  const type:Ref<string, string>  = ref('')
  const isOpenned:Ref<boolean, boolean> = ref(false)

  /**
   *
   * @param newMessage
   * @param newType
   */
  function setMessage(newMessage: string, newType: string = 'info') {
    isOpenned.value = true;
    message.value = newMessage;
    type.value = newType;
  }

  /**
   *
   */
  function clearMessage() {
    isOpenned.value = false;
    message.value = '';
    type.value = '';
  }

  return {
    message,
    type,
    isOpenned,
    setMessage,
    clearMessage
  }
})
