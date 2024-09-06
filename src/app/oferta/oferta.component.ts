import {Component, inject, OnInit} from '@angular/core';
import { OfertaService, Oferta, Page } from './oferta.service';
import { AuthService } from "../auth/auth.service";
import { FormsModule } from "@angular/forms";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-oferta',
  templateUrl: './oferta.component.html',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  styleUrls: ['./oferta.component.css']
})
export class OfertaComponent implements OnInit {
  ofertas: Oferta[] = [];
  nombre: string = '';
  currentPage: number = 0;
  pageSize: number = 3;
  totalPages: number = 0;
  authService = inject(AuthService);

  constructor(private ofertaService: OfertaService) {}

  ngOnInit(): void {
    this.getOfertas();
  }

  getOfertas(): void {
    this.ofertaService.getOfertas(this.currentPage, this.pageSize, this.nombre)
      .subscribe((data: Page<Oferta>) => {
        console.log(data);
        this.ofertas = data.content;
        this.totalPages = data.page.totalPages;
      });
  }

  onSearch(): void {
    this.currentPage = 0;
    this.getOfertas();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.getOfertas();
  }

  clearFilters(): void {
    this.nombre = '';
    this.onSearch();
  }

  deleteOferta(oferta: Oferta): void {
    this.ofertaService.deleteOferta(oferta.id).subscribe(() => this.getOfertas());
  }
}
