import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders(
    {
      'Content-Type': 'application/json',
    }
  )
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }
  
  login(loginUser): Observable<any>{
    let loginUrl:string="/news/user/authenticate";
    return this.http.post<any>(loginUrl, loginUser, httpOptions);
  }

  getOneUser(userData): Observable<any>{
    let getUserUrl:string="/news/user/getOneUser";
    return this.http.post<any>(getUserUrl, userData, httpOptions);
  }
  deleteArticle(articleDelete): Observable<any>{
    let getUserArticleDeleteUrl:string="/news/user/deleteArticle";
    return this.http.post<any>(getUserArticleDeleteUrl, articleDelete, httpOptions);
  }
}
