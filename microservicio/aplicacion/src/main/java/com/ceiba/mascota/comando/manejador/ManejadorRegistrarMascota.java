package com.ceiba.mascota.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.comando.fabrica.FabricaSolicitudRegistrarMascota;
import com.ceiba.mascota.servicio.ServicioRegistrarMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistrarMascota implements ManejadorComandoRespuesta<ComandoSolicitudRegistrarMascota, ComandoRespuesta<Long>> {

    private final FabricaSolicitudRegistrarMascota fabricaSolicitudRegistrarMascota;
    private final ServicioRegistrarMascota servicioRegistrarMascota;

    public ManejadorRegistrarMascota(FabricaSolicitudRegistrarMascota fabricaSolicitudRegistrarMascota, ServicioRegistrarMascota servicioRegistrarMascota) {
        this.fabricaSolicitudRegistrarMascota = fabricaSolicitudRegistrarMascota;
        this.servicioRegistrarMascota = servicioRegistrarMascota;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota) {
        return new ComandoRespuesta<>(servicioRegistrarMascota
                .ejecutar(fabricaSolicitudRegistrarMascota.crear(comandoSolicitudRegistrarMascota)));
    }
}
