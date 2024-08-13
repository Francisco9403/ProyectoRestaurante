// En menu.service.ts
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
  oferta: Oferta;
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

  getMenuItems(page: number = 0, size: number = 1, nombre: string = '', precioMin: number = 0, precioMax: number = 10000): Observable<Page<MenuItem>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('nombre', nombre)
      .set('precioMin', precioMin.toString())
      .set('precioMax', precioMax.toString());

    return this.http.get<any>(this.apiUrl, { params }).pipe(
      map(response => ({
        content: response.content,
        page: {
          number: response.page.number,
          size: response.page.size,
          totalElements: response.page.totalElements,
          totalPages: response.page.totalPages
        }
      }))
    );
  }
}
