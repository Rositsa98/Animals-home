import { TestBed } from '@angular/core/testing';

import { NotificationsDialogServiceService } from './notifications-dialog-service.service';

describe('NotificationsDialogServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NotificationsDialogServiceService = TestBed.get(NotificationsDialogServiceService);
    expect(service).toBeTruthy();
  });
});
