export class ModalUtils {

 static convertSize(size: string): string {
   let resultSize: string = '40%';
   switch (size) {
     case 'sm':
       resultSize = '20%';
       break;
     case 'md':
       resultSize = '40%';
       break;
     case 'lg':
       resultSize = '70%';
       break;
     default:
       return resultSize;
   }

   return resultSize;
 }
}
