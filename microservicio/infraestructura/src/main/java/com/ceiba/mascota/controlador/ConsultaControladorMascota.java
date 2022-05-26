package com.ceiba.mascota.controlador;

import com.ceiba.mascota.consulta.ManejadorConsultarMascotas;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mascota")
@Tag(name = "Controlador consulta mascota")
public class ConsultaControladorMascota {

    private final ManejadorConsultarMascotas manejadorConsultarMascotas;

    public ConsultaControladorMascota(ManejadorConsultarMascotas manejadorConsultarMascotas) {
        this.manejadorConsultarMascotas = manejadorConsultarMascotas;
    }

    @GetMapping()
    @Operation(summary = "Listar", description = "Metodo utilizado para listar las mascotas registradas")
    public List<MascotaDTO> listar() {
        return manejadorConsultarMascotas.ejecutar();
    }
}
