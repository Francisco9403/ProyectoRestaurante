import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from "../environments/environment";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

export interface Imagen {
  id: number;
  file: string; // Assuming base64 encoded string for the image
  fileContentType: string;
}

export interface Oferta {
  id: number;
  nombre: string;
  descripcion: string;
  porcentajeDescuento: number;
  menu: MenuItem[] | null;
  imagen: Imagen | null;
}

export interface MenuItem {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagen: Imagen | null;
  oferta?: Oferta | null;
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

@Injectable({
  providedIn: 'root'
})
export class OfertaService {

  private apiUrl = `${environment.API_URL}/ofertas`;

  constructor(private http: HttpClient) { }

  getOfertas(page: number = 0, size: number = 1, nombre: string = ''): Observable<Page<Oferta>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('nombre', nombre);

    return this.http.get<Page<Oferta>>(this.apiUrl, { params });
  }

  getOfertaById(id: number): Observable<Oferta> {
    return this.http.get<Oferta>(`${this.apiUrl}/${id}`);
  }

  updateOferta(oferta: Oferta, imagen: File | null): Observable<Oferta> {
    const formData: FormData = new FormData();
    formData.append('oferta', new Blob([JSON.stringify(oferta)], { type: 'application/json' }));

    if (imagen) {
      formData.append('imagen', imagen);
    }

    return this.http.put<Oferta>(`${this.apiUrl}/${oferta.id}`, formData);
  }

  crearOferta(oferta: Oferta, imagen: File): Observable<Oferta> {
    const formData: FormData = new FormData();
    formData.append('oferta', new Blob([JSON.stringify(oferta)], { type: 'application/json' }));
    formData.append('imagen', imagen);

    return this.http.post<Oferta>(this.apiUrl, formData);
  }

  deleteOferta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
