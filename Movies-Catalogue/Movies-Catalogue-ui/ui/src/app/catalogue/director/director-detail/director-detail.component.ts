import {Component, OnInit} from '@angular/core';
import {Director} from '../../../models/director.model';
import {RestService} from '../../../services/rest.service';
import {DirectorComponent} from '../director.component';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap} from 'rxjs/operators';
import {DirectorCommonComponent} from '../director.common.component';

@Component({
  selector: 'app-director-detail',
  templateUrl: './director-detail.component.html',
  styleUrls: ['./director-detail.component.css']
})
export class DirectorDetailComponent extends DirectorCommonComponent implements OnInit {

  title = 'Director detail';

  director: Director = new Director;
  error = null;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  async ngOnInit() {
    super.ngOnInit();

    this.route.params.pipe(
      mergeMap(params => this.service.find(params['id'])
      ))
      .subscribe(result => this.director = result);
  }

  remove(id){
    super.delete(id, () => super.toIndexPage());
    this.error = super.getError();
  }

}
