import {Component, effect, inject, OnInit} from '@angular/core';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    ButtonModule,
    FontAwesomeModule,
    ToolbarModule,
    MenuModule,
    CategoryComponent,
    AvatarComponent
  ],
  providers: [DialogService],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  authService = inject(AuthService);

  login = () => this.authService.login();
  logout = () => this.authService.logout();

  connectedUser: User = { email: this.authService.notConnected };

  constructor() {
    effect(() => {
      if (this.authService.fetchUser().status === "OK") {
        this.connectedUser = this.authService.fetchUser().value!;
        this.currentMenuItems = this.fetchMenu();
      }
    })
  }

  ngOnInit(): void {

  }

}
