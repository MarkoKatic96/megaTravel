import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizacijaComponent } from './organizacija.component';

describe('OrganizacijaComponent', () => {
  let component: OrganizacijaComponent;
  let fixture: ComponentFixture<OrganizacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
