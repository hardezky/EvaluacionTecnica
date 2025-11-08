import { Component } from '@angular/core';
import { Auth } from '../../services/auth';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  usuario = '';
  password = '';
  errorMessage = '';

  constructor(private authService: Auth, private router: Router) {}

  ngOnInit() {
  if (this.authService.getToken()) {
    this.router.navigate(['/dashboard']);
  }
}


  login() {
    this.authService.login(this.usuario, this.password).subscribe({
      next: () => this.router.navigate(['/dashboard']),
      error: () => (this.errorMessage = 'Usuario o contraseña incorrectos')
    });
  }
}
