import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProbaService {
  
  url: string = 'http://localhost:8443/api/certificate/all';

  constructor(private http: HttpClient) { }

  getSertifikate()
  {
    return this.http.get(this.url);
  }

  // showTodayDate()
  // {
  //   let ndate = new Date();
  //   return ndate;
  // }
}
