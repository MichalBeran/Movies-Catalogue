import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Router} from '@angular/router';
import {RestService} from '../../../services/rest.service';
import {RatingCommonComponent} from '../rating.common.component';
import {Rating} from "../../../models/rating.model";
import {AppHelper} from "../../../app.helper";
import {Movie} from "../../../models/movie.model";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-rating-index',
  templateUrl: './rating-index.component.html',
  styleUrls: ['./rating-index.component.less']
})
export class RatingIndexComponent extends RatingCommonComponent implements OnInit, OnChanges {
  getAuthenticated = AppHelper.getAuthenticated;

  @Input() needsReload: number;
  @Input() movie: Movie;
  lastReload: 0;
  title = 'Ratings';

  ratings: Rating[] = [];

  constructor(protected service: RestService, protected router: Router) {
    super(service, router);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.needsReload.currentValue !== this.lastReload) {
      if (!changes.needsReload.firstChange) {
        this.lastReload = changes.needsReload.currentValue;
        this.refresh();
      }
    }
  }

  ngOnInit() {
    console.log('movie:' + this.movie.id);
    super.ngOnInit();
    this.refresh();
  }

  remove(e, id) {
    e.preventDefault();
    super.delete(id, () => this.refresh());
  }

  refresh() {
    this.service.get().subscribe((list: Rating[]) => this.setRatings(list));
  }

  setRatings(list: Rating[]) {
    this.ratings = [];
    list.forEach(rating => {
      if (rating.movie.id === this.movie.id) {
        this.ratings.push(rating);
      }
    });
  }
}
