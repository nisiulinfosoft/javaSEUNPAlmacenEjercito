package pck_LOGICA_APLICACION.pck_Pojos;

import java.util.Date;

public class Cls_Cliente {
    private String codigo_cli;
    private Cls_Unidad unidad;
    private String nombre_cli;
    private String apepaterno_cli;
    private String apematerno_cli;
    private String sexo_cli;
    private Date fechanacimiento_cli;
    private Date fechaingreso_cli;
    private String telefono_cli;
    private String celular_cli;
    private String email_cli;
    private String calle_cli;
    private int numerocasa_cli;
    private String barrio_cli;
    private String observacion_cli;

    public Cls_Cliente() {
    }

    public Cls_Cliente(String codigo_cli, Cls_Unidad unidad, String nombre_cli, String apepaterno_cli, String apematerno_cli, String sexo_cli, Date fechanacimiento_cli, Date fechaingreso_cli, String telefono_cli, String celular_cli, String email_cli, String calle_cli, int numerocasa_cli, String barrio_cli, String observacion_cli) {
        this.codigo_cli = codigo_cli;
        this.unidad = unidad;
        this.nombre_cli = nombre_cli;
        this.apepaterno_cli = apepaterno_cli;
        this.apematerno_cli = apematerno_cli;
        this.sexo_cli = sexo_cli;
        this.fechanacimiento_cli = fechanacimiento_cli;
        this.fechaingreso_cli = fechaingreso_cli;
        this.telefono_cli = telefono_cli;
        this.celular_cli = celular_cli;
        this.email_cli = email_cli;
        this.calle_cli = calle_cli;
        this.numerocasa_cli = numerocasa_cli;
        this.barrio_cli = barrio_cli;
        this.observacion_cli = observacion_cli;
    }

    public String getBarrio_cli() {
        return barrio_cli;
    }

    public void setBarrio_cli(String barrio_cli) {
        this.barrio_cli = barrio_cli;
    }

    public String getApematerno_cli() {
        return apematerno_cli;
    }

    public void setApematerno_cli(String apematerno_cli) {
        this.apematerno_cli = apematerno_cli;
    }

    public String getApepaterno_cli() {
        return apepaterno_cli;
    }

    public void setApepaterno_cli(String apepaterno_cli) {
        this.apepaterno_cli = apepaterno_cli;
    }

    public String getCalle_cli() {
        return calle_cli;
    }

    public void setCalle_cli(String calle_cli) {
        this.calle_cli = calle_cli;
    }

    public String getCelular_cli() {
        return celular_cli;
    }

    public void setCelular_cli(String celular_cli) {
        this.celular_cli = celular_cli;
    }

    public String getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(String codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getEmail_cli() {
        return email_cli;
    }

    public void setEmail_cli(String email_cli) {
        this.email_cli = email_cli;
    }

    public Date getFechaingreso_cli() {
        return fechaingreso_cli;
    }

    public void setFechaingreso_cli(Date fechaingreso_cli) {
        this.fechaingreso_cli = fechaingreso_cli;
    }

    public Date getFechanacimiento_cli() {
        return fechanacimiento_cli;
    }

    public void setFechanacimiento_cli(Date fechanacimiento_cli) {
        this.fechanacimiento_cli = fechanacimiento_cli;
    }

    public String getNombre_cli() {
        return nombre_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        this.nombre_cli = nombre_cli;
    }

    public int getNumerocasa_cli() {
        return numerocasa_cli;
    }

    public void setNumerocasa_cli(int numerocasa_cli) {
        this.numerocasa_cli = numerocasa_cli;
    }

    public String getObservacion_cli() {
        return observacion_cli;
    }

    public void setObservacion_cli(String observacion_cli) {
        this.observacion_cli = observacion_cli;
    }

    public String getSexo_cli() {
        return sexo_cli;
    }

    public void setSexo_cli(String sexo_cli) {
        this.sexo_cli = sexo_cli;
    }

    public String getTelefono_cli() {
        return telefono_cli;
    }

    public void setTelefono_cli(String telefono_cli) {
        this.telefono_cli = telefono_cli;
    }

    public Cls_Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Cls_Unidad unidad) {
        this.unidad = unidad;
    }
}
