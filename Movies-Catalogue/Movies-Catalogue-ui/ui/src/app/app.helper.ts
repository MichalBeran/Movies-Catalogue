import {User} from './models/user.model';
import {isNullOrUndefined} from 'util';

export class AppHelper {
  static invalid(model: any): boolean {
    return model.invalid && (model.dirty || model.touched);
  }

  static getAuthenticated(): User {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  static isAuthenticated(): boolean{
    return !isNullOrUndefined(AppHelper.getAuthenticated());
  }

  static isAdmin(): boolean{
    if (AppHelper.isAuthenticated()){
      return AppHelper.getAuthenticated().admin;
    }
    return false;
  }
}
