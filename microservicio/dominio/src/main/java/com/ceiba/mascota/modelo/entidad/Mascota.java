package com.ceiba.mascota.modelo.entidad;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.util.ArrayList;
import java.util.List;

public class Mascota {

    private static final Long TIPO_MASCOTA_GATO = 1L;
    private static final Long TIPO_MASCOTA_PERRO = 2L;

    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;

    private List<Cupon> cupones;

    private Mascota (Mascota mascota, List<Cupon> cupon) {
        this.codigoMascota = mascota.getCodigoMascota();
        this.nombre = mascota.getNombre();
        this.tipoMascota = mascota.getTipoMascota();
        this.cupones = cupon;
    }

    private Mascota(Long id, String codigoMascota, String nombre, Long tipoMascota, List<Cupon> cupones) {
        this.id = id;
        this.codigoMascota = codigoMascota;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.cupones = cupones;
    }

    public static Mascota crear(SolicitudRegistrarMascota solicitudRegistrarMascota) {
        ValidadorArgumento.validarObligatorio(solicitudRegistrarMascota.getMascota(), "La mascota es requerida");
        if (!TIPO_MASCOTA_GATO.equals(solicitudRegistrarMascota.getMascota().getTipoMascota())
                && !TIPO_MASCOTA_PERRO.equals(solicitudRegistrarMascota.getMascota().getTipoMascota())) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        List<Cupon> cupones = new ArrayList<>();
        cupones.add(Cupon.crear(solicitudRegistrarMascota.getMascota()));
        return new Mascota(solicitudRegistrarMascota.getMascota(), cupones);
    }

    public static Mascota construir(String codigoMascota, String nombre, Long tipoMascota) {
        ValidadorArgumento.validarObligatorio(codigoMascota, "El código de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(tipoMascota, "El tipo de mascota es requerido");
        if (!TIPO_MASCOTA_GATO.equals(tipoMascota) && !TIPO_MASCOTA_PERRO.equals(tipoMascota)) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        return new Mascota(null, codigoMascota, nombre, tipoMascota, null);
    }

    public static Mascota reconstruir(Long id, String codigoMascota, String nombre, Long tipoMascota, List<Cupon> cupones) {
        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        ValidadorArgumento.validarObligatorio(codigoMascota, "El código de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(tipoMascota, "El tipo de mascota es requerido");
        if (!TIPO_MASCOTA_GATO.equals(tipoMascota) && !TIPO_MASCOTA_PERRO.equals(tipoMascota)) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        return new Mascota(id, codigoMascota, nombre, tipoMascota, cupones);
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

    public List<Cupon> getCupones() {
        return cupones;
    }
}
