export class AppHelper {
  static invalid(model: any): boolean {
    return model.invalid && (model.dirty || model.touched);
  }
}
