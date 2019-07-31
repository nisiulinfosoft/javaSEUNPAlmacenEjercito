package pck_GUI.pck_Mantenimiento;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.FrameView;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoCliente;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoReportes;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUnidad;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Cliente;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Unidad;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;
import pck_utiles.Utiles_Panel;

public class Jpn_Cliente_Mantenimiento extends JP_Modelo {

    JInternalFrame buscar,buscarunidad;
    boolean guardar=false;
    boolean modificar=false;
    String Codigo=" ";
    String vNombre,vApePaterno,vApeMaterno,vCodigoUnidad,vNombreUnidad;

    public Jpn_Cliente_Mantenimiento() {
        initComponents();
        this.buttonGroup1.add(this.jrbMasculino);
        this.buttonGroup1.add(this.jrbFemenino);
        this.buttonGroup1.add(this.jrbNinguno);
        this.jrbMasculino.setSelected(false);
        this.jrbFemenino.setSelected(false);
        this.jrbNinguno.setSelected(true);
        this.habilitarBotones(true,false,false,false,false,true,false);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void AsignarCasillas(){
        vNombre=this.txtApePaterno.getText().trim();
        vApePaterno=this.txtApeMaterno.getText().trim();
        vApeMaterno=this.txtApeMaterno.getText().trim();
        vCodigoUnidad=this.txtCodigoUnidad.getText().trim();
        vNombreUnidad=this.txtNombreUnidad.getText().trim();
    }
    public void habilitarBotones(boolean nuevo,boolean guardar,boolean modificar,boolean eliminar,boolean limpiar,boolean buscar,boolean buscarUnidad)
  {
      this.btnNuevo.setEnabled(nuevo);
      this.btnGuardar.setEnabled(guardar);
      this.btnModificar.setEnabled(modificar);
      this.btnEliminar.setEnabled(eliminar);
      this.btnLimpiar.setEnabled(limpiar);
      this.btnBuscar.setEnabled(buscar);
      this.btnBuscarUnidad.setEnabled(buscarUnidad);
  }
    public void habilitarTextos(boolean habilita)
   {
        this.txtCodigo.setEnabled(false);
        this.txtNombre.setEnabled(habilita);
        this.txtApePaterno.setEnabled(habilita);
        this.txtApeMaterno.setEnabled(habilita);
        this.jrbMasculino.setEnabled(habilita);
        this.jrbFemenino.setEnabled(habilita);
        this.jrbNinguno.setEnabled(habilita);
        this.DateFecNacimiento.setEnabled(habilita);
        this.DateFecIngreso.setEnabled(habilita);
        this.txtCodigoUnidad.setEnabled(false);
        this.txtNombreUnidad.setEnabled(false);
        this.txtTelefono.setEnabled(habilita);
        this.txtCelular.setEnabled(habilita);
        this.txtEmail.setEnabled(habilita);
        this.AreObservacion.setEnabled(habilita);
        this.txtCalle.setEnabled(habilita);
        this.txtNumero.setEnabled(habilita);
        this.txtBarrio.setEnabled(habilita);
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
        this.txtTelefono.setText("");
        this.txtCelular.setText("");
        this.txtEmail.setText("");
        this.AreObservacion.setText("");
        this.txtCalle.setText("");
        this.txtNumero.setText("");
        this.txtBarrio.setText("");
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
                try {
                    cliente=new Cls_Cliente();
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
                    unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(this.txtCodigoUnidad.getText().trim());
                    cliente.setUnidad(unidad);
                    cliente.setTelefono_cli(this.txtTelefono.getText().trim());
                    cliente.setCelular_cli(this.txtCelular.getText().trim());
                    cliente.setEmail_cli(this.txtEmail.getText());
                    cliente.setObservacion_cli(this.AreObservacion.getText().trim());
                    cliente.setCalle_cli(this.txtCalle.getText().trim());
                    if(this.txtNumero.getText().trim().equals("")){
                        cliente.setNumerocasa_cli(0);
                    }else{
                         cliente.setNumerocasa_cli(Integer.parseInt(this.txtNumero.getText().trim()));
                    }
                    cliente.setBarrio_cli(this.txtBarrio.getText().trim());

                    if(Cls_DaoCliente.DaoAgregarCliente(cliente)==true){
                        JOptionPane.showMessageDialog(this,"Registro guardado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this,"Error datos no registrados");
                    }
                }catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
                habilitarTextos(false);
                this.habilitarBotones(true, false, false, false, false,true,false);
                this.guardar=false;
                this.modificar=false;
            }
        }
    }
    public void modificarCliente()
    {
        this.AsignarCasillas();
        if(vNombre.isEmpty() || vApePaterno.isEmpty() || vApeMaterno.isEmpty() || vCodigoUnidad.isEmpty() || vNombreUnidad.isEmpty() || this.DateFecNacimiento.getDate()==null || this.DateFecIngreso.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios (Datos Generales) ó verificar que la fecha este correcta");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE MODIFICAR ESTE CLIENTE?","CLIENTE",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Cls_Cliente cliente=null;
                Cls_Unidad unidad=null;
                try {
                    cliente=new Cls_Cliente();
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
                    unidad=Cls_DaoUnidad.DaoObtenerUnidadPorCodigo(this.txtCodigoUnidad.getText().trim());
                    cliente.setUnidad(unidad);
                    cliente.setTelefono_cli(this.txtTelefono.getText().trim());
                    cliente.setCelular_cli(this.txtCelular.getText().trim());
                    cliente.setEmail_cli(this.txtEmail.getText());
                    cliente.setObservacion_cli(this.AreObservacion.getText().trim());
                    cliente.setCalle_cli(this.txtCalle.getText().trim());
                    if(this.txtNumero.getText().trim().equals("")){
                        cliente.setNumerocasa_cli(0);
                    }else{
                         cliente.setNumerocasa_cli(Integer.parseInt(this.txtNumero.getText().trim()));
                    }
                    cliente.setBarrio_cli(this.txtBarrio.getText().trim());

                    if(Cls_DaoCliente.DaoActualizarCliente(cliente)==true){
                        JOptionPane.showMessageDialog(this, "Registro Actualizado");
                    }else{
                        JOptionPane.showMessageDialog(this, "Error datos no actualizados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
                habilitarTextos(false);
                this.habilitarBotones(true, false, false, false, false,true,false);
                this.guardar=false;
                this.modificar=false;
            }
        }
    }
    public void eliminarCliente()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE ELIMINAR ESTE CLIENTE?","CLIENTE",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(Cls_DaoCliente.DaoEliminarCliente(codigo)){
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }
            limpiar();
            habilitarTextos(false);
            this.habilitarBotones(true, false, false, false, false,true,false);
        }
    }
   public void obtenerCliente(String codigo){
        Cls_Cliente cliente=null;
        try{
            cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(codigo);
            if (cliente!=null){
                this.txtCodigo.setText(cliente.getCodigo_cli());
                this.txtNombre.setText(cliente.getNombre_cli());
                this.txtApePaterno.setText(cliente.getApepaterno_cli());
                this.txtApeMaterno.setText(cliente.getApematerno_cli());
                this.txtCodigoUnidad.setText(cliente.getUnidad().getCodigo_uni());
                this.txtNombreUnidad.setText(cliente.getUnidad().getNombre_uni());
                if(cliente.getSexo_cli() != null)
                {
                    if(cliente.getSexo_cli().equals("M")){
                        this.jrbMasculino.setSelected(true);
                    }else if(cliente.getSexo_cli().equals("F")){
                        this.jrbFemenino.setSelected(true);
                    }else if(cliente.getSexo_cli().equals("")){
                        this.jrbNinguno.setSelected(true);
                    }else  {
                        this.jrbNinguno.setSelected(true);
                    }
                }else{
                    this.jrbNinguno.setSelected(true);
                }
                this.DateFecNacimiento.setDate(cliente.getFechanacimiento_cli());
                this.DateFecIngreso.setDate(cliente.getFechaingreso_cli());
                this.txtTelefono.setText(cliente.getTelefono_cli());
                this.txtCelular.setText(cliente.getCelular_cli());
                this.txtEmail.setText(cliente.getEmail_cli());
                this.AreObservacion.setText(cliente.getObservacion_cli());
                this.txtCalle.setText(cliente.getCalle_cli());
                this.txtNumero.setText(String.valueOf(cliente.getNumerocasa_cli()));
                this.txtBarrio.setText(cliente.getBarrio_cli());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
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
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtBarrio = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtApeMaterno = new javax.swing.JTextField();
        txtApePaterno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jrbMasculino = new javax.swing.JRadioButton();
        jrbFemenino = new javax.swing.JRadioButton();
        jrbNinguno = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        DateFecIngreso = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        DateFecNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoUnidad = new javax.swing.JTextField();
        btnBuscarUnidad = new javax.swing.JButton();
        txtNombreUnidad = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreObservacion = new javax.swing.JTextArea();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Cliente_Mantenimiento.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 355, 615, 60));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtCalle.setEnabled(false);
        txtCalle.setName("txtCalle"); // NOI18N
        jPanel3.add(txtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 360, -1));

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        txtNumero.setEnabled(false);
        txtNumero.setName("txtNumero"); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });
        jPanel3.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 125, -1));

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtBarrio.setEnabled(false);
        txtBarrio.setName("txtBarrio"); // NOI18N
        jPanel3.add(txtBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 545, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, 615, 80));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtEmail.setText(resourceMap.getString("txtEmail.text")); // NOI18N
        txtEmail.setEnabled(false);
        txtEmail.setName("txtEmail"); // NOI18N
        jPanel4.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 290, -1));

        txtCelular.setText(resourceMap.getString("txtCelular.text")); // NOI18N
        txtCelular.setEnabled(false);
        txtCelular.setName("txtCelular"); // NOI18N
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });
        jPanel4.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 120, -1));

        txtTelefono.setEnabled(false);
        txtTelefono.setName("txtTelefono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel4.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 120, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 195, 360, 80));

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

        txtApeMaterno.setEnabled(false);
        txtApeMaterno.setName("txtApeMaterno"); // NOI18N
        txtApeMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeMaternoKeyTyped(evt);
            }
        });
        jPanel5.add(txtApeMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 270, -1));

        txtApePaterno.setEnabled(false);
        txtApePaterno.setName("txtApePaterno"); // NOI18N
        txtApePaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApePaternoKeyTyped(evt);
            }
        });
        jPanel5.add(txtApePaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 270, -1));

        txtNombre.setEnabled(false);
        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel5.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 270, -1));

        txtCodigo.setEnabled(false);
        txtCodigo.setName("txtCodigo"); // NOI18N
        jPanel5.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 170, -1));

        btnBuscar.setIcon(resourceMap.getIcon("btnBuscar.icon")); // NOI18N
        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setEnabled(false);
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 95, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel11.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel11.border.titleColor"))); // NOI18N
        jPanel11.setName("jPanel11"); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jrbMasculino.setText(resourceMap.getString("jrbMasculino.text")); // NOI18N
        jrbMasculino.setEnabled(false);
        jrbMasculino.setName("jrbMasculino"); // NOI18N
        jPanel11.add(jrbMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jrbFemenino.setText(resourceMap.getString("jrbFemenino.text")); // NOI18N
        jrbFemenino.setEnabled(false);
        jrbFemenino.setName("jrbFemenino"); // NOI18N
        jPanel11.add(jrbFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jrbNinguno.setText(resourceMap.getString("jrbNinguno.text")); // NOI18N
        jrbNinguno.setEnabled(false);
        jrbNinguno.setName("jrbNinguno"); // NOI18N
        jPanel11.add(jrbNinguno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 245, 50));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        DateFecIngreso.setDateFormatString(resourceMap.getString("DateFecIngreso.dateFormatString")); // NOI18N
        DateFecIngreso.setEnabled(false);
        DateFecIngreso.setName("DateFecIngreso"); // NOI18N
        jPanel5.add(DateFecIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 165, -1));

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        DateFecNacimiento.setDateFormatString(resourceMap.getString("DateFecNacimiento.dateFormatString")); // NOI18N
        DateFecNacimiento.setEnabled(false);
        DateFecNacimiento.setName("DateFecNacimiento"); // NOI18N
        jPanel5.add(DateFecNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 165, -1));

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtCodigoUnidad.setText(resourceMap.getString("txtCodigoUnidad.text")); // NOI18N
        txtCodigoUnidad.setEnabled(false);
        txtCodigoUnidad.setName("txtCodigoUnidad"); // NOI18N
        jPanel5.add(txtCodigoUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 80, -1));

        btnBuscarUnidad.setIcon(resourceMap.getIcon("btnBuscarUnidad.icon")); // NOI18N
        btnBuscarUnidad.setText(resourceMap.getString("btnBuscarUnidad.text")); // NOI18N
        btnBuscarUnidad.setEnabled(false);
        btnBuscarUnidad.setName("btnBuscarUnidad"); // NOI18N
        btnBuscarUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUnidadActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscarUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 30, -1));

        txtNombreUnidad.setText(resourceMap.getString("txtNombreUnidad.text")); // NOI18N
        txtNombreUnidad.setEnabled(false);
        txtNombreUnidad.setName("txtNombreUnidad"); // NOI18N
        jPanel5.add(txtNombreUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 140, 410, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 615, 175));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel6.border.titleColor"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        AreObservacion.setColumns(20);
        AreObservacion.setRows(5);
        AreObservacion.setEnabled(false);
        AreObservacion.setName("AreObservacion"); // NOI18N
        jScrollPane1.setViewportView(AreObservacion);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 195, 255, 80));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 635, 425));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Jpn_Cliente_Buscar pn_buscar=new Jpn_Cliente_Buscar();
        buscar=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscar, "Buscar Cliente");
        pn_buscar.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscar);
}//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUnidadActionPerformed
        Jpn_Cliente_BuscarUnidad pn_buscarunidad=new Jpn_Cliente_BuscarUnidad();
        buscarunidad=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscarunidad, "Buscar Unidad de Cliente");
        pn_buscarunidad.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscarunidad.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarunidad);
    }//GEN-LAST:event_btnBuscarUnidadActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        habilitarTextos(true);
        this.habilitarBotones(false,true,false, false,true,false,true);
        Codigo=Cls_DaoCliente.GenerarCodigoCliente();
        txtCodigo.setText(Codigo);
        this.guardar=true;
        this.modificar=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(guardar==true)
        {
            guardarCliente();
        }
        else
        {
            if(modificar==true)
            {
                modificarCliente();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        habilitarTextos(true);
        this.txtCodigo.setEnabled(false);
        this.habilitarBotones(false, true,false, false, true,false,true);
        this.guardar=false;
        this.modificar=true;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.eliminarCliente();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        habilitarTextos(false);
        this.habilitarBotones(true,false,false,false,false,true,false);
        this.guardar=false;
        this.modificar=false;
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtNumero, evt);
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCelular, evt);
    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtTelefono, evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtNombre, evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApePaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePaternoKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtApePaterno, evt);
    }//GEN-LAST:event_txtApePaternoKeyTyped

    private void txtApeMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMaternoKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtApeMaterno, evt);
    }//GEN-LAST:event_txtApeMaternoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreObservacion;
    private com.toedter.calendar.JDateChooser DateFecIngreso;
    private com.toedter.calendar.JDateChooser DateFecNacimiento;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarUnidad;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrbFemenino;
    private javax.swing.JRadioButton jrbMasculino;
    private javax.swing.JRadioButton jrbNinguno;
    private javax.swing.JTextField txtApeMaterno;
    private javax.swing.JTextField txtApePaterno;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoUnidad;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreUnidad;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
