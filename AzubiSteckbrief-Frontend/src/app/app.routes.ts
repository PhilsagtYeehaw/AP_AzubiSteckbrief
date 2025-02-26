import { Routes } from '@angular/router';
import {AzubiListComponent} from './components/azubi-list/azubi-list.component';
import {AzubiDetailComponent} from './components/azubi-detail/azubi-detail.component';

export const routes: Routes = [

  {path: '', component: AzubiListComponent},
  {path: 'azubi/:id', component: AzubiDetailComponent}


];


