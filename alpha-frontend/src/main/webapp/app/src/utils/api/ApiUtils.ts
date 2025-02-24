import {
  MessageGlobalParseUtils,
  MessageGlobalToastUtils
} from '@/utils/message/MessageGlobalUtils.ts'
import type {MessageError} from "@/assets/object/ObjectUtils.ts";

export const useCatch = (catchReturn: Promise<void>) => {
  catchReturn.catch((axiosCatch) => {
    const response = axiosCatch.response.data as MessageError;
    MessageGlobalToastUtils.errorMessage(
      MessageGlobalParseUtils.getTextFromJsonPath(
        MessageGlobalParseUtils.concatMessageError(response.status, response.typeError, response.typeRequest), response.object));
  })
}


