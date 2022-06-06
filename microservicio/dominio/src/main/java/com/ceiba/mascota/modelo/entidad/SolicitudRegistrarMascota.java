package com.ceiba.mascota.modelo.entidad;

import com.ceiba.cupon.modelo.entidad.Cupon;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SolicitudRegistrarMascota {
    private final Mascota mascota;
    private final Cupon cupon;
}
