import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user.model';
import {UserService} from '../../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {UserCommonComponent} from "../user.common.component";

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

  title = 'Register';
  editing = false;

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
      this.service.create(this.user).subscribe(result => this.redirect(result));
    } else {
      // update
      this.service.update(this.user).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: User) {
    this.router.navigateByUrl(`users/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(user: User) {
    this.title = `Update`;
    this.user = user;
    this.editing = true;
  }

}
