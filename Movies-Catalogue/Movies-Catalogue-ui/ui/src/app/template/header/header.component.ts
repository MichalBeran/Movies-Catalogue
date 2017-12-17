import { Component, OnInit } from '@angular/core';
import {User} from '../../models/user.model';
import {isNullOrUndefined} from "util";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AppHelper} from "../../app.helper";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  user = new User();

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.service.setRepository('users');
    this.loadUser();
  }

  loadUser() {
    this.user =  AppHelper.getAuthenticated();
    console.log("user loaded");
  }

  isLoggedIn() {
    return AppHelper.isAuthenticated();
  }

  isAdmin(){
    return AppHelper.isAdmin();
  }

  logout() {
    this.service.logout();
    this.toMovies();
  }

  toMovies() {
    this.router.navigateByUrl(`movies/index`);

  }

}
