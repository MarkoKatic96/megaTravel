import { LoginComponent } from './login/login.component';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private loggedInStatus = false;

  private decoder: JwtHelperService;

  

  constructor(private http: HttpClient) { }

  getUserDetails(email, lozinka) //uzimamo podatke od korisnika i saljemo na server
  {
    return this.http.post('https://localhost:8443/api/login', {
      email,
      lozinka
    })
  }

  private setSession(authRes)
  {
    localStorage.setItem('token', authRes.setToken);
  }

  setLoggedIn(value: boolean)
  {
    this.loggedInStatus = value;
  } 

  get isLoggedIn()
  {
    return this.loggedInStatus;
  }

  //za proveru tokena (funkcija iz jwt)
  // getToken(): Observable<string>
  // {
  //   const token = localStorage.getItem('token');  
  // }

}
