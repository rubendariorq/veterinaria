package com.ceiba.tratamiento.comando.fabrica;

import com.ceiba.tratamiento.modelo.entidad.SolicitudEliminarTratamiento;
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
