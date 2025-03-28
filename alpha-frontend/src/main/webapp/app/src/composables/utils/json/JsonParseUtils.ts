import errorJson from "../../../assets/json/fr/error-fr.json";
import {TypeJsonDict} from "../../object/JsonType.js";
import textJson from "../../../assets/json/fr/text-fr.json";
import ariaJson from "../../../assets/json/fr/aria-fr.json";

export class JsonParseUtils {
  static getTextFromErrorJsonDict(pathJson: string, ...args: any[]): any {
    const json = errorJson as TypeJsonDict;
    return JsonParseUtils.convertPathFromJson(json, pathJson, args);
  }

  static getTextFromTextJsonDict(pathJson: string, ...args: any[]): any {
    const json = textJson as TypeJsonDict;
    return JsonParseUtils.convertPathFromJson(json, pathJson, args);
  }

  static getTextFromAriaJsonDict(pathJson: string, ...args: any[]): any {
    const json = ariaJson as TypeJsonDict;
    return JsonParseUtils.convertPathFromJson(json, pathJson, args);
  }

  static convertPathFromJson = (json: TypeJsonDict, pathJson: string, ...args: any[]) => {
    const messageJson: string = pathJson.split('.').reduce<any>((acc, key) => acc && acc[key], json);

    if (!JsonParseUtils.hasPath(json, pathJson)) {
      return '[No_Path]'
    } else {
      return messageJson.replace(/{(\d+)}/g, (match, index) => {
        return typeof args[index] !== "undefined" ? args[index] : match;
      });
    }
  }
  static hasPath(obj: any, path: string): boolean {
    return path.split('.').reduce((acc, key) => acc && acc[key], obj) !== undefined;
  }

  static concatMessageError(...params: any[]): string {
    return params.join('.');
  }
}
