import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/'

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private izaberiNads = new BehaviorSubject<string>('izaberi sertifikat..');
  currentNads = this.izaberiNads.asObservable(); //cuva trenutnu vrednost



  constructor() { }

  changeNads(nads: string)
  {
    this.izaberiNads.next(nads); //kao next state
  }
}
