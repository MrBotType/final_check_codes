import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouriteArticleComponent } from './favourite-article.component';
import { LoginService } from '../login.service';
import { AppComponent } from '../app.component';
import { SignupComponent } from '../signup/signup.component';
import { LoginComponent } from '../login/login.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { of } from 'rxjs';
import { SearchComponent } from '../search/search.component';

fdescribe('FavouriteArticleComponent', () => {
  let component: FavouriteArticleComponent;
  let fixture: ComponentFixture<FavouriteArticleComponent>;
  let userService: LoginService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
        RouterModule,
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
      ],
      providers: [HttpClientModule],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FavouriteArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  
  it('should delete an article from a user-favourite list.',()=>{
    userService=fixture.debugElement.injector.get(LoginService);
    let data : any = JSON.parse(JSON.stringify({
      "messege" : "Article deleted successfully",
      "userDeleted" : true,
      "userFavourite" : false,
    }));
    spyOn(userService,'deleteArticle').and.returnValue(of(data));
    component.delete("backendtesting");
    expect(userService.deleteArticle).toHaveBeenCalledTimes(1);
    expect(component.articleDeletedStatus).toBe(true);
  });
});
