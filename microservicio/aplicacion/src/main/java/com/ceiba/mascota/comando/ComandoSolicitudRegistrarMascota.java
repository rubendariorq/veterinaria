package com.ceiba.mascota.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudRegistrarMascota {
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;
}
