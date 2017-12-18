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
  needsReload = 0;
  isAuthenticated = AppHelper.isAuthenticated;

  onChanged(changed: boolean) {
    this.needsReload++;
  }

  constructor() {
  }

  ngOnInit() {
  }

}
