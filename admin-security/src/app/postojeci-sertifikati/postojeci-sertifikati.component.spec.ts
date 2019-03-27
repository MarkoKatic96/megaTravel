import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostojeciSertifikatiComponent } from './postojeci-sertifikati.component';

describe('PostojeciSertifikatiComponent', () => {
  let component: PostojeciSertifikatiComponent;
  let fixture: ComponentFixture<PostojeciSertifikatiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostojeciSertifikatiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostojeciSertifikatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
