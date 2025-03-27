import {SizeEnum} from "./sizeEnum.js";
import {FlexPositionEnum} from "./FlexEnum.js";

export interface PropsInput {
  id?: string,
  modelValue: string;
  labelInput?: string;
  ariaLabelInput?: string;
  placeHolder?: string;
  size?: SizeEnum;
  positionLabel?: FlexPositionEnum;
}
