import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarService } from '../navigation/navbar.service';

@Component({
  selector: 'app-internal-server-error',
  templateUrl: './internal-server-error.component.html',
  styleUrls: ['./internal-server-error.component.scss']
})
export class InternalServerErrorComponent implements OnInit {

  constructor(private navigation: NavbarService, private router: Router) { }

  ngOnInit() {
      this.navigation.hide();
  }

  goHome() {
    this.router.navigate(['/all']);
  }
}
