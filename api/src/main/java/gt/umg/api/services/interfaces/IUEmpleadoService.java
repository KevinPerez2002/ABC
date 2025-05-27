package gt.umg.api.services.interfaces;

import gt.umg.api.dto.UEmpleadoDTO;
import gt.umg.api.entity.UEmpleado;

import java.util.List;

public interface IUEmpleadoService {

    UEmpleado createEmpleado(UEmpleadoDTO empleadoDTO, String usuario);

    UEmpleado updateEmpleado(String id, UEmpleadoDTO empleadoDTO, String usuario);

    List<UEmpleado> getAllEmpleados();

    UEmpleado getEmpleadoById(String id);

    void deleteEmpleado(String id);
}
