package gt.umg.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gt.umg.api.util.Estatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UEmpleadoDTO {

    private String idEmpleado;

    private String nombre;

    private String direccion;

    private String telefono;

    private String nit;

    private String correo;

    private String dpi;

    private LocalDate fInicio;

    private LocalDate fFin;

    private String observaciones;

    private Estatus estatus;

    private String adiciono;

    private LocalDate fechaAdiciono;

    private String modifico;

    private LocalDate fechaModificacion;

    @JsonProperty("uRolDTO")
    private URolDTO uRolDTO;
}
