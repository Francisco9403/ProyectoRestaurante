import { Injectable } from '@angular/core';
import {Menu} from "primeng/menu";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private apiUrl = `${environment.API_URL}/menus`;

  constructor(private http: HttpClient) { }

  getMenuItems(): Observable<MenuItem[]> {
    return this.http.get<MenuItem[]>(this.apiUrl);
  }
}

export interface Oferta {
  id: number;
  nombre: string;
  descripcion: string;
  porcentajeDescuento: number;
  menus: Menu[];
}

export interface MenuItem {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagen: string;
  oferta: Oferta;
}
