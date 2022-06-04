package com.ceiba.servicio.controlador;

import com.ceiba.servicio.consulta.ManejadorConsultarServicios;
import com.ceiba.servicio.modelo.dto.ServicioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicio")
@Tag(name = "Controlador consulta servicio")
public class ConsultaControladorServicio {

    private final ManejadorConsultarServicios manejadorConsultarServicios;

    public ConsultaControladorServicio(ManejadorConsultarServicios manejadorConsultarServicios) {
        this.manejadorConsultarServicios = manejadorConsultarServicios;
    }

    @GetMapping()
    @Operation(summary = "Listar", description = "Metodo utilizado para listar los servicios registrados")
    public List<ServicioDTO> listar() {
        return manejadorConsultarServicios.ejecutar();
    }
}
