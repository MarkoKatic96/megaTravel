import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpremaComponent } from './oprema.component';

describe('OpremaComponent', () => {
  let component: OpremaComponent;
  let fixture: ComponentFixture<OpremaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpremaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpremaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
