import {Component, OnInit} from '@angular/core';
import {Director} from '../../../models/director.model';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {DirectorCommonComponent} from '../director.common.component';
import {AppHelper} from '../../../app.helper';




@Component({
  selector: 'app-director-create',
  templateUrl: './director-create.component.html',

})
export class DirectorCreateComponent extends DirectorCommonComponent implements OnInit {

  director: Director = new Director();

  title = 'Director Create';
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
    if (isNullOrUndefined(this.director.id)) {
      // create
      this.service.create(this.director).subscribe(result => this.redirect(result));
    } else {
      // update
      var dir = new Director();
      dir.id = this.director.id;
      dir.firstName = this.director.firstName;
      dir.lastName = this.director.lastName;
      dir.date = this.director.date;
      this.service.update(dir).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: Director) {
    this.router.navigateByUrl(`directors/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(director: Director) {
    this.title = `Director Update`;
    this.director = director;
    this.editing = true;
  }


}
