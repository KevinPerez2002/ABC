import {Component, OnInit} from '@angular/core';
import {EmpleadoService} from '../../../services/empleado.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {Empleado} from '../../../models/empleado.model';
import {FormsModule} from '@angular/forms';
import {Rol} from '../../../models/rol.model';
import {RolService} from '../../../services/rol.service';
import {NgForOf} from '@angular/common';
import {DateValueAccessorModule} from 'angular-date-value-accessor';

@Component({
  selector: 'app-empleados-form',
  imports: [
    FormsModule,
    RouterLink,
    NgForOf,
    DateValueAccessorModule
  ],
  templateUrl: './empleados-form.component.html',
  styleUrl: './empleados-form.component.css'
})
export class EmpleadosFormComponent implements OnInit {

  empleado: Empleado = {
    idEmpleado: '',
    nombre: '',
    estatus: 'A',
    finicio: new Date(),
    urol: {idRol: 0}
  };

  isEdit = false;
  roles: Rol[] = [];

  constructor(
    private empleadoService: EmpleadoService,
    private rolService: RolService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.loadRoles();

    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEdit = true;
      this.empleadoService.getEmpleadoById(id).subscribe(empleado => {
        this.empleado = {
          ...empleado,
          finicio: empleado.finicio ? new Date(empleado.finicio) : undefined,
          ffin: empleado.ffin ? new Date(empleado.ffin) : undefined,
          urol: {idRol: this.empleado.urol.idRol}
        };
      });
    }
  }

  loadRoles(): void {
    this.rolService.getAllRoles().subscribe({
      next: (data) => this.roles = data,
      error: (err) => console.error('Error loading roles:', err)
    });
  }

  onSubmit(): void {
    if (this.isEdit) {
      this.empleadoService.updateEmpleado(this.empleado.idEmpleado, this.empleado)
        .subscribe(() => this.router.navigate(['/empleados']));
    } else {
      this.empleadoService.createEmpleado(this.empleado)
        .subscribe(() => this.router.navigate(['/empleados']));
    }
  }
}
