import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from "../environments/environment";
import { map, Observable } from "rxjs";
import { Injectable } from "@angular/core";

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
  imagen: string;
  oferta: Oferta | null;
}

export interface Oferta {
  id: number;
  nombre: string;
  descripcion: string;
  porcentajeDescuento: number;
  menus: MenuItem[];
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

  createMenuItem(menuItem: MenuItem): Observable<MenuItem> {
    return this.http.post<MenuItem>(this.apiUrl, menuItem);
  }

  updateMenuItem(id: number, menuItem: MenuItem): Observable<MenuItem> {
    return this.http.put<MenuItem>(`${this.apiUrl}/${id}`, menuItem);
  }

  deleteMenuItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getMenuItemById(id: number): Observable<MenuItem> {
    return this.http.get<MenuItem>(`${this.apiUrl}/${id}`);
  }

}
