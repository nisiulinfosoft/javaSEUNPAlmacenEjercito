package pck_GUI.pck_Mantenimiento;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.FrameView;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoCliente;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUnidad;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Cliente;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Unidad;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;
import pck_utiles.Utiles_Panel;

public class Jpn_Cliente_Casual extends JP_Modelo {

    JInternalFrame buscar;
    String CodigoCli="";
    String vNombre,vApePaterno,vApeMaterno,vCodigoUnidad,vNombreUnidad;

    public Jpn_Cliente_Casual() {
        initComponents();
        //this.Jpn_GP=jpn_Gestion_Prestamo;
        CodigoCli=Cls_DaoCliente.GenerarCodigoCliente();
        txtCodigo.setText(CodigoCli);
        this.buttonGroup1.add(this.jrbMasculino);
        this.buttonGroup1.add(this.jrbFemenino);
        this.buttonGroup1.add(this.jrbNinguno);
        this.jrbMasculino.setSelected(false);
        this.jrbFemenino.setSelected(false);
        this.jrbNinguno.setSelected(true);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void AsignarCasillas(){
        vNombre=this.txtApePaterno.getText().trim();
        vApePaterno=this.txtApeMaterno.getText().trim();
        vApeMaterno=this.txtApeMaterno.getText().trim();
        vCodigoUnidad=this.txtCodigoUnidad.getText().trim();
        vNombreUnidad=this.txtNombreUnidad.getText().trim();
    }
    public void limpiar()
    {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtApePaterno.setText("");
        this.txtApeMaterno.setText("");
        this.jrbNinguno.setSelected(true);
        this.DateFecNacimiento.setDate(null);
        this.DateFecIngreso.setDate(null);
        this.txtCodigoUnidad.setText("");
        this.txtNombreUnidad.setText("");
        this.txtNombre.requestFocus();
    }
    public void guardarCliente()
    {
        this.AsignarCasillas();
        if(vNombre.isEmpty() || vApePaterno.isEmpty() || vApeMaterno.isEmpty() || vCodigoUnidad.isEmpty() || vNombreUnidad.isEmpty() || this.DateFecNacimiento.getDate()==null || this.DateFecIngreso.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios (Datos Generales) ó verificar que la fecha este correcta");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE GUARDAR ESTE CLIENTE?","CLIENTE",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Cls_Cliente cliente=null;
                Cls_Unidad unidad=null;
                String CodigoCliente,NombreCliente;
                try {
                    cliente=new Cls_Cliente();
                    CodigoCliente=this.txtCodigo.getText().trim();
                    NombreCliente=this.txtNombre.getText().trim();
                    cliente.setCodigo_cli(this.txtCodigo.getText().trim());
                    cliente.setNombre_cli(this.txtNombre.getText().trim());
                    cliente.setApepaterno_cli(this.txtApePaterno.getText().trim());
                    cliente.setApematerno_cli(this.txtApeMaterno.getText().trim());
                    if(this.jrbMasculino.isSelected()){
                        cliente.setSexo_cli("M");
                    }else if(this.jrbFemenino.isSelected()){
                        cliente.setSexo_cli("F");
                    }else{
                        cliente.setSexo_cli("");
                    }
                    cliente.setFechanacimiento_cli(this.DateFecNacimiento.getDate());
                    cliente.setFechaingreso_cli(this.DateFecIngreso.getDate());
                    unidad=Cls_DaoUnidad.DaoObtenerUnidadPorNombre(this.txtNombreUnidad.getText().trim());
                    cliente.setUnidad(unidad);
                    cliente.setTelefono_cli("");
                    cliente.setCelular_cli("");
                    cliente.setEmail_cli("");
                    cliente.setObservacion_cli("");
                    cliente.setCalle_cli("");
                    cliente.setNumerocasa_cli(0);
                    cliente.setBarrio_cli("");
                    if(Cls_DaoCliente.DaoAgregarCliente(cliente)==true){
                        JOptionPane.showMessageDialog(this,"Registro guardado con exito");
                        
                        //Jpn_GP.txtCodigoCliente.setText(CodigoCliente);
                        //Jpn_GP.txtNombreCliente.setText(NombreCliente);
                        //this.cerrarPadre();

                    }else{
                        JOptionPane.showMessageDialog(this,"Error datos no registrados");
                    }
                }catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
            }
        }
    }
    public void obtenerUnidad(String codigo){
        Cls_Unidad unidad=null;
        try{
            unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(codigo);
            if (unidad!=null){
                this.txtCodigoUnidad.setText(unidad.getCodigo_uni());
                this.txtNombreUnidad.setText(unidad.getNombre_uni());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtApeMaterno = new javax.swing.JTextField();
        txtApePaterno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jrbMasculino = new javax.swing.JRadioButton();
        jrbFemenino = new javax.swing.JRadioButton();
        jrbNinguno = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        DateFecIngreso = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoUnidad = new javax.swing.JTextField();
        btnBuscarUnidad = new javax.swing.JButton();
        txtNombreUnidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DateFecNacimiento = new com.toedter.calendar.JDateChooser();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Cliente_Casual.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 95, -1));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setText(resourceMap.getString("btnCerrar.text")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 95, -1));

        btnLimpiar.setIcon(resourceMap.getIcon("btnLimpiar.icon")); // NOI18N
        btnLimpiar.setText(resourceMap.getString("btnLimpiar.text")); // NOI18N
        btnLimpiar.setName("btnLimpiar"); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 95, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 195, 615, 60));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel5.border.titleColor"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txtApeMaterno.setName("txtApeMaterno"); // NOI18N
        jPanel5.add(txtApeMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 270, -1));

        txtApePaterno.setName("txtApePaterno"); // NOI18N
        jPanel5.add(txtApePaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 270, -1));

        txtNombre.setName("txtNombre"); // NOI18N
        jPanel5.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 270, -1));

        txtCodigo.setEnabled(false);
        txtCodigo.setName("txtCodigo"); // NOI18N
        jPanel5.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel11.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel11.border.titleColor"))); // NOI18N
        jPanel11.setName("jPanel11"); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jrbMasculino.setText(resourceMap.getString("jrbMasculino.text")); // NOI18N
        jrbMasculino.setName("jrbMasculino"); // NOI18N
        jPanel11.add(jrbMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jrbFemenino.setText(resourceMap.getString("jrbFemenino.text")); // NOI18N
        jrbFemenino.setName("jrbFemenino"); // NOI18N
        jPanel11.add(jrbFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jrbNinguno.setText(resourceMap.getString("jrbNinguno.text")); // NOI18N
        jrbNinguno.setName("jrbNinguno"); // NOI18N
        jPanel11.add(jrbNinguno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 245, 50));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        DateFecIngreso.setDateFormatString(resourceMap.getString("DateFecIngreso.dateFormatString")); // NOI18N
        DateFecIngreso.setName("DateFecIngreso"); // NOI18N
        jPanel5.add(DateFecIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 150, -1));

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtCodigoUnidad.setEnabled(false);
        txtCodigoUnidad.setName("txtCodigoUnidad"); // NOI18N
        jPanel5.add(txtCodigoUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 80, -1));

        btnBuscarUnidad.setIcon(resourceMap.getIcon("btnBuscarUnidad.icon")); // NOI18N
        btnBuscarUnidad.setName("btnBuscarUnidad"); // NOI18N
        btnBuscarUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUnidadActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscarUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 30, -1));

        txtNombreUnidad.setEnabled(false);
        txtNombreUnidad.setName("txtNombreUnidad"); // NOI18N
        jPanel5.add(txtNombreUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 140, 410, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        DateFecNacimiento.setDateFormatString(resourceMap.getString("DateFecNacimiento.dateFormatString")); // NOI18N
        DateFecNacimiento.setName("DateFecNacimiento"); // NOI18N
        jPanel5.add(DateFecNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 150, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 615, 175));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 635, 270));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUnidadActionPerformed
        Jpn_Cliente_Casual_BuscarUnidad pn_buscar=new Jpn_Cliente_Casual_BuscarUnidad();
        buscar=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscar, "Buscar Unidad del Cliente");
        pn_buscar.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscar);
}//GEN-LAST:event_btnBuscarUnidadActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardarCliente();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.limpiar();
        CodigoCli=Cls_DaoCliente.GenerarCodigoCliente();
        txtCodigo.setText(CodigoCli);
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateFecIngreso;
    private com.toedter.calendar.JDateChooser DateFecNacimiento;
    private javax.swing.JButton btnBuscarUnidad;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jrbFemenino;
    private javax.swing.JRadioButton jrbMasculino;
    private javax.swing.JRadioButton jrbNinguno;
    private javax.swing.JTextField txtApeMaterno;
    private javax.swing.JTextField txtApePaterno;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoUnidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreUnidad;
    // End of variables declaration//GEN-END:variables
    //private Jpn_Gestion_Prestamo Jpn_GP;
}
