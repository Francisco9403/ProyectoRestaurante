import { Component, OnInit } from '@angular/core';
import { MenuItem, MenuService, Page } from "./menu.service";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: MenuItem[] = [];
  currentPage: number = 0;
  totalPages: number = 0;
  filterName: string = '';
  filterPriceMin: number | null = null;
  filterPriceMax: number | null = null;

  constructor(private menuService: MenuService) { }

  ngOnInit(): void {
    this.loadMenus();
  }

  loadMenus(): void {
    // Validación para manejar precios vacíos o nulos
    const priceMin = this.filterPriceMin !== null ? this.filterPriceMin : undefined;
    const priceMax = this.filterPriceMax !== null ? this.filterPriceMax : undefined;

    this.menuService.getMenuItems(this.currentPage, 3, this.filterName, priceMin, priceMax)
      .subscribe((data: Page<MenuItem>) => {
        console.log("Data received:", data);
        this.menuItems = data.content;
        this.totalPages = data.page.totalPages;
        this.currentPage = data.page.number;
        console.log("number: " + data.page.number);
        console.log("totalPages: " + data.page.totalPages);
        console.log("size: " + data.page.size);
        console.log("totalElements: " + data.page.totalElements);
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
    this.currentPage = 0;  // Reset to first page

    // Validación para manejar filtros vacíos
    if (this.filterPriceMin !== null && this.filterPriceMin < 0) this.filterPriceMin = 0;
    if (this.filterPriceMax !== null && this.filterPriceMax < this.filterPriceMin!) this.filterPriceMax = this.filterPriceMin;

    this.loadMenus();
  }
}
