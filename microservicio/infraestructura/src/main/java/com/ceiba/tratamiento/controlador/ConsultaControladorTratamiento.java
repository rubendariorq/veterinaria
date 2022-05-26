package com.ceiba.tratamiento.controlador;

import com.ceiba.tratamiento.consulta.ManejadorConsultarTratamiento;
import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tratamiento")
@Tag(name = "Controlador consulta tratamiento")
public class ConsultaControladorTratamiento {

    private final ManejadorConsultarTratamiento manejadorConsultarTratamiento;

    public ConsultaControladorTratamiento(ManejadorConsultarTratamiento manejadorConsultarTratamiento) {
        this.manejadorConsultarTratamiento = manejadorConsultarTratamiento;
    }

    @GetMapping()
    @Operation(summary = "Listar", description = "Metodo utilizado para listar los tratamientos registradas")
    public List<TratamientoDTO> listar() {
        return manejadorConsultarTratamiento.ejecutar();
    }

    @GetMapping("/{id-tratamiento}")
    @Operation(summary = "Listar", description = "Metodo utilizado para listar los tratamientos registradas")
    public TratamientoDTO obtener(@PathVariable("id-tratamiento") Long idTratamiento) {
        return manejadorConsultarTratamiento.obtener(idTratamiento);
    }
}
