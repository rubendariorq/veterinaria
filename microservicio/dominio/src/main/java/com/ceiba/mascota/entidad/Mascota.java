package com.ceiba.mascota.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class Mascota {

    private static final Long TIPO_MASCOTA_GATO = 1L;
    private static final Long TIPO_MASCOTA_PERRO = 2L;

    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;

    public Mascota (Mascota mascota) {
        this.codigoMascota = mascota.getCodigoMascota();
        this.nombre = mascota.getNombre();
        this.tipoMascota = mascota.getTipoMascota();
    }

    public Mascota(String codigoMascota, String nombre, Long tipoMascota) {
        this.codigoMascota = codigoMascota;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
    }

    public static Mascota crear(SolicitudRegistrarMascota solicitudRegistrarMascota) {
        ValidadorArgumento.validarObligatorio(solicitudRegistrarMascota.getMascota(), "La mascota es requerida");
        if (!TIPO_MASCOTA_GATO.equals(solicitudRegistrarMascota.getMascota().getTipoMascota())
                && !TIPO_MASCOTA_PERRO.equals(solicitudRegistrarMascota.getMascota().getTipoMascota())) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        return new Mascota(solicitudRegistrarMascota.getMascota());
    }

    public static Mascota construir(String codigoMascota, String nombre, Long tipoMascota) {
        ValidadorArgumento.validarObligatorio(codigoMascota, "El código de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(tipoMascota, "El tipo de mascota es requerido");
        if (!TIPO_MASCOTA_GATO.equals(tipoMascota) && !TIPO_MASCOTA_PERRO.equals(tipoMascota)) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        return new Mascota(codigoMascota, nombre, tipoMascota);
    }

    public Long getId() {
        return id;
    }

    public String getCodigoMascota() {
        return codigoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getTipoMascota() {
        return tipoMascota;
    }
}
