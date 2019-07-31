package pck_GUI.pck_AccesoSistema;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUsuario;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;
import pck_utiles.Cls_Validar_CampoTexto;
import sistemaalmacenejercito.SistemaAlmacenEjercitoApp;
import sistemaalmacenejercito.SistemaAlmacenEjercitoView;

public class Jfr_Usuario_Identificacion extends javax.swing.JFrame {

    public Jfr_Usuario_Identificacion(javax.swing.JFrame padre) {
        super("Identificacion de Usuario");
        initComponents();
        this.setLocationRelativeTo(null);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("pck_imagenes/logo.png"));
        return retValue;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtcontrasena = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jfr_Usuario_Identificacion.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtcodigo.setName("txtcodigo"); // NOI18N
        jPanel2.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 200, -1));

        txtcontrasena.setName("txtcontrasena"); // NOI18N
        jPanel2.add(txtcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 200, -1));

        btnIngresar.setIcon(resourceMap.getIcon("btnIngresar.icon")); // NOI18N
        btnIngresar.setText(resourceMap.getString("btnIngresar.text")); // NOI18N
        btnIngresar.setName("btnIngresar"); // NOI18N
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, -1));

        btnSalir.setIcon(resourceMap.getIcon("btnSalir.icon")); // NOI18N
        btnSalir.setText(resourceMap.getString("btnSalir.text")); // NOI18N
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 100, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 280, 100));

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 410, 40));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 80));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 120, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        Cls_Usuario usuario=null;
        try{
            String codigo=this.txtcodigo.getText().trim();
            String contrasena=this.txtcontrasena.getText().trim();
            if(codigo.isEmpty() || contrasena.isEmpty()){
                JOptionPane.showMessageDialog(this, "Falta llenar los campos de texto");
                this.txtcontrasena.setText("");
               this.txtcodigo.requestFocus();
            }else{
                usuario=Cls_DaoUsuario.DaoValidarUsuario(codigo,contrasena);
                if(usuario!=null){
                    this.setVisible(false);
                    SistemaAlmacenEjercitoView auxClienteAlmacenView=new SistemaAlmacenEjercitoView(SistemaAlmacenEjercitoApp.getApplication(),this);
                    auxClienteAlmacenView.getApplication().show(auxClienteAlmacenView);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Usuario Incorrecto");
                    this.txtcontrasena.setText("");
                    this.txtcodigo.requestFocus();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
}//GEN-LAST:event_btnIngresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int respuesta=JOptionPane.showConfirmDialog(null,"¿SEGURO QUE DESEA CANCELAR EL INGRESO AL SISTEMA?","MENSAJE",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Operacion cancelada");
        }
}//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JPasswordField txtcontrasena;
    // End of variables declaration//GEN-END:variables

}
