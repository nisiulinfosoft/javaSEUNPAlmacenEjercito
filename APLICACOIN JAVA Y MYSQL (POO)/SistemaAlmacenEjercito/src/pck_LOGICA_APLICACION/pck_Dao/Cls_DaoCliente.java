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
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Unidad;

public abstract class Cls_DaoCliente {
    public static boolean DaoAgregarCliente(Cls_Cliente cliente)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_agregar_cliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,cliente.getCodigo_cli());
            sentencia.setString(2,cliente.getUnidad().getCodigo_uni());
            sentencia.setString(3,cliente.getNombre_cli());
            sentencia.setString(4,cliente.getApepaterno_cli());
            sentencia.setString(5,cliente.getApematerno_cli());
            sentencia.setString(6,cliente.getSexo_cli());
            sentencia.setDate(7,new java.sql.Date(cliente.getFechanacimiento_cli().getTime()));
            sentencia.setDate(8,new java.sql.Date(cliente.getFechaingreso_cli().getTime()));
            sentencia.setString(9,cliente.getTelefono_cli());
            sentencia.setString(10,cliente.getCelular_cli());
            sentencia.setString(11,cliente.getEmail_cli());
            sentencia.setString(12,cliente.getCalle_cli());
            sentencia.setInt(13,cliente.getNumerocasa_cli());
            sentencia.setString(14,cliente.getBarrio_cli());
            sentencia.setString(15,cliente.getObservacion_cli());

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
    public static boolean DaoActualizarCliente(Cls_Cliente cliente)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_actualizar_cliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,cliente.getCodigo_cli());
            sentencia.setString(2,cliente.getUnidad().getCodigo_uni());
            sentencia.setString(3,cliente.getNombre_cli());
            sentencia.setString(4,cliente.getApepaterno_cli());
            sentencia.setString(5,cliente.getApematerno_cli());
            sentencia.setString(6,cliente.getSexo_cli());
            sentencia.setDate(7,new java.sql.Date(cliente.getFechanacimiento_cli().getTime()));
            sentencia.setDate(8,new java.sql.Date(cliente.getFechaingreso_cli().getTime()));
            sentencia.setString(9,cliente.getTelefono_cli());
            sentencia.setString(10,cliente.getCelular_cli());
            sentencia.setString(11,cliente.getEmail_cli());
            sentencia.setString(12,cliente.getCalle_cli());
            sentencia.setInt(13,cliente.getNumerocasa_cli());
            sentencia.setString(14,cliente.getBarrio_cli());
            sentencia.setString(15,cliente.getObservacion_cli());

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
    public static boolean DaoEliminarCliente(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_eliminar_cliente(?)}");
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
    public static List DaoListarClienteCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Cliente cliente=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_cliente_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(resultadoQuery.getString(2));
              cliente=new Cls_Cliente(resultadoQuery.getString(1),unidad,resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),(java.util.Date)resultadoQuery.getDate(7),(java.util.Date)resultadoQuery.getDate(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),resultadoQuery.getString(12),resultadoQuery.getInt(13),resultadoQuery.getString(14),resultadoQuery.getString(15));
              lista.add(cliente);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static List DaoListarClienteNombreCompleto(String nombrecompleto)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Cliente cliente=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_cliente_nombrecompleto(?)}");
            sentencia.setString(1,nombrecompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(resultadoQuery.getString(2));
              cliente=new Cls_Cliente(resultadoQuery.getString(1),unidad,resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),(java.util.Date)resultadoQuery.getDate(7),(java.util.Date)resultadoQuery.getDate(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),resultadoQuery.getString(12),resultadoQuery.getInt(13),resultadoQuery.getString(14),resultadoQuery.getString(15));
              lista.add(cliente);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static List DaoListarClientesDebenArticulosCodigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_Clientes_queDebenArticulos_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              lista.add(resultadoQuery.getString(1));
              lista.add(resultadoQuery.getString(2));
              lista.add(resultadoQuery.getString(3));
              lista.add(resultadoQuery.getString(4));
              lista.add(resultadoQuery.getString(5));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static List DaoListarClientesDebenArticulosNombre(String nombreCompleto)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_listar_Clientes_queDebenArticulos_nombre(?)}");
            sentencia.setString(1,nombreCompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              lista.add(resultadoQuery.getString(1));
              lista.add(resultadoQuery.getString(2));
              lista.add(resultadoQuery.getString(3));
              lista.add(resultadoQuery.getString(4));
              lista.add(resultadoQuery.getString(5));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }
    public static Cls_Cliente DaoObtenerClientePorCodigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Cliente cliente=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_cliente_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(resultadoQuery.getString(2));
              cliente=new Cls_Cliente(resultadoQuery.getString(1),unidad,resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),(java.util.Date)resultadoQuery.getDate(7),(java.util.Date)resultadoQuery.getDate(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),resultadoQuery.getString(12),resultadoQuery.getInt(13),resultadoQuery.getString(14),resultadoQuery.getString(15));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return cliente;
    }
    public static Cls_Cliente DaoObtenerClientePorNombre(String nombreCompleto)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Cls_Cliente cliente=null;
        Cls_Unidad unidad=null;
        try
        {
            conexion =BD.getConnection();
            sentencia=conexion.prepareStatement("{call sp_buscar_cliente_nombre(?)}");
            sentencia.setString(1,nombreCompleto);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(resultadoQuery.getString(2));
              cliente=new Cls_Cliente(resultadoQuery.getString(1),unidad,resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),(java.util.Date)resultadoQuery.getDate(7),(java.util.Date)resultadoQuery.getDate(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),resultadoQuery.getString(12),resultadoQuery.getInt(13),resultadoQuery.getString(14),resultadoQuery.getString(15));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return cliente;
    }
    public static String GenerarCodigoCliente() {
        String codigo=" ";
        Connection conexion = BD.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultadoQuery = null;
        try {
            String sql = "select max(id_cli) as codigo from tbCliente";
            sentencia = conexion.prepareStatement(sql);
            resultadoQuery = sentencia.executeQuery();
            resultadoQuery.next();
            if (resultadoQuery.getString("codigo") != null)
            {
                Scanner s = new Scanner(resultadoQuery.getString("codigo"));
                int c = s.useDelimiter("C").nextInt() + 1;

                if (c < 10) {
                    codigo="C000000" + c;
                }else{
                    if (c < 100) {
                        codigo="C00000" + c;
                    }else{
                        if (c < 1000) {
                            codigo="C0000" + c;
                        }else{
                            if (c < 10000) {
                                codigo="C000" + c;
                            }else{
                                if (c < 100000) {
                                    codigo="C00" + c;
                                }else{
                                    if (c < 1000000) {
                                        codigo="C0" + c;
                                    }else {
                                        return "C" + c;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                codigo="C0000001";
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
