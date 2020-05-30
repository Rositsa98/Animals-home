import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-send-request-dialog',
  templateUrl: './send-request-dialog.component.html',
  styleUrls: ['./send-request-dialog.component.scss']
})
export class SendRequestDialogComponent implements OnInit {
  constructor(private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<SendRequestDialogComponent>) { }

  ngOnInit(): void {
    
  }
}
