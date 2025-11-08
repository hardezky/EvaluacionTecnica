import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { authGuard } from './guards/auth-guard';
import { SolicitudForm } from './pages/solicitudes/solicitud-form/solicitud-form';
import { SolicitudesList } from './pages/solicitudes/solicitudes-list/solicitudes-list';
import { Dashboard } from './pages/dashboard/dashboard';
import { SolicitudResultado } from './pages/solicitudes/solicitud-resultado/solicitud-resultado';
import { SolicitudesLote } from './pages/solicitudes/solicitudes-lote/solicitudes-lote';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  {
    path: 'solicitudes',
    canActivate: [authGuard],
    children: [
      { path: 'nueva', component: SolicitudForm },
      { path: 'lista', component: SolicitudesList },
      { path: 'resultado', component: SolicitudResultado },
      { path: 'lote', component: SolicitudesLote }
    ]
  },
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' }
];
