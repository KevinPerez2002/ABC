export interface Empleado {
  idEmpleado: string;
  nombre: string;
  direccion?: string;
  telefono?: string;
  nit?: string;
  correo?: string;
  dpi?: string;
  finicio?: Date;
  ffin?: Date;
  observaciones?: string;
  estatus?: string;
  adiciono?: string;
  fechaAdiciono?: Date;
  modifico?: string;
  fechaModificacion?: Date;
  urol: { idRol: number };
}
