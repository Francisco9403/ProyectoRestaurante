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

  getMenuItems(page: number = 0, size: number = 1, nombre: string = '', precioMin: number | null = null, precioMax: number | null = null): Observable<Page<MenuItem>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('nombre', nombre);

    // Solo agregar los filtros de precio si tienen valores definidos
    if (precioMin !== null) {
      params = params.set('precioMin', precioMin.toString());
    }
    if (precioMax !== null) {
      params = params.set('precioMax', precioMax.toString());
    }

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
