package pck_LOGICA_APLICACION.pck_Pojos;

public class Cls_Usuario {
    private String codigo_usu;
    private String nombre_usu;
    private String apepaterno_usu;
    private String apematerno_usu;
    private String contrasena_usu;

    public Cls_Usuario() {
    }

    public Cls_Usuario(String codigo_usu, String nombre_usu, String apepaterno_usu, String apematerno_usu, String contrasena_usu) {
        this.codigo_usu = codigo_usu;
        this.nombre_usu = nombre_usu;
        this.apepaterno_usu = apepaterno_usu;
        this.apematerno_usu = apematerno_usu;
        this.contrasena_usu = contrasena_usu;
    }

    public String getApematerno_usu() {
        return apematerno_usu;
    }

    public void setApematerno_usu(String apematerno_usu) {
        this.apematerno_usu = apematerno_usu;
    }

    public String getApepaterno_usu() {
        return apepaterno_usu;
    }

    public void setApepaterno_usu(String apepaterno_usu) {
        this.apepaterno_usu = apepaterno_usu;
    }

    public String getCodigo_usu() {
        return codigo_usu;
    }

    public void setCodigo_usu(String codigo_usu) {
        this.codigo_usu = codigo_usu;
    }

    public String getContrasena_usu() {
        return contrasena_usu;
    }

    public void setContrasena_usu(String contrasena_usu) {
        this.contrasena_usu = contrasena_usu;
    }

    public String getNombre_usu() {
        return nombre_usu;
    }

    public void setNombre_usu(String nombre_usu) {
        this.nombre_usu = nombre_usu;
    }
}
