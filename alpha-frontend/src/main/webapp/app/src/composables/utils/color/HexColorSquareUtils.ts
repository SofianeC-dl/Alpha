import {SizeEnum} from "../../enum/sizeEnum.js";
import {SizeBox} from "../../object/SizeBox.js";

export class HexColorSquareUtils {
  static convertSize(size: SizeEnum): string {
    let resultSize: SizeBox = {
      width: 'auto',
      height: 'auto'
    };
    switch (size) {
      case SizeEnum.SMALL:
        resultSize.width = '10rem';
        resultSize.height = '5rem';
        break;
      case SizeEnum.MEDIUM:
        resultSize.width = '12.5rem';
        resultSize.height = '7rem';
        break;
      case SizeEnum.LARGER:
        resultSize.width = '15rem';
        resultSize.height = '9rem';
        break;
      case SizeEnum.DEFAULT:
        resultSize.width = 'auto';
        resultSize.height = 'auto';
        break;
      default:
        return resultSize;
    }

    return resultSize;
  }
}
