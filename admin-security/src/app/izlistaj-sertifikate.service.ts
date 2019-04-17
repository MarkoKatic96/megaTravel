import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { RequestOptions, ResponseContentType } from '@angular/http';

export interface Certs {
  
}

@Injectable({
  providedIn: 'root'
})
export class IzlistajSertifikateService {

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  private nadsertifikat: string;


  constructor(private http: HttpClient) { }

  allCerts(): Observable<any>
  {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
    console.log(headers)
    return this.http.get('https://localhost:8443/api/certificate/all', {
      headers
    }).pipe(map(this.extractData))
  }

  certBySn(id): Observable<Blob>
  {
    let options = new RequestOptions({responseType: ResponseContentType.Blob});
    return this.http.get('https://localhost:8443/api/certificate/sn/' + id, {responseType: 'blob'}).pipe (
      tap (
        data => console.log('You received data'),
        error => console.log(error)
      )
     );
  }
}
