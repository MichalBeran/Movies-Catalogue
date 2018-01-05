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

  title = 'Actor Create';
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
      var act = new Actor();
      act.id = this.actor.id;
      act.firstName = this.actor.firstName;
      act.lastName = this.actor.lastName;
      act.date = this.actor.date;
      this.service.update(act).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: Actor) {
    this.router.navigateByUrl(`actors/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(actor: Actor) {
    this.title = `Actor Update`;
    this.actor = actor;
    this.editing = true;
  }


}
