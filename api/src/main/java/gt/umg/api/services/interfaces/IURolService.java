package gt.umg.api.services.interfaces;

import gt.umg.api.dto.URolDTO;
import gt.umg.api.entity.URol;

import java.util.List;

public interface IURolService {

    URol createRol(URolDTO rolDTO);

    URol updateRol(Integer id, URolDTO rolDTO);

    List<URol> getAllRoles();

    URol getRolById(Integer id);

    void deleteRol(Integer id);
}
