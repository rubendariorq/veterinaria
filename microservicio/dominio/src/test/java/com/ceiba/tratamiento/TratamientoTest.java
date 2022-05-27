package com.ceiba.tratamiento;

import com.ceiba.BasePrueba;
import com.ceiba.cupon.CuponTestDataBuilder;
import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.dominio.excepcion.ExcepcionSolicitudIncorrecta;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.MascotaTestDataBuilder;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.ServicioTestDataBuilder;
import com.ceiba.servicio.entidad.Servicio;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TratamientoTest {

    @Test
    void deberiaCraerTratamientoConRecargoyDescuentoCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conMascota(mascota)
                .conServicio(servicio)
                .conCupon(cupon)
                .conCodigoTratamiento("TRAT001")
                .conTipoTratamiento(1l)
                .conFechaInicio(LocalDate.of(2022,05,30))
                .conFechaFin(LocalDate.of(2022,06,06))
                .crear();

        Assertions.assertEquals("TRAT001", tratamiento.getCodigoTratamiento());
        Assertions.assertEquals(1l, tratamiento.getTipoTratamiento());
        Assertions.assertEquals("2022-05-30", tratamiento.getFechaInicio().toString());
        Assertions.assertEquals("2022-06-06", tratamiento.getFechaFin().toString());
        Assertions.assertEquals((45900*(1+0.15)*(1-0.10)), tratamiento.getValor());
    }

    @Test
    void deberiaCraerTratamientoConRecargoCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conMascota(mascota)
                .conServicio(servicio)
                .conCodigoTratamiento("TRAT001")
                .conTipoTratamiento(1l)
                .conFechaInicio(LocalDate.of(2022,05,30))
                .conFechaFin(LocalDate.of(2022,06,06))
                .crear();

        Assertions.assertEquals("TRAT001", tratamiento.getCodigoTratamiento());
        Assertions.assertEquals(1l, tratamiento.getTipoTratamiento());
        Assertions.assertEquals("2022-05-30", tratamiento.getFechaInicio().toString());
        Assertions.assertEquals("2022-06-06", tratamiento.getFechaFin().toString());
        Assertions.assertEquals((45900*(1+0.15)), tratamiento.getValor());
    }

    @Test
    void deberiaCraerTratamientoConDescuentoCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conMascota(mascota)
                .conServicio(servicio)
                .conCupon(cupon)
                .conCodigoTratamiento("TRAT001")
                .conTipoTratamiento(1l)
                .conFechaInicio(LocalDate.of(2022,05,31))
                .conFechaFin(LocalDate.of(2022,06,06))
                .crear();

        Assertions.assertEquals("TRAT001", tratamiento.getCodigoTratamiento());
        Assertions.assertEquals(1l, tratamiento.getTipoTratamiento());
        Assertions.assertEquals("2022-05-31", tratamiento.getFechaInicio().toString());
        Assertions.assertEquals("2022-06-06", tratamiento.getFechaFin().toString());
        Assertions.assertEquals((45900*(1-0.10)), tratamiento.getValor());
    }

    @Test
    void deberiaCraerTratamientoSinRecargoSinDescuentoCorrectamente() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        Tratamiento tratamiento = new TratamientoTestDataBuilder()
                .conMascota(mascota)
                .conServicio(servicio)
                .conCodigoTratamiento("TRAT001")
                .conTipoTratamiento(1l)
                .conFechaInicio(LocalDate.of(2022,05,31))
                .conFechaFin(LocalDate.of(2022,06,06))
                .crear();

        Assertions.assertEquals("TRAT001", tratamiento.getCodigoTratamiento());
        Assertions.assertEquals(1l, tratamiento.getTipoTratamiento());
        Assertions.assertEquals("2022-05-31", tratamiento.getFechaInicio().toString());
        Assertions.assertEquals("2022-06-06", tratamiento.getFechaFin().toString());
        Assertions.assertEquals((45900), tratamiento.getValor());
    }

    @Test
    void deberiaFallarCuandoFechaInicioEsDiaNoLaboral() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                .conMascota(mascota)
                .conServicio(servicio)
                .conCodigoTratamiento("TRAT001")
                .conTipoTratamiento(1l)
                .conFechaInicio(LocalDate.of(2022,05,29))
                .conFechaFin(LocalDate.of(2022,06,06))
                .crear(),
                ExcepcionValorInvalido.class, "La fecha de inicio indicada es un día no laboral");
    }

    @Test
    void deberiaFallarCuandoFechaFinEsDiaNoLaboral() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,31))
                        .conFechaFin(LocalDate.of(2022,06,05))
                        .crear(),
                ExcepcionValorInvalido.class, "La fecha de finalización indicada es un día no laboral");
    }

    @Test
    void deberiaFallarCreacionTratamientoSinMascota() {
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                .conServicio(servicio)
                .conCodigoTratamiento("TRAT001")
                .conTipoTratamiento(1l)
                .conFechaInicio(LocalDate.of(2022,05,30))
                .conFechaFin(LocalDate.of(2022,06,06))
                .crear(),
                ExcepcionValorObligatorio.class, "La mascota es requerida");
    }

    @Test
    void deberiaFallarCreacionTratamientoSinServicio() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conCupon(cupon)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .crear(),
                ExcepcionValorObligatorio.class, "El servicio es requerido");
    }

    @Test
    void deberiaFallarCreacionTratamientoSinCodigoTratamiento() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCupon(cupon)
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .crear(),
                ExcepcionValorObligatorio.class, "El código del tratamiento es requerido");
    }

    @Test
    void deberiaFallarCreacionTratamientoSinFechaInicio() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCupon(cupon)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .crear(),
                ExcepcionValorObligatorio.class, "Debe indicar una fecha de inicio");
    }

    @Test
    void deberiaFallarCreacionTratamientoSinFechaFin() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCupon(cupon)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .crear(),
                ExcepcionValorObligatorio.class, "Debe indicar una fecha de finalización");
    }

    @Test
    void deberiaFallarCreacionTratamientoSinTipoTratamiento() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .crear(),
                ExcepcionValorObligatorio.class, "Debe indicar el tipo de tratamiento");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinId() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El id de la mascota es requerido");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinMascota() {
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "La mascota es requerida");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinServicio() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conMascota(mascota)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El servicio es requerido");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinCodigoTratamiento() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "El código del tratamiento es requerido");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinFechaInicio() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "Debe indicar una fecha de inicio");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinFechaFin() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "Debe indicar una fecha de finalización");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinTipoTratamiento() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .conValor(45900D)
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "Debe indicar el tipo de tratamiento");
    }

    @Test
    void deberiaFallarReconstruccionTratamientoSinValor() {
        Mascota mascota = new MascotaTestDataBuilder()
                .conNombre("Harry")
                .conTipoMascota(2l)
                .conCodigoMascota("MASC0001")
                .crear();
        Servicio servicio = new ServicioTestDataBuilder()
                .conId(1l)
                .conDescripcion("Servicio Tipo Medico")
                .conValor(45900D)
                .reconstruir();
        Cupon cupon = new CuponTestDataBuilder()
                .conMascota(mascota)
                .crear();

        BasePrueba.assertThrows(() -> new TratamientoTestDataBuilder()
                        .conId(1l)
                        .conMascota(mascota)
                        .conServicio(servicio)
                        .conCodigoTratamiento("TRAT001")
                        .conTipoTratamiento(1l)
                        .conFechaInicio(LocalDate.of(2022,05,30))
                        .conFechaFin(LocalDate.of(2022,06,06))
                        .reconstruir(),
                ExcepcionValorObligatorio.class, "Debe indicar el valor del tratamiento");
    }

}
