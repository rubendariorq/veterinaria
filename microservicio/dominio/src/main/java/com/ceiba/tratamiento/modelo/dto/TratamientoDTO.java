package com.ceiba.tratamiento.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TratamientoDTO {
    private Long id;
    private String codigoTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long tipoTratamiento;
    private Long idMascota;
    private Long idServicio;
    private Double valor;
}
