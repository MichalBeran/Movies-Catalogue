import { Component, OnInit } from '@angular/core';
import {MovieCommonComponent} from '../movie.common.component';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Movie} from '../../../models/movie.model';
import {isNullOrUndefined} from 'util';
import {MovieCreate} from '../../../models/movie.create.model';

@Component({
  selector: 'app-movie-create',
  templateUrl: './movie-create.component.html',
  styleUrls: ['./movie-create.component.css']
})
export class MovieCreateComponent extends MovieCommonComponent implements OnInit {

  movie: MovieCreate = new MovieCreate();

  title = 'Create';
  editing = false;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute){
    super(service, router);
  }

  ngOnInit() {

    this.route.params.subscribe(params => {
      if (!isNullOrUndefined(params['id'])) {
        this.service.find(params['id']).subscribe(res => this.setFormForEdit(res));
      }
    });
  }

  save() {
    if (isNullOrUndefined(this.movie.id)) {
      // create
      this.service.create(this.movie).subscribe(result => this.redirect(result));
    } else {
      // update
      this.service.update(this.movie).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: Movie) {
    this.router.navigateByUrl(`movies/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(movie: any) {
    this.title = `Update`;
    this.movie = movie;
    this.editing = true;
  }


}

