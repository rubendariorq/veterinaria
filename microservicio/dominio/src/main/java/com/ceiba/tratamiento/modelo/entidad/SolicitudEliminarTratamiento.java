package com.ceiba.tratamiento.modelo.entidad;

public class SolicitudEliminarTratamiento {
    private Tratamiento tratamiento;

    public SolicitudEliminarTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }
}
