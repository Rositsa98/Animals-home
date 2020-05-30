import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { MatDialogRef, MatDialog } from '@angular/material';
import { NotificationsDialogService } from '../dialog-content/notifications-dialog.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  public name: string;
  public isLoggedInUser: boolean = false;
  public isShelter:boolean = localStorage.getItem("shelterName")!=null ? true: false;

  constructor(private authService: AuthenticationService, 
    private confirmationDialogService: ConfirmationDialogService, private router: Router, private dialog:MatDialog,
    private notificationsDialogService:NotificationsDialogService) { }


  ngOnInit() {
    if (this.authService.isAuthenticated()) {

      if(localStorage.getItem("username")!=null){
        this.name = localStorage.getItem("username");
      } else if(localStorage.getItem("shelterName")!=null){
        this.name = localStorage.getItem("shelterName");
      }

      if(this.name!=null && this.name!=""){
        this.isLoggedInUser = true;
      }
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

  processReq(){
    this.router.navigate(['/process-requests']).then(() => window.location.reload());
  }

  logout() {

    if (localStorage.getItem("token") != null) {
      localStorage.removeItem("token");

      if (localStorage.getItem("shelterName") != null) {
        localStorage.removeItem("shelterName");
      } else if (localStorage.getItem("username") != null) {
        localStorage.removeItem("username");
      }

      this.router.navigate(['/login']).then(() => window.location.reload());

    } 

  }

  openNofitificationsModal(){
    this.notificationsDialogService.notify()
    .then(()=> window.location.reload)
  
  }


}
