package com.ceiba.tratamiento.comando.fabrica;

import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudIniciarTratamiento {

    private final RepositorioTratamiento repositorioTratamiento;
    private final RepositorioMascota repositorioMascota;
    private final RepositorioServicio repositorioServicio;

    public FabricaSolicitudIniciarTratamiento(RepositorioTratamiento repositorioTratamiento, RepositorioMascota repositorioMascota, RepositorioServicio repositorioServicio) {
        this.repositorioTratamiento = repositorioTratamiento;
        this.repositorioMascota = repositorioMascota;
        this.repositorioServicio = repositorioServicio;
    }

    public SolicitudIniciarTratamiento crear(ComandoSolicitudIniciarTratamiento comando) {
        return new SolicitudIniciarTratamiento(repositorioMascota.obtener(comando.getIdMascota()),
                repositorioServicio.obtener(comando.getIdServicio()),
                Tratamiento.construir(comando.getCodigoTratamiento(),
                repositorioMascota.obtener(comando.getIdMascota()).getId(), comando.getFechaInicio(), comando.getFechaFin(),
                comando.getTipoTratamiento(), repositorioServicio.obtener(comando.getIdServicio()).getId()));
    }
}
