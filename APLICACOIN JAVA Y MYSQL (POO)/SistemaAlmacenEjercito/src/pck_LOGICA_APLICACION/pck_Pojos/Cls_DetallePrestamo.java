package pck_LOGICA_APLICACION.pck_Pojos;

public class Cls_DetallePrestamo {
    private Cls_Articulo articulo;
    private Cls_PedidoPrestamo prestamo;
    private int cantidad_prestada_prest;
    private int cantidad_devuelta_prest;

    public Cls_DetallePrestamo() {
    }

    public Cls_DetallePrestamo(Cls_Articulo articulo, Cls_PedidoPrestamo prestamo, int cantidad_prestada_prest, int cantidad_devuelta_prest) {
        this.articulo = articulo;
        this.prestamo = prestamo;
        this.cantidad_prestada_prest = cantidad_prestada_prest;
        this.cantidad_devuelta_prest = cantidad_devuelta_prest;
    }

    public Cls_Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Cls_Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad_devuelta_prest() {
        return cantidad_devuelta_prest;
    }

    public void setCantidad_devuelta_prest(int cantidad_devuelta_prest) {
        this.cantidad_devuelta_prest = cantidad_devuelta_prest;
    }

    public int getCantidad_prestada_prest() {
        return cantidad_prestada_prest;
    }

    public void setCantidad_prestada_prest(int cantidad_prestada_prest) {
        this.cantidad_prestada_prest = cantidad_prestada_prest;
    }

    public Cls_PedidoPrestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Cls_PedidoPrestamo prestamo) {
        this.prestamo = prestamo;
    }
}
