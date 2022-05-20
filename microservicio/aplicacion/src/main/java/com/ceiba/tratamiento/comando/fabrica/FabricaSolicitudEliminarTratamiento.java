package com.ceiba.tratamiento.comando.fabrica;

import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudEliminarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudEliminarTratamiento {
    private final RepositorioTratamiento repositorioTratamiento;

    public FabricaSolicitudEliminarTratamiento(RepositorioTratamiento repositorioTratamiento) {
        this.repositorioTratamiento = repositorioTratamiento;
    }

    public SolicitudEliminarTratamiento crear(Long idTratamiento) {
        return new SolicitudEliminarTratamiento(repositorioTratamiento.obtener(idTratamiento));
    }
}
