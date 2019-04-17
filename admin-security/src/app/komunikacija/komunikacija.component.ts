import { PoveziSertifikateService } from './../povezi-sertifikate.service';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-komunikacija',
  templateUrl: './komunikacija.component.html',
  styleUrls: ['./komunikacija.component.css']
})
export class KomunikacijaComponent implements OnInit {

  serijskiBrojS1: number;
  serijskiBrojS2: number;
  uspesno: boolean = false;
  istiSerts: boolean = false;

  constructor(private data: DataService, private povezi: PoveziSertifikateService) { }

  ngOnInit() {
    this.data.currIzaberiSert1.subscribe(res => this.serijskiBrojS1 = res);
    this.data.currIzaberiSert2.subscribe(res => this.serijskiBrojS2 = res);
    console.log(this.serijskiBrojS1, this.serijskiBrojS2);
  }

  poveziSert()
  {
    let id = Math.floor(Math.random() * 100) + 17;
    if(this.serijskiBrojS1 == this.serijskiBrojS1)
    {
      this.istiSerts = true;
      return;
    }
    this.povezi.connectCerts(id, this.serijskiBrojS1, this.serijskiBrojS2).subscribe(res =>
    {
      if(res != null)
      {
        this.uspesno = true;
      }
      console.log(res);
    });
  }

}
