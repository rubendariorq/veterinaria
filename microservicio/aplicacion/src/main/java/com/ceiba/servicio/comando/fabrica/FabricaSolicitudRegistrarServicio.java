package com.ceiba.servicio.comando.fabrica;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.modelo.entidad.SolicitudRegistrarMascota;
import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;
import com.ceiba.servicio.entidad.Servicio;
import com.ceiba.servicio.entidad.SolicitudRegistrarServicio;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudRegistrarServicio {

    public SolicitudRegistrarServicio crear(ComandoSolicitudRegistrarServicio comando) {
        Servicio servicio = Servicio.crear(comando.getDescripcion(), comando.getValor());
        return new SolicitudRegistrarServicio(servicio);
    }
}
