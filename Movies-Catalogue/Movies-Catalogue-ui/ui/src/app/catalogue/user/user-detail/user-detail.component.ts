import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user.model';
import {UserService} from '../../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {UserCommonComponent} from "../user.common.component";
import {AppHelper} from '../../../app.helper';
import {mergeMap} from 'rxjs/operators';
/**
 * @author Michal Beran
 */
@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent extends UserCommonComponent implements OnInit {

  user: User = new User();

  title = 'Detail';
  editing = false;

  constructor(protected service: UserService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  ngOnInit() {
    super.ngOnInit();

    this.route.params.pipe(
      mergeMap(params => this.service.find(params['id'])
      ))
      .subscribe(result => this.user = result);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

}
