import {Component, OnInit} from '@angular/core';
import {Movie} from '../../../models/movie.model';
import {RestService} from '../../../services/rest.service';
import {MovieComponent} from '../movie.component';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap} from 'rxjs/operators';
import {MovieCommonComponent} from '../movie.common.component';

/**
* @author Maros
*/
@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent extends MovieCommonComponent implements OnInit {

  title = 'Movie detail';

  movie: Movie = new Movie;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  async ngOnInit() {
    super.ngOnInit();

    // Shit just got real
    this.route.params.pipe(
      mergeMap(params => this.service.find(params['id'])
      ))
      .subscribe(result => this.movie = result);
  }

  remove(id){
    super.delete(id, () => super.toIndexPage());
  }

}
