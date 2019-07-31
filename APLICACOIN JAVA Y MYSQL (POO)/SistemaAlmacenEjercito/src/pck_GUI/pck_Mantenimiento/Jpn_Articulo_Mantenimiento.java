package pck_GUI.pck_Mantenimiento;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.FrameView;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoArticulo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Articulo;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;
import pck_utiles.Utiles_Panel;

public class Jpn_Articulo_Mantenimiento extends JP_Modelo {

    JInternalFrame buscar;
     boolean guardar=false;
    boolean modificar=false;
    String Codigo = "";
    String vnombre,vfecha,vcantIngreso,vcantInternada,vcantExistente,vdescripcion;
    String buscador="";

    public Jpn_Articulo_Mantenimiento() {
        initComponents();
        this.buttonGroup1.add(this.jrbNingunoSeleccionado);
        this.buttonGroup1.add(this.jrbCantAumentoIngreso);
        this.buttonGroup1.add(this.jrbCantAumentoInternacion);
        this.buttonGroup1.add(this.jrbCantDisminuirIngreso);
        this.buttonGroup1.add(this.jrbCantDisminuirInternacion);
        this.jrbNingunoSeleccionado.setSelected(true);
        this.habilitarBotones(true,false,false,false,false,true);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void AsignarCasillas(){
        vnombre=this.txtNombre.getText().trim();
        //vfecha=this.txtFechaIngreso.getDate();
        vcantIngreso=this.txtCantidadIngreso.getText().trim();
        vcantInternada=this.txtCantidadInternaron.getText().trim();
        vcantExistente=this.txtCantidadExistente.getText().trim();
        vdescripcion=this.AreDescripcion.getText().trim();
    }
    public void habilitarTextosAumentoDisminucion(boolean a,boolean b,boolean c,boolean d){
        this.txtCantIngresoAum.setEnabled(a);
        this.txtCantInternaronAum.setEnabled(b);
        this.txtCantIngresoDis.setEnabled(c);
        this.txtCantInternaronDis.setEnabled(d);
        
       this.txtCantIngresoAum.setText("");
       this.txtCantInternaronAum.setText("");
       this.txtCantIngresoDis.setText("");
       this.txtCantInternaronDis.setText("");

       this.txtCantIngresoMod.setText(this.txtCantidadIngreso.getText().trim());
       this.txtCantInternaronMod.setText(this.txtCantidadInternaron.getText().trim());
       this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
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
    public void habilitarTextos(boolean habilita,boolean habilitaRadio)
   {
        this.txtCodigo.setEnabled(false);
        this.txtNombre.setEnabled(habilita);
        this.txtFechaIngreso.setEnabled(habilita);
        this.txtCantidadIngreso.setEnabled(habilita);
        this.txtCantidadInternaron.setEnabled(habilita);
        this.txtCantidadExistente.setEnabled(false);
        this.AreDescripcion.setEnabled(habilita);

        this.jrbNingunoSeleccionado.setEnabled(habilitaRadio);
        this.jrbCantAumentoIngreso.setEnabled(habilitaRadio);
        this.jrbCantAumentoInternacion.setEnabled(habilitaRadio);
        this.jrbCantDisminuirIngreso.setEnabled(habilitaRadio);
        this.jrbCantDisminuirInternacion.setEnabled(habilitaRadio);
   }
    public void limpiar()
    {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtFechaIngreso.setDate(null);
        this.txtCantidadIngreso.setText("");
        this.txtCantidadInternaron.setText("");
        this.txtCantidadExistente.setText("");
        this.AreDescripcion.setText("");

       this.txtCantIngresoMod.setText("");
       this.txtCantInternaronMod.setText("");
       this.txtCantExistenteMod.setText("");
       this.txtCantidadPrestado.setText("");

       this.txtCantIngresoAum.setText("");
       this.txtCantInternaronAum.setText("");
       this.txtCantIngresoDis.setText("");
       this.txtCantInternaronDis.setText("");

        this.txtNombre.requestFocus();
    }
    public void guardarArticulo()
    {
        this.AsignarCasillas();
        if(vnombre.isEmpty() || vcantIngreso.isEmpty() || vcantInternada.isEmpty() || vcantExistente.isEmpty() || this.txtFechaIngreso.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios ó verificar que la fecha este correcta");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE GUARDAR ESTE ARTICULO?","ARTICULO",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Cls_Articulo articulo=null;
                try {
                    articulo=new Cls_Articulo();
                    articulo.setCodigo_art(this.txtCodigo.getText());
                    articulo.setNombre_art(this.txtNombre.getText().trim());
                    articulo.setFechaingreso_art(this.txtFechaIngreso.getDate());
                    if(this.txtCantidadIngreso.getText().trim().equals("")){
                         articulo.setCantidad_igreso_art(0);
                    }else{
                         articulo.setCantidad_igreso_art(Integer.parseInt(this.txtCantidadIngreso.getText().trim()));
                    }
                    if(this.txtCantidadInternaron.getText().trim().equals("")){
                         articulo.setCantidad_internada_art(0);
                    }else{
                        articulo.setCantidad_internada_art(Integer.parseInt(this.txtCantidadInternaron.getText().trim()));
                    }
                    if(this.txtCantidadExistente.getText().trim().equals("")){
                         articulo.setCantidad_existente_art(0);
                    }else{
                        articulo.setCantidad_existente_art(Integer.parseInt(this.txtCantidadExistente.getText().trim()));
                    }
                    articulo.setDescripcion_art(this.AreDescripcion.getText().trim());

                    if(Cls_DaoArticulo.DaoAgregarArticulo(articulo)==true){
                        JOptionPane.showMessageDialog(this, "Registro guardado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this, "Error datos no registrados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
                habilitarTextos(false,false);
                this.habilitarBotones(true, false, false, false, false,true);
                this.guardar=false;
                this.modificar=false;
            }
        }
    }
    public void modificarArticulo()
    {
        this.AsignarCasillas();
        if(vnombre.isEmpty() || vcantIngreso.isEmpty() || vcantInternada.isEmpty() || vcantExistente.isEmpty() || this.txtFechaIngreso.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios ó verificar que la fecha este correcta");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE MODIFICAR ESTE ARTICULO?","ARTICULO",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Cls_Articulo articulo=null;
                try {
                    articulo=new Cls_Articulo();
                    articulo.setCodigo_art(this.txtCodigo.getText());
                    articulo.setNombre_art(this.txtNombre.getText().trim());
                    articulo.setFechaingreso_art(this.txtFechaIngreso.getDate());
                    if(this.txtCantIngresoMod.getText().trim().equals("")){
                         articulo.setCantidad_igreso_art(0);
                    }else{
                         articulo.setCantidad_igreso_art(Integer.parseInt(this.txtCantIngresoMod.getText().trim()));
                    }
                    if(this.txtCantInternaronMod.getText().trim().equals("")){
                         articulo.setCantidad_internada_art(0);
                    }else{
                        articulo.setCantidad_internada_art(Integer.parseInt(this.txtCantInternaronMod.getText().trim()));
                    }
                    if(this.txtCantExistenteMod.getText().trim().equals("")){
                         articulo.setCantidad_existente_art(0);
                    }else{
                        articulo.setCantidad_existente_art(Integer.parseInt(this.txtCantExistenteMod.getText().trim()));
                    }
                    articulo.setDescripcion_art(this.AreDescripcion.getText().trim());;

                    if(Cls_DaoArticulo.DaoActualizarArticulo(articulo)==true){
                        JOptionPane.showMessageDialog(this, "Registro Actualizado");
                    }else{
                        JOptionPane.showMessageDialog(this, "Error datos no actualizados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
                limpiar();
                habilitarTextos(false,false);
                this.habilitarBotones(true, false, false, false, false,true);
                this.guardar=false;
                this.modificar=false;
            }
        }
    }
    public void eliminarArticulo()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE ELIMINAR ESTE ARTICULO?","ARTICULO",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(Cls_DaoArticulo.DaoEliminarArticulo(codigo)==true){
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }
            limpiar();
            habilitarTextos(false,false);
            this.habilitarBotones(true, false, false, false, false,true);
        }
    }
    public void obtenerArticulo(String codigo){
        Cls_Articulo articulo=null;
        try{
            articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(codigo);
            if (articulo!=null){
                this.txtCodigo.setText(articulo.getCodigo_art());
                this.txtNombre.setText(articulo.getNombre_art());
                this.txtFechaIngreso.setDate(articulo.getFechaingreso_art());
                this.txtCantidadIngreso.setText(String.valueOf(articulo.getCantidad_igreso_art()));
                this.txtCantidadInternaron.setText(String.valueOf(articulo.getCantidad_internada_art()));
                this.txtCantidadExistente.setText(String.valueOf(articulo.getCantidad_existente_art()));
                this.txtCantIngresoMod.setText(String.valueOf(articulo.getCantidad_igreso_art()));
                this.txtCantInternaronMod.setText(String.valueOf(articulo.getCantidad_internada_art()));
                this.txtCantExistenteMod.setText(String.valueOf(articulo.getCantidad_existente_art()));
                this.AreDescripcion.setText(articulo.getDescripcion_art());
                int cantIngreso=Integer.parseInt(this.txtCantidadIngreso.getText().trim());
                int cantInternaron=Integer.parseInt(this.txtCantidadInternaron.getText().trim());
                int cantExistente=Integer.parseInt(this.txtCantidadExistente.getText().trim());
                int cantPrestada=cantIngreso-(cantInternaron+cantExistente);
                txtCantidadPrestado.setText(String.valueOf(cantPrestada));
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCantidadInternaron = new javax.swing.JTextField();
        txtCantidadIngreso = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtCantidadExistente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreDescripcion = new javax.swing.JTextArea();
        txtCantIngresoMod = new javax.swing.JTextField();
        txtCantInternaronMod = new javax.swing.JTextField();
        txtCantExistenteMod = new javax.swing.JTextField();
        txtCantIngresoAum = new javax.swing.JTextField();
        txtCantInternaronAum = new javax.swing.JTextField();
        txtCantIngresoDis = new javax.swing.JTextField();
        txtCantInternaronDis = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCantidadPrestado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jrbCantAumentoIngreso = new javax.swing.JRadioButton();
        jrbCantAumentoInternacion = new javax.swing.JRadioButton();
        jrbCantDisminuirIngreso = new javax.swing.JRadioButton();
        jrbCantDisminuirInternacion = new javax.swing.JRadioButton();
        jrbNingunoSeleccionado = new javax.swing.JRadioButton();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Articulo_Mantenimiento.class);
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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 615, 60));

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
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtCantidadInternaron.setEnabled(false);
        txtCantidadInternaron.setName("txtCantidadInternaron"); // NOI18N
        txtCantidadInternaron.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadInternaronKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadInternaronKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantidadInternaron, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 70, -1));

        txtCantidadIngreso.setEnabled(false);
        txtCantidadIngreso.setName("txtCantidadIngreso"); // NOI18N
        txtCantidadIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadIngresoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadIngresoKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantidadIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 70, -1));

        txtNombre.setEnabled(false);
        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel5.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 490, -1));

        txtCodigo.setEnabled(false);
        txtCodigo.setName("txtCodigo"); // NOI18N
        jPanel5.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 130, -1));

        btnBuscar.setIcon(resourceMap.getIcon("btnBuscar.icon")); // NOI18N
        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setEnabled(false);
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 95, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        txtFechaIngreso.setDateFormatString(resourceMap.getString("txtFechaIngreso.dateFormatString")); // NOI18N
        txtFechaIngreso.setEnabled(false);
        txtFechaIngreso.setName("txtFechaIngreso"); // NOI18N
        txtFechaIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaIngresoKeyTyped(evt);
            }
        });
        jPanel5.add(txtFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 170, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        txtCantidadExistente.setText(resourceMap.getString("txtCantidadExistente.text")); // NOI18N
        txtCantidadExistente.setEnabled(false);
        txtCantidadExistente.setName("txtCantidadExistente"); // NOI18N
        txtCantidadExistente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadExistenteKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantidadExistente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 70, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        AreDescripcion.setColumns(20);
        AreDescripcion.setRows(5);
        AreDescripcion.setEnabled(false);
        AreDescripcion.setName("AreDescripcion"); // NOI18N
        jScrollPane1.setViewportView(AreDescripcion);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 570, 60));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 590, 90));

        txtCantIngresoMod.setText(resourceMap.getString("txtCantIngresoMod.text")); // NOI18N
        txtCantIngresoMod.setEnabled(false);
        txtCantIngresoMod.setName("txtCantIngresoMod"); // NOI18N
        jPanel5.add(txtCantIngresoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 90, -1));

        txtCantInternaronMod.setText(resourceMap.getString("txtCantInternaronMod.text")); // NOI18N
        txtCantInternaronMod.setEnabled(false);
        txtCantInternaronMod.setName("txtCantInternaronMod"); // NOI18N
        jPanel5.add(txtCantInternaronMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 90, -1));

        txtCantExistenteMod.setText(resourceMap.getString("txtCantExistenteMod.text")); // NOI18N
        txtCantExistenteMod.setEnabled(false);
        txtCantExistenteMod.setName("txtCantExistenteMod"); // NOI18N
        jPanel5.add(txtCantExistenteMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 90, -1));

        txtCantIngresoAum.setText(resourceMap.getString("txtCantIngresoAum.text")); // NOI18N
        txtCantIngresoAum.setEnabled(false);
        txtCantIngresoAum.setName("txtCantIngresoAum"); // NOI18N
        txtCantIngresoAum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantIngresoAumKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantIngresoAumKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantIngresoAum, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 70, -1));

        txtCantInternaronAum.setText(resourceMap.getString("txtCantInternaronAum.text")); // NOI18N
        txtCantInternaronAum.setEnabled(false);
        txtCantInternaronAum.setName("txtCantInternaronAum"); // NOI18N
        txtCantInternaronAum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantInternaronAumKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantInternaronAumKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantInternaronAum, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 70, -1));

        txtCantIngresoDis.setText(resourceMap.getString("txtCantIngresoDis.text")); // NOI18N
        txtCantIngresoDis.setEnabled(false);
        txtCantIngresoDis.setName("txtCantIngresoDis"); // NOI18N
        txtCantIngresoDis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantIngresoDisKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantIngresoDisKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantIngresoDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 70, -1));

        txtCantInternaronDis.setText(resourceMap.getString("txtCantInternaronDis.text")); // NOI18N
        txtCantInternaronDis.setEnabled(false);
        txtCantInternaronDis.setName("txtCantInternaronDis"); // NOI18N
        txtCantInternaronDis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantInternaronDisKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantInternaronDisKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantInternaronDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 70, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        txtCantidadPrestado.setText(resourceMap.getString("txtCantidadPrestado.text")); // NOI18N
        txtCantidadPrestado.setEnabled(false);
        txtCantidadPrestado.setName("txtCantidadPrestado"); // NOI18N
        jPanel5.add(txtCantidadPrestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 490, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, -1, -1));

        jrbCantAumentoIngreso.setText(resourceMap.getString("jrbCantAumentoIngreso.text")); // NOI18N
        jrbCantAumentoIngreso.setEnabled(false);
        jrbCantAumentoIngreso.setName("jrbCantAumentoIngreso"); // NOI18N
        jrbCantAumentoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCantAumentoIngresoActionPerformed(evt);
            }
        });
        jPanel5.add(jrbCantAumentoIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, -1));

        jrbCantAumentoInternacion.setText(resourceMap.getString("jrbCantAumentoInternacion.text")); // NOI18N
        jrbCantAumentoInternacion.setEnabled(false);
        jrbCantAumentoInternacion.setName("jrbCantAumentoInternacion"); // NOI18N
        jrbCantAumentoInternacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCantAumentoInternacionActionPerformed(evt);
            }
        });
        jPanel5.add(jrbCantAumentoInternacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        jrbCantDisminuirIngreso.setText(resourceMap.getString("jrbCantDisminuirIngreso.text")); // NOI18N
        jrbCantDisminuirIngreso.setEnabled(false);
        jrbCantDisminuirIngreso.setName("jrbCantDisminuirIngreso"); // NOI18N
        jrbCantDisminuirIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCantDisminuirIngresoActionPerformed(evt);
            }
        });
        jPanel5.add(jrbCantDisminuirIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        jrbCantDisminuirInternacion.setText(resourceMap.getString("jrbCantDisminuirInternacion.text")); // NOI18N
        jrbCantDisminuirInternacion.setEnabled(false);
        jrbCantDisminuirInternacion.setName("jrbCantDisminuirInternacion"); // NOI18N
        jrbCantDisminuirInternacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCantDisminuirInternacionActionPerformed(evt);
            }
        });
        jPanel5.add(jrbCantDisminuirInternacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));

        jrbNingunoSeleccionado.setText(resourceMap.getString("jrbNingunoSeleccionado.text")); // NOI18N
        jrbNingunoSeleccionado.setEnabled(false);
        jrbNingunoSeleccionado.setName("jrbNingunoSeleccionado"); // NOI18N
        jrbNingunoSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNingunoSeleccionadoActionPerformed(evt);
            }
        });
        jPanel5.add(jrbNingunoSeleccionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 310, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 615, 320));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 635, 410));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Jpn_Articulo_Buscar pn_buscar=new Jpn_Articulo_Buscar();
        buscar=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscar, "Buscar Articulo");
        pn_buscar.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscar);
}//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        habilitarTextos(true,false);
        this.habilitarBotones(false, true,false, false, true,false);
        Codigo=Cls_DaoArticulo.GenerarCodigoArticulo();
        txtCodigo.setText(Codigo);
        this.guardar=true;
        this.modificar=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(guardar==true)
        {
            guardarArticulo();
        }
        else
        {
            if(modificar==true)
            {
                modificarArticulo();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        habilitarTextos(true,true);
        this.txtCantidadIngreso.setEnabled(false);
        this.txtCantidadInternaron.setEnabled(false);
        this.txtCodigo.setEnabled(false);
        this.habilitarBotones(false, true,false, false, true,false);
        this.guardar=false;
        this.modificar=true;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.eliminarArticulo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.guardar=false;
        this.modificar=false;
        limpiar();
        habilitarTextos(false,false);
        this.habilitarBotones(true,false,false,false,false,true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCantidadIngresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadIngresoKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantidadIngreso, evt);
    }//GEN-LAST:event_txtCantidadIngresoKeyTyped

    private void txtCantidadInternaronKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadInternaronKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantidadInternaron,evt);
    }//GEN-LAST:event_txtCantidadInternaronKeyTyped

    private void txtCantidadExistenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadExistenteKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantidadExistente,evt);
    }//GEN-LAST:event_txtCantidadExistenteKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloLetras(txtNombre,evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCantidadInternaronKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadInternaronKeyReleased
       try{
           this.ValidarCantidadInternada();
       }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantidadInternaronKeyReleased

    private void txtCantidadIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadIngresoKeyReleased
        try{
            this.ValidarCantidadIngresar();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantidadIngresoKeyReleased

    private void txtCantIngresoAumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantIngresoAumKeyReleased
        try{
           this.ValidarCantidadIngresarAumentar();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantIngresoAumKeyReleased

    private void txtCantInternaronAumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantInternaronAumKeyReleased
        try{
            this.ValidarCantidadInternarAumentar();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantInternaronAumKeyReleased

    private void txtCantIngresoAumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantIngresoAumKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantIngresoAum,evt);
    }//GEN-LAST:event_txtCantIngresoAumKeyTyped

    private void txtCantInternaronAumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantInternaronAumKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantInternaronAum,evt);
    }//GEN-LAST:event_txtCantInternaronAumKeyTyped

    private void txtCantIngresoDisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantIngresoDisKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantIngresoDis,evt);
    }//GEN-LAST:event_txtCantIngresoDisKeyTyped

    private void txtCantInternaronDisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantInternaronDisKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantInternaronDis,evt);
    }//GEN-LAST:event_txtCantInternaronDisKeyTyped

    private void txtCantIngresoDisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantIngresoDisKeyReleased
        try{
            this.ValidarCantidadIngresarDisminuir();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantIngresoDisKeyReleased

    private void txtCantInternaronDisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantInternaronDisKeyReleased
        try{
             this.ValidarCantidadInternarDisminuir();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantInternaronDisKeyReleased

    private void txtFechaIngresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaIngresoKeyTyped
        
    }//GEN-LAST:event_txtFechaIngresoKeyTyped

    private void jrbCantAumentoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCantAumentoIngresoActionPerformed
        this.habilitarTextosAumentoDisminucion(true,false,false,false);
        this.txtCantIngresoAum.requestFocus();

    }//GEN-LAST:event_jrbCantAumentoIngresoActionPerformed

    private void jrbCantAumentoInternacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCantAumentoInternacionActionPerformed
        this.habilitarTextosAumentoDisminucion(false,true,false,false);
        this.txtCantInternaronAum.requestFocus();
    }//GEN-LAST:event_jrbCantAumentoInternacionActionPerformed

    private void jrbCantDisminuirIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCantDisminuirIngresoActionPerformed
        this.habilitarTextosAumentoDisminucion(false,false,true,false);
        this.txtCantIngresoDis.requestFocus();
    }//GEN-LAST:event_jrbCantDisminuirIngresoActionPerformed

    private void jrbCantDisminuirInternacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCantDisminuirInternacionActionPerformed
        this.habilitarTextosAumentoDisminucion(false,false,false,true);
        this.txtCantInternaronDis.requestFocus();
    }//GEN-LAST:event_jrbCantDisminuirInternacionActionPerformed

    private void jrbNingunoSeleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNingunoSeleccionadoActionPerformed
        this.habilitarTextosAumentoDisminucion(false,false,false,false);
    }//GEN-LAST:event_jrbNingunoSeleccionadoActionPerformed
    public void ValidarCantidadIngresar(){
                    this.txtCantidadExistente.setText("");
                    if(this.txtCantidadInternaron.getText().trim().equals("")){
                        this.txtCantidadExistente.setText(this.txtCantidadIngreso.getText().trim());
                    }else{
                        int cantInternada=Integer.parseInt(this.txtCantidadInternaron.getText().trim());
                        if(this.txtCantidadIngreso.getText().trim().equals("")){
                            this.txtCantidadIngreso.setText("");
                            this.txtCantidadInternaron.setText("");
                            this.txtCantidadExistente.setText("");
                        }else{
                            if(Integer.parseInt(this.txtCantidadIngreso.getText().trim())>=cantInternada){
                                int cantIngreso=Integer.parseInt(this.txtCantidadIngreso.getText().trim());
                                int cantidadExistente=cantIngreso-cantInternada;
                                this.txtCantidadExistente.setText(String.valueOf(cantidadExistente));
                            }else{
                                JOptionPane.showMessageDialog(this, "La cantidad a ingresar es < a la cantida a internar");
                                this.txtCantidadIngreso.setText("");
                                this.txtCantidadInternaron.setText("");
                                this.txtCantidadExistente.setText("");
                            }
                        }
                    }
    }
    public void ValidarCantidadInternada(){
                this.txtCantidadExistente.setText("");
                if(this.txtCantidadIngreso.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(this, "Primero debe estar la cantidad de ingreso");
                    this.txtCantidadInternaron.setText("");
                    this.txtCantidadIngreso.requestFocus();
                }else{
                    if(this.txtCantidadInternaron.getText().trim().equals("")){
                         this.txtCantidadExistente.setText(this.txtCantidadIngreso.getText().trim());
                    }else{
                        int cantIngreso=Integer.parseInt(this.txtCantidadIngreso.getText().trim());
                        if(Integer.parseInt(this.txtCantidadInternaron.getText().trim())<=cantIngreso){
                             int cantInternada=Integer.parseInt(this.txtCantidadInternaron.getText().trim());
                             int cantidadExistente=cantIngreso-cantInternada;
                             this.txtCantidadExistente.setText(String.valueOf(cantidadExistente));
                        }else{
                            JOptionPane.showMessageDialog(this, "Cantidad a internar debe ser <= a la cantidad ingresada");
                            this.txtCantidadInternaron.setText("");
                            this.txtCantidadExistente.setText("");
                            buscador=this.txtCantidadIngreso.getText().trim();
                            this.txtCantidadExistente.setText(buscador);
                        }
                    }
                }
    }
    public void ValidarCantidadIngresarAumentar(){
                        this.txtCantIngresoMod.setText("");
                        this.txtCantExistenteMod.setText("");
                        if(this.txtCantIngresoAum.getText().trim().equals("")){
                            this.txtCantIngresoMod.setText(this.txtCantidadIngreso.getText().trim());
                            this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                        }else{
                                int cantIngresoAum=Integer.parseInt(this.txtCantIngresoAum.getText().trim());
                                int cantIngreso=Integer.parseInt(this.txtCantidadIngreso.getText().trim());
                                int cantExistente=Integer.parseInt(this.txtCantidadExistente.getText().trim());
                                int cantidadIngresoMod=cantIngreso+cantIngresoAum;
                                int cantidadExistenteMod=cantExistente+cantIngresoAum;
                                this.txtCantIngresoMod.setText(String.valueOf(cantidadIngresoMod));
                                this.txtCantExistenteMod.setText(String.valueOf(cantidadExistenteMod));
                        }
                    
    }
    public void ValidarCantidadInternarAumentar(){
                        this.txtCantInternaronMod.setText("");
                        this.txtCantExistenteMod.setText("");
                        if(this.txtCantInternaronAum.getText().trim().equals("")){
                           this.txtCantInternaronMod.setText(this.txtCantidadInternaron.getText().trim());
                           this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                        }else{
                                int cantInternaronAum=Integer.parseInt(this.txtCantInternaronAum.getText().trim());
                                int cantInternaron=Integer.parseInt(this.txtCantidadInternaron.getText().trim());
                                int cantExistente=Integer.parseInt(this.txtCantidadExistente.getText().trim());
                                if(cantInternaronAum<=cantExistente){
                                    int cantidadInternaronMod=cantInternaron+cantInternaronAum;
                                    int cantidadExistenteMod=cantExistente-cantInternaronAum;
                                    this.txtCantInternaronMod.setText(String.valueOf(cantidadInternaronMod));
                                    this.txtCantExistenteMod.setText(String.valueOf(cantidadExistenteMod));
                                }else{
                                    JOptionPane.showMessageDialog(this, "Cantidad a internar a umentar debe ser <= a la cantidad existente");
                                    this.txtCantInternaronAum.setText("");
                                    this.txtCantInternaronMod.setText("");
                                    this.txtCantExistenteMod.setText("");
                                    this.txtCantInternaronMod.setText(this.txtCantidadInternaron.getText().trim());
                                    this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                                }
                        }

    }
    public void ValidarCantidadIngresarDisminuir(){
                        this.txtCantIngresoMod.setText("");
                        this.txtCantExistenteMod.setText("");
                        if(this.txtCantIngresoDis.getText().trim().equals("")){
                            this.txtCantIngresoMod.setText(this.txtCantidadIngreso.getText().trim());
                            this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                        }else{
                                int cantIngresoDis=Integer.parseInt(this.txtCantIngresoDis.getText().trim());
                                int cantIngreso=Integer.parseInt(this.txtCantidadIngreso.getText().trim());
                                int cantExistente=Integer.parseInt(this.txtCantidadExistente.getText().trim());
                                int cantInternaron=Integer.parseInt(this.txtCantidadInternaron.getText().trim());
                                int cantPrestada=cantIngreso-(cantInternaron+cantExistente);
                                if(cantIngresoDis<=cantExistente){
                                    int cantidadIngresoMod=cantIngreso-cantIngresoDis;
                                    int cantidadExistenteMod=cantExistente-cantIngresoDis;
                                    this.txtCantIngresoMod.setText(String.valueOf(cantidadIngresoMod));
                                    this.txtCantExistenteMod.setText(String.valueOf(cantidadExistenteMod));
                                }else{
                                    JOptionPane.showMessageDialog(this, "Cantidad a ingresar a disminuir debe ser <= "+String.valueOf(cantExistente)+" porque hay "+String.valueOf(cantPrestada)+" articulos prestados");
                                    this.txtCantIngresoDis.setText("");
                                    this.txtCantIngresoMod.setText("");
                                    this.txtCantExistenteMod.setText("");
                                    this.txtCantIngresoMod.setText(this.txtCantidadInternaron.getText().trim());
                                    this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                                }
                        }

    }
    public void ValidarCantidadInternarDisminuir(){
                        this.txtCantInternaronMod.setText("");
                        this.txtCantExistenteMod.setText("");
                        if(this.txtCantInternaronDis.getText().trim().equals("")){
                           this.txtCantInternaronMod.setText(this.txtCantidadInternaron.getText().trim());
                           this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                        }else{
                                int cantInternaronDis=Integer.parseInt(this.txtCantInternaronDis.getText().trim());
                                int cantInternaron=Integer.parseInt(this.txtCantidadInternaron.getText().trim());
                                int cantExistente=Integer.parseInt(this.txtCantidadExistente.getText().trim());
                                if(cantInternaronDis<=cantInternaron){
                                    int cantidadInternaronMod=cantInternaron-cantInternaronDis;
                                    int cantidadExistenteMod=cantExistente+cantInternaronDis;
                                    this.txtCantInternaronMod.setText(String.valueOf(cantidadInternaronMod));
                                    this.txtCantExistenteMod.setText(String.valueOf(cantidadExistenteMod));
                                }else{
                                    JOptionPane.showMessageDialog(this, "Cantidad a internar a disminuir debe ser <= a la cantidad internada");
                                    this.txtCantInternaronDis.setText("");
                                    this.txtCantInternaronMod.setText("");
                                    this.txtCantExistenteMod.setText("");
                                    this.txtCantInternaronMod.setText(this.txtCantidadInternaron.getText().trim());
                                    this.txtCantExistenteMod.setText(this.txtCantidadExistente.getText().trim());
                                }
                        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreDescripcion;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrbCantAumentoIngreso;
    private javax.swing.JRadioButton jrbCantAumentoInternacion;
    private javax.swing.JRadioButton jrbCantDisminuirIngreso;
    private javax.swing.JRadioButton jrbCantDisminuirInternacion;
    private javax.swing.JRadioButton jrbNingunoSeleccionado;
    private javax.swing.JTextField txtCantExistenteMod;
    private javax.swing.JTextField txtCantIngresoAum;
    private javax.swing.JTextField txtCantIngresoDis;
    private javax.swing.JTextField txtCantIngresoMod;
    private javax.swing.JTextField txtCantInternaronAum;
    private javax.swing.JTextField txtCantInternaronDis;
    private javax.swing.JTextField txtCantInternaronMod;
    private javax.swing.JTextField txtCantidadExistente;
    private javax.swing.JTextField txtCantidadIngreso;
    private javax.swing.JTextField txtCantidadInternaron;
    private javax.swing.JTextField txtCantidadPrestado;
    private javax.swing.JTextField txtCodigo;
    private com.toedter.calendar.JDateChooser txtFechaIngreso;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
