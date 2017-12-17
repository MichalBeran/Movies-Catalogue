import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {Router} from '@angular/router';
import {AppHelper} from '../../app.helper';

@Component({
  template: ''
})
export class ActorCommonComponent implements OnInit {
  public invalid = AppHelper.invalid;


  constructor(protected service: RestService, protected router: Router) {
  }

  ngOnInit() {
    this.service.setRepository('actors');
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback());
  }

  public afterDelete(res) {
    // blank
  }

  protected toIndexPage() {
    this.router.navigateByUrl(`actors/index`);
  }

  invalid(model: any): boolean {
    return AppHelper.invalid(model);
  }

}
