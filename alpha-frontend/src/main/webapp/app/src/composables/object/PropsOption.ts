import {SizeEnum} from "../enum/sizeEnum.js";
import {FlexPositionEnum} from "../enum/FlexEnum.js";
import {SizeBox} from "./SizeBox.js";

export interface PropsInput {
  id?: string,
  modelValue: string;
  labelInput?: string;
  ariaLabelInput?: string;
  placeHolder?: string;
  size?: SizeEnum;
  sizeCustom?: SizeBox;
  maxSize? : string;
  positionLabel?: FlexPositionEnum;
}
