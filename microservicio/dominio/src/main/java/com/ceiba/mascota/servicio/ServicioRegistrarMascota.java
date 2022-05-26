package com.ceiba.mascota.servicio;

import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.modelo.entidad.SolicitudRegistrarMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;

public class ServicioRegistrarMascota {

    private final RepositorioMascota repositorioMascota;

    public ServicioRegistrarMascota(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    public Mascota ejecutar(SolicitudRegistrarMascota solicitudRegistrarMascota) {
        return repositorioMascota.guardar(solicitudRegistrarMascota.getMascota(),
                solicitudRegistrarMascota.getCupon());
    }
}
