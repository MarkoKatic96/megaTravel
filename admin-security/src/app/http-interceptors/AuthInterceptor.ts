import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable } from 'rxjs';

//za sad nista ne rade ovi interseptori, treba ih baciti u upotrebu

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

      if(localStorage.getItem('token') != null)
      {
        req = req.clone({
          setHeaders: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          }
        });
      }
      else
      {
        req = req.clone({

        })
      }

      return next.handle(req);
  }
}