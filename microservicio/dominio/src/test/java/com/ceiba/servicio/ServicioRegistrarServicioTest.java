package com.ceiba.servicio;

import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.servicio.modelo.entidad.SolicitudRegistrarServicio;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import com.ceiba.servicio.servicio.ServicioRegistrarServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioRegistrarServicioTest {

    @Test
    void deberiaCrearServicioCorrectamente() {
        Servicio servicio = new ServicioTestDataBuilder()
                .conServicioPorDefecto()
                .reconstruir();

        SolicitudRegistrarServicio solicitudRegistrarServicio = new SolicitudRegistrarServicio(servicio);
        RepositorioServicio repositorioServicio = Mockito.mock(RepositorioServicio.class);
        Mockito.when(repositorioServicio.guardar(Mockito.any())).thenReturn(new ServicioTestDataBuilder().conServicioPorDefecto().reconstruir());

        ServicioRegistrarServicio servicioRegistrarServicio = new ServicioRegistrarServicio(repositorioServicio);
        Servicio servicioResp = servicioRegistrarServicio.ejecutar(solicitudRegistrarServicio);

        Assertions.assertEquals(1l, servicioResp.getId());
        Assertions.assertEquals("Servicio Tipo Medico", servicioResp.getDescripcion());
        Assertions.assertEquals(50000D, servicioResp.getValor());
    }
}
