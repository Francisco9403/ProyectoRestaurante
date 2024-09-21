import { Component, OnInit } from '@angular/core';
import { CartService } from './cart.service';
import {MenuItem} from "../menu/menu.service"; // Asegúrate de que esta ruta sea correcta

@Component({
  selector: 'app-cart',
  standalone: true,
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: { item: MenuItem; quantity: number }[] = [];

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe(items => {
      this.cartItems = items;
    });
  }

  removeFromCart(item: MenuItem): void {
    this.cartService.removeFromCart(item);
  }

  getSubtotal(item: MenuItem, quantity: number): number {
    return item.precio * quantity; // Asegúrate de que `precio` es el nombre correcto de la propiedad
  }

  getTotal(): number {
    return this.cartItems.reduce((total, cartItem) => {
      return total + this.getSubtotal(cartItem.item, cartItem.quantity);
    }, 0);
  }
}
