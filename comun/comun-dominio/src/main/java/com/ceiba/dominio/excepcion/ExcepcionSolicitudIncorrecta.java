package com.ceiba.dominio.excepcion;

public class ExcepcionSolicitudIncorrecta extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionSolicitudIncorrecta(String message) {
        super(message);
    }
}
