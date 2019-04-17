import { DataService } from './../../data.service';
import { Component, OnInit } from '@angular/core';
import { PoveziSertifikateService } from 'src/app/povezi-sertifikate.service';

@Component({
  selector: 'app-povezani-sertifikati',
  templateUrl: './povezani-sertifikati.component.html',
  styleUrls: ['./povezani-sertifikati.component.css']
})
export class PovezaniSertifikatiComponent implements OnInit {

  povezaniSertifikati: any = [];

  povezaniId: number;

  constructor(private data: DataService, private povezani: PoveziSertifikateService) { }

  ngOnInit() {
    this.connectedCertsList();
    this.data.currPovezaniSert.subscribe(res => this.povezaniId = res);
  }

  connectedCertsList()
  {
    this.povezaniSertifikati = [];
    this.povezani.allConnectedCerts().subscribe((data: {}) =>
    {
      console.log("usao u listanje:  ");
      console.log(data);
      this.povezaniSertifikati = data;
    })
  }

  selectConnCert(event, cert)
  {
    cert.flag = !cert.flag;
    this.povezaniSertifikati.forEach(element => {
      if(cert.id == element.id)
      {
        this.data.changePovezaniSert(element.id);
      }
    });
  }

}
