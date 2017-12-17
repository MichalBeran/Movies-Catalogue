import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../services/rest.service';
import {Actor} from '../../../models/actor.model';
import {ActorComponent} from '../actor.component';
import {Router} from '@angular/router';
import {ActorCommonComponent} from '../actor.common.component';

@Component({
  selector: 'app-actor-index',
  templateUrl: './actor-index.component.html',
  styleUrls: ['./actor-index.component.css']
})
export class ActorIndexComponent extends ActorCommonComponent implements OnInit {

  title = 'Actors';

  actors: Actor[];

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
    this.service.get().subscribe(list => this.actors = list);
  }
}
