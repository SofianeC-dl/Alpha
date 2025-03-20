import {useGlobalMessageStore} from "../../../stores/globalMesage/globalMessageStore.js";
import {TypeMessageEnum} from "../../../assets/enum/TypeMessageEnum.js";
import {ErrorJsonDict} from "../../../assets/TypeGlobal.js";
import errorJson from "../../../assets/json/error.json"

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
    const messageJson: string = pathJson.split('.').reduce<any>((acc, key) => acc && acc[key], json);


      return messageJson.replace(/{(\d+)}/g, (match, index) => {
        return typeof args[index] !== "undefined" ? args[index] : match;
      });
  }

  static hasPath(obj: any, path: string): boolean {
    return path.split('.').reduce((acc, key) => acc && acc[key], obj) !== undefined;
  }

  static concatMessageError(...params: any[]): string {
    return params.join('.');
  }
}
