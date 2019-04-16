import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RootcertComponent } from './rootcert.component';

describe('RootcertComponent', () => {
  let component: RootcertComponent;
  let fixture: ComponentFixture<RootcertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RootcertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RootcertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
