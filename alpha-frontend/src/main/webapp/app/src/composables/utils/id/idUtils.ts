export class IdUtils {
  static generateRandomId(): string {
    return Math.random().toString(36);
  }
}
