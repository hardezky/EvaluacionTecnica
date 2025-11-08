import { Component, signal } from '@angular/core';
import { RouterLink, Router, RouterOutlet } from '@angular/router';
import { Auth } from './services/auth';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('credit-frontend');

  constructor(public auth: Auth, private router: Router) {}

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  get userName(): string {
    //console.log('info2:', this.auth.getUserInfo());
    const info = this.auth.getUserInfo();
    return info?.nombre || info?.usuario || '';
  }
}
