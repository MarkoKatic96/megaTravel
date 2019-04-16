import { DataService } from './../../data.service';
import { IzlistajSertifikateService } from './../../izlistaj-sertifikate.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-tabela1',
  templateUrl: './tabela1.component.html',
  styleUrls: ['./tabela1.component.css']
})
export class Tabela1Component implements OnInit {

  sertifikati: any = [];

  constructor(private listService: IzlistajSertifikateService, private data: DataService) { }

  ngOnInit() {
    this.izlistajSertifikate()
  }

  izlistajSertifikate()
  {
    this.sertifikati = [];
    this.listService.allCerts().subscribe((data: {}) =>
    {
      console.log("usao u listanje:  ");
      console.log(data);
      this.sertifikati = data;
    })
  }

}
