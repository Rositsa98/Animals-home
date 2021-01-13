import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdViewerComponent } from './ad-viewer.component';

describe('AdViewerComponent', () => {
  let component: AdViewerComponent;
  let fixture: ComponentFixture<AdViewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdViewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
