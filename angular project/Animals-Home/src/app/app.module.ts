import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ProcessRequestsComponent } from './process-requests/process-requests.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from './services/authentication/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MainPanelComponent } from './main-panel/main-panel.component';
import { RegistrationComponent } from './registration/registration.component';
import { RegistrationService } from './services/registration/registration.service';
import { RegistrationShelterComponent } from './registration-shelter/registration-shelter.component';
import { LoginShelterComponent } from './login-shelter/login-shelter.component';
import { AgmCoreModule } from '@agm/core';
import { RequestService } from './services/request/request.service';
import { DatePipe } from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { SendRequestComponent } from './send-request/send-request.component';
import { ChatComponent } from './chat/chat.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatDialogModule } from '@angular/material/dialog';
import { NotificationsDialogComponent } from './notifications-dialog/notifications-dialog.component';
import { SendRequestDialogComponent } from './send-request-dialog/send-request-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProcessRequestsComponent,
    AdminPanelComponent,
    MainPanelComponent,
    RegistrationComponent,
    RegistrationShelterComponent,
    LoginShelterComponent,
    SendRequestComponent,
    ChatComponent,
    NotificationsDialogComponent,
    SendRequestDialogComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCifSZ4mMeZTYbNXAoIB802ot5UT_9IJSI',
      libraries: ['places']
    }),
    BrowserAnimationsModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgbModule,
    MatDialogModule
  ],
  providers: [AuthenticationService, RegistrationService, RequestService, MatDatepickerModule, DatePipe],
  bootstrap: [AppComponent],
  entryComponents: [NotificationsDialogComponent]
})
export class AppModule { }
