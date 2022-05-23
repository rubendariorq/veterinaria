package com.ceiba.tratamiento.servicio;

import com.ceiba.tratamiento.modelo.entidad.SolicitudEliminarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;

public class ServicioTratamiento {

    private final RepositorioTratamiento repositorioTratamiento;

    public ServicioTratamiento(RepositorioTratamiento repositorioTratamiento) {
        this.repositorioTratamiento = repositorioTratamiento;
    }

    public Tratamiento ejecutar(SolicitudIniciarTratamiento solicitudIniciarTratamiento) {
        Tratamiento tratamiento = solicitudIniciarTratamiento.getTratamiento();
        return repositorioTratamiento.guardar(tratamiento);
    }

    public String eliminar(SolicitudEliminarTratamiento solicitudEliminarTratamiento) {
        return repositorioTratamiento.eliminar(solicitudEliminarTratamiento.getTratamiento());
    }
}
