import {AfterContentInit, AfterViewInit, Component, ElementRef, OnInit} from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {Movie} from '../../../models/movie.model';
import {MovieComponent} from '../movie.component';
import {Router} from '@angular/router';
import {MovieCommonComponent} from '../movie.common.component';
import {MovieService} from '../../../services/movie.service';
import {ActivatedRoute} from '@angular/router';
import {isNullOrUndefined} from 'util';

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
  searchString = '';

  constructor(protected service: MovieService, protected router: Router, private route: ActivatedRoute) {
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
      const filter = this.route.snapshot.paramMap.get('filter');
      const id = this.route.snapshot.paramMap.get('id');
      if (!isNullOrUndefined(filter) && !isNullOrUndefined(id)) {
        if (filter === 'actor') {
          this.getMoviesByActor(id);
        }
        if (filter === 'director') {
          this.getMoviesByDirector(id);
        }
        if (filter === 'genre') {
          this.getMoviesByGenre(id);
        }
      } else {
        this.movies = list;
      }
      this.setTooltips();
    });
  }

  search() {
    this.service.search(this.searchString).subscribe(list => {
      this.movies = list;
      this.setTooltips();
    });
  }

  clearSearch() {
    this.searchString = '';
    this.search();
  }

  getMoviesByActor(id) {
    this.service.moviesByActor(id).subscribe(list => {
      this.movies = list;
    });
  }

  getMoviesByDirector(id) {
    this.service.moviesByDirector(id).subscribe(list => {
      this.movies = list;
    });
  }

  getMoviesByGenre(id) {
    this.service.moviesByGenre(id).subscribe(list => {
      this.movies = list;
    });
  }

  setTooltips() {
    setTimeout(function () {
      const ratings = $('.rating-stars');
      ratings.tooltip();
    }, 500);
  }
}
