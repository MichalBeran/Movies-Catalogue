import {Component, OnInit, Input} from '@angular/core';
import {AppHelper} from '../../app.helper';
import {Movie} from '../../models/movie.model';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  @Input() movie: Movie;
  detailNeedsReload = 0;
  indexNeedsReload = 0;
  isAuthenticated = AppHelper.isAuthenticated;

  onDetailChanged(changed: boolean) {
    this.indexNeedsReload++;
  }
  onIndexChanged(changed: boolean) {
    this.detailNeedsReload++;
  }

  constructor() {
  }

  ngOnInit() {
  }

}
