import {Component, OnInit} from '@angular/core';
import {Actor} from '../../../models/actor.model';
import {RestService} from '../../../services/rest.service';
import {ActorComponent} from '../actor.component';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap} from 'rxjs/operators';
import {Actor} from '../actor.common.component';

@Component({
  selector: 'app-actor-detail',
  templateUrl: './actor-detail.component.html',
  styleUrls: ['./actor-detail.component.css']
})
export class ActorDetailComponent extends ActorCommonComponent implements OnInit {

  title = 'Actor detail';

  actor: Actor = new Actor;

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  async ngOnInit() {
    super.ngOnInit();

    this.route.params.pipe(
      mergeMap(params => this.service.find(params['id'])
      ))
      .subscribe(result => this.actor = result);
  }

  remove(id){
    super.delete(id, () => super.toIndexPage());
  }

}
