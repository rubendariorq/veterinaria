package com.ceiba.tratamiento.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSolicitudIncorrecta;
import com.ceiba.tratamiento.modelo.entidad.SolicitudEliminarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.SolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ServicioTratamiento {

    private static final Long TRATAMIENTO_MEDICO = 2L;
    private static final Long DIAS_REQUERIDOS_INICIAR_TRATAMIENTO_MEDICO = 15L;
    private final RepositorioTratamiento repositorioTratamiento;

    public ServicioTratamiento(RepositorioTratamiento repositorioTratamiento) {
        this.repositorioTratamiento = repositorioTratamiento;
    }

    public Tratamiento ejecutar(SolicitudIniciarTratamiento solicitudIniciarTratamiento) {
        validarSiTieneTratamientoMedicoVigente(solicitudIniciarTratamiento.getMascota().getId(),
                solicitudIniciarTratamiento.getMascota().getNombre());

        Tratamiento ultimoTratamiento = repositorioTratamiento
                .obtenerUltimoTratamientoMedico(solicitudIniciarTratamiento.getMascota().getId(), TRATAMIENTO_MEDICO);

        if (ultimoTratamiento != null) {
            Long ultimoTratammientoEnDias = ChronoUnit.DAYS.between(ultimoTratamiento.getFechaFin(), LocalDate.now());
            if (ultimoTratammientoEnDias < DIAS_REQUERIDOS_INICIAR_TRATAMIENTO_MEDICO) {
                throw new ExcepcionSolicitudIncorrecta("La mascota " + solicitudIniciarTratamiento.getMascota().getNombre()
                        + " finalizó el día " + ultimoTratamiento.getFechaFin() + " su tratamiento médico. Recomendable esperar "
                        + (DIAS_REQUERIDOS_INICIAR_TRATAMIENTO_MEDICO - ultimoTratammientoEnDias) + " días más.");
            }
        }

        Tratamiento tratamiento = solicitudIniciarTratamiento.getTratamiento();
        return repositorioTratamiento.guardar(tratamiento);
    }

    private void validarSiTieneTratamientoMedicoVigente(Long idMascota, String nombreMascota) {
        if (!repositorioTratamiento.listarPorMascotayTipo(idMascota,
                TRATAMIENTO_MEDICO).isEmpty()) {
            throw new ExcepcionSolicitudIncorrecta("La mascota " + nombreMascota
                    + " se encuentra con un tratamiento Médico en curso.");
        }
    }

    public String eliminar(SolicitudEliminarTratamiento solicitudEliminarTratamiento) {
        return repositorioTratamiento.eliminar(solicitudEliminarTratamiento.getTratamiento());
    }
}
