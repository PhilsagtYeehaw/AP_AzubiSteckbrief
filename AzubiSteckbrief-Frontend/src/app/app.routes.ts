import { Routes } from '@angular/router';
import {AzubiListComponent} from './components/azubi-list/azubi-list.component';
import {AzubiDetailComponent} from './components/azubi-detail/azubi-detail.component';
import {BewertungFormComponent} from './components/bewertung-form/bewertung-form.component';

export const routes: Routes = [

  { path: '', component: AzubiListComponent },
  { path: 'azubi/:id', component: AzubiDetailComponent },
  { path: 'bewertung/neu', component: BewertungFormComponent },


];


