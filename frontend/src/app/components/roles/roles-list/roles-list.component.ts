import {Component, OnInit} from '@angular/core';
import {Rol} from '../../../models/rol.model';
import {RolService} from '../../../services/rol.service';
import {RouterLink} from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-roles-list',
  imports: [
    RouterLink,
    NgForOf,
    NgIf
  ],
  templateUrl: './roles-list.component.html',
  styleUrl: './roles-list.component.css'
})
export class RolesListComponent implements OnInit {

  roles: Rol[] = [];
  loading = true;
  errorMessage = '';

  constructor(private rolService: RolService) {

  }

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    this.rolService.getAllRoles().subscribe({
      next: (data) => {
        this.roles = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Error cargando roles. Intente nuevamente.';
        this.loading = false;
        console.error('Error:', err);
      }
    });
  }

  deleteRol(id: number): void {
    if (confirm('¿Estás seguro de eliminar este rol?')) {
      this.rolService.deleteRol(id).subscribe({
        next: () => this.loadRoles(),
        error: (err) => {
          this.errorMessage = 'Error eliminando el rol';
          console.error(err);
        }
      });
    }
  }
}
