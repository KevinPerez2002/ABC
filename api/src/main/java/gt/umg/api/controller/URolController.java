package gt.umg.api.controller;

import gt.umg.api.dto.URolDTO;
import gt.umg.api.entity.URol;
import gt.umg.api.services.interfaces.IURolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class URolController {

    private final IURolService iuRolService;

    @PostMapping
    public ResponseEntity<URol> crearRol(@RequestBody URolDTO rolDTO) {
        URol nuevoRol = iuRolService.createRol(rolDTO);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<URol> actualizarRol(
            @PathVariable Integer id,
            @RequestBody URolDTO rolDTO) {
        URol rolActualizado = iuRolService.updateRol(id, rolDTO);
        return ResponseEntity.ok(rolActualizado);
    }

    @GetMapping
    public ResponseEntity<List<URol>> obtenerTodosRoles() {
        List<URol> roles = iuRolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<URol> obtenerRolPorId(@PathVariable Integer id) {
        URol rol = iuRolService.getRolById(id);
        return ResponseEntity.ok(rol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Integer id) {
        iuRolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}
