import { PoveziSertifikateService } from './../povezi-sertifikate.service';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-komunikacija',
  templateUrl: './komunikacija.component.html',
  styleUrls: ['./komunikacija.component.css']
})
export class KomunikacijaComponent implements OnInit {

  serijskiBrojS1: number;
  serijskiBrojS2: number;
  povezaniId: number;
  uspesno: boolean = false;
  istiSerts: boolean = false;
  razvezani: boolean = false;

  spisakKomunikacija: any = [];

  constructor(private data: DataService, private povezi: PoveziSertifikateService, private router: Router) { }

  ngOnInit() {
    this.data.currIzaberiSert1.subscribe(res => this.serijskiBrojS1 = res);
    this.data.currIzaberiSert2.subscribe(res => this.serijskiBrojS2 = res);
    this.data.currPovezaniSert.subscribe(res => this.povezaniId = res);
    console.log(this.serijskiBrojS1, this.serijskiBrojS2);
  }

  poveziSert()
  {
    let id = Math.floor(Math.random() * 100) + 17;
    if(this.serijskiBrojS1 == this.serijskiBrojS2)
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

      this.router.navigateByUrl('/postojecisert', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/komunikacija"]));
      console.log(res);
    });
  }

  razveziSert()
  {
    this.povezi.divorceCerts(this.povezaniId).subscribe(res =>
    {
      if(res != null)
      {
        this.razvezani = true;
      }

      this.router.navigateByUrl('/postojecisert', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/komunikacija"]));
    })
  }

}
