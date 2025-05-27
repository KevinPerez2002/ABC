import { Routes } from '@angular/router';
import {RolesListComponent} from './components/roles/roles-list/roles-list.component';
import {RolFormComponent} from './components/roles/rol-form/rol-form.component';
import {EmpleadosListComponent} from './components/empleados/empleados-list/empleados-list.component';
import {EmpleadosFormComponent} from './components/empleados/empleados-form/empleados-form.component';

export const routes: Routes = [
  {
    path: 'roles',
    component: RolesListComponent,
    title: 'Listado de Roles'
  },
  {
    path: 'roles/new',
    component: RolFormComponent,
    title: 'Nuevo Rol'
  },
  {
    path: 'roles/edit/:id',
    component: RolFormComponent,
    title: 'Editar Rol'
  },
  {
    path: 'empleados',
    component: EmpleadosListComponent,
    title: 'Listado de Empleados'
  },
  {
    path: 'empleados/new',
    component: EmpleadosFormComponent,
    title: 'Nuevo Empleado'
  },
  {
    path: 'empleados/edit/:id',
    component: EmpleadosFormComponent,
    title: 'Editar Empleado'
  },
  {
    path: '',
    redirectTo: 'roles',
    pathMatch: 'full'
  }
];
