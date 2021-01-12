import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFavoriteAdsComponent } from './user-favorite-ads.component';

describe('UserFavoriteAdsComponent', () => {
  let component: UserFavoriteAdsComponent;
  let fixture: ComponentFixture<UserFavoriteAdsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserFavoriteAdsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserFavoriteAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
