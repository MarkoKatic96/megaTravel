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
