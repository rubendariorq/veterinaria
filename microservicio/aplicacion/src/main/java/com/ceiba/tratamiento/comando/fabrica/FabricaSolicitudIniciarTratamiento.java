package com.ceiba.tratamiento.comando.fabrica;

import com.ceiba.cupon.modelo.entidad.Cupon;
import com.ceiba.cupon.puerto.repositorio.RepositorioCupon;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudIniciarTratamiento {

    private final RepositorioMascota repositorioMascota;
    private final RepositorioServicio repositorioServicio;
    private final RepositorioCupon repositorioCupon;

    public FabricaSolicitudIniciarTratamiento(RepositorioMascota repositorioMascota, RepositorioServicio repositorioServicio, RepositorioCupon repositorioCupon) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioServicio = repositorioServicio;
        this.repositorioCupon = repositorioCupon;
    }

    public SolicitudIniciarTratamiento crear(ComandoSolicitudIniciarTratamiento comando) {
        Mascota mascota = repositorioMascota.obtener(comando.getIdMascota());
        Servicio servicio = repositorioServicio.obtener(comando.getIdServicio());
        Cupon cupon = repositorioCupon.obtener(comando.getIdCupon());

        return new SolicitudIniciarTratamiento(mascota, servicio, Tratamiento.crear(mascota, servicio, cupon,
                comando.getCodigoTratamiento(), comando.getFechaInicio(), comando.getFechaFin(),
                comando.getTipoTratamiento()), cupon);
    }
}
