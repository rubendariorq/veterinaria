package com.ceiba.mascota;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.modelo.entidad.Mascota;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MascotaTest {

    @Test
    void deberiaCrearMascotaCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conCodigoMascota("MASC001")
                .conNombre("Manotas")
                .conTipoMascota(1l)
                .crear();

        Assertions.assertEquals("MASC001", mascota.getCodigoMascota());
        Assertions.assertEquals("Manotas", mascota.getNombre());
        Assertions.assertEquals(1l, mascota.getTipoMascota());
    }

    @Test
    void deberiaFallarCrearMascotaConTipoMascotaNoValido() {
        BasePrueba.assertThrows(() -> new MascotaTestDataBuilder()
                .conCodigoMascota("MASC001")
                .conNombre("Manotas")
                .conTipoMascota(3l)
                .crear(),
                ExcepcionValorInvalido.class, "Tipo de mascota no permitido en la veterinaria");
    }

    @Test
    void deberiaReconstruirMascotaCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conId(1l)
                .conCodigoMascota("MASC001")
                .conNombre("Manotas")
                .conTipoMascota(1l)
                .reconstruir();

        Assertions.assertEquals(1l, mascota.getId());
        Assertions.assertEquals("MASC001", mascota.getCodigoMascota());
        Assertions.assertEquals("Manotas", mascota.getNombre());
        Assertions.assertEquals(1l, mascota.getTipoMascota());
    }

    @Test
    void deberiaFallarReconstruccionSinId() {
        BasePrueba.assertThrows(() -> new MascotaTestDataBuilder()
                        .conCodigoMascota("MASC001")
                        .conNombre("Manotas")
                        .conTipoMascota(1l)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El id de la mascota es requerido");
    }

    @Test
    void deberiaFallarReconstruccionSinCodigoMascota() {
        BasePrueba.assertThrows(() -> new MascotaTestDataBuilder()
                        .conId(1l)
                        .conNombre("Manotas")
                        .conTipoMascota(1l)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El código de la mascota es requerido");
    }

    @Test
    void deberiaFallarReconstruccionSinNombre() {
        BasePrueba.assertThrows(() -> new MascotaTestDataBuilder()
                        .conId(1l)
                        .conCodigoMascota("MASC001")
                        .conTipoMascota(1l)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El nombre de la mascota es requerido");
    }

    @Test
    void deberiaFallarReconstruccionSinTipoMascota() {
        BasePrueba.assertThrows(() -> new MascotaTestDataBuilder()
                        .conId(1l)
                        .conCodigoMascota("MASC001")
                        .conNombre("Manotas")
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El tipo de mascota es requerido");
    }
}
