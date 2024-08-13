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
  filterPriceMin: number = 0;
  filterPriceMax: number = 100000;

  constructor(private menuService: MenuService) { }

  ngOnInit(): void {
    this.loadMenus();
  }

  loadMenus(): void {
    this.menuService.getMenuItems(this.currentPage, 3, this.filterName, this.filterPriceMin, this.filterPriceMax).subscribe((data: Page<MenuItem>) => {
      console.log("Data received:", data);

      // Accede a las propiedades anidadas en page
      this.menuItems = data.content;
      this.totalPages = data.page.totalPages;
      this.currentPage = data.page.number;
      // Puedes ajustar size y totalElements si los necesitas
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
    this.loadMenus();
  }
}
