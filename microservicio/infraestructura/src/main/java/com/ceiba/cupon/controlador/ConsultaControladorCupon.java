package com.ceiba.cupon.controlador;

import com.ceiba.cupon.consulta.ManejadorConsultarCupon;
import com.ceiba.cupon.modelo.dto.CuponDTO;
import com.ceiba.servicio.consulta.ManejadorConsultarServicios;
import com.ceiba.servicio.modelo.dto.ServicioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cupon")
@Tag(name = "Controlador consulta cupon")
public class ConsultaControladorCupon {

    private final ManejadorConsultarCupon manejadorConsultarCupon;

    public ConsultaControladorCupon(ManejadorConsultarCupon manejadorConsultarCupon) {
        this.manejadorConsultarCupon = manejadorConsultarCupon;
    }

    @GetMapping("/{id-tratamiento}")
    @Operation(summary = "ListarPorIdMascota", description = "Metodo utilizado para listar los cupones de una mascota")
    public List<CuponDTO> listarPorIdMascota(@PathVariable("id-tratamiento") Long idMascota) {
        return manejadorConsultarCupon.ejecutar(idMascota);
    }
}
