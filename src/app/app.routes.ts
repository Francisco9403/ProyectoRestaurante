import { Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { authorityRouteAccess } from "./auth/authority-route-access";

// Definición de las rutas de la aplicación
export const routes: Routes = [
  {
    path: '', // Ruta raíz de la aplicación
    component: HomeComponent // Componente que se mostrará en la ruta raíz
  }
];
