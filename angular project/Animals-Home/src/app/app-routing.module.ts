import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ProcessRequestsComponent } from './process-requests/process-requests.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MainPanelComponent } from './main-panel/main-panel.component';


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
    path: 'process-requests',
    component: ProcessRequestsComponent
  },
  {
    path:'admin',
    component: AdminPanelComponent
  },
  {
    path:'main',
    component: MainPanelComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
