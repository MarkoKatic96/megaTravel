import { DataService } from './../../data.service';
import { Component, OnInit } from '@angular/core';
import { IzlistajSertifikateService } from 'src/app/izlistaj-sertifikate.service';

@Component({
  selector: 'app-tabela2',
  templateUrl: './tabela2.component.html',
  styleUrls: ['./tabela2.component.css']
})
export class Tabela2Component implements OnInit {

  sertifikati: any = [];
  serijskiBrojS2: number;
  

  constructor(private listService: IzlistajSertifikateService, private data: DataService) { }

  ngOnInit() {
    this.izlistajSertifikate()
    this.data.currIzaberiSert2.subscribe(res => this.serijskiBrojS2 = res);
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
        this.data.changeSert2(element.serijskiBroj);
      }
    });
  }

}
