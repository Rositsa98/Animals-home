import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ProcessRequestsComponent } from './process-requests/process-requests.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MainPanelComponent } from './main-panel/main-panel.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginShelterComponent } from './login-shelter/login-shelter.component';
import { RegistrationShelterComponent } from './registration-shelter/registration-shelter.component';

import { SendRequestComponent } from './send-request/send-request.component';
import { ChatComponent } from './chat/chat.component';

import { PetCatalogComponent } from "./pet-catalog/pet-catalog.component";
import { CreatePetComponent } from "./pet-catalog/create-pet/create-pet.component";
import { UserAdsComponent } from './user-ads/user-ads.component';
import { EditPetComponent } from './pet-catalog/edit-pet/edit-pet.component';
import { UserFavoriteAdsComponent } from './user-favorite-ads/user-favorite-ads.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { InternalServerErrorComponent } from './internal-server-error/internal-server-error.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AdViewerComponent } from './ad-viewer/ad-viewer.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'all',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'loginShelter',
    component: LoginShelterComponent
  },
  {
    path: 'process-requests',
    component: ProcessRequestsComponent
  },
  {
    path: 'send-request',
    component: SendRequestComponent
  },
  {
    path:'admin',

    component: AdminPanelComponent
  },
  {
    path: 'main',
    component: MainPanelComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {

    path:'shelterRegistration',
    component:RegistrationShelterComponent
  },
  {
    path:'chat',
    component:ChatComponent
    
  },
  {
    path: 'all',
    component: PetCatalogComponent,
  },
  {
    path: 'create-ad',
    component: CreatePetComponent
  },
  {
    path: 'edit/:id',
    component: EditPetComponent
  },
  {
    path: 'my-ads',
    component: UserAdsComponent
  },
  {
    path: 'ads/favorites',
    component: UserFavoriteAdsComponent
  },
  {
    path: 'view-ad/:id',
    component: AdViewerComponent
  },
  {
    path: 'error/internal-server-error',
    component: InternalServerErrorComponent
  },
  {
    path: '**',
    component: NotFoundComponent

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabled',
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
