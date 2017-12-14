import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';

const HttpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable()
export class RestService {


  //private baseUrl = 'https://jsonplaceholder.typicode.com/posts';
  private baseUrl = 'http://localhost:8080/';
  private repository = this.baseUrl;

  public setRepository(name: string) {
    this.repository = this.baseUrl + name;
  }

  constructor(private http: HttpClient) {
  }

  get(): Observable<any[]> {
    return this.http.get<any[]>(this.repository);
  }

  find(id): Observable<any> {
    return this.http.get<any>(`${this.repository}/${id}`);
  }

  create(model: any): Observable<any> {
    return this.http.post<any>(this.repository, model, HttpOptions);
  }

  update(model: any): Observable<any> {
    return this.http.put<any>(`${this.repository}/${model.id}`, model, HttpOptions);
  }

  delete(id): Observable<any> {
    return this.http.delete<any>(`${this.repository}/${id}`);
  }
}
