import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KreirajSertService {

  private extractData(res: Response)
  { 
    let body = res;
    return body || {};
  }
  

  constructor(private http: HttpClient)
  {

  }

  createCert(x500Name, serialNumber, startDate, endDate, publicKey, privateKey, rootSerialNumber, tipCertifikata): Observable<any>
  {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
    console.log(headers)
    return this.http.put('https://localhost:8443/api/certificate/create', {
      x500Name, serialNumber, startDate, endDate, publicKey, privateKey, rootSerialNumber, tipCertifikata
    }).pipe(map(this.extractData))  
  }

}
