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
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoDevolucion;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;

public abstract class Cls_DaoPedidoDevolucion {
    public static boolean DaoAgregarPedidoDevolucion(Cls_PedidoDevolucion devolucion)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_pedidoDevolucion(?,?,?,?)}");
            sentencia.setString(1,devolucion.getCodigo_devol());
            sentencia.setString(2,devolucion.getCliente().getCodigo_cli());
            sentencia.setString(3,devolucion.getUsuario().getCodigo_usu());
            sentencia.setDate(4,new java.sql.Date(devolucion.getFechadevolucion_devol().getTime()));
   
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
    public static boolean DaoActualizarPedidoDevolucion(Cls_PedidoDevolucion devolucion)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_pedidoDevolucion(?,?,?,?)}");
            sentencia.setString(1,devolucion.getCodigo_devol());
            sentencia.setString(2,devolucion.getCliente().getCodigo_cli());
            sentencia.setString(3,devolucion.getUsuario().getCodigo_usu());
            sentencia.setDate(4,new java.sql.Date(devolucion.getFechadevolucion_devol().getTime()));

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
    public static boolean DaoEliminarPedidoDevolucion(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_pedidoDevolucion(?)}");
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
    public static List DaoListarPedidoDevolucionCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_PedidoDevolucion devolucion=null;
        Cls_Cliente cliente=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_pedidoDevolucion_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
                cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(resultadoQuery.getString(2));
                usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(resultadoQuery.getString(3));
              devolucion=new Cls_PedidoDevolucion(resultadoQuery.getString(1),cliente,usuario,resultadoQuery.getDate(4));
              lista.add(devolucion);
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
        return lista;
    }
    public static Cls_PedidoDevolucion DaoObtenerPedidoDevolucionPorCodigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_PedidoDevolucion devolucion=null;
        Cls_Cliente cliente=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_pedidoDevolucion_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(resultadoQuery.getString(2));
              usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(resultadoQuery.getString(3));
              devolucion=new Cls_PedidoDevolucion(resultadoQuery.getString(1),cliente,usuario,resultadoQuery.getDate(4));
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
        return devolucion;
    }
    public static String GenerarCodigoPedDevolucion() {
        String codigo=" ";
        Connection conexion = BD.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultadoQuery = null;
        try {
            String sql = "select max(id_devol) as codigo from tbPedidoDevolucion";
            sentencia = conexion.prepareStatement(sql);
            resultadoQuery = sentencia.executeQuery();
            resultadoQuery.next();
            if (resultadoQuery.getString("codigo") != null)
            {
                Scanner s = new Scanner(resultadoQuery.getString("codigo"));
                int c = s.useDelimiter("D").nextInt() + 1;

                if (c < 10) {
                    codigo="D000000" + c;
                }else{
                    if (c < 100) {
                        codigo="D00000" + c;
                    }else{
                        if (c < 1000) {
                            codigo="D0000" + c;
                        }else{
                            if (c < 10000) {
                                codigo="D000" + c;
                            }else{
                                if (c < 100000) {
                                    codigo="D00" + c;
                                }else{
                                    if (c < 1000000) {
                                        codigo="D0" + c;
                                    }else {
                                        return "D" + c;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                codigo="D0000001";
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
