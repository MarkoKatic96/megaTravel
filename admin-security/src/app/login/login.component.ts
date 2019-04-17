import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private Auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  loginUser(event) {
    event.preventDefault();
    console.log(event);

    const target = event.target;
    const email = target.querySelector('#email').value;
    const password = target.querySelector('#password').value;
    console.log(email);


    this.Auth.getUserDetails(email, password).subscribe(data =>{
      if(data.success){
        this.router.navigate(['kreirajsert']);
        this.Auth.setLoggedIn(true);
      }
      else{
        window.alert(data.message);
      }
    })

  }
}