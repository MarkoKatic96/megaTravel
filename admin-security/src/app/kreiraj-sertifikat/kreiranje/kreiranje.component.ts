import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-kreiranje',
  templateUrl: './kreiranje.component.html',
  styleUrls: ['./kreiranje.component.css']
})
export class KreiranjeComponent implements OnInit {

  @Input() krSertVrste: String[]; //prosledjeno od parent-a

  selected: String = 'APLIKACIJA';
 

  constructor()
  {
    
  }

  ngOnInit()
  {

  }

  selectChangeHandler(event: any)
  {
    this.selected = event.target.value;
  }

}
