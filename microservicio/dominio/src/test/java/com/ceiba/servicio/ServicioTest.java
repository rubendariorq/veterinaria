package com.ceiba.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.servicio.entidad.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServicioTest {

    @Test
    void deberiaCrearServicioExitoso() {

        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        Assertions.assertEquals(1l, servicio.getId());
        Assertions.assertEquals("Servicio Tipo Medico", servicio.getDescripcion());
        Assertions.assertEquals(45900D, servicio.getValor());
    }

    @Test
    void reconstruirServicioSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ServicioTestDataBuilder()
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir(), ExcepcionValorObligatorio.class,
                "El id del servicio es requerido");
    }

    @Test
    void reconstruirServicioSinDescripcionDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ServicioTestDataBuilder()
                        .conId(1l)
                        .conValor(45900D)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "La descripción del servicio es requerida");
    }

    @Test
    void reconstruirServicioSinValorDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ServicioTestDataBuilder()
                        .conId(1l)
                        .conDescripcion("Servicio Tipo Medico")
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El valor del servicio es requerido");
    }
}
