package com.ceiba.servicio.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;
import com.ceiba.servicio.comando.manejador.ManejadorCrearServicio;
import com.ceiba.servicio.entidad.Servicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicio")
@Tag(name = "Controlador comando servicio")
public class ComandoControladorServicio {
    private final ManejadorCrearServicio manejadorCrearServicio;

    public ComandoControladorServicio(ManejadorCrearServicio manejadorCrearServicio) {
        this.manejadorCrearServicio = manejadorCrearServicio;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar servicio", description = "Metodo utilizado para registrar un nuevo servicio")
    public ComandoRespuesta<Servicio> registrarServicio(@RequestBody ComandoSolicitudRegistrarServicio comandoSolicitudRegistrarServicio) {
        return this.manejadorCrearServicio.ejecutar(comandoSolicitudRegistrarServicio);
    }
}
