import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarService } from '../navigation/navbar.service';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent implements OnInit {

  constructor(private navigation: NavbarService, private router: Router) { }

  ngOnInit() {
    this.navigation.hide();
  }

  goHome() {
    this.router.navigate(['/all']);
  }
}
