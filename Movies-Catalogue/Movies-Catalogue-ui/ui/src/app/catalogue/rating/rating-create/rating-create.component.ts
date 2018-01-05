import {AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {AppHelper} from '../../../app.helper';
import {Rating} from '../../../models/rating.model';
import {RatingCommonComponent} from '../rating.common.component';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Movie} from '../../../models/movie.model';
import {isNullOrUndefined} from 'util';

declare const Slider: any;
declare const $: any;

@Component({
  selector: 'app-rating-create',
  templateUrl: './rating-create.component.html',
  styleUrls: ['./rating-create.component.less']
})
export class RatingCreateComponent extends RatingCommonComponent implements OnInit, AfterViewInit, OnChanges {


  getAuthenticated = AppHelper.getAuthenticated;


  rating: Rating = new Rating();
  lastReload: 0;
  @Input() movie: Movie;
  @Input() needsReload: number;
  @Output() onChanged = new EventEmitter<boolean>();

  btnText = 'Rate movie!';
  edit = false;
  cleared = false;
  sliders: any = {};

  form: any;

  ngAfterViewInit(): void {
  }

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  ngOnInit() {
    this.initNullRating();
    const user = this.getAuthenticated().id;
    if (!isNullOrUndefined(user)) {
      this.service.get().subscribe((list: Rating[]) => this.checkUserRating(list, user));
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.needsReload.currentValue !== this.lastReload) {
      if (!changes.needsReload.firstChange) {
        this.lastReload = changes.needsReload.currentValue;
        this.clearInputs();
      }
    }
  }

  checkUserRating(list: Rating[], user: number) {
    let userHasReview = false;
    for (const rating of list) {
      if (!isNullOrUndefined(rating.user.id)
        && !isNullOrUndefined(rating.movie.id)
        && rating.movie.id === this.movie.id
        && rating.user.id === user) {
        this.showAsEdit(rating);
        userHasReview = true;
        break;
      }
    }
    if (!userHasReview) {
      this.initSliders();
    }
  }

  showAsEdit(rating: Rating) {
    this.edit = true;
    this.rating = rating;
    this.btnText = 'Edit my review';
    this.initSliders();
  }

  showAsCreate() {
    this.edit = false;
    this.rating.overallRating = 0;
    this.rating = new Rating();
    this.initNullRating();
    this.btnText = 'Rate movie!';
    this.resetSliders(this.sliders);
    this.cleared = true;
  }

  wasCleared() {
    const cleared = this.cleared;
    this.cleared = false;
    return cleared;
  }

  resetSliders(sliders: any) {
    $('#overall-slider').slider({value: 0});
    $('#overall-val').text(0);
    $('#actors-slider').slider({value: 0});
    $('#actors-val').text(0);
    $('#scenario-slider').slider({value: 0});
    $('#scenario-val').text(0);
  }

  initSliders() {
    const thatRating = this.rating;
    const overall = $('#overall');
    overall.slider({value: this.rating.overallRating});
    overall.on('slide', function (slideEvent) {
      $('#overall-val').text(slideEvent.value);
      thatRating.overallRating = slideEvent.value;
    });
    this.sliders.overall = overall;

    const actors = $('#actors');
    actors.slider({value: this.rating.actorsRating});
    actors.on('slide', function (slideEvent) {
      $('#actors-val').text(slideEvent.value);
      thatRating.actorsRating = slideEvent.value;
    })
    this.sliders.actors = actors;


    const scenario = $('#scenario');
    scenario.slider({value: this.rating.scenarioRating});
    scenario.on('slide', function (slideEvent) {
      $('#scenario-val').text(slideEvent.value);
      thatRating.scenarioRating = slideEvent.value;
    });
    this.sliders.scenario = scenario;

  }

  toggleNewRatingForm() {
    $('.rating-create-container').slideToggle(500);
  }

  save() {
    const auth = this.getAuthenticated();
    if (auth) {
      this.rating.user = auth;
      this.rating.movie.id = this.movie.id;
      if (this.edit) {
        this.service.update(this.rating).subscribe(result => this.refresh());
      } else {
        this.service.create(this.rating).subscribe(result => {
          this.refresh();
          this.showAsEdit(result);
        });
        this.toggleNewRatingForm();
        this.btnText = 'Edit my review';
      }
    }
  }

  refresh() {
    this.onChanged.emit(true);
  }

  private clearInputs() {
    this.showAsCreate();
  }

  initNullRating() {
    this.rating.overallRating = 0;
    this.rating.actorsRating = 0;
    this.rating.scenarioRating = 0;
  }

  isInvalid(model) {
    // custom validation rule to fix the error listing after deleting review
    // unfortunately the input disables itself after model.reset()
    //if (this.cleared) {
    //  model.reset();
    //}
    return AppHelper.invalid(model);
  }
}
