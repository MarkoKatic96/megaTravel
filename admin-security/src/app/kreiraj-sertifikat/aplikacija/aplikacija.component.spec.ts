import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AplikacijaComponent } from './aplikacija.component';

describe('AplikacijaComponent', () => {
  let component: AplikacijaComponent;
  let fixture: ComponentFixture<AplikacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AplikacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AplikacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
