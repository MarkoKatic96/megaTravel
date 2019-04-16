import { DataService } from './../../data.service';
import { KreirajSertService } from './../../kreiraj-sert.service';
import { Component, OnInit, Input } from '@angular/core';
import { IzlistajSertifikateService } from 'src/app/izlistaj-sertifikate.service';

@Component({
  selector: 'app-aplikacija',
  templateUrl: './aplikacija.component.html',
  styleUrls: ['./aplikacija.component.css']
})
export class AplikacijaComponent implements OnInit {

  retVal: any = [];

  nadsertifikat: string;

  
  constructor(private createSert: KreirajSertService, private listaj: IzlistajSertifikateService, private data: DataService) { }

  ngOnInit() {
    this.data.currentNads.subscribe(nads => this.nadsertifikat = nads)
  }

  kreirajAplikacija(event)
  {
    event.preventDefault();

    const target = event.target;
    const pocetak = target.querySelector('#pocetak').value; //uzima
    const kraj = target.querySelector('#kraj').value; //uzima
    const keystore = target.querySelector('#keystore').value; //kako poslati fajl??
    const sertifikat = target.querySelector('#sertifikat').value; //putanja gde ce snimiti sertifikat
    const nadsertifikat = target.querySelector('#nadsertifikat').value;
    const nazivaplikacije = target.querySelector('#nazivaplikacije').value; //uzima
    const organizacija = target.querySelector('#organizacija').value; //uzima
    const verzijaaplikacije = target.querySelector('#verzijaaplikacije').value; //uzima
    
    var id = Math.floor((Math.random() * 100) + 13);
    console.log("ID -> " + id)

    var serijskibroj = Math.floor((Math.random() * 1000) + 121355);

    const tip = 'APLIKACIJA';
    var current: Date = new Date();
    var datumkreiranja = current.getFullYear() + '-' + current.getMonth() + '-' + current.getDate();
    console.log(id, sertifikat,  pocetak, kraj, keystore, sertifikat, nadsertifikat, nazivaplikacije, organizacija, verzijaaplikacije);


    this.retVal = [];
    this.createSert.createCert(id, sertifikat, pocetak, kraj, datumkreiranja, serijskibroj,  tip, nadsertifikat).subscribe((res: {}) =>
    {
      console.log("kreiranje..");
      console.log(res);
      this.retVal = res;
      //nakon kreiranja, izlistaj (izgleda ovo baca error)
      //this.listaj.izlistajSertifikate();
    })

  }

  uzmiNadsertifikat()
  {
    //this.nadsertifikat = this.listaj.getNadsertifikat();
    console.log("POZIV IZ APLIKACIJE:" + this.nadsertifikat)
  }

}
