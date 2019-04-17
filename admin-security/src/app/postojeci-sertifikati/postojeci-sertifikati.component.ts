import { Component, OnInit } from '@angular/core';
import { HttpClient } from  "@angular/common/http";
import { Observable } from 'rxjs';
import { ProbaService } from '../proba.service';

class  Sertifikat {

  id : number;

  tip: string;
  
  pocetak: string;
  
  kraj: string;
  
  serijski_broj: number;

  nadsertifikat: number;
  
  }

@Component({
  selector: 'app-postojeci-sertifikati',
  templateUrl: './postojeci-sertifikati.component.html',
  styleUrls: ['./postojeci-sertifikati.component.css']
})
export class PostojeciSertifikatiComponent implements OnInit {

  id: number;
  tip: string;

  constructor(private sertService: ProbaService)
  {
    console.log('constructor');
  }

  ngOnInit() 
  {
    console.log('ngInit')
  }

  prikaziSertifikate()
  {
    this.sertService.getSertifikate().subscribe((data: Sertifikat) => {
      id: data['id'];
      tip: data['tip'];
    })

    console.log(this.tip + 'dsads');
  }

}
