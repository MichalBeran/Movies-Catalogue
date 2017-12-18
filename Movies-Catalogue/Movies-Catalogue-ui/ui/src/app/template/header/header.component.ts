import { Component, OnInit } from '@angular/core';
import {User} from '../../models/user.model';
import {isNullOrUndefined} from 'util';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {AppHelper} from '../../app.helper';
declare const $: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.service.setRepository('users');
  }

  getLoggedUser(){
    console.log('GET LOGGED USER');
    return AppHelper.getAuthenticated();
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

  dropdownMenuToggle(e) {
    e.preventDefault();
    $('.dropdown-container').slideToggle(500, 'easeOutExpo');
  }

}
