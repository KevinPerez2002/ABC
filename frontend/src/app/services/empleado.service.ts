import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Empleado} from '../models/empleado.model';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private apiUrl = 'http://localhost:8080/api/empleados';

  constructor(private http: HttpClient) {
  }

  getAllEmpleados(): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(this.apiUrl);
  }

  createEmpleado(empleado: Empleado): Observable<Empleado> {
    const headers = new HttpHeaders().set('Usuario', 'encargado');
    return this.http.post<Empleado>(this.apiUrl, empleado, {headers});
  }

  updateEmpleado(id: string, empleado: Empleado): Observable<Empleado> {
    const headers = new HttpHeaders().set('Usuario', 'encargado');
    return this.http.put<Empleado>(`${this.apiUrl}/${id}`, empleado, {headers});
  }

  deleteEmpleado(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getEmpleadoById(id: string): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.apiUrl}/${id}`);
  }
}
