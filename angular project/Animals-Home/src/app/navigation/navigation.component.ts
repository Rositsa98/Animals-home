import { Component, OnInit, OnChanges, DoCheck } from '@angular/core';
import { AuthenticationService } from '../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { NavbarService } from './navbar.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, DoCheck {
  name: string;
  isLoggedInUser: boolean = false;

  constructor(private authService: AuthenticationService, private confirmationDialogService: ConfirmationDialogService, public nav: NavbarService, private router: Router) { }

  ngOnInit() {
  }

  ngDoCheck() {
    if (this.authService.isAuthenticated()) {
      this.name = this.authService.getUsername();
      this.isLoggedInUser = true;
    } else {
      this.isLoggedInUser = false;
    }
  }

  createAd() {
    if (this.isLoggedInUser) {
      this.router.navigate(['/create-ad']);
    } else {
      this.showLoggedInMessage('You must sign in to add new pet.');
    }
  }

  showFavoritePets() {
    if (this.isLoggedInUser) {
      this.router.navigate(['/ads/favorites']);
    } else {
      this.showLoggedInMessage('You must sign in to see your list with favorite pets.');
    }
  }

  showLoggedInMessage(message: string) {
    this.confirmationDialogService.confirm('Warrning!', message, 'Log in', 'Sign up')
      .then((isConfirmed) => {
        if (isConfirmed) {
          this.router.navigate(['/login'])
        } else {
          this.router.navigate(['/registration'])
        }
      });
  }

  getAdsByType(type: string) {
    this.router.navigate(['/all/type/', type]);
  }

}
