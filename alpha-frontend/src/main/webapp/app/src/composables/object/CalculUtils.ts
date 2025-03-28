export class CalculUtils {
  static transformRGB(color: number): number {
    if (color <= 0.03928) {
      return color / 12.92;
    } else {
      return ((color + 0.055) / 1.055) ** 2.4;
    }
  }

  static relativeLuminance(transformRGB: RGB): number {
    return 0.2126 * transformRGB.r + 0.7152 * transformRGB.g + 0.0722 * transformRGB.b;
  }

  static ratioContrastBlack(luminanceRGB: number): number {
    return (luminanceRGB + 0.05) / 0.05;
  }

  static ratioContrastWhite(luminanceRGB: number): number {
    return (1 + 0.05) / (luminanceRGB + 0.05);
  }
}
