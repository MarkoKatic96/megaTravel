import { ProbaService } from './proba.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'admin-security';
  todaydate;
  componentproperty;
  constructor(private probaservis: ProbaService)
  {

  }

  ngOnInit()
  {
    // this.todaydate = this.probaservis.showTodayDate();
  }
  
}
