import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DialogContentComponent } from './dialog-content.component';
import { NotificationsDialogComponent } from './notifications-dialog/notifications-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class NotificationsDialogService {

  constructor(private modalService: NgbModal) { }

  public notify(

    dialogSize: 'sm'|'lg' = 'lg'): Promise<boolean> {
    const modalRef = this.modalService.open(NotificationsDialogComponent, { size: dialogSize });
    
    window.location.reload;

    return modalRef.result;
  }
}
