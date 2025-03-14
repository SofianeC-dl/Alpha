import type {MessageError} from "@/assets/object/MessageError.ts";
import errorJson from "@/assets/json/error.json";
import type {ErrorJsonDict} from "@/assets/TypeGlobal.ts";
import {MessageGlobalParseUtils, MessageGlobalToastUtils} from "../message/MessageGlobalUtils.js";
import {useLoadingStore} from "../../../stores/loading/loadingStore.js";

export const useCatch = (catchReturn: Promise<void>) => {
  const json = errorJson as ErrorJsonDict;

  const loadingStore = useLoadingStore();

  catchReturn.catch((axiosCatch) => {
    loadingStore.disabledLoading();
    if (axiosCatch && axiosCatch.response && axiosCatch.response.data) {
      const response = axiosCatch.response.data as MessageError;
      const pathJson: string = MessageGlobalParseUtils.concatMessageError(response.status, response.typeError, response.typeRequest);
      (!MessageGlobalParseUtils.hasPath(json, pathJson)) ?
        MessageGlobalToastUtils.warningMessage("internal.nopath")
        :
        MessageGlobalToastUtils.errorMessage(
          MessageGlobalParseUtils.getTextFromJsonPath(
            pathJson, response.object))
    }
  })
}


