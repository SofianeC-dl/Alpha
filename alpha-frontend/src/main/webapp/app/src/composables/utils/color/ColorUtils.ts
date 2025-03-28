import {RGB, RGBA} from "../../object/Colors.js";
import {CalculUtils} from "../../object/CalculUtils.js";
import {Ref} from "vue";

export class ColorUtils {
  static hexToRGB(colorHex: string): RGB {
    const hexPattern: RegExp = /^#(?:[0-9A-Fa-f]{3}|[0-9A-Fa-f]{6})$/;

    if (!hexPattern.test(colorHex)) return {r: 0, g: 0, b: 0}

    if (colorHex.startsWith('#')) {
      colorHex = colorHex.slice(1);
    }

    if (colorHex.length === 3) {
      colorHex = colorHex.split('').map(ch => ch + ch).join('');
    }

    return {
      r: parseInt(colorHex.substring(0, 2), 16),
      g: parseInt(colorHex.substring(2, 4), 16),
      b: parseInt(colorHex.substring(4, 6), 16)
    };
  }

  static normalizeRGBValue(value: number): number {
    return value / 255;
  }

  static getNormalizedColor(hex: string): RGB {
    const rgb: RGB = ColorUtils.hexToRGB(hex);
    return {
      r: ColorUtils.normalizeRGBValue(rgb.r),
      g: ColorUtils.normalizeRGBValue(rgb.g),
      b: ColorUtils.normalizeRGBValue(rgb.b),
    };
  }

  static contrastColor(colorHex: string): string {
    const rgb: RGB = ColorUtils.getNormalizedColor(colorHex);
    const transformRGB: RGB = {
      r: CalculUtils.transformRGB(rgb.r),
      g: CalculUtils.transformRGB(rgb.g),
      b: CalculUtils.transformRGB(rgb.b)
    }

    const luminanceRGB: number = CalculUtils.relativeLuminance(transformRGB);

    const whiteRatio: number = CalculUtils.ratioContrastWhite(luminanceRGB);
    const blackRatio: number = CalculUtils.ratioContrastBlack(luminanceRGB);

    return whiteRatio > blackRatio ? '#e5e5e5' : '#262626';
  }

  /** HSV → RGB */
  static hsvToRgb(h: number, s: number, v: number): RGB {
    const c = v * s;
    const hh = h / 60;
    const x = c * (1 - Math.abs((hh % 2) - 1));
    let r = 0, g = 0, b = 0;

    if (hh >= 0 && hh < 1) {
      r = c; g = x; b = 0;
    } else if (hh >= 1 && hh < 2) {
      r = x; g = c; b = 0;
    } else if (hh >= 2 && hh < 3) {
      r = 0; g = c; b = x;
    } else if (hh >= 3 && hh < 4) {
      r = 0; g = x; b = c;
    } else if (hh >= 4 && hh < 5) {
      r = x; g = 0; b = c;
    } else if (hh >= 5 && hh < 6) {
      r = c; g = 0; b = x;
    }

    const m = v - c;
    r = Math.round((r + m) * 255);
    g = Math.round((g + m) * 255);
    b = Math.round((b + m) * 255);
    return { r, g, b };
  }

  /** RGB → HSV */
  static rgbToHsv(rgb: RGB,
                  hue: Ref<number, number>,
                  saturation:  Ref<number, number>,
                  value:  Ref<number, number>) {
    const rN = rgb.r / 255;
    const gN = rgb.g / 255;
    const bN = rgb.b / 255;
    const max = Math.max(rN, gN, bN);
    const min = Math.min(rN, gN, bN);
    const delta = max - min;

    let h = 0;
    if (delta !== 0) {
      if (max === rN) {
        h = 60 * (((gN - bN) / delta) % 6);
      } else if (max === gN) {
        h = 60 * ((bN - rN) / delta + 2);
      } else {
        h = 60 * ((rN - gN) / delta + 4);
      }
    }
    if (h < 0) h += 360;

    const s = max === 0 ? 0 : delta / max;
    const v = max;

    hue.value = Math.round(h);
    saturation.value = s;
    value.value = v;
  }
  /** RGB + alpha → Hex8 (#RRGGBBAA) */
  static rgbAtoHex8(rgba: RGBA): string {
    const toHex = (val: number) => {
      const h = val.toString(16);
      return h.length === 1 ? '0' + h : h;
    };
    const rHex = toHex(rgba.r);
    const gHex = toHex(rgba.g);
    const bHex = toHex(rgba.b);
    const aHex = toHex(Math.round(rgba.a * 255));
    return `#${rHex}${gHex}${bHex}${aHex}`;
  }

  /** Hex8 (#RRGGBBAA) → RGBA { r, g, b, a } */
  static hex8ToRgba(hex: string): RGBA {
    if (hex.startsWith('#')) {
      hex = hex.slice(1);
    }

    if (hex.length !== 8) return null;

    const r = parseInt(hex.slice(0, 2), 16);
    const g = parseInt(hex.slice(2, 4), 16);
    const b = parseInt(hex.slice(4, 6), 16);
    const a = parseInt(hex.slice(6, 8), 16) / 255;

    return { r, g, b, a };
  }
}
