import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessRequestsComponent } from './process-requests.component';

describe('ProcessRequestsComponent', () => {
  let component: ProcessRequestsComponent;
  let fixture: ComponentFixture<ProcessRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
