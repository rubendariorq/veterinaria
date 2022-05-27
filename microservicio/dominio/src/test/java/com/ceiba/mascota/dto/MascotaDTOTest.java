package com.ceiba.mascota.dto;

import com.ceiba.mascota.modelo.dto.MascotaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MascotaDTOTest {

    @Test
    void deberiaCrearMascotaCorrectamente() {
        MascotaDTO mascotaDTO = new MascotaDTOTestDataBuilder()
                .conId(1l)
                .conCodigoMascota("MASC001")
                .conNombre("Manotas")
                .conTipoMascota(1l)
                .crear();

        Assertions.assertEquals(1l, mascotaDTO.getId());
        Assertions.assertEquals("MASC001", mascotaDTO.getCodigoMascota());
        Assertions.assertEquals("Manotas", mascotaDTO.getNombre());
        Assertions.assertEquals(1l, mascotaDTO.getTipoMascota());
    }
}
