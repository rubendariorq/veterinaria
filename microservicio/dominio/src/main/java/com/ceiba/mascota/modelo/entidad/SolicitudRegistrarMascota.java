package com.ceiba.mascota.modelo.entidad;

import com.ceiba.cupon.entidad.Cupon;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudRegistrarMascota {
    private final Mascota mascota;
    private final Cupon cupon;
}
