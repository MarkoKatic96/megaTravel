import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Tabela1Component } from './tabela1.component';

describe('Tabela1Component', () => {
  let component: Tabela1Component;
  let fixture: ComponentFixture<Tabela1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Tabela1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Tabela1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
