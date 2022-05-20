package com.ceiba.tratamiento.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.comando.fabrica.FabricaSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.servicio.ServicioTratamiento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorIniciarTratamiento implements ManejadorComandoRespuesta<ComandoSolicitudIniciarTratamiento, ComandoRespuesta<Tratamiento>> {

    private final FabricaSolicitudIniciarTratamiento fabricaSolicitudIniciarTratamiento;
    private final ServicioTratamiento servicioTratamiento;

    public ManejadorIniciarTratamiento(FabricaSolicitudIniciarTratamiento fabricaSolicitudIniciarTratamiento, ServicioTratamiento servicioTratamiento) {
        this.fabricaSolicitudIniciarTratamiento = fabricaSolicitudIniciarTratamiento;
        this.servicioTratamiento = servicioTratamiento;
    }

    @Override
    public ComandoRespuesta<Tratamiento> ejecutar(ComandoSolicitudIniciarTratamiento comandoSolicitudIniciarTratamiento) {
        return new ComandoRespuesta<>(servicioTratamiento
                .ejecutar(fabricaSolicitudIniciarTratamiento.crear(comandoSolicitudIniciarTratamiento)));
    }
}
