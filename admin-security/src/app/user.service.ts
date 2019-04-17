import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface myData 
{
  message: string,
  success: boolean
}

interface isLoggedIn
{
  status: boolean
}

interface logoutStatus
{
  success: boolean
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  


}
