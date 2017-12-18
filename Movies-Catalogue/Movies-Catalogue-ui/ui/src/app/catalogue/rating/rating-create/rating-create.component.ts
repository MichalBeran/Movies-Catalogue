import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AppHelper} from "../../../app.helper";
import {Rating} from "../../../models/rating.model";

declare const Slider: any;
declare const $: any;
@Component({
  selector: 'app-rating-create',
  templateUrl: './rating-create.component.html',
  styleUrls: ['./rating-create.component.css']
})
export class RatingCreateComponent implements OnInit, AfterViewInit {

  invalid = AppHelper.invalid;
  rating: Rating = new Rating();

  ngAfterViewInit(): void {
    this.initOverallSlider();
  }

  constructor() { }

  ngOnInit() {
  }

  initOverallSlider() {
    const overall = $('#overall');
    overall.slider({
      formatter: function(value) {
        return 'Current value: ' + value;
      }
    });
    overall.on('slide', function(slideEvent){
      $('#overall-val').text(slideEvent.value);
    });


  }
}
