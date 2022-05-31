package com.ceiba.servicio.modelo.entidad;

public class SolicitudRegistrarServicio {
    private final Servicio servicio;

    public SolicitudRegistrarServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Servicio getServicio() {
        return servicio;
    }
}
