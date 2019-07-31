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
import pck_LOGICA_APLICACION.pck_Pojos.Cls_DetallePrestamo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoPrestamo;

public abstract class Cls_DaoDetallePrestamo {
    public static boolean DaoAgregarDetallePedidoPrestamo(Cls_DetallePrestamo detalleprestamo)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_detallepedidoPrestamo(?,?,?,?)}");
            sentencia.setString(1,detalleprestamo.getArticulo().getCodigo_art());
            sentencia.setString(2,detalleprestamo.getPrestamo().getCodigo_prest());
            sentencia.setInt(3,detalleprestamo.getCantidad_prestada_prest());
            sentencia.setInt(4,detalleprestamo.getCantidad_devuelta_prest());

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
    public static boolean DaoActualizarDetallePedidoPrestamo(Cls_DetallePrestamo detalleprestamo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_detallepedidoPrestamo(?,?,?,?)}");
            sentencia.setString(1,detalleprestamo.getArticulo().getCodigo_art());
            sentencia.setString(2,detalleprestamo.getPrestamo().getCodigo_prest());
            sentencia.setInt(3,detalleprestamo.getCantidad_prestada_prest());
            sentencia.setInt(4,detalleprestamo.getCantidad_devuelta_prest());

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
    public static boolean DaoEliminarDetallePedidoPrestamo(String codigoPrestamo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_detallepedidoPrestamo(?)}");
            sentencia.setString(1,codigoPrestamo);

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
    public static boolean ActualizarCantidadDevueltaDevolucion(String codigoArticulo,String codigoPrestamo,int cantidad){
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_cantDevueltaDetPresDevolucion(?,?,?)}");
            sentencia.setString(1,codigoArticulo);
            sentencia.setString(2,codigoPrestamo);
            sentencia.setInt(3,cantidad);

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
    public static List DaoListarDetallePedidoPrestamoCodigo(String codigoPrestamo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_DetallePrestamo detalleprestamo=null;
        Cls_PedidoPrestamo pedido=null;
        Cls_Articulo articulo=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_detallepedidoPrestamo_codigo(?)}");
            sentencia.setString(1,codigoPrestamo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
                articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(resultadoQuery.getString(1));
                pedido=Cls_DaoPedidoPrestamo.DaoObtenerPedidoPrestamoPorCodigo(resultadoQuery.getString(2));
              detalleprestamo=new Cls_DetallePrestamo(articulo,pedido,resultadoQuery.getInt(3),resultadoQuery.getInt(4));
              lista.add(detalleprestamo);
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
    public static Cls_DetallePrestamo DaoObtenerDetallePedidoPrestamoPorCodigo(String codigoPrestamo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_DetallePrestamo detalleprestamo=null;
        Cls_PedidoPrestamo pedido=null;
        Cls_Articulo articulo=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_detallepedidoPrestamo_codigo(?)}");
            sentencia.setString(1,codigoPrestamo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(resultadoQuery.getString(1));
                pedido=Cls_DaoPedidoPrestamo.DaoObtenerPedidoPrestamoPorCodigo(resultadoQuery.getString(2));
              detalleprestamo=new Cls_DetallePrestamo(articulo,pedido,resultadoQuery.getInt(3),resultadoQuery.getInt(4));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return detalleprestamo;
    }
}
