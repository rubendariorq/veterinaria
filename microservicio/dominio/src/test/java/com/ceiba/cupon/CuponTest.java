package com.ceiba.cupon;

import com.ceiba.BasePrueba;
import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.MascotaTestDataBuilder;
import com.ceiba.mascota.modelo.entidad.Mascota;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

class CuponTest {

    private static final int DIAS_HABILES_VIGENCIA_CUPON = 3;

    @Test
    void deberiaCrearCuponCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();

        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        Assertions.assertEquals("HarryWELCOME", cupon.getCodigoCupon());
        Assertions.assertEquals(calcularFecha(), cupon.getFechaVigencia());
        Assertions.assertEquals(0.10D, cupon.getValorDescuento());
    }

    @Test
    void deberiaReconstruirCuponCorrectamente() {
        Cupon cupon = new CuponTestDataBuilder()
                .conId(1l)
                .conCodigoCupon("HarryWELCOME")
                .conFechaVigencia(LocalDate.of(2022, 05, 27))
                .conValorDescuento(0.10D)
                .reconstruir();

        Assertions.assertEquals(1l, cupon.getId());
        Assertions.assertEquals("HarryWELCOME", cupon.getCodigoCupon());
        Assertions.assertEquals("2022-05-27", cupon.getFechaVigencia().toString());
        Assertions.assertEquals(0.10D, cupon.getValorDescuento());
    }

    @Test
    void deberiaFallarCreacionSinMascota() {
        BasePrueba.assertThrows(() -> new CuponTestDataBuilder()
                        .conId(1l)
                        .crear(),
                ExcepcionValorObligatorio.class, "La mascota es requerida");
    }

    @Test
    void deberiaFallarReconstruccionSinId() {
        BasePrueba.assertThrows(() -> new CuponTestDataBuilder()

                .conFechaVigencia(LocalDate.of(2022,05,27))
                .conValorDescuento(0.10D)
                .reconstruir(),
                ExcepcionValorObligatorio.class, "El id del cupón es requerido");
    }

    @Test
    void deberiaFallarReconstruccionSinCodigoCupon() {
        BasePrueba.assertThrows(() -> new CuponTestDataBuilder()
                        .conId(1l)
                        .conFechaVigencia(LocalDate.of(2022,05,27))
                        .conValorDescuento(0.10D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El código del cupón es requerido");
    }

    @Test
    void deberiaFallarReconstruccionSinFechaVigencia() {
        BasePrueba.assertThrows(() -> new CuponTestDataBuilder()
                        .conId(1l)
                        .conCodigoCupon("HarryWELCOME")
                        .conValorDescuento(0.10D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "La fecha de vigencia del cupón es requerida");
    }

    @Test
    void deberiaFallarReconstruccionSinValorDescuento() {
        BasePrueba.assertThrows(() -> new CuponTestDataBuilder()
                        .conId(1l)
                        .conCodigoCupon("HarryWELCOME")
                        .conFechaVigencia(LocalDate.of(2022,05,27))
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El valor de descuento del cupón es requerido");
    }

    private LocalDate calcularFecha() {
        LocalDate result = LocalDate.now();
        int diaAgregado = 0;
        while (diaAgregado < DIAS_HABILES_VIGENCIA_CUPON) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diaAgregado;
            }
        }
        return result;
    }
}
