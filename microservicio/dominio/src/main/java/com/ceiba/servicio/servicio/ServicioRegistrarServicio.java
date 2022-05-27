package com.ceiba.servicio.servicio;

import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.servicio.modelo.entidad.SolicitudRegistrarServicio;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;

public class ServicioRegistrarServicio {

    private final RepositorioServicio repositorioServicio;

    public ServicioRegistrarServicio(RepositorioServicio repositorioServicio) {
        this.repositorioServicio = repositorioServicio;
    }

    public Servicio ejecutar(SolicitudRegistrarServicio solicitudRegistrarServicio) {
        return repositorioServicio.guardar(solicitudRegistrarServicio.getServicio());
    }
}
