package gt.umg.api.repository;

import gt.umg.api.entity.UEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUEmpleado extends JpaRepository<UEmpleado, String> {
}
