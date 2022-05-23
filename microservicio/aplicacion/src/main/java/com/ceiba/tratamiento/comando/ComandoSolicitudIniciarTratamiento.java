package com.ceiba.tratamiento.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudIniciarTratamiento {
    private String codigoTratamiento;
    private Long idMascota;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long idServicio;
    private Long tipoTratamiento;
    private Long idCupon;
}
