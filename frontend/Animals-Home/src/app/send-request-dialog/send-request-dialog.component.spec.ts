import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendRequestDialogComponent } from './send-request-dialog.component';

describe('SendRequestDialogComponent', () => {
  let component: SendRequestDialogComponent;
  let fixture: ComponentFixture<SendRequestDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendRequestDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendRequestDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
