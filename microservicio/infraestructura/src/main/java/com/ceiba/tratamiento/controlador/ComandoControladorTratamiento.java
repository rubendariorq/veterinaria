package com.ceiba.tratamiento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.comando.manejador.ManejadorIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tratamiento")
@Tag(name = "Controlador comando tratamiento")
public class ComandoControladorTratamiento {

    private final ManejadorIniciarTratamiento manejadorIniciarTratamiento;

    public ComandoControladorTratamiento(ManejadorIniciarTratamiento manejadorIniciarTratamiento) {
        this.manejadorIniciarTratamiento = manejadorIniciarTratamiento;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar tratamiento", description = "Metodo utilizado para registrar un nuevo tratamiento")
    public ComandoRespuesta<Tratamiento> registrarTratamiento(@RequestBody ComandoSolicitudIniciarTratamiento comandoSolicitudIniciarTratamiento) {
        return this.manejadorIniciarTratamiento.ejecutar(comandoSolicitudIniciarTratamiento);
    }
}
