import { BrowserModule } from '@angular/platform-browser';
import { NavbarComponent } from './navbar/navbar.component';
import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { SearchComponent } from './search/search.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FavouriteArticleComponent } from './favourite-article/favourite-article.component';
import { APP_BASE_HREF } from '@angular/common';

const routes: Routes = [
  { path: '', component: LoginComponent} ,
  {path: 'signup', component: SignupComponent },
  {path: 'login',component: LoginComponent},
  {path: 'search',component: SearchComponent},
  {path: 'favourite',component: FavouriteArticleComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    SearchComponent,
    FavouriteArticleComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [HttpClientModule,{provide: APP_BASE_HREF, useValue : '/' }],
  bootstrap: [AppComponent]
})
export class AppModule { }