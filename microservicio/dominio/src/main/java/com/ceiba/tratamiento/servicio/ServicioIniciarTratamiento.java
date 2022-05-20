package com.ceiba.tratamiento.servicio;

import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;

public class ServicioIniciarTratamiento {

    private final RepositorioTratamiento repositorioTratamiento;

    public ServicioIniciarTratamiento(RepositorioTratamiento repositorioTratamiento) {
        this.repositorioTratamiento = repositorioTratamiento;
    }

    public Tratamiento ejecutar(SolicitudIniciarTratamiento solicitudIniciarTratamiento) {
        Tratamiento tratamiento = Tratamiento.crear(solicitudIniciarTratamiento);
        return repositorioTratamiento.guardar(tratamiento);
    }
}
