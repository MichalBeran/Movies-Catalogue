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
export class MovieService extends RestService {

  public token: string;

  constructor(protected http: HttpClient) {
    super(http);
  }

  search(searchString: String): Observable<Movie[]> {
    return this.http.post<Movie[]>(`${this.repository}/search`, {title: searchString}, HttpOptions);
  }

  moviesByActor(id): Observable<Movie[]> {
    return this.http.post<Movie[]>(`${this.repository}/byActor`, {id: id}, HttpOptions);
  }

  moviesByDirector(id): Observable<Movie[]> {
    return this.http.post<Movie[]>(`${this.repository}/byDirector`, {id: id}, HttpOptions);
  }
}
