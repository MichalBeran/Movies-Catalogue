import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {Router} from '@angular/router';

/**
 * @author Marek Urban
 */
@Component({
  template: ''
})
export class GenreCommonComponent implements OnInit {

  constructor(protected service: RestService, protected router: Router) {
  }

  ngOnInit() {
    this.service.setRepository('genres');
  }

  protected delete(id, callback: () => void) {
    this.service.delete(id).subscribe(res => callback());
  }

  public afterDelete(res) {
    // blank
  }

  protected toIndexPage() {
    this.router.navigateByUrl(`genres/index`);
  }
}
