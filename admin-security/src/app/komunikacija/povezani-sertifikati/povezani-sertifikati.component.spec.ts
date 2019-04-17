import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PovezaniSertifikatiComponent } from './povezani-sertifikati.component';

describe('PovezaniSertifikatiComponent', () => {
  let component: PovezaniSertifikatiComponent;
  let fixture: ComponentFixture<PovezaniSertifikatiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PovezaniSertifikatiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PovezaniSertifikatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
