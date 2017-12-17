import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {RestService} from '../../../services/rest.service';
import {RatingCommonComponent} from '../rating.common.component';
import {Rating} from "../../../models/rating.model";

@Component({
  selector: 'app-rating-index',
  templateUrl: './rating-index.component.html',
  styleUrls: ['./rating-index.component.css']
})
export class RatingIndexComponent extends RatingCommonComponent implements OnInit {

  title = 'Ratings';

  ratings: Rating[];

  constructor(protected service: RestService, protected router: Router) {
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
    this.service.get().subscribe(list => this.ratings = list);
  }
}
