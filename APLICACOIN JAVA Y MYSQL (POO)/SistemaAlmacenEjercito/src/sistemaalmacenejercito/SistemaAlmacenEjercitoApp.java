package sistemaalmacenejercito;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import pck_GUI.pck_AccesoSistema.Jfr_Usuario_Identificacion;

public class SistemaAlmacenEjercitoApp extends SingleFrameApplication {

    @Override protected void startup() {
        //show(new SistemaAlmacenEjercitoView(this));
    }

    @Override protected void configureWindow(java.awt.Window root) {
    }

    public static SistemaAlmacenEjercitoApp getApplication() {
        return Application.getInstance(SistemaAlmacenEjercitoApp.class);
    }

    public static void main(String[] args) {
        javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
        //launch(SistemaAlmacenEjercitoApp.class, args);
        Jfr_Usuario_Identificacion inicio=new Jfr_Usuario_Identificacion(new javax.swing.JFrame());
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
                try{
                    //para cambiar aparienza a las interfaces graficas
                    //SubstanceLookAndFeel.setSkin(new org.pushingpixels.substance.api.skin.OfficeSilver2007Skin());
                    SubstanceLookAndFeel.setSkin(new org.pushingpixels.substance.api.skin.SaharaSkin());
                    //SubstanceLookAndFeel.setSkin(new org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin());
                }
                catch (Exception e) {
                    System.out.println("Fallo la inicializacion del substance");
                    System.out.println(e.getMessage().toString());
                }
            }
        });
        inicio.setVisible(true);
    }
}
