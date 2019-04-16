import { KreirajSertService } from './../kreiraj-sert.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-kreiraj-sertifikat',
  templateUrl: './kreiraj-sertifikat.component.html',
  styleUrls: ['./kreiraj-sertifikat.component.css']
})
export class KreirajSertifikatComponent implements OnInit {

  selected: String = 'APLIKACIJA';

  sertVrste: any[];
 
  constructor(private createCert: KreirajSertService)
  {
    
  }


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

  selectChangeHandler(event: any)
  {
    this.selected = event.target.value;
  }

}
