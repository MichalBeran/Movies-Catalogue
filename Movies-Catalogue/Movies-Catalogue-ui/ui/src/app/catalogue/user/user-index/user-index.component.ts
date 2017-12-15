import { Component, OnInit } from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {User} from '../../../models/user.model';
import {UserComponent} from '../user.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-index',
  templateUrl: './user-index.component.html',
  styleUrls: ['./user-index.component.css']
})
export class UserIndexComponent implements OnInit {

  title= 'Users';

  users: User[];

  constructor(protected service: RestService, protected router: Router) {
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback());
  }

  ngOnInit() {
    this.service.setRepository('users');
    this.refresh();
  }
  remove(id) {
    this.delete(id, () => this.refresh());
  }
  refresh() {
    this.service.get().subscribe(list => this.users = list);
  }

}
