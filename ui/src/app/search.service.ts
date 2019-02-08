import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class SearchService {

 
  constructor(private http: HttpClient,private auth: AuthService) { }

  apiKey: string = "150b2bcb78cd410cb386549ae9d991d4";

  getNews(languagecode): Observable<any> {
    let newsUrl: string = "https://newsapi.org/v2/top-headlines?language="+languagecode+"&apiKey="+this.apiKey;
     return this.http.get(newsUrl);

  }
  getLanguageCodeById(): Observable<any>{
    let languageUrl:string = "/news/user/language/";
    return this.http.get(languageUrl+this.auth.languageid);
  }
  getAllUsers(): Observable<any>{
    let userUrl:string = "/news/user/user/getAll";
    return this.http.get(userUrl);
  }
  addtofavourites(article): Observable<any>{
    let favUrl:string="/news/user/saveArticleList";
    return this.http.post<any>(favUrl, article, httpOptions);
  }
  blockuser(userid): Observable<any>{
    let blockUrl:string="/news/user/blacklist/";
    return this.http.get(blockUrl+userid);
  }
  articlepresent(userarticle):Observable<any>{
    let articleUrl:string="/news/user/checkArticle";
    return this.http.post<any>(articleUrl, userarticle, httpOptions);
  }
}
