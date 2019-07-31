package pck_LOGICA_APLICACION.pck_Pojos;

import java.util.Date;

public class Cls_PedidoPrestamo {
    private String codigo_prest;
    private Cls_Cliente cliente;
    private Cls_Usuario usuario;
    private Date fechaprestamo_prest;

    public Cls_PedidoPrestamo() {
    }

    public Cls_PedidoPrestamo(String codigo_prest, Cls_Cliente cliente, Cls_Usuario usuario, Date fechaprestamo_prest) {
        this.codigo_prest = codigo_prest;
        this.cliente = cliente;
        this.usuario = usuario;
        this.fechaprestamo_prest = fechaprestamo_prest;
    }

    public Cls_Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cls_Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigo_prest() {
        return codigo_prest;
    }

    public void setCodigo_prest(String codigo_prest) {
        this.codigo_prest = codigo_prest;
    }

    public Date getFechaprestamo_prest() {
        return fechaprestamo_prest;
    }

    public void setFechaprestamo_prest(Date fechaprestamo_prest) {
        this.fechaprestamo_prest = fechaprestamo_prest;
    }

    public Cls_Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Cls_Usuario usuario) {
        this.usuario = usuario;
    }
}
