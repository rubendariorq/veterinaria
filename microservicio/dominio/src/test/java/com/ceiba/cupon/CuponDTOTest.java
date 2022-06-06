package com.ceiba.cupon;

import com.ceiba.cupon.modelo.dto.CuponDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CuponDTOTest {

    @Test
    void deberiaCrearCuponCorrectamente() {
        CuponDTO cupon = new CuponDTOTestDataBuilder()
                .conId(1l)
                .conCodigoCupon("HarryWELCOME")
                .conFechaVigencia(LocalDate.of(2022, 05, 27))
                .conValorDescuento(0.10D)
                .conIdMascota(1l)
                .crear();

        Assertions.assertEquals(1l, cupon.getId());
        Assertions.assertEquals("HarryWELCOME", cupon.getCodigoCupon());
        Assertions.assertEquals("2022-05-27", cupon.getFechaVigencia().toString());
        Assertions.assertEquals(0.10D, cupon.getValorDescuento());
    }
}
