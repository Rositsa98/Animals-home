import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ProcessRequestsComponent } from './process-requests/process-requests.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from './services/authentication/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { MatCardModule } from '@angular/material/card';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatButtonModule, MatIconModule } from '@angular/material/'
import { MatGridListModule } from '@angular/material/grid-list';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MainPanelComponent } from './main-panel/main-panel.component';
import { RegistrationComponent } from './registration/registration.component';
import { RegistrationService } from './services/registration/registration.service';
import { RegistrationShelterComponent } from './registration-shelter/registration-shelter.component';
import { LoginShelterComponent } from './login-shelter/login-shelter.component';
import { AgmCoreModule } from '@agm/core';
import { RequestService } from './services/request/request.service';
import { PetCatalogComponent } from './pet-catalog/pet-catalog.component';
import { CreatePetComponent } from './pet-catalog/create-pet/create-pet.component';
import { EditPetComponent } from './pet-catalog/edit-pet/edit-pet.component';
import { PetListComponent } from './pet-catalog/pet-list/pet-list.component';
import { PetItemComponent } from './pet-catalog/pet-list/pet-item/pet-item.component';
import { UserAdsComponent } from './user-ads/user-ads.component';
import { UserFavoriteAdsComponent } from './user-favorite-ads/user-favorite-ads.component';
import { PetFormComponent } from './pet-catalog/pet-form/pet-form.component';
import { NavigationComponent } from './navigation/navigation.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { InternalServerErrorComponent } from './internal-server-error/internal-server-error.component';
import { AlertModule } from './alert/';
import { DialogContentComponent } from './dialog-content/dialog-content.component';
import { ConfirmationDialogService } from './dialog-content/confirmation-dialog.service';
import { AdViewerComponent } from './ad-viewer/ad-viewer.component';
import { FinderComponent } from './finder/finder.component';
import { DatePipe } from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { SendRequestComponent } from './send-request/send-request.component';
import { ChatComponent } from './chat/chat.component';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { SendRequestDialogComponent } from './send-request-dialog/send-request-dialog.component';
import {GoogleChartsModule} from 'angular-google-charts';
import {NotificationsDialogService} from "./dialog-content/notifications-dialog.service";
import { NotificationsDialogComponent } from './dialog-content/notifications-dialog/notifications-dialog.component';

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
    PetCatalogComponent,
    CreatePetComponent,
    EditPetComponent,
    PetListComponent,
    PetItemComponent,
    UserAdsComponent,
    UserFavoriteAdsComponent,
    PetFormComponent,
    NavigationComponent,
    NotFoundComponent,
    InternalServerErrorComponent,
    DialogContentComponent,
    AdViewerComponent,
    FinderComponent,
    AdViewerComponent,
    SendRequestComponent,
    ChatComponent,
    NotificationsDialogComponent,
    SendRequestDialogComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    ButtonsModule,
    MatButtonModule,
    AlertModule,
    MatIconModule,
    MatGridListModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCifSZ4mMeZTYbNXAoIB802ot5UT_9IJSI',
      libraries: ['places']
    }),
    BrowserAnimationsModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgbModule,
    MatDialogModule,
    NgbModule,
    GoogleChartsModule
  ],
  providers: [AuthenticationService, RegistrationService, RequestService, ConfirmationDialogService,
    SendRequestDialogComponent, MatDatepickerModule, DatePipe, NotificationsDialogComponent,
     NotificationsDialogService],
  bootstrap: [AppComponent],
  entryComponents:[DialogContentComponent, NotificationsDialogComponent]
})
export class AppModule { }
