import {User} from "./models/user.model";
import {isNullOrUndefined} from "util";

export class AppHelper {
  static invalid(model: any): boolean {
    return model.invalid && (model.dirty || model.touched);
  }

  static getAuthenticated(): User {
    return !isNullOrUndefined(JSON.parse(localStorage.getItem('currentUser'))) ? JSON.parse(localStorage.getItem('currentUser')) : new User();
  }

  static isAuthenticated(): boolean{
    return !isNullOrUndefined(AppHelper.getAuthenticated().id);
  }

  static isAdmin() : boolean{
    if(AppHelper.isAuthenticated()){
      return AppHelper.getAuthenticated().admin;
    }
    return false;
  }
}
