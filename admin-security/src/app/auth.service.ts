import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

interface myData
{
  success: boolean,
  message: string
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedInStatus = false;

  constructor(private http: HttpClient) { }

  getUserDetails(email, password)
  {
    return this.http.post('https://localhost:8443/api/login', {
      email,
      password
    })
  }

  setLoggedIn(value: boolean)
  {
    this.loggedInStatus = value;
  } 

  get isLoggedIn()
  {
    return this.loggedInStatus;
  }

}
