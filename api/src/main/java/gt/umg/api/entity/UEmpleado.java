package gt.umg.api.entity;

import gt.umg.api.util.Estatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "u_empleados")
public class UEmpleado {

    @Id
    @Column(name = "idempleado", length = 20, nullable = false)
    private String idEmpleado;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String direccion;

    @Column(name = "teléfono", length = 50)
    private String telefono;

    @Column(length = 15)
    private String nit;

    @Column(length = 20)
    private String correo;

    @Column(length = 15)
    private String dpi;

    @Column(name = "finicio", columnDefinition = "DATE DEFAULT (CURRENT_DATE())")
    private LocalDate fInicio;

    @Column(name = "ffin")
    private LocalDate fFin;

    @Column(length = 200)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('A', 'I') DEFAULT 'A'")
    @Comment("Activo, Inactivo")
    private Estatus estatus;

    private String adiciono;

    @Column(name = "fecha_adiciono", columnDefinition = "DATE DEFAULT (CURRENT_DATE())")
    private LocalDate fechaAdiciono;

    private String modifico;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "idrol", foreignKey = @ForeignKey(name = "fk_u_empleados_u_roles"))
    @Comment("Rol que tiene el empleado")
    private URol uRol;
}
