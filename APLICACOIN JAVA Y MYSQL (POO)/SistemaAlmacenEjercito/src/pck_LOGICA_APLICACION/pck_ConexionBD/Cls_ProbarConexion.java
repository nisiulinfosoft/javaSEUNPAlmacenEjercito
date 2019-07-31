package pck_LOGICA_APLICACION.pck_ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Cls_ProbarConexion {
    public static void main(String[ ]args)throws IOException, SQLException
    {
        Connection conn =BD.getConnection();
        try
        {
            if (conn != null)
            {
                System.out.println("Base Conectada...OK");
                conn.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(Exception ex){ex.printStackTrace();}
    }
}
