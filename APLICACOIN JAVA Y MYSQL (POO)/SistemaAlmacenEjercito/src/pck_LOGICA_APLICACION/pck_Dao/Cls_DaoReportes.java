package pck_LOGICA_APLICACION.pck_Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pck_LOGICA_APLICACION.pck_ConexionBD.BD;

public class Cls_DaoReportes {
    public void DaoImprimirReporteClientes(){
	int respuesta1=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE VISUALIZAR LOS CLIENTES?","CLIENTE",JOptionPane.YES_NO_OPTION);
        if(respuesta1==JOptionPane.OK_OPTION)
        {
            Connection conexion=null;
            JasperPrint reporte1=null;
            JasperViewer ver1=null;//visualizarlo con el jasperviewer
            Map parametros=new HashMap();
            //parametros.put("id_cli",this.txtCodigo.toString().trim());
            try{
                conexion=BD.getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/pck_Reportes/ImprimirClientes.jasper"));
                reporte1=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver1=new JasperViewer(reporte1,false);
                ver1.setSize(500,550);
                ver1.setTitle("REPORTE DE CLIENTES");
                ver1.setZoomRatio((float) 0.5);
                ver1.setLocationRelativeTo(null);
                ver1.setVisible(true);
            }catch(SQLException e){//PARA ERROR DE CODIGO SQL
                JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
            }catch (JRException e) {
                JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
            }catch(NullPointerException ne){//PARA ERROR DE CODIGO
                JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
            }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
                es.printStackTrace();
            }
        }
    }
    public void DaoImprimirReporteArticulos(){
        int respuesta2=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE VISUALIZAR LOS ARTICULOS?","ARTICULO",JOptionPane.YES_NO_OPTION);
        if(respuesta2==JOptionPane.OK_OPTION)
        {
            Connection conexion=null;
            JasperPrint reporte2=null;
            JasperViewer ver2=null;//visualizarlo con el jasperviewer
            Map parametros=new HashMap();
            //parametros.put("id_cli",this.txtCodigo.toString().trim());
            try{
                conexion=BD.getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/pck_Reportes/ImprimirArticulos.jasper"));
                reporte2=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver2=new JasperViewer(reporte2,false);
                ver2.setSize(500,550);
                ver2.setTitle("REPORTE DE ARTICULOS");
                ver2.setZoomRatio((float) 0.5);
                ver2.setLocationRelativeTo(null);
                ver2.setVisible(true);
            }catch(SQLException e){//PARA ERROR DE CODIGO SQL
                JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
            }catch (JRException e) {
                JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
            }catch(NullPointerException ne){//PARA ERROR DE CODIGO
                JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
            }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
                es.printStackTrace();
            }
        }
    }
    public void DaoImprimirReporteClientequeDeben(){
        int respuesta3=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE VISUALIZAR LOS CLIENTES QUE DEBEN ARTICULOS?","CLIENTE",JOptionPane.YES_NO_OPTION);
        if(respuesta3==JOptionPane.OK_OPTION)
        {
            Connection conexion=null;
            JasperPrint reporte3=null;
            JasperViewer ver3=null;//visualizarlo con el jasperviewer
            Map parametros=new HashMap();
            //parametros.put("id_cli",this.txtCodigo.toString().trim());
            try{
                conexion=BD.getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/pck_Reportes/ImprimirClientesDebenArticulos.jasper"));
                reporte3=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver3=new JasperViewer(reporte3,false);
                ver3.setSize(500,550);
                ver3.setTitle("REPORTE DE CLIENTES QUE DEBEN ARTICULOS");
                ver3.setZoomRatio((float) 0.5);
                ver3.setLocationRelativeTo(null);
                ver3.setVisible(true);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
