import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { MenuItem, MenuService, Oferta, Page } from "../../menu/menu.service";

@Component({
  selector: 'app-oferta-form',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './oferta-form.component.html',
  styleUrl: './oferta-form.component.css'
})
export class OfertaFormComponent {
  menus: MenuItem[] = [];
  selectedMenus: MenuItem[] = [];
  nombre: string = '';
  descripcion: string = '';
  porcentajeDescuento: number | null = null;
  imagenFile: File | null = null;
  currentPage: number = 0;
  pageSize: number = 3;
  totalPages: number = 0;

  constructor(private menuService: MenuService, private router: Router) {}

  ngOnInit(): void {
    this.getMenus();
  }

  getMenus(): void {
    this.menuService.getMenuItems(this.currentPage, this.pageSize)
      .subscribe((data: Page<MenuItem>) => {
        this.menus = data.content;
        this.totalPages = data.page.totalPages;
      });
  }

  onPageChange(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.getMenus();
    }
  }

  isSelected(menu: MenuItem): boolean {
    return this.selectedMenus.includes(menu);
  }

  toggleMenuSelection(menu: MenuItem): void {
    if (this.isSelected(menu)) {
      this.removeMenuFromOffer(menu);
    } else {
      this.addMenuToOffer(menu);
    }
  }

  addMenuToOffer(menu: MenuItem): void {
    if (!this.selectedMenus.includes(menu)) {
      this.selectedMenus.push(menu);
    }
  }

  removeMenuFromOffer(menu: MenuItem): void {
    this.selectedMenus = this.selectedMenus.filter(m => m !== menu);
  }

  onFileChange(event: any): void {
    this.imagenFile = event.target.files[0] || null;
  }

  createOffer(): void {
    if (this.selectedMenus.length > 0 && this.porcentajeDescuento !== null) {
      const oferta = {
        nombre: this.nombre,
        descripcion: this.descripcion,
        porcentajeDescuento: this.porcentajeDescuento
      };

      const formData = new FormData();
      formData.append('ofertaJson', new Blob([JSON.stringify(oferta)], { type: 'application/json' }));

      if (this.imagenFile) {
        formData.append('imagen', this.imagenFile);
      }

      // Enviar los IDs de los menús como un campo separado
      formData.append('menuIds', JSON.stringify(this.selectedMenus.map(menu => menu.id)));

      this.menuService.createOffer(formData).subscribe(() => {
        alert('Oferta creada exitosamente');
        this.clearForm();
        this.router.navigate(['/menu']);  // Redirige a la lista de menús
      });
    }
  }

  clearForm(): void {
    this.nombre = '';
    this.descripcion = '';
    this.porcentajeDescuento = null;
    this.selectedMenus = [];
    this.imagenFile = null;
  }

  trackByMenuId(index: number, menu: MenuItem): string {
    return `${index}-${menu.id}`;
  }
}
