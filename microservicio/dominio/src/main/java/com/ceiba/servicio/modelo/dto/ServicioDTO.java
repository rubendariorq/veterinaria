package com.ceiba.servicio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServicioDTO {

    private Long id;
    private String descripcion;
    private Double valor;
}
