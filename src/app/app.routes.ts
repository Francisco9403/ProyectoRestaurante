import { Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { authorityRouteAccess } from "./auth/authority-route-access";
import {MenuComponent} from "./menu/menu.component";
import {ProductFormComponent} from "./product-form/product-form.component";

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
  { path: 'menu/new', component: ProductFormComponent },  // Ruta para crear un nuevo producto
  { path: 'menu/edit/:id', component: ProductFormComponent },  // Ruta para editar un producto existente

];
