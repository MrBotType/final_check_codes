import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { SearchComponent } from './search/search.component';
import { FavouriteArticleComponent } from './favourite-article/favourite-article.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NavbarComponent } from './navbar/navbar.component';
import { APP_BASE_HREF } from '@angular/common';

fdescribe('AppComponent', () => {
  const routes: Routes = [
    { path: '', component: LoginComponent} ,
    {path: 'signup', component: SignupComponent },
    {path: 'login',component: LoginComponent},
    {path: 'search',component: SearchComponent},
    {path: 'favourite',component: FavouriteArticleComponent}
  ];



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
        RouterModule.forRoot(routes),
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
      ],
      providers: [{provide: APP_BASE_HREF, useValue : '/' }],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'news'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('news');
  });

  // it('should render title in a h1 tag', () => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   fixture.detectChanges();
  //   const compiled = fixture.debugElement.nativeElement;
  //   expect(compiled.querySelector('h1').textContent).toContain('Welcome to news!');
  // });
});
