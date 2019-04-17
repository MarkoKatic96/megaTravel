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

  serijskiBrojS1: number;
  

  constructor(private listService: IzlistajSertifikateService, private data: DataService) { }

  ngOnInit() {
    this.izlistajSertifikate()
    this.data.currIzaberiSert1.subscribe(res => this.serijskiBrojS1 = res);
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


  public selectCert(event: any, cert: any)
  {
    cert.flag = !cert.flag;
    this.sertifikati.forEach(element => {
      if(cert.id == element.id)
      {
        this.data.changeSert1(element.serijskiBroj);
      }
    });
  }
}
