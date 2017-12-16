import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {Router} from '@angular/router';
import {UserService} from "../../services/user.service";

/**
 * @author Michal Beran
 */
@Component({
  template: ''
})
export class UserCommonComponent implements OnInit {

  constructor(protected service: UserService, protected router: Router) {
  }

  ngOnInit() {
    console.log("setting user repo:");
    this.service.setRepository('users');
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback());
  }

  public afterDelete(res) {
    // blank
  }

  protected toIndexPage() {
    this.router.navigateByUrl(`users/index`);
  }
}
