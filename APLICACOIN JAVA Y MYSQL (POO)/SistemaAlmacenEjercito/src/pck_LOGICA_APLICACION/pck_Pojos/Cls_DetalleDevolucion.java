package pck_LOGICA_APLICACION.pck_Pojos;

public class Cls_DetalleDevolucion {
    private Cls_PedidoPrestamo prestamo;
    private Cls_Articulo articulo;
    private Cls_PedidoDevolucion devolucion;
    private int cantidad_devuelta_devol;

    public Cls_DetalleDevolucion() {
    }

    public Cls_DetalleDevolucion(Cls_PedidoPrestamo prestamo, Cls_Articulo articulo, Cls_PedidoDevolucion devolucion, int cantidad_devuelta_devol) {
        this.prestamo = prestamo;
        this.articulo = articulo;
        this.devolucion = devolucion;
        this.cantidad_devuelta_devol = cantidad_devuelta_devol;
    }

    public Cls_Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Cls_Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad_devuelta_devol() {
        return cantidad_devuelta_devol;
    }

    public void setCantidad_devuelta_devol(int cantidad_devuelta_devol) {
        this.cantidad_devuelta_devol = cantidad_devuelta_devol;
    }

    public Cls_PedidoDevolucion getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Cls_PedidoDevolucion devolucion) {
        this.devolucion = devolucion;
    }

    public Cls_PedidoPrestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Cls_PedidoPrestamo prestamo) {
        this.prestamo = prestamo;
    }
}
