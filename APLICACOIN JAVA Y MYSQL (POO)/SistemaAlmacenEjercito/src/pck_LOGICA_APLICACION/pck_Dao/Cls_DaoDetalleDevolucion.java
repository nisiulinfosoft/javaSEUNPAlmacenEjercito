package pck_LOGICA_APLICACION.pck_Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pck_LOGICA_APLICACION.pck_ConexionBD.BD;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Articulo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_DetalleDevolucion;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoDevolucion;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoPrestamo;

public abstract class Cls_DaoDetalleDevolucion {
    public static boolean DaoAgregarDetallePedidoDevolucion(Cls_DetalleDevolucion detalledevolucion)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_detallepedidoDevolucion(?,?,?,?)}");
            sentencia.setString(1,detalledevolucion.getPrestamo().getCodigo_prest());
            sentencia.setString(2,detalledevolucion.getArticulo().getCodigo_art());
            sentencia.setString(3,detalledevolucion.getDevolucion().getCodigo_devol());
            sentencia.setInt(4,detalledevolucion.getCantidad_devuelta_devol());

            int fialUpdate=sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            if(fialUpdate > 0)
            {
                respuesta = true;
            }else{
                System.out.println("Error datos no registrados");
                respuesta = false;
            }
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return respuesta;
    }
    public static boolean DaoActualizarDetallePedidoDevolucion(Cls_DetalleDevolucion detalledevolucion)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_detallepedidoDevolucion(?,?,?,?)}");
            sentencia.setString(1,detalledevolucion.getPrestamo().getCodigo_prest());
            sentencia.setString(2,detalledevolucion.getArticulo().getCodigo_art());
            sentencia.setString(3,detalledevolucion.getDevolucion().getCodigo_devol());
            sentencia.setInt(4,detalledevolucion.getCantidad_devuelta_devol());

            int fialUpdate=sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            if(fialUpdate > 0)
            {
                respuesta=true;
            }else{
                System.out.println("Error datos no actualizados");
                respuesta=false;
            }
         }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return respuesta;
     }
    public static boolean DaoEliminarDetallePedidoDevolucion(String codigoDevolucion)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_detallepedidoDevolucion(?)}");
            sentencia.setString(1,codigoDevolucion);

            int fialUpdate=sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
            if(fialUpdate > 0)
            {
                respuesta=true;
            }else{
                System.out.println("Error datos no eliminados");
                respuesta=false;
            }
       }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return respuesta;
    }
    public static List DaoListarDetallePedidoDevolucionCodigo(String codigoDevolucion)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_DetalleDevolucion detalledevolucion=null;
        Cls_PedidoPrestamo pedido=null;
        Cls_Articulo articulo=null;
        Cls_PedidoDevolucion devolucion=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_detallepedidoDevolucion_codigo(?)}");
            sentencia.setString(1,codigoDevolucion);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
               pedido=Cls_DaoPedidoPrestamo.DaoObtenerPedidoPrestamoPorCodigo(resultadoQuery.getString(1));
               articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(resultadoQuery.getString(2));
               devolucion=Cls_DaoPedidoDevolucion.DaoObtenerPedidoDevolucionPorCodigo(resultadoQuery.getString(3));
              detalledevolucion=new Cls_DetalleDevolucion(pedido,articulo,devolucion,resultadoQuery.getInt(4));
              lista.add(detalledevolucion);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){
            es.printStackTrace();
            es.getMessage();
        }
        return lista;
    }
    public static Cls_DetalleDevolucion DaoObtenerDetallePedidoDevolucionPorCodigo(String codigoDevolucion)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_DetalleDevolucion detalledevolucion=null;
        Cls_PedidoPrestamo pedido=null;
        Cls_Articulo articulo=null;
        Cls_PedidoDevolucion devolucion=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_detallepedidoDevolucion_codigo(?)}");
            sentencia.setString(1,codigoDevolucion);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              pedido=Cls_DaoPedidoPrestamo.DaoObtenerPedidoPrestamoPorCodigo(resultadoQuery.getString(1));
               articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(resultadoQuery.getString(2));
               devolucion=Cls_DaoPedidoDevolucion.DaoObtenerPedidoDevolucionPorCodigo(resultadoQuery.getString(3));
              detalledevolucion=new Cls_DetalleDevolucion(pedido,articulo,devolucion,resultadoQuery.getInt(4));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return detalledevolucion;
    }
}
