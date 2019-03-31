import { HttpClient, HttpClientModule } from '@angular/common/http';
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

  token: string;

  constructor(private http: HttpClient) { }

  getUserDetails(email, password) //uzimamo podatke od korisnika i saljemo na server
  {
    return this.http.post<myData>('https://localhost:8443/api/login', {
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
