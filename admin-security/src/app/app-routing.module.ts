import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';
import { KomunikacijaComponent } from './komunikacija/komunikacija.component';
import { KreirajSertifikatComponent } from './kreiraj-sertifikat/kreiraj-sertifikat.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PovuciSertifikatComponent } from './povuci-sertifikat/povuci-sertifikat.component';
import { PostojeciSertifikatiComponent } from './postojeci-sertifikati/postojeci-sertifikati.component';
import { RegisterComponent } from './register/register.component';
import { PovezaniSertifikatiComponent } from './komunikacija/povezani-sertifikati/povezani-sertifikati.component';

const routes: Routes = [
  {path: '', redirectTo: 'kreirajsert', pathMatch: 'full'},
  {path: 'login', component: LoginComponent}, //vrati na login component
  {path: 'register', component: RegisterComponent},
  {path: 'kreirajsert', component: KreirajSertifikatComponent, canActivate: [AuthGuard]}, //canActivate: [AuthGuard] -> ovo ubaci na kraju
  {path: 'komunikacija', component: KomunikacijaComponent, canActivate: [AuthGuard]},
  {path: 'povucisert', component: PovuciSertifikatComponent, canActivate: [AuthGuard]},
  {path: 'postojecisert', component: PostojeciSertifikatiComponent, canActivate: [AuthGuard]},
  {path: 'odjava', redirectTo: 'login', canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
