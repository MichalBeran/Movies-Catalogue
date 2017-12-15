import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user.model';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';

/**
 * @author Michal Beran
 */
@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  user: User = new User();

  title = 'Register';
  editing = false;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback());
  }

  protected toIndexPage() {
    this.router.navigateByUrl(`users/index`);
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
      this.service.update(this.genre).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: User) {
    this.router.navigateByUrl(`users/detail/${created.id}`);
  }

  remove(id) {
    this.delete(id, () => this.toIndexPage());
  }

  private setFormForEdit(user: User) {
    this.title = `Update`;
    this.user = user;
    this.editing = true;
  }

}
