import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {AppHelper} from '../../app.helper';

/**
 * @author Michal Beran
 */
@Component({
  template: ''
})
export class UserCommonComponent implements OnInit {
  public invalid = AppHelper.invalid;
  public isUserLogged = AppHelper.isAuthenticated;
  public isUserAdmin = AppHelper.isAdmin;
  public getLoggedUser = AppHelper.getAuthenticated;

  public error = null;

  constructor(protected service: UserService, protected router: Router) {
  }

  ngOnInit() {
    this.service.setRepository('users');
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback(), error => {this.error = "Cannot delete user. Try to delete all ratings of this user first."});
  }

  public afterDelete(res) {
    // blank
  }

  protected toIndexPage() {
    this.router.navigateByUrl(`users/index`);
  }
}
