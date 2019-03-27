import { ListanjeComponent } from './kreiraj-sertifikat/listanje/listanje.component';
import { KreiranjeComponent } from './kreiraj-sertifikat/kreiranje/kreiranje.component';
import { KreirajSertifikatComponent } from './kreiraj-sertifikat/kreiraj-sertifikat.component';
import { KomunikacijaComponent } from './komunikacija/komunikacija.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostojeciSertifikatiComponent } from './postojeci-sertifikati/postojeci-sertifikati.component';
import { PovuciSertifikatComponent } from './povuci-sertifikat/povuci-sertifikat.component';
import { Tabela1Component } from './komunikacija/tabela1/tabela1.component';
import { Tabela2Component } from './komunikacija/tabela2/tabela2.component';

@NgModule({
  declarations: [
    KomunikacijaComponent,
    KreirajSertifikatComponent,
    KreiranjeComponent,
    ListanjeComponent,
    PostojeciSertifikatiComponent,
    PovuciSertifikatComponent,
    AppComponent,
    Tabela1Component,
    Tabela2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
