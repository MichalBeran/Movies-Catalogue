import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AppHelper} from '../../../app.helper';
import {Rating} from '../../../models/rating.model';
import {RatingCommonComponent} from '../rating.common.component';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Movie} from '../../../models/movie.model';

declare const Slider: any;
declare const $: any;

@Component({
  selector: 'app-rating-create',
  templateUrl: './rating-create.component.html',
  styleUrls: ['./rating-create.component.less']
})
export class RatingCreateComponent extends RatingCommonComponent implements OnInit, AfterViewInit {
  getAuthenticated = AppHelper.getAuthenticated;

  invalid = AppHelper.invalid;
  rating: Rating = new Rating();

  @Input() movie: Movie;
  @Output() onChanged = new EventEmitter<boolean>();

  ngAfterViewInit(): void {
    this.initSliders();
  }

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  ngOnInit() {
  }

  initSliders() {
    const thatRating = this.rating;
    const overall = $('#overall');
    overall.slider();
    overall.on('slide', function (slideEvent) {
      $('#overall-val').text(slideEvent.value);
      thatRating.overallRating = slideEvent.value;
    });

    const actors = $('#actors');
    actors.slider();
    actors.on('slide', function (slideEvent) {
      $('#actors-val').text(slideEvent.value);
      thatRating.actorsRating = slideEvent.value;
    });

    const scenario = $('#scenario');
    scenario.slider();
    scenario.on('slide', function (slideEvent) {
      $('#scenario-val').text(slideEvent.value);
      thatRating.scenarioRating = slideEvent.value;
    });
  }

  toggleNewRatingForm() {
    $('.rating-create-container').slideToggle(500);
  }

  save() {
    const auth = this.getAuthenticated();
    if (auth) {
      this.rating.user = auth;
      this.rating.movie.id = this.movie.id;
      this.service.create(this.rating).subscribe(result => this.refresh());
    }
  }

  refresh() {
    this.onChanged.emit(true);
  }
}
