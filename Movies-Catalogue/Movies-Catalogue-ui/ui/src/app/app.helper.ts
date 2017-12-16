import {Injectable} from '@angular/core';

@Injectable()
export class AppHelper {
  static invalid(model: any): boolean {
    return model.invalid && (model.dirty || model.touched);
  }
}
