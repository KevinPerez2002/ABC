import {Component, OnInit} from '@angular/core';
import {Empleado} from '../../../models/empleado.model';
import {EmpleadoService} from '../../../services/empleado.service';
import {RouterLink} from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-empleados-list',
  imports: [
    RouterLink,
    NgIf,
    NgForOf
  ],
  templateUrl: './empleados-list.component.html',
  styleUrl: './empleados-list.component.css'
})
export class EmpleadosListComponent implements OnInit {

  empleados: Empleado[] = [];
  loading = true;
  errorMessage = '';

  constructor(private empleadoService: EmpleadoService) {
  }

  ngOnInit(): void {
    this.loadEmpleados();
  }

  loadEmpleados(): void {
    this.empleadoService.getAllEmpleados().subscribe({
      next: (data) => {
        this.empleados = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Error cargando empleados';
        this.loading = false;
        console.error(err);
      }
    });
  }

  deleteEmpleado(id: string): void {
    if (confirm('¿Estás seguro de eliminar este empleado?')) {
      this.empleadoService.deleteEmpleado(id).subscribe({
        next: () => this.loadEmpleados(),
        error: (err) => {
          this.errorMessage = 'Error eliminando empleado';
          console.error(err);
        }
      });
    }
  }
}
