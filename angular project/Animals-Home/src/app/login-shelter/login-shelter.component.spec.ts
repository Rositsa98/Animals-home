import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginShelterComponent } from './login-shelter.component';

describe('LoginShelterComponent', () => {
  let component: LoginShelterComponent;
  let fixture: ComponentFixture<LoginShelterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginShelterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginShelterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
