import {SizeBox} from "../../object/SizeBox.js";
import {SizeEnum} from "../../assets/enum/sizeEnum.js";

  export class InputUtils {

  static convertSize(size: SizeEnum): SizeBox {
    let resultSize: SizeBox = {
      width: 'auto',
      height: '1rem'
    };
    switch (size) {
      case SizeEnum.SQUARE:
        resultSize.width = '1rem';
        resultSize.height = '1rem';
        break;
      case SizeEnum.SMALL:
        resultSize.width = '10rem';
        resultSize.height = '1rem';
        break;
      case SizeEnum.MEDIUM:
        resultSize.width = '20rem';
        resultSize.height = '1rem';
        break;
      case SizeEnum.LARGER:
        resultSize.width = '30rem';
        resultSize.height = '1rem';
        break;
      default:
        return resultSize;
    }

    return resultSize;
  }
}
