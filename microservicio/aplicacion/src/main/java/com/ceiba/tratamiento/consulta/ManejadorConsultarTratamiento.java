package com.ceiba.tratamiento.consulta;

import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
import com.ceiba.tratamiento.puerto.dao.DaoTratamiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarTratamiento {
    private final DaoTratamiento daoTratamiento;

    public ManejadorConsultarTratamiento(DaoTratamiento daoTratamiento) {
        this.daoTratamiento = daoTratamiento;
    }

    public List<TratamientoDTO> ejecutar(){
        return daoTratamiento.listarTratamientos();
    }
}
