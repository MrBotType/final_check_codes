import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComponent } from './search.component';
import { SearchService } from '../search.service';
import { AppComponent } from '../app.component';
import { SignupComponent } from '../signup/signup.component';
import { LoginComponent } from '../login/login.component';
import { FavouriteArticleComponent } from '../favourite-article/favourite-article.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { of } from 'rxjs';

fdescribe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;
  let searchService : SearchService;

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
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  
  it('should check language for fetching news',()=>{
    searchService = fixture.debugElement.injector.get(SearchService);
    /* let data : any = JSON.parse(JSON.stringify({

    })); */
    let result : any = 'en';
    spyOn(searchService,'getLanguageCodeById').and.returnValue(of(result));
    component.ngOnInit();
    expect(searchService.getLanguageCodeById).toHaveBeenCalled();
    expect(component.languagecode).toBe('en');
   
  });

  it('should check the article status If not exist',()=>{
    searchService = fixture.debugElement.injector.get(SearchService);
    let data : any = JSON.parse(JSON.stringify({
      "messege" : "Saved As User Favourite Article.",
      "userFavourite" : false ,
      "userDeleted" : false
    }));
    spyOn(searchService,'addtofavourites').and.returnValue(of(data));
    component.addToFavourites("title","description","url");
    expect(searchService.addtofavourites).toHaveBeenCalledTimes(1);
    expect(component.userFavouriteStatus).toBe(false);
  });
  it('should check the article status If exist',()=>{
    searchService = fixture.debugElement.injector.get(SearchService);
    let data : any = JSON.parse(JSON.stringify({
      "messege" : "Already Exist As User Favourite Article.",
      "userFavourite" : true,
      "userDeleted" : false
    }));
    spyOn(searchService,'addtofavourites').and.returnValue(of(data));
    component.addToFavourites("title","description","url");
    expect(searchService.addtofavourites).toHaveBeenCalledTimes(1);
    expect(component.userFavouriteStatus).toBe(true);
  });
});
