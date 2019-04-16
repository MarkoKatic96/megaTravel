import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListanjeComponent } from './listanje.component';

describe('ListanjeComponent', () => {
  let component: ListanjeComponent;
  let fixture: ComponentFixture<ListanjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListanjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
