package com.ceiba.mascota.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.comando.manejador.ManejadorRegistrarMascota;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascota")
@Tag(name = "Controlador comando mascota")
public class ComandoControladorMascota {

    private final ManejadorRegistrarMascota manejadorRegistrarMascota;

    public ComandoControladorMascota(ManejadorRegistrarMascota manejadorRegistrarMascota) {
        this.manejadorRegistrarMascota = manejadorRegistrarMascota;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar mascota", description = "Metodo utilizado para registrar una nueva mascota")
    public ComandoRespuesta<Long> facturar(@RequestBody ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota) {
        return this.manejadorRegistrarMascota.ejecutar(comandoSolicitudRegistrarMascota);
    }
}
