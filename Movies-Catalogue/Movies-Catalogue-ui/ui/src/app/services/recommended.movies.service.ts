import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {RestService} from "./rest.service";
import 'rxjs/add/operator/map';
import {Movie} from "../models/movie.model";


const HttpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable()
export class RecomMoviesService extends RestService {

  public token: string;

  constructor(protected http: HttpClient) {
    super(http);

    // const currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  find(id): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.repository}/recom/${id}`);
  }

}
