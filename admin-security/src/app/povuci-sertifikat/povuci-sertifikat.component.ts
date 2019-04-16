import { IzlistajSertifikateService } from 'src/app/izlistaj-sertifikate.service';
import { Component, OnInit } from '@angular/core';
import { IzbrisiSertifikatService } from '../izbrisi-sertifikat.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-povuci-sertifikat',
  templateUrl: './povuci-sertifikat.component.html',
  styleUrls: ['./povuci-sertifikat.component.css']
})
export class PovuciSertifikatComponent implements OnInit {

  sertifikati: any = [];

  constructor(private izlistaj: IzlistajSertifikateService, private povuci: IzbrisiSertifikatService, private router: Router) { }

  ngOnInit() {
    this.certList();
  }

  certList()
  {
    this.sertifikati = [];
    this.izlistaj.allCerts().subscribe((res: {}) =>
    {
      this.sertifikati = res;
    })

  }

  // public selectCert(event: any, cert: any)
  // {
  //   cert.flag = !cert.flag;
  //   this.sertifikati.forEach(element => {
  //     if(cert.id == element.id)
  //     {
  //       this.povuciSert(element.id)
  //     }
  //   });
    
    
  // }

  povuciSert(id)
  {
    this.povuci.izbrisi(id).subscribe((res : {}) =>
        {
          this.router.navigateByUrl('/postojecisert', {skipLocationChange: true}).then(()=>
          this.router.navigate(["/povucisert"])); 
        })
  }


}
