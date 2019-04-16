import { httpInterceptorProviders } from './http-interceptors/index';
import { AuthInterceptor } from './http-interceptors/AuthInterceptor';
import { AuthService } from './auth.service';
import { AuthGuard } from './auth.guard';
import { KreirajSertifikatComponent } from './kreiraj-sertifikat/kreiraj-sertifikat.component';
import { KomunikacijaComponent } from './komunikacija/komunikacija.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from  '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostojeciSertifikatiComponent } from './postojeci-sertifikati/postojeci-sertifikati.component';
import { PovuciSertifikatComponent } from './povuci-sertifikat/povuci-sertifikat.component';
import { Tabela1Component } from './komunikacija/tabela1/tabela1.component';
import { Tabela2Component } from './komunikacija/tabela2/tabela2.component';
import { ListanjeComponent } from './kreiraj-sertifikat/listanje/listanje.component';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegisterComponent } from './register/register.component';

import { JwtHelperService } from '@auth0/angular-jwt'
import { StorageServiceModule } from 'ngx-webstorage-service';
import { CookieService } from 'ngx-cookie-service';
import { AplikacijaComponent } from './kreiraj-sertifikat/aplikacija/aplikacija.component';
import { DomenComponent } from './kreiraj-sertifikat/domen/domen.component';
import { OpremaComponent } from './kreiraj-sertifikat/oprema/oprema.component';
import { OrganizacijaComponent } from './kreiraj-sertifikat/organizacija/organizacija.component';
import { OsobaComponent } from './kreiraj-sertifikat/osoba/osoba.component';
import { RootcertComponent } from './kreiraj-sertifikat/rootcert/rootcert.component';
import { CaComponent } from './kreiraj-sertifikat/ca/ca.component';

@NgModule({
  declarations: [
    LoginComponent,
    KomunikacijaComponent,
    KreirajSertifikatComponent,
    PostojeciSertifikatiComponent,
    PovuciSertifikatComponent,
    AppComponent,
    Tabela1Component,
    Tabela2Component,
    ListanjeComponent,
    NavigationComponent,
    RegisterComponent,
    AplikacijaComponent,
    DomenComponent,
    OpremaComponent,
    OrganizacijaComponent,
    OsobaComponent,
    RootcertComponent,
    CaComponent
  ],
  imports: [
    HttpClientModule, 
    BrowserModule,
    AppRoutingModule,
    StorageServiceModule 
  ],
  providers: [
    httpInterceptorProviders,
    JwtHelperService,
    AuthService,
    CookieService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
