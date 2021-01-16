import { Component, OnInit, OnChanges, DoCheck } from '@angular/core';
import { AuthenticationService } from '../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { NavbarService } from './navbar.service';
import { MatDialogRef, MatDialog } from '@angular/material';
import { NotificationsDialogService } from '../dialog-content/notifications-dialog.service';
import { NotificationsService } from '../services/notifications/notifications.service';
import { ApiService } from '../services/api.service';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, DoCheck {
  name: string;
  isShelter: boolean;
  isLoggedInUser: boolean = false;

  constructor(private authService: AuthenticationService, 
    public nav: NavbarService,
    private confirmationDialogService: ConfirmationDialogService, 
    private router: Router, 
    private dialog: MatDialog,
    private notificationsDialogService: NotificationsDialogService,
    private notificationsService: NotificationsService,
    private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getCatFact().subscribe(response => {
      document.getElementById("cat-fact-header").innerText = response.text;
    });
  }

  ngDoCheck() {
    if (this.authService.isAuthenticated()) {
      this.name = this.authService.getUsername();
      this.isLoggedInUser = true;
      if (localStorage.getItem("role") === "Shelter") {
        this.isShelter = true;
      } else {
        this.isShelter = false;
      }
    } else {
      this.isLoggedInUser = false;
      this.isShelter = false;
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

  processReq() {
    this.router.navigate(['/process-requests']).then(() => window.location.reload());
  }

  logout() {

    if (localStorage.getItem("token") != null) {
      localStorage.removeItem("token");

      if (localStorage.getItem("username") != null) {
        localStorage.removeItem("username");
      }

      if (localStorage.getItem("role") != null) {
        localStorage.removeItem("role");
      }

      this.router.navigate(['/all']).then(() => window.location.reload());

    }

  }

  openNofitificationsModal() {
    this.notificationsDialogService.notify()
      .then(() => window.location.reload)

  }

  getAdsByType(type: string) {
    this.router.navigate(['/all/type/', type]);
  }
}
