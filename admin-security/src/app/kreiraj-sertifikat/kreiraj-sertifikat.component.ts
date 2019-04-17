import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kreiraj-sertifikat',
  templateUrl: './kreiraj-sertifikat.component.html',
  styleUrls: ['./kreiraj-sertifikat.component.css'],
  template: '<app-kreiranje [krSertVrste]="sertVrste"></app-kreiranje>' //prosledjivanje
})
export class KreirajSertifikatComponent implements OnInit {

  sertVrste: String[];

  constructor() { }

  ngOnInit()
  {
    this.sertVrste = [
      "APLIKACIJA",
      "DOMEN",
      "OPREMA",
      "ORGANIZACIJA",
      "OSOBA",
      "ROOT",
      "CA_APLIKACIJA",
      "CA_DOMEN",
      "CA_OPREMA",
      "CA_ORGANIZACIJA",
      "CA_OSOBA"
    ]
  }

  

}
