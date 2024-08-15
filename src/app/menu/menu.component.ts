import {Component, inject, OnInit} from '@angular/core';
import { MenuItem, MenuService, Page } from "./menu.service";
import { FormsModule } from "@angular/forms";
import { AuthService } from "../auth/auth.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  standalone: true,
  imports: [FormsModule, RouterLink],
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: MenuItem[] = [];
  currentPage: number = 0;
  totalPages: number = 0;
  filterName: string = '';
  filterPriceMin: number = 0;
  filterPriceMax: number = 100;
  authService = inject(AuthService);

  constructor(private menuService: MenuService) { }

  ngOnInit(): void {
    this.loadMenus();
  }

  loadMenus(): void {
    this.menuService.getMenuItems(this.currentPage, 3, this.filterName, this.filterPriceMin, this.filterPriceMax).subscribe((data: Page<MenuItem>) => {
      this.menuItems = data.content;
      this.totalPages = data.page.totalPages;
      this.currentPage = data.page.number;
    });
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadMenus();
    }
  }

  prevPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadMenus();
    }
  }

  applyFilter(): void {
    this.currentPage = 0;
    this.loadMenus();
  }

  addMenuItem(): void {
    const newItem: MenuItem = {
      id: 0,  // El backend generará el ID
      nombre: 'Nuevo Producto',
      descripcion: 'Descripción del producto',
      precio: 0,
      imagen: '',  // Ruta o URL de la imagen
      oferta: null
    };
    this.menuService.createMenuItem(newItem).subscribe(() => this.loadMenus());
  }

  editMenuItem(menuItem: MenuItem): void {
    this.menuService.updateMenuItem(menuItem.id, menuItem).subscribe(() => this.loadMenus());
  }

  deleteMenuItem(menuItem: MenuItem): void {
    this.menuService.deleteMenuItem(menuItem.id).subscribe(() => this.loadMenus());
  }
}
