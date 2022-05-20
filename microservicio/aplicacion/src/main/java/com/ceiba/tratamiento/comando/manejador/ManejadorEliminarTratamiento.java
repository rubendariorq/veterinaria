package com.ceiba.tratamiento.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.tratamiento.comando.fabrica.FabricaSolicitudEliminarTratamiento;
import com.ceiba.tratamiento.servicio.ServicioTratamiento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarTratamiento implements ManejadorComandoRespuesta<Long, ComandoRespuesta<String>> {

    private final FabricaSolicitudEliminarTratamiento fabricaSolicitudEliminarTratamiento;
    private final ServicioTratamiento servicioTratamiento;

    public ManejadorEliminarTratamiento(FabricaSolicitudEliminarTratamiento fabricaSolicitudEliminarTratamiento, ServicioTratamiento servicioTratamiento) {
        this.fabricaSolicitudEliminarTratamiento = fabricaSolicitudEliminarTratamiento;
        this.servicioTratamiento = servicioTratamiento;
    }

    @Override
    public ComandoRespuesta<String> ejecutar(Long idTratamiento) {
        return new ComandoRespuesta<>(servicioTratamiento
                .eliminar(fabricaSolicitudEliminarTratamiento.crear(idTratamiento)));
    }
}
