package pck_GUI.pck_Mantenimiento;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.FrameView;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUsuario;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;
import pck_utiles.Utiles_Panel;

public class Jpn_Usuario_Mantenimiento extends JP_Modelo {

    JInternalFrame buscar;
     boolean guardar=false;
    boolean modificar=false;
    String Codigo=" ";
    String vnombre,vapepat,vapemat,vcontraseña;

    public Jpn_Usuario_Mantenimiento() {
        initComponents();
        this.habilitarBotones(true,false,false,false,false,true);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void AsignarCasillas(){
        vnombre= this.txtNombre.getText().trim();
        vapepat=this.txtApePat.getText().trim();
        vapemat=this.txtApeMat.getText().trim();
        vcontraseña=this.txtContrasena.getText().trim();
    }
   public void habilitarBotones(boolean nuevo,boolean guardar,boolean modificar,boolean eliminar,boolean limpiar,boolean buscar)
  {
      this.btnNuevo.setEnabled(nuevo);
      this.btnGuardar.setEnabled(guardar);
      this.btnModificar.setEnabled(modificar);
      this.btnEliminar.setEnabled(eliminar);
      this.btnLimpiar.setEnabled(limpiar);
      this.btnBuscar.setEnabled(buscar);
  }
    public void habilitarTextos(boolean habilita)
   {
        this.txtCodigo.setEnabled(false);
        this.txtNombre.setEnabled(habilita);
        this.txtApePat.setEnabled(habilita);
        this.txtApeMat.setEnabled(habilita);
        this.txtContrasena.setEnabled(habilita);
   }
    public void limpiar()
    {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtApePat.setText("");
        this.txtApeMat.setText("");
        this.txtContrasena.setText("");
        this.txtNombre.requestFocus();
    }
    public void guardarUsuario()
    {
        this.AsignarCasillas();
        if(vnombre.isEmpty() || vapepat.isEmpty() || vapemat.isEmpty() || vcontraseña.isEmpty()){
            JOptionPane.showMessageDialog(this,"Porfavor llenar todos los campos");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE GUARDAR ESTE USUARIO?","USUARIO",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Cls_Usuario usuario=null;
                try {
                    usuario=new Cls_Usuario();
                    usuario.setCodigo_usu(this.txtCodigo.getText());
                    usuario.setNombre_usu(this.txtNombre.getText().trim());
                    usuario.setApepaterno_usu(this.txtApePat.getText().trim());
                    usuario.setApematerno_usu(this.txtApeMat.getText().trim());
                    usuario.setContrasena_usu(this.txtContrasena.getText().trim());

                    if(Cls_DaoUsuario.DaoAgregarUsuario(usuario)==true){
                        JOptionPane.showMessageDialog(this, "Registro guardado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this, "Error datos no registrados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
                habilitarTextos(false);
                this.habilitarBotones(true, false, false, false, false,true);
                this.guardar=false;
                this.modificar=false;
            }
        }
    }
    public void modificarUsuario()
    {
        this.AsignarCasillas();
        if(vnombre.isEmpty() || vapepat.isEmpty() || vapemat.isEmpty() || vcontraseña.isEmpty()){
            JOptionPane.showMessageDialog(this,"Porfavor llenar todos los campos");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE MODIFICAR ESTE USUARIO?","USUARIO",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Cls_Usuario usuario=null;
                try {
                    usuario=new Cls_Usuario();
                    usuario.setCodigo_usu(this.txtCodigo.getText());
                    usuario.setNombre_usu(this.txtNombre.getText().trim());
                    usuario.setApepaterno_usu(this.txtApePat.getText().trim());
                    usuario.setApematerno_usu(this.txtApeMat.getText().trim());
                    usuario.setContrasena_usu(this.txtContrasena.getText().trim());

                    if(Cls_DaoUsuario.DaoActualizarUsuario(usuario)==true){
                        JOptionPane.showMessageDialog(this, "Registro Actualizado");
                    }else{
                        JOptionPane.showMessageDialog(this, "Error datos no actualizados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
                habilitarTextos(false);
                this.habilitarBotones(true, false, false, false, false,true);
                this.guardar=false;
                this.modificar=false;
            }
        }
    }
    public void eliminarUsuario()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE ELIMINAR ESTE USUARIO?","USUARIO",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(Cls_DaoUsuario.DaoEliminarUsuario(codigo)==true){
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }
            limpiar();
            habilitarTextos(false);
            this.habilitarBotones(true, false, false, false, false,true);
        }
    }
    public void obtenerUsuario(String codigo){
        Cls_Usuario usuario=null;
        try{
            usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(codigo);
            if (usuario!=null){
                this.txtCodigo.setText(usuario.getCodigo_usu());
                this.txtNombre.setText(usuario.getNombre_usu());
                this.txtApePat.setText(usuario.getApepaterno_usu());
                this.txtApeMat.setText(usuario.getApematerno_usu());
                this.txtContrasena.setText(usuario.getContrasena_usu());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApePat = new javax.swing.JTextField();
        txtApeMat = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Usuario_Mantenimiento.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setEnabled(false);
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 95, -1));

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 95, -1));

        btnModificar.setIcon(resourceMap.getIcon("btnModificar.icon")); // NOI18N
        btnModificar.setText(resourceMap.getString("btnModificar.text")); // NOI18N
        btnModificar.setEnabled(false);
        btnModificar.setName("btnModificar"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 95, -1));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setText(resourceMap.getString("btnEliminar.text")); // NOI18N
        btnEliminar.setEnabled(false);
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 95, -1));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setText(resourceMap.getString("btnCerrar.text")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 95, -1));

        btnLimpiar.setIcon(resourceMap.getIcon("btnLimpiar.icon")); // NOI18N
        btnLimpiar.setText(resourceMap.getString("btnLimpiar.text")); // NOI18N
        btnLimpiar.setEnabled(false);
        btnLimpiar.setName("btnLimpiar"); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 95, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 615, 60));

        btnBuscar.setIcon(resourceMap.getIcon("btnBuscar.icon")); // NOI18N
        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setEnabled(false);
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 95, -1));

        txtCodigo.setEnabled(false);
        txtCodigo.setName("txtCodigo"); // NOI18N
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 165, -1));

        txtNombre.setEnabled(false);
        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 270, -1));

        txtApePat.setEnabled(false);
        txtApePat.setName("txtApePat"); // NOI18N
        txtApePat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApePatKeyTyped(evt);
            }
        });
        jPanel1.add(txtApePat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 270, -1));

        txtApeMat.setEnabled(false);
        txtApeMat.setName("txtApeMat"); // NOI18N
        txtApeMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeMatKeyTyped(evt);
            }
        });
        jPanel1.add(txtApeMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 270, -1));

        txtContrasena.setEnabled(false);
        txtContrasena.setName("txtContrasena"); // NOI18N
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 270, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 250, 140));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 635, 230));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Jpn_Usuario_Buscar pn_buscar=new Jpn_Usuario_Buscar();
        buscar=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscar, "Buscar Usuario");
        pn_buscar.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscar);
}//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.guardar=false;
        this.modificar=false;
        limpiar();
        habilitarTextos(false);
        this.habilitarBotones(true,false,false,false,false,true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        habilitarTextos(true);
        this.habilitarBotones(false, true,false, false, true,false);
        Codigo=Cls_DaoUsuario.GenerarCodigoUsuario();
        txtCodigo.setText(Codigo);
        this.guardar=true;
        this.modificar=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(guardar==true)
        {
            guardarUsuario();
        }
        else
        {
            if(modificar==true)
            {
                modificarUsuario();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        habilitarTextos(true);
        this.txtCodigo.setEnabled(false);
        this.habilitarBotones(false, true,false, false, true,false);
        this.guardar=false;
        this.modificar=true;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.eliminarUsuario();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtNombre, evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApePatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePatKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtApePat, evt);
    }//GEN-LAST:event_txtApePatKeyTyped

    private void txtApeMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMatKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtApeMat, evt);
    }//GEN-LAST:event_txtApeMatKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtApeMat;
    private javax.swing.JTextField txtApePat;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
