package com.ceiba.tratamiento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.comando.manejador.ManejadorEliminarTratamiento;
import com.ceiba.tratamiento.comando.manejador.ManejadorIniciarTratamiento;
import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
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
    private final ManejadorEliminarTratamiento manejadorEliminarTratamiento;

    public ComandoControladorTratamiento(ManejadorIniciarTratamiento manejadorIniciarTratamiento, ManejadorEliminarTratamiento manejadorEliminarTratamiento) {
        this.manejadorIniciarTratamiento = manejadorIniciarTratamiento;
        this.manejadorEliminarTratamiento = manejadorEliminarTratamiento;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar tratamiento", description = "Metodo utilizado para registrar un nuevo tratamiento")
    public ComandoRespuesta<Tratamiento> registrarTratamiento(@RequestBody ComandoSolicitudIniciarTratamiento comandoSolicitudIniciarTratamiento) {
        return this.manejadorIniciarTratamiento.ejecutar(comandoSolicitudIniciarTratamiento);
    }

    @DeleteMapping("/{id-tratamiento}")
    @Operation(summary = "Eliminar", description = "Metodo utilizado para eliminar el tratamiento indicado")
    public ComandoRespuesta<String> eliminar(@PathVariable("id-tratamiento") Long idTratamiento) {
        return manejadorEliminarTratamiento.ejecutar(idTratamiento);
    }
}
