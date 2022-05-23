package com.ceiba.mascota.servicio;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.cupon.puerto.repositorio.RepositorioCupon;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.modelo.entidad.SolicitudRegistrarMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;

public class ServicioRegistrarMascota {

    private final RepositorioMascota repositorioMascota;
    private final RepositorioCupon repositorioCupon;

    public ServicioRegistrarMascota(RepositorioMascota repositorioMascota, RepositorioCupon repositorioCupon) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioCupon = repositorioCupon;
    }

    public Mascota ejecutar(SolicitudRegistrarMascota solicitudRegistrarMascota) {
        return repositorioMascota.guardar(solicitudRegistrarMascota.getMascota(),
                solicitudRegistrarMascota.getCupon());
    }
}
