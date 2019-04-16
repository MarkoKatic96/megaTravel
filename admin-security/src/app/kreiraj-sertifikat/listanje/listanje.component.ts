import { DataService } from './../../data.service';
import { Component, OnInit, Directive, Input, Renderer2 } from '@angular/core';
import { IzlistajSertifikateService } from 'src/app/izlistaj-sertifikate.service';

@Component({
  selector: 'app-listanje',
  templateUrl: './listanje.component.html',
  styleUrls: ['./listanje.component.css']
})
export class ListanjeComponent implements OnInit {

  sertifikati: any = [];
  flag: boolean = false;

  
  proslediNads: string;

  constructor(private listService: IzlistajSertifikateService, private data: DataService, private render: Renderer2) { }

  ngOnInit() {
    this.izlistajSertifikate()
    this.data.currentNads.subscribe(nads => this.proslediNads = nads)
    console.log(this.proslediNads);
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
        this.data.changeNads(cert.serijskiBroj);
      }
    });
  }

}
