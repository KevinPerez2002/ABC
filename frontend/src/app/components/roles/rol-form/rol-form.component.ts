import {Component, OnInit} from '@angular/core';
import {Rol} from '../../../models/rol.model';
import {RolService} from '../../../services/rol.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-rol-form',
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './rol-form.component.html',
  styleUrl: './rol-form.component.css'
})
export class RolFormComponent implements OnInit {

  rol: Rol = { idRol: 0, nombre: '', descripcion: '' };
  isEdit = false;

  constructor(
    private rolService: RolService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEdit = true;
      this.rolService.getRolById(+id).subscribe(rol => this.rol = rol);
    }
  }

  onSubmit(): void {
    if (this.isEdit) {
      this.rolService.updateRol(this.rol.idRol, this.rol).subscribe(() => {
        this.router.navigate(['/roles']);
      });
    } else {
      this.rolService.createRol(this.rol).subscribe(() => {
        this.router.navigate(['/roles']);
      });
    }
  }
}
