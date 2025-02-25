import type {MessageError} from "@/assets/object/ObjectUtils.ts";
import errorJson from "@/assets/json/error.json";
import type {ErrorJsonDict} from "@/assets/TypeGlobal.ts";
import {MessageGlobalParseUtils, MessageGlobalToastUtils} from "../message/MessageGlobalUtils.js";

export const useCatch = (catchReturn: Promise<void>) => {
  const json = errorJson as ErrorJsonDict;

  catchReturn.catch((axiosCatch) => {
    const response = axiosCatch.response.data as MessageError;
    const pathJson: string = MessageGlobalParseUtils.concatMessageError(response.status, response.typeError, response.typeRequest);
    (!MessageGlobalParseUtils.hasPath(json, pathJson)) ?
      MessageGlobalToastUtils.warningMessage("internal.nopath")
      :
    MessageGlobalToastUtils.errorMessage(
      MessageGlobalParseUtils.getTextFromJsonPath(
        pathJson, response.object))
  })
}


