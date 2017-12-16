import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {RestService} from "./rest.service";
import 'rxjs/add/operator/map';


const HttpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable()
export class UserService extends RestService {

  public token: string;

  constructor(protected http: HttpClient) {
    super(http);

    // const currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  login(mail: String, password: String): Observable<any> {
    return this.http.post<any>(`${this.repository}/login`, {mail: mail, password: password}, HttpOptions)
      .map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.id) {
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      });
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    localStorage.removeItem('currentUser');
  }
}
