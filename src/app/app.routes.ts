import { Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { authorityRouteAccess } from "./auth/authority-route-access";
import {MenuComponent} from "./menu/menu.component";
import {MenuFormComponent} from "./menu/menu-form/menu-form.component";
import {OfertaFormComponent} from "./oferta/oferta-form/oferta-form.component";
import {OfertaComponent} from "./oferta/oferta.component";

// Definición de las rutas de la aplicación
export const routes: Routes = [
  {
    path: '', // Ruta raíz de la aplicación
    component: HomeComponent // Componente que se mostrará en la ruta raíz
  },
  {
    path: "menu",
    component: MenuComponent
  },
  {
    path: 'menu/new',
    component: MenuFormComponent
  },
  {
    path: 'menu/edit/:id',
    component: MenuFormComponent
  },
  { path: 'oferta/new',
    component: OfertaFormComponent
  }
  ,  {
    path: 'ofertas', // Nueva ruta para ver la lista de ofertas
    component: OfertaComponent
  }

];
