package com.ceiba.mascota.modelo.entidad;

import com.ceiba.cupon.modelo.entidad.Cupon;

public class SolicitudRegistrarMascota {
    private final Mascota mascota;
    private final Cupon cupon;

    public SolicitudRegistrarMascota(Mascota mascota, Cupon cupon) {
        this.mascota = mascota;
        this.cupon = cupon;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Cupon getCupon() {
        return cupon;
    }
}
