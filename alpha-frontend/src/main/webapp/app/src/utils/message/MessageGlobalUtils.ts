import { useGlobalMessageStore } from '@/stores/globalError/globalMessageStore.ts'
import { TypeMessageEnum } from '@/assets/enum/TypeMessageEnum.ts'
import errorJson from '@/assets/json/error.json'
import type {ErrorJsonDict} from "@/assets/TypeGlobal.ts";

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

export class MessageGlobalParseUtils {
  static getTextFromJsonPath(pathJson: string, ...args: any[]): any {
    const json = errorJson as ErrorJsonDict;
    const messageJson: string =  pathJson.split('.').reduce<any>((acc, key) => acc && acc[key], json);

    return messageJson.replace(/{(\d+)}/g, (match, index) => {
      return typeof args[index] !== "undefined" ? args[index] : match;
    });
  }

  static concatMessageError(...params: any[]): string {
    const test = params.join('.');
    console.log(test);
    return test;
  }
}
