import {SizeEnum} from "./sizeEnum.js";
import {FlexPositionEnum} from "./FlexEnum.js";

export interface PropsInput {
  modelValue: string;
  labelInput?: string;
  placeHolder?: string;
  size?: SizeEnum;
  positionLabel?: FlexPositionEnum;
}
