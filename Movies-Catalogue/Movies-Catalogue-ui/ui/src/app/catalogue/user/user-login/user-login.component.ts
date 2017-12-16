import { Component, OnInit } from '@angular/core';
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

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute, private userService: UserService) {
    super(service, router);
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (!isNullOrUndefined(params['id'])) {
        this.service.find(params['id']).subscribe(res => this.setFormForEdit(res));
      }
    });
    // reset login status
    //this.userService.logout();

    // get return url from route parameters or default to '/'
    //this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    this.loading = true;
    this.userService.login(this.user.mail, this.user.password)
      .subscribe(
        data => {
          //this.router.navigate([this.returnUrl]);
        },
        error => {
          //error
          this.loading = false;
        });
  }

  private setFormForEdit(user: User) {
    this.title = `Update`;
    this.user = user;
    this.editing = true;
  }

}
