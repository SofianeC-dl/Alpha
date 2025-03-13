import {SizeEnum} from "../../../assets/enum/sizeEnum.js";
import {SizeBox} from "../../object/SizeBox.js";

export class ButtonUtils {

  static convertSize(size: SizeEnum): SizeBox {
    let resultSize: SizeBox = {
      width: 'auto',
      height: 'auto'
    };
    switch (size) {
      case SizeEnum.SMALL:
        resultSize.width = '6rem';
        resultSize.height = '1.5rem';
        break;
      case SizeEnum.MEDIUM:
        resultSize.width = '8rem';
        resultSize.height = '2rem';
        break;
      case SizeEnum.LARGER:
        resultSize.width = '10rem';
        resultSize.height = '3rem';
        break;
      case SizeEnum.DEFAULT:
        resultSize.width = 'max-content';
        resultSize.height = 'max-content';
        break;
      default:
        return resultSize;
    }

    return resultSize;
  }
}
