import { Routes } from '@angular/router';
import { TraineeForm } from './trainee-form/trainee-form';

export const routes: Routes = [
      {
    path: '',
    redirectTo: 'trainee-form',
    pathMatch: 'full'
  },
  {
    path: 'trainee-form',
    component: TraineeForm
  },
];
