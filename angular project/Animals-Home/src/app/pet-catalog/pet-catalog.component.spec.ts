import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetCatalogComponent } from './pet-catalog.component';

describe('PetCatalogComponent', () => {
  let component: PetCatalogComponent;
  let fixture: ComponentFixture<PetCatalogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetCatalogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
