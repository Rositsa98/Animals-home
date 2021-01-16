import { not } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import * as signalR from "@aspnet/signalr";
import { AuthenticationService } from '../authentication/authentication.service';
import { RequestService } from '../request/request.service';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  private hubConnection: signalR.HubConnection;
  public notifications: String[] = [];
  public haveLoadedNotifications = false;

  constructor(requestService: RequestService) {
    this.loadNotifications(requestService);
  }

  private loadNotifications(requestService: RequestService) {
    requestService.getNotifications().subscribe(notifications => {
      this.notifications = notifications as String[];
      this.haveLoadedNotifications = true;
    })
  }

  public initializeEventListeners() {
    this.hubConnection.on("NotificationReceived", (notification: String) => {
      this.notifications.push(notification);
    });
  }

  public startConnection = () => {
    this.hubConnection = new signalR.HubConnectionBuilder()
                            .withUrl('http://localhost:5000/live', { 
                              accessTokenFactory: () => localStorage.getItem("token")
                            })
                            .build();
    this.hubConnection
      .start()
      .then(() => console.log('Connection started'))
      .catch(err => console.log('Error while starting connection: ' + err))
  }
}