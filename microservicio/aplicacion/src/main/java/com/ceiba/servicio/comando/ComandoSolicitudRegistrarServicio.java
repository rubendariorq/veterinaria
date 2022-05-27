package com.ceiba.servicio.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudRegistrarServicio {
    private String descripcion;
    private Double valor;
}
