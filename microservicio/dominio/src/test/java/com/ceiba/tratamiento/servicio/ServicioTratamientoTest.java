package com.ceiba.tratamiento.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSolicitudIncorrecta;
import com.ceiba.tratamiento.TratamientoTestDataBuilder;
import com.ceiba.tratamiento.modelo.entidad.SolicitudEliminarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ServicioTratamientoTest {

    @Test
    void deberiaCrearTratamientoCorrectamente() {
        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conTratamientoPorDefecto()
                .reconstruir();

        SolicitudIniciarTratamiento solicitudIniciarTratamiento = new SolicitudIniciarTratamiento(tratamiento.getMascota(), tratamiento.getServicio(), tratamiento, null);
        RepositorioTratamiento repositorioTratamiento = Mockito.mock(RepositorioTratamiento.class);
        Mockito.when(repositorioTratamiento.guardar(Mockito.any())).thenReturn(new TratamientoTestDataBuilder().conTratamientoPorDefecto().reconstruir());

        ServicioTratamiento servicioTratamiento = new ServicioTratamiento(repositorioTratamiento);
        Tratamiento tratamientoResp = servicioTratamiento.ejecutar(solicitudIniciarTratamiento);

        Assertions.assertEquals(1l, tratamientoResp.getId());
        Assertions.assertEquals("TRAT001", tratamientoResp.getCodigoTratamiento());
        Assertions.assertEquals("2022-06-01", tratamientoResp.getFechaInicio().toString());
        Assertions.assertEquals("2022-06-04", tratamientoResp.getFechaFin().toString());
        Assertions.assertEquals(2l, tratamientoResp.getTipoTratamiento());
        Assertions.assertEquals(49000D, tratamientoResp.getValor());
    }

    @Test
    void deberiaEliminarTratamientoCorrectamente() {
        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conTratamientoPorDefecto()
                .reconstruir();
        SolicitudEliminarTratamiento solicitudEliminarTratamiento = new SolicitudEliminarTratamiento(tratamiento);


        RepositorioTratamiento repositorioTratamiento = Mockito.mock(RepositorioTratamiento.class);
        ServicioTratamiento servicioTratamiento = new ServicioTratamiento(repositorioTratamiento);

        servicioTratamiento.eliminar(solicitudEliminarTratamiento);
        Mockito.verify(repositorioTratamiento, Mockito.times(1)).eliminar(tratamiento);
    }

    @Test
    void deberiaLanzarExcepcionValidandoSiRegistraTratamientoMedicoConUnoVigente() {
        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conTratamientoPorDefecto()
                .reconstruir();

        SolicitudIniciarTratamiento solicitudIniciarTratamiento = new SolicitudIniciarTratamiento(tratamiento.getMascota(), tratamiento.getServicio(), tratamiento, null);
        RepositorioTratamiento repositorioTratamiento = Mockito.mock(RepositorioTratamiento.class);

        List<Tratamiento> tratamientos = new ArrayList<>();
        tratamientos.add(new TratamientoTestDataBuilder().conTratamientoPorDefecto().reconstruir());
        Mockito.when(repositorioTratamiento.listarPorMascotayTipo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(tratamientos);

        ServicioTratamiento servicioTratamiento = new ServicioTratamiento(repositorioTratamiento);
        BasePrueba.assertThrows(() -> servicioTratamiento.ejecutar(solicitudIniciarTratamiento),
                ExcepcionSolicitudIncorrecta.class, "La mascota Manotas se encuentra con un tratamiento Médico en curso.");
    }

    @Test
    void deberiaLanzarExcepcionValidandoSiRegistraTratamientoMedicoFinalizadoRecientementeOtro() {
        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conTratamientoPorDefecto()
                .reconstruir();

        SolicitudIniciarTratamiento solicitudIniciarTratamiento = new SolicitudIniciarTratamiento(tratamiento.getMascota(), tratamiento.getServicio(), tratamiento, null);
        RepositorioTratamiento repositorioTratamiento = Mockito.mock(RepositorioTratamiento.class);

        Mockito.when(repositorioTratamiento.obtenerUltimoTratamientoMedico(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(new TratamientoTestDataBuilder()
                        .conId(2l)
                        .conCodigoTratamiento(tratamiento.getCodigoTratamiento())
                        .conFechaInicio(LocalDate.of(2022,05,15))
                        .conFechaFin(LocalDate.of(2022,05,30))
                        .conTipoTratamiento(tratamiento.getTipoTratamiento())
                        .conMascota(tratamiento.getMascota())
                        .conServicio(tratamiento.getServicio())
                        .conValor(tratamiento.getValor())
                        .reconstruir()
                );

        ServicioTratamiento servicioTratamiento = new ServicioTratamiento(repositorioTratamiento);
        BasePrueba.assertThrows(() -> servicioTratamiento.ejecutar(solicitudIniciarTratamiento),
                ExcepcionSolicitudIncorrecta.class, "La mascota Manotas finalizó el día 2022-05-30 su tratamiento médico. Recomendable esperar 13 días más");
    }
}
