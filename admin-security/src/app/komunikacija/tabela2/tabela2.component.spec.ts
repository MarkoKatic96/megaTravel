import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Tabela2Component } from './tabela2.component';

describe('Tabela2Component', () => {
  let component: Tabela2Component;
  let fixture: ComponentFixture<Tabela2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Tabela2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Tabela2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
