import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {Router} from '@angular/router';
import {AppHelper} from '../../app.helper';

@Component({
  template: ''
})
export class DirectorCommonComponent implements OnInit {

  constructor(protected service: RestService, protected router: Router) {
  }

  ngOnInit() {
    this.service.setRepository('directors');
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback());
  }

  public afterDelete(res) {
    // blank
  }

  protected toIndexPage() {
    this.router.navigateByUrl(`directors/index`);
  }

  invalid(model: any): boolean {
    return AppHelper.invalid(model);
  }

}
