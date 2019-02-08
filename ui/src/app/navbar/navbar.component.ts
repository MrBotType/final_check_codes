import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private service : AuthService,private router: Router) { }
  loggedIn:boolean;
  ngOnInit() {}
  
  logout(){
    this.service.isLogggedIn=false;
    this.router.navigate(['/login']);
  }
  signup(){
    this.router.navigate(['/signup']);
  }
  favouriteArticle(){
    this.router.navigate(['/favourite']);
  }
  search(){
    this.router.navigate(['/search']);
  }
}
