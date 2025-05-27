package gt.umg.api.services.implementation;

import gt.umg.api.dto.URolDTO;
import gt.umg.api.entity.URol;
import gt.umg.api.repository.IURolRepository;
import gt.umg.api.services.interfaces.IURolService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class URolServiceImplementation implements IURolService {

    private final IURolRepository iuRolRepository;

    private static final String MENSAJE_ROL = "Rol no encontrado";

    @Override
    @Transactional
    public URol createRol(URolDTO dto) {
        URol rol = new URol();
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        return iuRolRepository.save(rol);
    }

    @Override
    @Transactional
    public URol updateRol(Integer id, URolDTO dto) {
        URol rol = iuRolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MENSAJE_ROL));

        rol.setNombre(dto.getNombre() != null ? dto.getNombre() : rol.getNombre());
        rol.setDescripcion(dto.getDescripcion() != null ? dto.getDescripcion() : rol.getDescripcion());

        return iuRolRepository.save(rol);
    }

    @Override
    public List<URol> getAllRoles() {
        return iuRolRepository.findAll();
    }

    @Override
    public URol getRolById(Integer id) {
        return iuRolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MENSAJE_ROL));
    }

    @Override
    @Transactional
    public void deleteRol(Integer id) {
        URol rol = iuRolRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        iuRolRepository.delete(rol);
    }
}
