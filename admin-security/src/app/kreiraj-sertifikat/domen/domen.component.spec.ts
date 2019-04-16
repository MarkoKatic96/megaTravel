import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DomenComponent } from './domen.component';

describe('DomenComponent', () => {
  let component: DomenComponent;
  let fixture: ComponentFixture<DomenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DomenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DomenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
