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
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Articulo;

public abstract class Cls_DaoArticulo {

    public static boolean DaoAgregarArticulo(Cls_Articulo articulo)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_articulo(?,?,?,?,?,?,?)}");
            sentencia.setString(1,articulo.getCodigo_art());
            sentencia.setString(2,articulo.getNombre_art());
            sentencia.setDate(3,new java.sql.Date(articulo.getFechaingreso_art().getTime()));
            sentencia.setInt(4,articulo.getCantidad_igreso_art());
            sentencia.setInt(5,articulo.getCantidad_internada_art());
            sentencia.setInt(6,articulo.getCantidad_existente_art());
            sentencia.setString(7,articulo.getDescripcion_art());

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
    public static boolean DaoActualizarArticulo(Cls_Articulo articulo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_articulo(?,?,?,?,?,?,?)}");
            sentencia.setString(1,articulo.getCodigo_art());
            sentencia.setString(2,articulo.getNombre_art());
            sentencia.setDate(3,new java.sql.Date(articulo.getFechaingreso_art().getTime()));
            sentencia.setInt(4,articulo.getCantidad_igreso_art());
            sentencia.setInt(5,articulo.getCantidad_internada_art());
            sentencia.setInt(6,articulo.getCantidad_existente_art());
            sentencia.setString(7,articulo.getDescripcion_art());

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
    public static boolean DaoEliminarArticulo(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_articulo(?)}");
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
    public static boolean ActualizarCantidadExistentePrestamo(String codigoArt,int cantidad){
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_cantExistenteArtPrestamo(?,?)}");
            sentencia.setString(1,codigoArt);
            sentencia.setInt(2,cantidad);
  
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
    public static boolean ActualizarCantidadExistenteDevolucion(String codigoArt,int cantidad){
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_cantExistenteArtDevolucion(?,?)}");
            sentencia.setString(1,codigoArt);
            sentencia.setInt(2,cantidad);

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
    public static List DaoListarArticulosCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Articulo articulo=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_articulos_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              articulo=new Cls_Articulo(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getDate(3),resultadoQuery.getInt(4),resultadoQuery.getInt(5),resultadoQuery.getInt(6),resultadoQuery.getString(7));
              lista.add(articulo);
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
    public static List DaoListarArticulosNombreCompleto(String nombrecompleto)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Articulo articulo=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_articulos_nombrecompleto(?)}");
            sentencia.setString(1,nombrecompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              articulo=new Cls_Articulo(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getDate(3),resultadoQuery.getInt(4),resultadoQuery.getInt(5),resultadoQuery.getInt(6),resultadoQuery.getString(7));
              lista.add(articulo);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static List DaoListarArticulosDebeCliente(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_Articulos_queDebeCliente(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              lista.add(resultadoQuery.getString(1));
              lista.add(resultadoQuery.getString(2));
              lista.add(resultadoQuery.getString(3));
              lista.add(resultadoQuery.getString(4));
              lista.add(resultadoQuery.getString(5));
              lista.add(resultadoQuery.getString(6));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static Cls_Articulo DaoObtenerArticuloPorCodigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Articulo articulo=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_articulo_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              articulo=new Cls_Articulo(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getDate(3),resultadoQuery.getInt(4),resultadoQuery.getInt(5),resultadoQuery.getInt(6),resultadoQuery.getString(7));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return articulo;
    }
    public static Cls_Articulo DaoObtenerArticuloPorNombre(String nombreCompleto)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Articulo articulo=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_articulo_nombre(?)}");
            sentencia.setString(1,nombreCompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              articulo=new Cls_Articulo(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getDate(3),resultadoQuery.getInt(4),resultadoQuery.getInt(5),resultadoQuery.getInt(6),resultadoQuery.getString(7));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return articulo;
    }
    public static String GenerarCodigoArticulo() {
        String codigo=" ";
        Connection conexion = BD.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultadoQuery = null;
        try {
            String sql = "select max(id_art) as codigo from tbArticulo";
            sentencia = conexion.prepareStatement(sql);
            resultadoQuery = sentencia.executeQuery();
            resultadoQuery.next();
            if (resultadoQuery.getString("codigo") != null)
            {
                Scanner s = new Scanner(resultadoQuery.getString("codigo"));
                int c = s.useDelimiter("A").nextInt() + 1;

                if (c < 10) {
                    codigo="A000000" + c;
                }else{
                    if (c < 100) {
                        codigo="A00000" + c;
                    }else{
                        if (c < 1000) {
                            codigo="A0000" + c;
                        }else{
                            if (c < 10000) {
                                codigo="A000" + c;
                            }else{
                                if (c < 100000) {
                                    codigo="A00" + c;
                                }else{
                                    if (c < 1000000) {
                                        codigo="A0" + c;
                                    }else {
                                        return "A" + c;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                codigo="A0000001";
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
