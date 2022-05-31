package com.ceiba.tratamiento.modelo.entidad;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.modelo.entidad.Servicio;

public class SolicitudIniciarTratamiento {
    private Mascota mascota;
    private Servicio servicio;
    private Tratamiento tratamiento;
    private Cupon cupon;

    public SolicitudIniciarTratamiento(Mascota mascota, Servicio servicio, Tratamiento tratamiento, Cupon cupon) {
        this.mascota = mascota;
        this.servicio = servicio;
        this.tratamiento = tratamiento;
        this.cupon = cupon;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public Cupon getCupon() {
        return cupon;
    }
}
