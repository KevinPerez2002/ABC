package gt.umg.api.controller;

import gt.umg.api.dto.UEmpleadoDTO;
import gt.umg.api.entity.UEmpleado;
import gt.umg.api.services.interfaces.IUEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class UEmpleadoController {

    private final IUEmpleadoService iuEmpleadoService;

    @PostMapping
    public ResponseEntity<UEmpleado> crearEmpleado(
            @RequestBody UEmpleadoDTO empleadoDTO,
            @RequestHeader(value = "Usuario", defaultValue = "encargado") String usuario) {
        UEmpleado nuevoEmpleado = iuEmpleadoService.createEmpleado(empleadoDTO, usuario);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UEmpleado> actualizarEmpleado(
            @PathVariable String id,
            @RequestBody UEmpleadoDTO empleadoDTO,
            @RequestHeader(value = "Usuario", defaultValue = "encargado") String usuario) {
        UEmpleado empleadoActualizado = iuEmpleadoService.updateEmpleado(id, empleadoDTO, usuario);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @GetMapping
    public ResponseEntity<List<UEmpleado>> obtenerTodosEmpleados() {
        List<UEmpleado> empleados = iuEmpleadoService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UEmpleado> obtenerEmpleadoPorId(@PathVariable String id) {
        UEmpleado empleado = iuEmpleadoService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String id) {
        iuEmpleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
