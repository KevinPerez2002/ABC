package gt.umg.api.services.implementation;

import gt.umg.api.dto.UEmpleadoDTO;
import gt.umg.api.entity.UEmpleado;
import gt.umg.api.entity.URol;
import gt.umg.api.repository.IUEmpleado;
import gt.umg.api.repository.IURolRepository;
import gt.umg.api.services.interfaces.IUEmpleadoService;
import gt.umg.api.util.Estatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UEmpleadoServiceImplementation implements IUEmpleadoService {

    private final IUEmpleado empleadoRepository;
    private final IURolRepository rolRepository;

    private static final String MENSAJE_EMPLEADO = "Empleado no encontrado";
    private static final String MENSAJE_ROL = "Rol no encontrado";

    @Override
    @Transactional
    public UEmpleado createEmpleado(UEmpleadoDTO dto, String usuario) {
        URol rol = rolRepository.findById(dto.getURolDTO().getIdRol())
                .orElseThrow(RuntimeException::new);

        UEmpleado empleado = UEmpleado.builder()
                .idEmpleado(dto.getIdEmpleado())
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .nit(dto.getNit())
                .correo(dto.getCorreo())
                .dpi(dto.getDpi())
                .fInicio(dto.getFInicio() != null ? dto.getFInicio() : LocalDate.now())
                .fFin(dto.getFFin())
                .observaciones(dto.getObservaciones())
                .estatus(dto.getEstatus() != null ? dto.getEstatus() : Estatus.A)
                .adiciono(usuario)
                .fechaAdiciono(LocalDate.now())
                .uRol(rol)
                .build();
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional
    public UEmpleado updateEmpleado(String id, UEmpleadoDTO dto, String usuario) {
        UEmpleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MENSAJE_EMPLEADO));

        if (dto.getURolDTO() != null) {
            URol rol = rolRepository.findById(dto.getURolDTO().getIdRol())
                    .orElseThrow(() -> new RuntimeException(MENSAJE_ROL));
            empleado.setURol(rol);
        }

        empleado.setNombre(dto.getNombre() != null ? dto.getNombre() : empleado.getNombre());
        empleado.setDireccion(dto.getDireccion() != null ? dto.getDireccion() : empleado.getDireccion());
        empleado.setTelefono(dto.getTelefono() != null ? dto.getTelefono() : empleado.getTelefono());
        empleado.setNit(dto.getNit() != null ? dto.getNit() : empleado.getNit());
        empleado.setCorreo(dto.getCorreo() != null ? dto.getCorreo() : empleado.getCorreo());
        empleado.setDpi(dto.getDpi() != null ? dto.getDpi() : empleado.getDpi());
        empleado.setFFin(dto.getFFin() != null ? dto.getFFin() : empleado.getFFin());
        empleado.setObservaciones(dto.getObservaciones() != null ? dto.getObservaciones() : empleado.getObservaciones());
        empleado.setEstatus(dto.getEstatus() != null ? dto.getEstatus() : empleado.getEstatus());
        empleado.setModifico(usuario);
        empleado.setFechaModificacion(LocalDate.now());

        return empleadoRepository.save(empleado);
    }

    @Override
    public List<UEmpleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public UEmpleado getEmpleadoById(String id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MENSAJE_EMPLEADO));
    }

    @Override
    @Transactional
    public void deleteEmpleado(String id) {
        UEmpleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MENSAJE_EMPLEADO));
        empleadoRepository.delete(empleado);
    }
}
