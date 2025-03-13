import {SizeEnum} from "../../assets/enum/sizeEnum.js";

export interface ModalOption {
  title: string,
  size: SizeEnum,
  body: string,
  isModulable?: boolean
}
