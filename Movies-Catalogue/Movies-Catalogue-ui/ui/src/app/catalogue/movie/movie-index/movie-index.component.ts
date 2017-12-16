import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {Movie} from '../../../models/movie.model';
import {MovieComponent} from '../movie.component';
import {Router} from '@angular/router';
import {MovieCommonComponent} from '../movie.common.component';

/**
* @author Maros Grman
*/
@Component({
  selector: 'app-movie-index',
  templateUrl: './movie-index.component.html',
  styleUrls: ['./movie-index.component.css']
})
export class MovieIndexComponent extends MovieCommonComponent implements OnInit {

  title = 'Movies';

  movies: Movie[];

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
    this.service.get().subscribe(list => this.movies = list);
  }
}
