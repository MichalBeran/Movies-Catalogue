import {Component, OnInit} from '@angular/core';
import {Genre} from '../../../models/genre.model';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {GenreCommonComponent} from '../genre.common.component';

/**
 * @author Marek Urban
 */
@Component({
  selector: 'app-genre-create',
  templateUrl: './genre-create.component.html',
  styleUrls: ['./genre-create.component.less']
})
export class GenreCreateComponent extends GenreCommonComponent implements OnInit {

  genre: Genre = new Genre();

  title = 'Create';
  editing = false;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
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
    if (isNullOrUndefined(this.genre.id)) {
      // create
      this.service.create(this.genre).subscribe(result => this.redirect(result));
    } else {
      // update
      this.service.update(this.genre).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: Genre) {
    this.router.navigateByUrl(`genres/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(genre: Genre) {
    this.title = `Update`;
    this.genre = genre;
    this.editing = true;
  }
}
