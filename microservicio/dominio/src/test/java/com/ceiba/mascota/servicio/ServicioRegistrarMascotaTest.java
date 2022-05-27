package com.ceiba.mascota.servicio;

import com.ceiba.mascota.MascotaTestDataBuilder;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.modelo.entidad.SolicitudRegistrarMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.servicio.ServicioTestDataBuilder;
import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.servicio.modelo.entidad.SolicitudRegistrarServicio;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import com.ceiba.servicio.servicio.ServicioRegistrarServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioRegistrarMascotaTest {

    @Test
    void deberiaCrearMascotaCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conMascotaPorDefecto()
                .reconstruir();

        SolicitudRegistrarMascota solicitudRegistrarMascota = new SolicitudRegistrarMascota(mascota,null);
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.guardar(Mockito.any(), Mockito.any())).thenReturn(new MascotaTestDataBuilder().conMascotaPorDefecto().reconstruir());

        ServicioRegistrarMascota servicioRegistrarMascota = new ServicioRegistrarMascota(repositorioMascota);
        Mascota mascotaResp = servicioRegistrarMascota.ejecutar(solicitudRegistrarMascota);

        Assertions.assertEquals(1l, mascotaResp.getId());
        Assertions.assertEquals("MASC001", mascotaResp.getCodigoMascota());
        Assertions.assertEquals("Manotas", mascotaResp.getNombre());
        Assertions.assertEquals(1l, mascotaResp.getTipoMascota());
    }
}
