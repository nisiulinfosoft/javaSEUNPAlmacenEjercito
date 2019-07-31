package pck_LOGICA_APLICACION.pck_Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pck_LOGICA_APLICACION.pck_ConexionBD.BD;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Cliente;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoPrestamo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;

public abstract class Cls_DaoPedidoPrestamo {

    public static boolean DaoAgregarPedidoPrestamo(Cls_PedidoPrestamo prestamo)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_pedidoPrestamo(?,?,?,?)}");
            sentencia.setString(1,prestamo.getCodigo_prest());
            sentencia.setString(2,prestamo.getCliente().getCodigo_cli());
            sentencia.setString(3,prestamo.getUsuario().getCodigo_usu());
            sentencia.setDate(4,new java.sql.Date(prestamo.getFechaprestamo_prest().getTime()));
   
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
    public static boolean DaoActualizarPedidoPrestamo(Cls_PedidoPrestamo prestamo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_pedidoPrestamo(?,?,?,?)}");
            sentencia.setString(1,prestamo.getCodigo_prest());
            sentencia.setString(2,prestamo.getCliente().getCodigo_cli());
            sentencia.setString(3,prestamo.getUsuario().getCodigo_usu());
            sentencia.setDate(4,new java.sql.Date(prestamo.getFechaprestamo_prest().getTime()));

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
    public static boolean DaoEliminarPedidoPrestamo(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_pedidoPrestamo(?)}");
            sentencia.setString(1,codigo);

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
    public static List DaoListarPedidoPrestamoCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_PedidoPrestamo pedido=null;
        Cls_Cliente cliente=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_pedidoPrestamo_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
                cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(resultadoQuery.getString(2));
                usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(resultadoQuery.getString(3));
              pedido=new Cls_PedidoPrestamo(resultadoQuery.getString(1),cliente,usuario,resultadoQuery.getDate(4));
              lista.add(pedido);
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
    public static Cls_PedidoPrestamo DaoObtenerPedidoPrestamoPorCodigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_PedidoPrestamo pedido=null;
        Cls_Cliente cliente=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_pedidoPrestamo_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(resultadoQuery.getString(2));
              usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(resultadoQuery.getString(3));
              pedido=new Cls_PedidoPrestamo(resultadoQuery.getString(1),cliente,usuario,resultadoQuery.getDate(4));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return pedido;
    }
    public static String GenerarCodigoPedPrestamo() {
        String codigo=" ";
        Connection conexion = BD.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultadoQuery = null;
        try {
            String sql = "select max(id_prest) as codigo from tbPedidoPrestamo";
            sentencia = conexion.prepareStatement(sql);
            resultadoQuery = sentencia.executeQuery();
            resultadoQuery.next();
            if (resultadoQuery.getString("codigo") != null)
            {
                Scanner s = new Scanner(resultadoQuery.getString("codigo"));
                int c = s.useDelimiter("P").nextInt() + 1;

                if (c < 10) {
                    codigo="P000000" + c;
                }else{
                    if (c < 100) {
                        codigo="P00000" + c;
                    }else{
                        if (c < 1000) {
                            codigo="P0000" + c;
                        }else{
                            if (c < 10000) {
                                codigo="P000" + c;
                            }else{
                                if (c < 100000) {
                                    codigo="P00" + c;
                                }else{
                                    if (c < 1000000) {
                                        codigo="P0" + c;
                                    }else {
                                        return "P" + c;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                codigo="P0000001";
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return codigo;
    }
}
