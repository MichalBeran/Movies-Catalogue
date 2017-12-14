import {Component, OnInit} from '@angular/core';
import {Genre} from '../../../models/genre.model';
import {RestService} from '../../../services/rest.service';
import {GenreComponent} from '../genre.component';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap} from 'rxjs/operators';
import {GenreCommonComponent} from '../genre.common.component';

/**
 * @author Marek Urban
 */
@Component({
  selector: 'app-genre-detail',
  templateUrl: './genre-detail.component.html',
  styleUrls: ['./genre-detail.component.css']
})
export class GenreDetailComponent extends GenreCommonComponent implements OnInit {

  title = 'Genre detail';

  genre: Genre = new Genre;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  async ngOnInit() {
    super.ngOnInit();

    // Shit just got real
    this.route.params.pipe(
      mergeMap(params => this.service.find(params['id'])
      ))
      .subscribe(result => this.genre = result);
  }

  remove(id){
    super.delete(id, () => super.toIndexPage());
  }

}
