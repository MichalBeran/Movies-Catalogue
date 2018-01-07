import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {Genre} from '../../../models/genre.model';
import {GenreComponent} from '../genre.component';
import {Router} from '@angular/router';
import {GenreCommonComponent} from '../genre.common.component';

/**
 * @author Marek Urban
 */
@Component({
  selector: 'app-genre-index',
  templateUrl: './genre-index.component.html',
  styleUrls: ['./genre-index.component.css']
})
export class GenreIndexComponent extends GenreCommonComponent implements OnInit {

  title = 'Genres';

  genres: Genre[];

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
    this.service.get().subscribe(list => this.genres = list);
  }

  getMovies(id){
    this.router.navigate(['/movies/index',{genre: id}]);
  }
}
