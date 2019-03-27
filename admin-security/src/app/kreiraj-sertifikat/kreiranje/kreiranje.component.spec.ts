import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjeComponent } from './kreiranje.component';

describe('KreiranjeComponent', () => {
  let component: KreiranjeComponent;
  let fixture: ComponentFixture<KreiranjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KreiranjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KreiranjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
