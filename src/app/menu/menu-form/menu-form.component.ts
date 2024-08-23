import { Component } from '@angular/core';
import { MenuService, MenuItem } from '../menu.service';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-menu-form',
  templateUrl: './menu-form.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./menu-form.component.css']
})
export class MenuFormComponent {
  menu: MenuItem = { id: 0, nombre: '', descripcion: '', precio: 0, imagenId: null };
  selectedFile: File | null = null;

  constructor(private menuService: MenuService) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(): void {
    if (this.selectedFile) {
      this.menuService.crearMenu(this.menu, this.selectedFile).subscribe(
        (response) => {
          console.log('Menú creado:', response);
        },
        (error) => {
          console.error('Error al crear el menú:', error);
        }
      );
    }
  }
}
