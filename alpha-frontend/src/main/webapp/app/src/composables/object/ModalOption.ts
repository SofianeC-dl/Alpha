import {SizeEnum} from "../enum/sizeEnum.js";

export interface ModalOption {
  title: string,
  size: SizeEnum,
  body: string,
  isModulable?: boolean
}

export interface ModalCustomOption {
  component: Object
}
