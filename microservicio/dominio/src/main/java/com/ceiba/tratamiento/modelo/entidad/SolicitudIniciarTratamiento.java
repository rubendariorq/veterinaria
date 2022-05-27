package com.ceiba.tratamiento.modelo.entidad;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.modelo.entidad.Servicio;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudIniciarTratamiento {
    private Mascota mascota;
    private Servicio servicio;
    private Tratamiento tratamiento;
    private Cupon cupon;
}
