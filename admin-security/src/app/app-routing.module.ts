import { KomunikacijaComponent } from './komunikacija/komunikacija.component';
import { KreirajSertifikatComponent } from './kreiraj-sertifikat/kreiraj-sertifikat.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PovuciSertifikatComponent } from './povuci-sertifikat/povuci-sertifikat.component';
import { PostojeciSertifikatiComponent } from './postojeci-sertifikati/postojeci-sertifikati.component';

const routes: Routes = [
  {path: '', redirectTo: 'kreirajsert', pathMatch: 'full'},
  {path: 'kreirajsert', component: KreirajSertifikatComponent},
  {path: 'komunikacija', component: KomunikacijaComponent},
  {path: 'povucisert', component: PovuciSertifikatComponent},
  {path: 'postojecisert', component: PostojeciSertifikatiComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
