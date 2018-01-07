import {AfterViewInit, Component, OnInit} from '@angular/core';
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
export class HeaderComponent implements OnInit, AfterViewInit {


  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.service.setRepository('users');
  }
  ngAfterViewInit(): void {
    const that = this;
    $(document).ready(function(){

    });
    $('.dropdown-container a').click(function(){
      that.dropdownMenuToggle(null);
    })

    setInterval(function(){
      const date = new Date();
      const hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
      const mins = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
      const sec = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
      const mils = Math.floor(date.getMilliseconds() / 10) < 10 ? '0' + Math.floor(date.getMilliseconds() / 10) : Math.floor(date.getMilliseconds() / 10);
      const text = hours + ':' + mins + ':' + sec + '.' + mils;
    $('.logo-bg-timer').text(text);
    }, 91);
  }

  getLoggedUser() {
    //console.log('GET LOGGED USER');
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
    if (!isNullOrUndefined(e)) {
      e.preventDefault();
    }
    $('.dropdown-container').slideToggle(500, 'easeOutExpo');
  }

}
