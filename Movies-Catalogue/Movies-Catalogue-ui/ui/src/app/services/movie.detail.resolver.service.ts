import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {
  Router, Resolve, RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {Movie} from '../models/movie.model';
import {RestService} from './rest.service';


@Injectable()
export class MovieDetailResolver implements Resolve<Movie> {
  constructor(private service: RestService, private router: Router) {
    service.setRepository('movies');
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Movie> {
    const id = route.paramMap.get('id');

    return this.service.find(id);
  }
}
