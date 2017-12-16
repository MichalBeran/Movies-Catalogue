import {Component, OnInit} from '@angular/core';
import {User} from '../../../models/user.model';
import {RestService} from '../../../services/rest.service';
import {UserService} from '../../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {UserCommonComponent} from "../user.common.component";

/**
 * @author Michal Beran
 */
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent extends UserCommonComponent implements OnInit {

  user: User = new User();

  title = 'Login';
  editing = false;
  returnUrl: string;
  loading = false;

  constructor(protected service: UserService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  ngOnInit() {
    super.ngOnInit();
    this.route.params.subscribe(params => {
      if (!isNullOrUndefined(params['id'])) {
        this.service.find(params['id']).subscribe(res => this.setFormForEdit(res));
      }
    });
    // reset login status
    this.service.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    this.loading = true;
    this.service.login(this.user.mail, this.user.password)
      .subscribe(
        data => {
          this.toIndexPage();

          //this.router.navigateByUrl(this.returnUrl);
        },
        error => {
          this.loading = false;
        });
    //.subscribe(() => this.toIndexPage());
  }

  private setFormForEdit(user: User) {
    this.title = `Update`;
    this.user = user;
    this.editing = true;
  }

}
