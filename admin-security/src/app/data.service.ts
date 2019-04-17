import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/'

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private izaberiNads = new BehaviorSubject<string>('izaberi sertifikat..');
  currentNads = this.izaberiNads.asObservable(); //cuva trenutnu vrednost

  private izaberiSert1 = new BehaviorSubject<number>(999);
  currIzaberiSert1 = this.izaberiSert1.asObservable();

  private izaberiSert2 = new BehaviorSubject<number>(999);
  currIzaberiSert2 = this.izaberiSert2.asObservable();

  private povezaniSert = new BehaviorSubject<number>(999);
  currPovezaniSert = this.povezaniSert.asObservable();

  constructor() { }

  changeNads(nads: string)
  {
    this.izaberiNads.next(nads); //kao next state
  }

  changeSert1(sn: number)
  {
    this.izaberiSert1.next(sn);
  }

  changeSert2(sn: number)
  {
    this.izaberiSert2.next(sn);
  }

  changePovezaniSert(id: number)
  {
    this.povezaniSert.next(id);
  }

}
