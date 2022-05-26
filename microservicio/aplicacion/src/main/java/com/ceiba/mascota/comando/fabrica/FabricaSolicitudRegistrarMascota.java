package com.ceiba.mascota.comando.fabrica;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.modelo.entidad.SolicitudRegistrarMascota;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudRegistrarMascota {

    public SolicitudRegistrarMascota crear(ComandoSolicitudRegistrarMascota comando) {
        return new SolicitudRegistrarMascota(Mascota.crear(comando.getCodigoMascota(),
                comando.getNombre(), comando.getTipoMascota()), Cupon.crear(comando.getNombre()));
    }


}
