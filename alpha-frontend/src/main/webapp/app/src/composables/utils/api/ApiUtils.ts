import type {MessageError} from "@/assets/object/MessageError.ts";
import errorJson from "../../../assets/json/fr/error-fr.json";
import type {ErrorJsonDict} from "@/assets/TypeGlobal.ts";
import {MessageGlobalToastUtils} from "../message/MessageGlobalUtils.js";
import {useLoadingStore} from "../../../stores/loading/loadingStore.js";
import {JsonParseUtils} from "../json/JsonParseUtils.js";

export const useCatch = (catchReturn: Promise<void>) => {
  const json = errorJson as ErrorJsonDict;

  const loadingStore = useLoadingStore();

  catchReturn.catch((axiosCatch) => {
    loadingStore.disabledLoading();
    if (axiosCatch && axiosCatch.response && axiosCatch.response.data) {
      const response = axiosCatch.response.data as MessageError;
      const pathJson: string = JsonParseUtils.concatMessageError(response.status, response.typeError, response.typeRequest);
      (!JsonParseUtils.hasPath(json, pathJson)) ?
        MessageGlobalToastUtils.warningMessage("internal.nopath")
        :
        MessageGlobalToastUtils.errorMessage(
          JsonParseUtils.getTextFromErrorJsonDict(
            pathJson, response.object))
    }
  })
}


