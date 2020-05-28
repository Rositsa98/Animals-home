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

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path:'loginShelter',
    component:LoginShelterComponent
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
    path:'main',
    component: MainPanelComponent
  },
  {
    path:'registration',
    component: RegistrationComponent
  },
  {
    path:'shelterRegistration',
    component:RegistrationShelterComponent
  },
  {
    path:'chat',
    component:ChatComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
