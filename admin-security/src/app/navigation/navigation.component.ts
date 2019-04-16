import { AuthService } from './../auth.service';
import { LoginComponent } from './../login/login.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {


  constructor(private logged: AuthService, private router: Router)
  {

  }

  ngOnInit() {
    
  }

  ulogovan(): boolean
  {
    return this.logged.isLoggedIn;
  }

  logout()
  {
    localStorage.removeItem('token');
    this.logged.setLoggedIn(false);
    this.router.navigate['login'];

  }

}
