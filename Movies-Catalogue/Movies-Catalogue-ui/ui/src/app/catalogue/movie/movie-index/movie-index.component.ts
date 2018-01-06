import {AfterContentInit, AfterViewInit, Component, ElementRef, OnInit} from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {Movie} from '../../../models/movie.model';
import {MovieComponent} from '../movie.component';
import {Router} from '@angular/router';
import {MovieCommonComponent} from '../movie.common.component';
import {MovieService} from "../../../services/movie.service";

declare const $: any;

/**
 * @author Maros Grman
 */
@Component({
  selector: 'app-movie-index',
  templateUrl: './movie-index.component.html',
  styleUrls: ['./movie-index.component.less']
})
export class MovieIndexComponent extends MovieCommonComponent implements OnInit {

  title = 'Movies';

  movies: Movie[];
  searchString = "";

  constructor(protected service: MovieService, protected router: Router) {
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
    this.service.get().subscribe(list => {
      this.movies = list;
      this.setTooltips();
    });
  }

  search(){
    this.service.search(this.searchString).subscribe(list => {
      this.movies = list;
      this.setTooltips();
    });
  }

  setTooltips() {
    setTimeout(function () {
      const ratings = $('.rating-stars');
      ratings.tooltip();
    }, 500);
  }
}
