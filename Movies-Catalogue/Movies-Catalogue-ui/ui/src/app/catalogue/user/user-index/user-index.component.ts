import { Component, OnInit } from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {User} from '../../../models/user.model';
import {UserComponent} from '../user.component';
import {Router} from '@angular/router';
import {UserCommonComponent} from '../user.common.component';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-user-index',
  templateUrl: './user-index.component.html',
  styleUrls: ['./user-index.component.css']
})
export class UserIndexComponent extends UserCommonComponent implements OnInit {

  title= 'Users';

  users: User[];

  constructor(protected service: UserService, protected router: Router) {
    super(service, router);
  }

  ngOnInit() {
    super.ngOnInit();
    this.refresh();
  }
  remove(id) {
    super.delete(id, () => this.refresh());
  }
  refresh() {
    this.service.get().subscribe(list => this.users = list);
  }

}
