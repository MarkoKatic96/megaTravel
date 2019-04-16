import { Component, OnInit } from '@angular/core';
import { HttpClient } from  "@angular/common/http";
import { Observable } from 'rxjs';

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


  constructor()
  {
  }

  ngOnInit() 
  {
  }

}
