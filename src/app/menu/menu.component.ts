import {Component, OnInit} from '@angular/core';
import {MenuItem, MenuService} from "./menu.service";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit{

  menuItems: MenuItem[] = [];

  constructor(private menuService: MenuService) { }

  ngOnInit(): void {
    this.menuService.getMenuItems().subscribe(data => {
      this.menuItems = data;
    });
  }
}
