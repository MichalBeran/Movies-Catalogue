import {Component, OnInit} from '@angular/core';
import {Actor} from '../../../models/actor.model';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {isNullOrUndefined} from 'util';
import {ActorCommonComponent} from '../actor.common.component';
import {AppHelper} from '../../../app.helper';




@Component({
  selector: 'app-actor-create',
  templateUrl: './actor-create.component.html',

})
export class ActorCreateComponent extends ActorCommonComponent implements OnInit {

  actor: Actor = new Actor();

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
    if (isNullOrUndefined(this.actor.id)) {
      // create
      this.service.create(this.actor).subscribe(result => this.redirect(result));
    } else {
      // update
      this.service.update(this.actor).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: Actor) {
    this.router.navigateByUrl(`actors/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(actor: Actor) {
    this.title = `Update`;
    this.actor = actor;
    this.editing = true;
  }


}
