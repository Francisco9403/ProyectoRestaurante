import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from "../environments/environment";
import { map, Observable } from "rxjs";
import { Injectable } from "@angular/core";

export interface Imagen {
  id: number;
  file: string; // Assuming base64 encoded string for the image
  fileContentType: string;
}

export interface Page<T> {
  content: T[];
  page: {
    number: number;
    size: number;
    totalElements: number;
    totalPages: number;
  };
}

export interface MenuItem {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagen: Imagen | null;
  oferta?: Oferta | null;
}

export interface Oferta {
  id: number;
  nombre: string;
  descripcion: string;
  porcentajeDescuento: number;
  menu: MenuItem[]  | null;
  imagenId: Imagen | null;
}

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private apiUrl = `${environment.API_URL}/menus`;

  constructor(private http: HttpClient) { }

  getMenuItems(page: number = 0, size: number = 1, nombre: string = '', precioMin: number | null = null, precioMax: number | null = null): Observable<Page<MenuItem>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('nombre', nombre);

    if (precioMin !== null) {
      params = params.set('precioMin', precioMin.toString());
    }
    if (precioMax !== null) {
      params = params.set('precioMax', precioMax.toString());
    }

    return this.http.get<Page<MenuItem>>(this.apiUrl, { params });
  }

  getMenuItemById(id: number): Observable<MenuItem> {
    return this.http.get<MenuItem>(`${this.apiUrl}/${id}`);
  }

  updateMenu(menu: MenuItem, imagen: File | null): Observable<MenuItem> {
    const formData: FormData = new FormData();
    formData.append('menu', new Blob([JSON.stringify(menu)], { type: 'application/json' }));

    if (imagen) {
      formData.append('imagen', imagen);
    }

    return this.http.put<MenuItem>(`${this.apiUrl}/${menu.id}`, formData);
  }

  // Crear un nuevo menú
  crearMenu(menu: MenuItem, imagen: File): Observable<MenuItem> {
    const formData: FormData = new FormData();
    formData.append('menu', new Blob([JSON.stringify(menu)], { type: 'application/json' }));
    formData.append('imagen', imagen);

    return this.http.post<MenuItem>(this.apiUrl, formData);
  }

  // Eliminar un menú por su ID
  deleteMenuItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }




  createOffer(formData: FormData): Observable<Oferta> {
    return this.http.post<Oferta>(`${this.apiUrl}/nuevaOferta`, formData);
  }

  // Método para calcular el precio con descuento de un menú
  calcularPrecioConDescuento(menu: MenuItem): number {
    if (menu.oferta && menu.oferta.porcentajeDescuento > 0) {
      const descuento = menu.precio * (menu.oferta.porcentajeDescuento / 100);
      return menu.precio - descuento;
    }
    return menu.precio;
  }
}
