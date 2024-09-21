import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {MenuItem} from "../menu/menu.service";

export interface CartItem {
  item: MenuItem;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems: CartItem[] = this.loadCartFromLocalStorage();
  private cartItemsSubject = new BehaviorSubject<CartItem[]>(this.cartItems);

  addToCart(item: MenuItem) {
    const existingItem = this.cartItems.find(cartItem => cartItem.item.id === item.id);
    if (existingItem) {
      existingItem.quantity++;
    } else {
      this.cartItems.push({ item, quantity: 1 });
    }
    this.saveCartToLocalStorage();
    this.cartItemsSubject.next(this.cartItems);
  }

  getCartItems() {
    return this.cartItemsSubject.asObservable();
  }

  clearCart() {
    this.cartItems = [];
    this.saveCartToLocalStorage();
    this.cartItemsSubject.next(this.cartItems);
  }

  removeFromCart(item: MenuItem) {
    this.cartItems = this.cartItems.filter(cartItem => cartItem.item.id !== item.id);
    this.saveCartToLocalStorage();
    this.cartItemsSubject.next(this.cartItems);
  }

  private saveCartToLocalStorage() {
    localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
  }

  private loadCartFromLocalStorage(): CartItem[] {
    const cartData = localStorage.getItem('cartItems');
    return cartData ? JSON.parse(cartData) : [];
  }
}
