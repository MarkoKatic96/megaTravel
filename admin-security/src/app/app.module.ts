import { UserService } from './user.service';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth.guard';
import { ProbaService } from './proba.service';
import { KreiranjeComponent } from './kreiraj-sertifikat/kreiranje/kreiranje.component';
import { KreirajSertifikatComponent } from './kreiraj-sertifikat/kreiraj-sertifikat.component';
import { KomunikacijaComponent } from './komunikacija/komunikacija.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from  '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostojeciSertifikatiComponent } from './postojeci-sertifikati/postojeci-sertifikati.component';
import { PovuciSertifikatComponent } from './povuci-sertifikat/povuci-sertifikat.component';
import { Tabela1Component } from './komunikacija/tabela1/tabela1.component';
import { Tabela2Component } from './komunikacija/tabela2/tabela2.component';
import { ListanjeComponent } from './kreiraj-sertifikat/listanje/listanje.component';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './navigation/navigation.component';


@NgModule({
  declarations: [
    LoginComponent,
    KomunikacijaComponent,
    KreirajSertifikatComponent,
    KreiranjeComponent,
    PostojeciSertifikatiComponent,
    PovuciSertifikatComponent,
    AppComponent,
    Tabela1Component,
    Tabela2Component,
    ListanjeComponent,
    NavigationComponent
  ],
  imports: [
    HttpClientModule, 
    BrowserModule,
    AppRoutingModule
  ],
  providers: [ProbaService, AuthService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
