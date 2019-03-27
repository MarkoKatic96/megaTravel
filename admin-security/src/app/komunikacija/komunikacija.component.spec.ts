import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KomunikacijaComponent } from './komunikacija.component';

describe('KomunikacijaComponent', () => {
  let component: KomunikacijaComponent;
  let fixture: ComponentFixture<KomunikacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KomunikacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KomunikacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
