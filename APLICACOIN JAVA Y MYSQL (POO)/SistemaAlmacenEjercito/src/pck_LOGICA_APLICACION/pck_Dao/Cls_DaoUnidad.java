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
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Unidad;

public abstract class Cls_DaoUnidad {
    
    public static boolean DaoAgregarUnidad(Cls_Unidad unidad)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_unidad(?,?,?)}");
            sentencia.setString(1,unidad.getCodigo_uni());
            sentencia.setString(2,unidad.getNombre_uni());
            sentencia.setString(3,unidad.getDescripcion_uni());

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
    public static boolean DaoActualizarUnidad(Cls_Unidad unidad)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_unidad(?,?,?)}");
            sentencia.setString(1,unidad.getCodigo_uni());
            sentencia.setString(2,unidad.getNombre_uni());
            sentencia.setString(3,unidad.getDescripcion_uni());

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
    public static boolean DaoEliminarUnidad(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_unidad(?)}");
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
    public static List DaoListarUnidadCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_unidad_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=new Cls_Unidad(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
              lista.add(unidad);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static List DaoListarUnidadNombreCompleto(String nombrecompleto)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_unidades_nombrecompleto(?)}");
            sentencia.setString(1,nombrecompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=new Cls_Unidad(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
                lista.add(unidad);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static Cls_Unidad DaoObtenerUnidadPorCodigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_unidad_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=new Cls_Unidad(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return unidad;
    }
    public static Cls_Unidad DaoObtenerUnidadPorNombre(String nombre) throws SQLException {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia = null;
        Cls_Unidad unidad = null;
        try
        {
            conexion = BD.getConnection();
            sentencia = conexion.prepareStatement("{call sp_buscar_unidad_nombre(?)}");
            sentencia.setString(1, nombre);
            resultadoQuery = sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=new Cls_Unidad(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }/*catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }*/catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA
            es.printStackTrace();
        }
        return unidad;
    }

    /*public static Cls_Unidad DaoUnidadObtenerCodigoPorNombre(String nombre) throws SQLException {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia = null;
        Cls_Unidad unidad = null;
        try
        {
            conexion = BD.getConnection();
            sentencia = conexion.prepareStatement("select id_uni from tbUnidad where nombre_uni='"+nombre+"'");
            sentencia.setString(1,nombre);
            resultadoQuery = sentencia.executeQuery();
            
            if (resultadoQuery.next()) {
                unidad = new Cls_Unidad(){
                };
                unidad.setCodigo_uni(resultadoQuery.getString("id_uni"));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(Exception es){es.printStackTrace();}
        return unidad;
    }*/
    /*public static String DaoUnidadBuscaIdxNombre(String nombre)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia = null;
        String codigo=" ";
        try {
            conexion =BD.getConnection();
            String consultasql ="select id_uni from tbUnidad where nombre_uni='"+nombre+"'";
            sentencia=conexion.prepareStatement(consultasql);
            sentencia.setString(1,nombre);
            resultadoQuery=sentencia.executeQuery();

            if(resultadoQuery.next())
            {
                codigo=resultadoQuery.getString(1);
            }
            sentencia.close();
            resultadoQuery.close();
            conexion.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         return codigo;
     }*/
    public static String GenerarCodigoUnidad() {
        String codigo=" ";
        Connection conexion = BD.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultadoQuery = null;
        try {
            String sql = "select max(id_uni) as codigo from tbUnidad";
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
