package com.ceiba.mascota.consulta;

import com.ceiba.factura.modelo.dto.ResumenFacturaDTO;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarMascotas {

    private final DaoMascota daoMascota;

    public ManejadorConsultarMascotas(DaoMascota daoMascota) {
        this.daoMascota = daoMascota;
    }

    public List<MascotaDTO> ejecutar(){
        return daoMascota.listarMascotas();
    }
}
