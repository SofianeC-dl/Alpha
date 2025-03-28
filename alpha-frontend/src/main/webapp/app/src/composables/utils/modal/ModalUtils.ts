import {SizeEnum} from "../../enum/sizeEnum.js";

export class ModalUtils {

 static convertSize(size: SizeEnum): string {
   let resultSize: string = '40%';
   switch (size) {
     case SizeEnum.SMALL:
       resultSize = '20%';
       break;
     case SizeEnum.MEDIUM:
       resultSize = '40%';
       break;
     case SizeEnum.LARGER:
       resultSize = '70%';
       break;
     default:
       return resultSize;
   }

   return resultSize;
 }
}
