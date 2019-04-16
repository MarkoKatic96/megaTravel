import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IzbrisiSertifikatService {

  constructor(private http: HttpClient) { }

  izbrisi(id): Observable<any>
  {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.delete('https://localhost:8443/api/certificate/id/' + id, {
      headers
    })
  }

}
