import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  role="";
  useremail:any;
  languagecode:any;
  languageid:any;
  isLogggedIn = false;
  isButtonClicked=false;
  constructor() { }
}
