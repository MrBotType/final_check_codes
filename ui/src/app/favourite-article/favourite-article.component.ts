import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-favourite-article',
  templateUrl: './favourite-article.component.html',
  styleUrls: ['./favourite-article.component.css']
})
export class FavouriteArticleComponent implements OnInit {

  constructor(private auth: AuthService,private userservice: LoginService) { }
  userData:any;
  articlesOfUser:any;
  ngOnInit() {
    this.userData=JSON.stringify({
      "email" : this.auth.useremail
    })
    this.userservice.getOneUser(this.userData).subscribe(
      data => {
        this.articlesOfUser=data.favouriteArticle;
        console.log("Articles of User are:");
        console.log(this.articlesOfUser);
        console.log(data);
     });
  }
  articleDelete:any;
  articleDeletedStatus : boolean=false;
  delete(url) {
    this.articleDelete=JSON.stringify({
      "email" : this.auth.useremail,
      "favouriteArticle": [
        {
            "url": url
        }
      ]
    });
    this.userservice.deleteArticle(this.articleDelete).subscribe(
      data => {
        this.articleDeletedStatus = data.articleDeleted;
        console.log("after delete an article:");
        console.log(data);
        this.userservice.getOneUser(this.userData).subscribe(
          data => {
            this.articlesOfUser=data.favouriteArticle;
         });
     })
  }

}
