package com.ceiba.servicio.consulta;

import com.ceiba.servicio.modelo.dto.ServicioDTO;
import com.ceiba.servicio.puerto.dao.DaoServicio;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarServicios {

    private final DaoServicio daoServicio;

    public ManejadorConsultarServicios(DaoServicio daoServicio) {
        this.daoServicio = daoServicio;
    }

    public List<ServicioDTO> ejecutar(){
        return daoServicio.listarServicios();
    }
}
