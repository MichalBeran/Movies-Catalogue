import { Component, OnInit } from '@angular/core';
import {AppHelper} from '../../app.helper';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.less']
})
export class SidenavComponent implements OnInit {

  isAdmin = AppHelper.isAdmin;
  constructor() { }

  ngOnInit() {
  }

}
