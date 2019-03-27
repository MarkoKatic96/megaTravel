import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KreirajSertifikatComponent } from './kreiraj-sertifikat.component';

describe('KreirajSertifikatComponent', () => {
  let component: KreirajSertifikatComponent;
  let fixture: ComponentFixture<KreirajSertifikatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KreirajSertifikatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KreirajSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
