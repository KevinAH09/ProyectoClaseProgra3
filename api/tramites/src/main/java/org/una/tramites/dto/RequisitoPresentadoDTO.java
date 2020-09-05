package org.una.tramites.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Requisito;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author colo7
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class RequisitoPresentadoDTO {
    private Long id;
    private Date fechaRegistro;
    private TramiteRegistrado tramiteRegistradoId;
    private Requisito requisitoId;
}
