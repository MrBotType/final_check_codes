import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { AuthService } from '../auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service:LoginService,private auth:AuthService,private fb : FormBuilder, private router: Router) { }

  ngOnInit() {
  
  }
  dataForAuthenticate:any;
  loginForm =  this.fb.group({
    email: ['',Validators.compose([Validators.required,Validators.minLength(8),Validators.maxLength(50)])],
    password: ['',Validators.compose([Validators.required,Validators.minLength(3),Validators.maxLength(20)])]
  });
  //loginJson:any;
  login(){
   /*  this.loginJson=JSON.stringify({
      "email" : this.loginForm.value.email,
      "password" : this.loginForm.value.password
    }); */
    console.log("loginJson is:");
    console.log(this.loginForm.value);

    this.service.login(this.loginForm.value).subscribe(
      data => {
        console.log("authenticate status:");
        console.log(data);
        this.dataForAuthenticate=data;
        if(data.authenticated){
          this.auth.role=data.user.role.name;
          this.auth.languageid=data.user.language.id;
          this.auth.useremail=data.user.email;
          this.auth.isLogggedIn=data.authenticated;
          this.router.navigate(['/search']);
        } else {
          this.auth.isLogggedIn=data.authenticated;
        }
     })
  }

}
