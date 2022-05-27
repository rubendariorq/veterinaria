package com.ceiba.servicio.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;
import com.ceiba.servicio.comando.fabrica.FabricaSolicitudRegistrarServicio;
import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.servicio.servicio.ServicioRegistrarServicio;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearServicio implements ManejadorComandoRespuesta<ComandoSolicitudRegistrarServicio, ComandoRespuesta<Servicio>> {

    private final FabricaSolicitudRegistrarServicio fabricaSolicitudRegistrarServicio;
    private final ServicioRegistrarServicio servicioRegistrarServicio;

    public ManejadorCrearServicio(FabricaSolicitudRegistrarServicio fabricaSolicitudRegistrarServicio, ServicioRegistrarServicio servicioRegistrarServicio) {
        this.fabricaSolicitudRegistrarServicio = fabricaSolicitudRegistrarServicio;
        this.servicioRegistrarServicio = servicioRegistrarServicio;
    }

    @Override
    public ComandoRespuesta<Servicio> ejecutar(ComandoSolicitudRegistrarServicio comando) {
        return new ComandoRespuesta<>(servicioRegistrarServicio
                .ejecutar(fabricaSolicitudRegistrarServicio.crear(comando)));
    }
}
