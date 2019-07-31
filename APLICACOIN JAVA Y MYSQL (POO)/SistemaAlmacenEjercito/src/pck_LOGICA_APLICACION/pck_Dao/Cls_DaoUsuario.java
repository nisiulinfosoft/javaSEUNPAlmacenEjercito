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
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;

public abstract class Cls_DaoUsuario {

    public static boolean DaoAgregarUsuario(Cls_Usuario usuario)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_usuario(?,?,?,?,?)}");
            sentencia.setString(1,usuario.getCodigo_usu());
            sentencia.setString(2,usuario.getNombre_usu());
            sentencia.setString(3,usuario.getApepaterno_usu());
            sentencia.setString(4,usuario.getApematerno_usu());
            sentencia.setString(5,usuario.getContrasena_usu());

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
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA
            es.printStackTrace();
        }
        return respuesta;
    }
    public static boolean DaoActualizarUsuario(Cls_Usuario usuario)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_usuario(?,?,?,?,?)}");
            sentencia.setString(1,usuario.getCodigo_usu());
            sentencia.setString(2,usuario.getNombre_usu());
            sentencia.setString(3,usuario.getApepaterno_usu());
            sentencia.setString(4,usuario.getApematerno_usu());
            sentencia.setString(5,usuario.getContrasena_usu());

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
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA
            es.printStackTrace();
        }
        return respuesta;
     }
    public static boolean DaoEliminarUsuario(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_usuario(?)}");
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
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA
            es.printStackTrace();
        }
        return respuesta;
    }
    public static List DaoListarUsuariosCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_usuarios_codigo(?)}");
            sentencia.setString(1,codigo);
            
            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Cls_Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5));
              lista.add(usuario);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static List DaoListarUsuariosNombreCompleto(String nombrecompleto)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_usuarios_nombrecompleto(?)}");
            sentencia.setString(1,nombrecompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Cls_Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5));
                lista.add(usuario);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static Cls_Usuario DaoObtenerUsuarioPorCodigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_usuario_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Cls_Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return usuario;
    }
    public static Cls_Usuario DaoObtenerUsuarioPorNombre(String nombreCompleto)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_usuario_nombre(?)}");
            sentencia.setString(1,nombreCompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Cls_Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return usuario;
    }
    public static Cls_Usuario DaoValidarUsuario(String codigo,String password)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Usuario usuario=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_validar_usuario(?,?)}");
            sentencia.setString(1,codigo);
            sentencia.setString(2,password);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Cls_Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return usuario;
   }
    public static String GenerarCodigoUsuario() {
        String codigo=" ";
        Connection conexion = BD.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultadoQuery = null;
        try {
            String sql = "select max(id_usu) as codigo from tbUsuario";
            sentencia = conexion.prepareStatement(sql);
            resultadoQuery = sentencia.executeQuery();
            resultadoQuery.next();
            if (resultadoQuery.getString("codigo") != null)
            {
                Scanner s = new Scanner(resultadoQuery.getString("codigo"));
                int c = s.useDelimiter("U").nextInt() + 1;

                if (c < 10) {
                    codigo="U000000" + c;
                }else{
                    if (c < 100) {
                        codigo="U00000" + c;
                    }else{
                        if (c < 1000) {
                            codigo="U0000" + c;
                        }else{
                            if (c < 10000) {
                                codigo="U000" + c;
                            }else{
                                if (c < 100000) {
                                    codigo="U00" + c;
                                }else{
                                    if (c < 1000000) {
                                        codigo="U0" + c;
                                    }else {
                                        return "U" + c;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                codigo="U0000001";
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch (Exception es) {es.printStackTrace();}
        return codigo;
    }
}
