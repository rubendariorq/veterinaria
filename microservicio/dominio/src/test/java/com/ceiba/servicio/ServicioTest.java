package com.ceiba.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.servicio.entidad.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServicioTest {

    @Test
    void deberiaCrearServicioCorrectamente() {
        Servicio servicio = new ServicioTestDataBuilder()
                .conDescripcion("Servicio Tipo Medico")
                .conValor(50000D)
                .crear();

        Assertions.assertEquals("Servicio Tipo Medico", servicio.getDescripcion());
        Assertions.assertEquals(50000D, servicio.getValor());
    }

    @Test
    void deberiaFallarCreacionServicioSinDescripcion() {
        BasePrueba.assertThrows(() -> new ServicioTestDataBuilder()
                        .conValor(45900D)
                        .crear(), ExcepcionValorObligatorio.class,
                "La descripción del servicio es requerida");
    }

    @Test
    void deberiaFallarCreacionServicioSinValor() {
        BasePrueba.assertThrows(() -> new ServicioTestDataBuilder()
                        .conDescripcion("Servicio Tipo Medico")
                        .crear(), ExcepcionValorObligatorio.class,
                "El valor del servicio es requerido");
    }

    @Test
    void deberiaReconstruirServicioExitoso() {

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
