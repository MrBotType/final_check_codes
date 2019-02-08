import { Component, OnInit } from '@angular/core';
import { SearchService } from '../search.service';
import { AuthService } from '../auth.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  languagecode: any;
  users: any;
  constructor(private auth: AuthService, private search: SearchService, private userservice: LoginService) { }
  searchedItems: any[];
  userData: any;
  userArticles: any = null;
  article: any;
  ngOnInit() {
    //  this.languagecode=  this.service.getLanguageCode();
    if (this.auth.role != 'admin') {
      this.search.getLanguageCodeById().subscribe(data => {
        console.log("getting language details based on id");
        console.log(data);
        this.languagecode = data;
        this.search.getNews(this.languagecode.code).subscribe(data => {
          console.log(data);
          this.searchedItems = data.articles;
         
        });
      });
      if (this.auth.role == 'admin') {
        this.search.getAllUsers().subscribe(data => {
          console.log("all users here : ");
          this.users = data;
          console.log(this.users);
        });
      }
    }
  }
    articleJson: any;
    userFavouriteStatus:boolean;
    
    addToFavourites(title, description, url){
      this.articleJson = JSON.stringify({
        "email": this.auth.useremail,
        "favouriteArticle": [
          {

            "title": title,
            "description": description,
            "url": url,
            "language": {
              "id": this.auth.languageid
            }
          }
        ]
      });
      console.log("Fav article is : ");
      console.log(this.articleJson);
      this.search.addtofavourites(this.articleJson).subscribe(data => {
        console.log("Added to Favourites");
        // console.log();
        console.log(data);
        this.userFavouriteStatus=data.userFavourite;
      });
    }

    blockuser(userid){
      this.search.blockuser(userid).subscribe(data => {
        console.log("Blacklisted a user");

      });
    }
  }