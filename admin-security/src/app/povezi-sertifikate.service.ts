import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PoveziSertifikateService {

  private extractData(res: Response)
  {
    let body = res;
    return body || {};
  }

  constructor(private http: HttpClient) { }

  connectCerts(id, serijskiBroj1, serijskiBroj2): Observable<any>
  {
    return this.http.put('https://localhost:8443/api/communication', {
      id, serijskiBroj1, serijskiBroj2
    }).pipe(map(this.extractData))
  }
}
