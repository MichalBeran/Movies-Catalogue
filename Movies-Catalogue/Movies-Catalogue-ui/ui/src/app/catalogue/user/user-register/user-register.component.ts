import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user.model';
import {UserService} from '../../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {UserCommonComponent} from "../user.common.component";
import {AppHelper} from '../../../app.helper';
/**
 * @author Michal Beran
 */
@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent extends UserCommonComponent implements OnInit {

  user: User = new User();

  title = 'User Register';
  editing = false;
  regError = null;

  constructor(protected service: UserService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (!isNullOrUndefined(params['id'])) {
        this.service.find(params['id']).subscribe(res => this.setFormForEdit(res));
      }
    });
  }

  save() {
    if (isNullOrUndefined(this.user.id)) {
      // create
      this.service.create(this.user).subscribe(result => this.redirect(result), error => {this.regError = "Registration failed. This e-mail or nick is probably used already. Try another one."});
    } else {
      // update
      this.service.update(this.user).subscribe(result => this.redirect(result), error => {this.regError = "Update failed. This e-mail or nick is probably used already. Try another one."});
    }
  }

  redirect(created: User) {
    this.router.navigateByUrl(`users/index`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(user: User) {
    this.title = `User Update`;
    this.user = user;
    this.user.password = '';
    this.editing = true;
  }

}
