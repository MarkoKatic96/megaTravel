import { CookieService } from 'ngx-cookie-service';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpInterceptor } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private token: string;

  constructor(private Auth: AuthService, private router: Router, private cookie: CookieService) { }

  ngOnInit() {

  }

  loginUser(event) {
    event.preventDefault();

    const target = event.target;
    const email = target.querySelector('#email').value;
    const password = target.querySelector('#password').value;

    this.Auth.getUserDetails(email, password).subscribe(data =>
    {
      this.token = data.toString(); //samo za proveru
      console.log(this.token);
      this.router.navigate(['kreirajsert']);
      this.Auth.setLoggedIn(true);
      localStorage.setItem('token', data.toString()); //smestamo token u localstorage
    })
  }

  //ne radi ovo bas set i get cookie
  // setCookie()
  // {
  //   this.cookie.set('token', this.token);
  // }

  // getCookie(): string
  // {
  //   console.log(this.cookie.get('token').toString());
  //   return this.cookie.get('token').toString();
  // }

  getToken(): string
  {
    return this.token;
  }



}

export class HttpAuthInterceptor implements HttpInterceptor
{
  intercept
}