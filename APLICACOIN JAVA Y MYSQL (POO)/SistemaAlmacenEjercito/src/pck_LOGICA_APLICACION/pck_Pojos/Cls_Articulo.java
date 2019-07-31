package pck_LOGICA_APLICACION.pck_Pojos;

import java.util.Date;

public class Cls_Articulo {
    private String codigo_art;
    private String nombre_art;
    private Date fechaingreso_art;
    private int cantidad_igreso_art;
    private int cantidad_internada_art;
    private int cantidad_existente_art;
    private String descripcion_art;

    public Cls_Articulo() {
    }

    public Cls_Articulo(String codigo_art, String nombre_art, Date fechaingreso_art, int cantidad_igreso_art, int cantidad_internada_art, int cantidad_existente_art, String descripcion_art) {
        this.codigo_art = codigo_art;
        this.nombre_art = nombre_art;
        this.fechaingreso_art = fechaingreso_art;
        this.cantidad_igreso_art = cantidad_igreso_art;
        this.cantidad_internada_art = cantidad_internada_art;
        this.cantidad_existente_art = cantidad_existente_art;
        this.descripcion_art = descripcion_art;
    }

    public int getCantidad_existente_art() {
        return cantidad_existente_art;
    }

    public void setCantidad_existente_art(int cantidad_existente_art) {
        this.cantidad_existente_art = cantidad_existente_art;
    }

    public int getCantidad_igreso_art() {
        return cantidad_igreso_art;
    }

    public void setCantidad_igreso_art(int cantidad_igreso_art) {
        this.cantidad_igreso_art = cantidad_igreso_art;
    }

    public int getCantidad_internada_art() {
        return cantidad_internada_art;
    }

    public void setCantidad_internada_art(int cantidad_internada_art) {
        this.cantidad_internada_art = cantidad_internada_art;
    }

    public String getCodigo_art() {
        return codigo_art;
    }

    public void setCodigo_art(String codigo_art) {
        this.codigo_art = codigo_art;
    }

    public String getDescripcion_art() {
        return descripcion_art;
    }

    public void setDescripcion_art(String descripcion_art) {
        this.descripcion_art = descripcion_art;
    }

    public Date getFechaingreso_art() {
        return fechaingreso_art;
    }

    public void setFechaingreso_art(Date fechaingreso_art) {
        this.fechaingreso_art = fechaingreso_art;
    }

    public String getNombre_art() {
        return nombre_art;
    }

    public void setNombre_art(String nombre_art) {
        this.nombre_art = nombre_art;
    }
}
