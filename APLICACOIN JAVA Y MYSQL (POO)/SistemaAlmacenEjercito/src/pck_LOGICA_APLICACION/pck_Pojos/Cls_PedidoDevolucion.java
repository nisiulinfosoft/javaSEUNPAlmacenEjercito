package pck_LOGICA_APLICACION.pck_Pojos;

import java.util.Date;

public class Cls_PedidoDevolucion {
    private String codigo_devol;
    private Cls_Cliente cliente;
    private Cls_Usuario usuario;
    private Date fechadevolucion_devol;

    public Cls_PedidoDevolucion() {
    }

    public Cls_PedidoDevolucion(String codigo_devol, Cls_Cliente cliente, Cls_Usuario usuario, Date fechadevolucion_devol) {
        this.codigo_devol = codigo_devol;
        this.cliente = cliente;
        this.usuario = usuario;
        this.fechadevolucion_devol = fechadevolucion_devol;
    }

    public Cls_Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cls_Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigo_devol() {
        return codigo_devol;
    }

    public void setCodigo_devol(String codigo_devol) {
        this.codigo_devol = codigo_devol;
    }

    public Date getFechadevolucion_devol() {
        return fechadevolucion_devol;
    }

    public void setFechadevolucion_devol(Date fechadevolucion_devol) {
        this.fechadevolucion_devol = fechadevolucion_devol;
    }

    public Cls_Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Cls_Usuario usuario) {
        this.usuario = usuario;
    }
}
