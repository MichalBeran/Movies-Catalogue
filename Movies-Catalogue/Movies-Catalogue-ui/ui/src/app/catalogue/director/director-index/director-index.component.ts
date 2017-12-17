import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {Director} from '../../../models/director.model';
import {DirectorComponent} from '../director.component';
import {Router} from '@angular/router';
import {DirectorCommonComponent} from '../director.common.component';

@Component({
  selector: 'app-director-index',
  templateUrl: './director-index.component.html',
  styleUrls: ['./director-index.component.css']
})
export class DirectorIndexComponent extends DirectorCommonComponent implements OnInit {

  title = 'Directors';

  directors: Director[];

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
    this.service.get().subscribe(list => this.directors = list);
  }
}
