package com.ceiba.cupon.consulta;

import com.ceiba.cupon.modelo.dto.CuponDTO;
import com.ceiba.cupon.puerto.dao.DaoCupon;
import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
import com.ceiba.tratamiento.puerto.dao.DaoTratamiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarCupon {

    private final DaoCupon daoCupon;

    public ManejadorConsultarCupon(DaoCupon daoCupon) {
        this.daoCupon = daoCupon;
    }

    public List<CuponDTO> ejecutar(Long idMascota){
        return daoCupon.listarPorIdMascota(idMascota);
    }
}
