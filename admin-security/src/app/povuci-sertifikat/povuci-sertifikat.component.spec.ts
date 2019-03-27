import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PovuciSertifikatComponent } from './povuci-sertifikat.component';

describe('PovuciSertifikatComponent', () => {
  let component: PovuciSertifikatComponent;
  let fixture: ComponentFixture<PovuciSertifikatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PovuciSertifikatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PovuciSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
