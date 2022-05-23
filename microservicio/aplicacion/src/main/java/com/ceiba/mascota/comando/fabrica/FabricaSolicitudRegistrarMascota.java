package com.ceiba.mascota.comando.fabrica;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.modelo.entidad.SolicitudRegistrarMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudRegistrarMascota {

    private final RepositorioMascota repositorioMascota;

    public FabricaSolicitudRegistrarMascota(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    public SolicitudRegistrarMascota crear(ComandoSolicitudRegistrarMascota comando) {
        return new SolicitudRegistrarMascota(Mascota.crear(comando.getCodigoMascota(),
                comando.getNombre(), comando.getTipoMascota()), Cupon.crear(comando.getNombre()));
    }


}
