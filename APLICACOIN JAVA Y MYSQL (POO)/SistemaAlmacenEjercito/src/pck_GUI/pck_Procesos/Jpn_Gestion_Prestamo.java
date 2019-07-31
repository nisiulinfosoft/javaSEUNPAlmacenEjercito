package pck_GUI.pck_Procesos;

import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.FrameView;
import pck_GUI.pck_Mantenimiento.Jpn_Cliente_Casual;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoArticulo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoCliente;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoDetallePrestamo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoPedidoPrestamo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUsuario;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Articulo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Cliente;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_DetallePrestamo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoPrestamo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;
import pck_utiles.Utiles_Panel;
import sistemaalmacenejercito.SistemaAlmacenEjercitoView;

public class Jpn_Gestion_Prestamo extends JP_Modelo {

    JInternalFrame buscar,jif_unidad_buscar,buscarcliente,buscarusuario;
    String vcodCliente,vcodUsuario,vcodPrestamo;
    String codigo="";
    String nombre="";
    String CodPedidoPrestamo=" ";

    public Jpn_Gestion_Prestamo() {
        initComponents();
        this.buttonGroup1.add(this.jrbCodigo);
        this.buttonGroup1.add(this.jrbNombreCompleto);
        this.jrbNombreCompleto.setSelected(true);
        this.ActualizarBusqueda();
        this.HabilitarTextos(false);
        this.HabilitarBotones(true,false,false,false,false,false,false,false,false);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void Limpiar(){
        this.txtCodigoArt.setText("");
        this.txtNombreArt.setText("");
        this.txtCantExistArt.setText("");
        this.txtCantAPrestarArt.setText("");
    }
    public void LimpiarGeneral(){
        this.txtCodigoCliente.setText("");
        this.txtNombreCliente.setText("");
        this.txtCodigoUsuario.setText("");
        this.txtNombreUsuario.setText("");
        this.txtCodigoPedido.setText("");
        //this.txtBuscador.setText("");
        this.jdcFechaPrestamo.setDate(null);
        if(this.tablaDetalleArticulo.getRowCount()>0){
            int limite = this.tablaDetalleArticulo.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
            DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
            for (int f = 0; f < limite; f++) {
                modelo.removeRow(0);
            }
        }
    }
    public void HabilitarBotones(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h,boolean l){
        this.btnNuevo.setEnabled(a);
        this.btnGuardar.setEnabled(b);
        this.btnLimpiar.setEnabled(c);
        this.btnBuscarCliente.setEnabled(d);
        this.btnAgregarNuevoCliente.setEnabled(e);
        this.btnBuscarUsuario.setEnabled(f);
        this.btnAgregar.setEnabled(g);
        this.btnEliminar.setEnabled(h);
        this.btnCancelar.setEnabled(l);
    }
    public void HabilitarTextos(boolean a){
        this.jdcFechaPrestamo.setEnabled(a);
        this.txtCantAPrestarArt.setEnabled(a);
    }
    public void ActualizarBusqueda(){
        List lista = null;
        try {
            if(this.jrbCodigo.isSelected())
            {
                codigo=this.txtBuscador.getText().trim();
                if(codigo.compareTo("")==0){
                    lista=Cls_DaoArticulo.DaoListarArticulosCodigo("");
                }
                else{
                    lista=Cls_DaoArticulo.DaoListarArticulosCodigo(codigo);
                }
            }else if(this.jrbNombreCompleto.isSelected())
            {
                nombre=this.txtBuscador.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=Cls_DaoArticulo.DaoListarArticulosNombreCompleto("");
                }
                else{
                    lista=Cls_DaoArticulo.DaoListarArticulosNombreCompleto(nombre);
                }
            }
            RecargarTabla(lista);
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }
    public void RecargarTabla(List lista){
        Cls_Articulo articulo=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.tablaArticulo.getModel();
            while(this.tablaArticulo.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                articulo=(Cls_Articulo)lista.get(i);
                fila=new String[5];
                fila[0]=articulo.getCodigo_art();
                fila[1]=articulo.getNombre_art();
                fila[2]=String.valueOf(articulo.getCantidad_igreso_art());
                fila[3]=String.valueOf(articulo.getCantidad_internada_art());
                fila[4]=String.valueOf(articulo.getCantidad_existente_art());
                modelo.addRow(fila);
            }
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }
    public boolean VerificarArticuloRegistradoTabla() {
        boolean valor = false;
        if(this.tablaDetalleArticulo.getRowCount()>0){
            int fila = this.tablaDetalleArticulo.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
            for (int f = 0; f < fila; f++) {
                if (this.txtCodigoArt.getText().trim().equals(String.valueOf(modelo.getValueAt(f,0)))){
                    valor = true;
                }
            }
        }
        return valor;
    }
    public void ActualizarCantidadProducto(String codigo,String cantidadExistente) {
        if(this.tablaDetalleArticulo.getRowCount()>0){
            int sumaCantidad=0;
            int restaCantidad=0;
            boolean respuesta=false;
            int fila = this.tablaDetalleArticulo.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
            for (int f = 0; f < fila; f++) {
                if (codigo.equals(String.valueOf(modelo.getValueAt(f,0)))){
                    sumaCantidad=sumaCantidad+Integer.parseInt(String.valueOf(modelo.getValueAt(f,2)));
                    respuesta=true;
                }
            }
            if(respuesta=true){
                restaCantidad=Integer.parseInt(cantidadExistente)- sumaCantidad;
                this.txtCantExistArt.setText(String.valueOf(restaCantidad));
            }else{
                 this.txtCantExistArt.setText(cantidadExistente);
            }
        }else{
            this.txtCantExistArt.setText(cantidadExistente);
        }
    }
    public void ActualizarCantidadProdTabla(String codigo,String cantAPrestar) {
        int sumaCantTabla=0;
        int fila = this.tablaDetalleArticulo.getRowCount();
        DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
        for (int f = 0; f < fila; f++) {
           if (codigo.equals(String.valueOf(modelo.getValueAt(f,0)))){
               sumaCantTabla=Integer.parseInt(String.valueOf(cantAPrestar))+Integer.parseInt(String.valueOf(modelo.getValueAt(f,2)));
               modelo.setValueAt(String.valueOf(sumaCantTabla),f,2);
           }
        }
    }
    public void obtenerCliente(String codigo){
        Cls_Cliente cliente=null;
        try{
            cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(codigo);
            if (cliente!=null){
                this.txtCodigoCliente.setText(cliente.getCodigo_cli());
                this.txtNombreCliente.setText(cliente.getNombre_cli());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void AsignarCasillas(){
        vcodCliente=this.txtCodigoCliente.getText().trim();
        vcodUsuario=this.txtCodigoUsuario.getText().trim();
        vcodPrestamo=this.txtCodigoPedido.getText().trim();
    }
    public void GenerarPrestamo(){
        this.AsignarCasillas();
        if(vcodCliente.isEmpty() || vcodUsuario.isEmpty() || vcodPrestamo.isEmpty() || this.jdcFechaPrestamo.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios");
        }else{
            if(this.tablaDetalleArticulo.getRowCount()>0){
                int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE EFECTUAR EL PRESTAMO?","PRESTAMO",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    boolean respuesta1=false;
                    boolean respuesta2=false;
                    boolean respuesta3=false;
                    Cls_PedidoPrestamo pedido=null;
                    Cls_DetallePrestamo detallePrestamo=null;
                    Cls_Cliente cliente=null;
                    Cls_Usuario usuario=null;
                    Cls_Articulo articulo=null;
                    try {
                        //PARA LA TABLA PEDIDO DE PRESTAMO
                        pedido=new Cls_PedidoPrestamo();
                        pedido.setCodigo_prest(this.txtCodigoPedido.getText().trim());
                        cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(this.txtCodigoCliente.getText().trim());
                        pedido.setCliente(cliente);
                        usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(this.txtCodigoUsuario.getText().trim());
                        pedido.setUsuario(usuario);
                        pedido.setFechaprestamo_prest(this.jdcFechaPrestamo.getDate());
                        if(Cls_DaoPedidoPrestamo.DaoAgregarPedidoPrestamo(pedido)==true){
                            respuesta1=true;
                        }else{
                            JOptionPane.showMessageDialog(this, "Error al registrar pedido");
                        }
                        //PARA LA TABLA DETALLE DE PRESTAMO
                        detallePrestamo=new Cls_DetallePrestamo();
                        articulo=new Cls_Articulo();
                        int limite = this.tablaDetalleArticulo.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
                        DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
                        for (int f = 0; f < limite; f++) {  //RACORREMOS LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ, PARA IR REGISTRANDO UNO POR UNO AL TABLA DETALLEPEDIDO DE LA BD
                            articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(String.valueOf(modelo.getValueAt(f,0)));
                            detallePrestamo.setArticulo(articulo);
                            pedido=Cls_DaoPedidoPrestamo.DaoObtenerPedidoPrestamoPorCodigo(this.txtCodigoPedido.getText().trim());
                            detallePrestamo.setPrestamo(pedido);
                            detallePrestamo.setCantidad_prestada_prest(Integer.parseInt(String.valueOf(modelo.getValueAt(f,2))));
                            detallePrestamo.setCantidad_devuelta_prest(0);
                            if(Cls_DaoDetallePrestamo.DaoAgregarDetallePedidoPrestamo(detallePrestamo)==true){
                                respuesta2=true;
                            }else{
                                JOptionPane.showMessageDialog(this, "Error al registrar detalle pedido");
                            }
                            //POR ARTICULO PRESTADO SE DEBE REALIZAR UNA ACTUALIZACION DE LA CANTIDAD EXISTEN EN AL TABLA ARTICULO DE LA BD
                            if(Cls_DaoArticulo.ActualizarCantidadExistentePrestamo(String.valueOf(modelo.getValueAt(f,0)),Integer.parseInt(String.valueOf(modelo.getValueAt(f,2))))==true){
                                respuesta3=true;
                            }else{
                                JOptionPane.showMessageDialog(this, "Error al actualizar cantidad existente articulo");
                            }
                        }
                        if(respuesta1==true && respuesta2==true && respuesta3==true){
                            JOptionPane.showMessageDialog(this, "El prestamo se efectuo con exito");
                            this.txtBuscador.setText("");
                            this.ActualizarBusqueda();
                        }else{
                            JOptionPane.showMessageDialog(this, "Error prestamo no efectuado");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }
                    this.Limpiar();
                    this.LimpiarGeneral();
                    this.HabilitarTextos(false);
                    this.HabilitarBotones(true, false, false,false,false,false,false,false,false);
                }else{
                    JOptionPane.showMessageDialog(null, "Operacion cancelada");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay articulos en la lista detalle");
            }
        }
    }
    public void obtenerUsuario(String codigo){
        Cls_Usuario usuario=null;
        try{
            usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(codigo);
            if (usuario!=null){
                this.txtCodigoUsuario.setText(usuario.getCodigo_usu());
                this.txtNombreUsuario.setText(usuario.getNombre_usu());
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
        txtCodigoCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        txtNombreCliente = new javax.swing.JTextField();
        btnAgregarNuevoCliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtCodigoUsuario = new javax.swing.JTextField();
        btnBuscarUsuario = new javax.swing.JButton();
        txtNombreUsuario = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jrbCodigo = new javax.swing.JRadioButton();
        jrbNombreCompleto = new javax.swing.JRadioButton();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArticulo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCantExistArt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoArt = new javax.swing.JTextField();
        txtNombreArt = new javax.swing.JTextField();
        txtCantAPrestarArt = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jdcFechaPrestamo = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleArticulo = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Gestion_Prestamo.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigoCliente.setText(resourceMap.getString("txtCodigoCliente.text")); // NOI18N
        txtCodigoCliente.setEnabled(false);
        txtCodigoCliente.setName("txtCodigoCliente"); // NOI18N
        jPanel2.add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, -1));

        btnBuscarCliente.setIcon(resourceMap.getIcon("btnBuscarCliente.icon")); // NOI18N
        btnBuscarCliente.setText(resourceMap.getString("btnBuscarCliente.text")); // NOI18N
        btnBuscarCliente.setToolTipText(resourceMap.getString("btnBuscarCliente.toolTipText")); // NOI18N
        btnBuscarCliente.setEnabled(false);
        btnBuscarCliente.setName("btnBuscarCliente"); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 30, -1));

        txtNombreCliente.setText(resourceMap.getString("txtNombreCliente.text")); // NOI18N
        txtNombreCliente.setEnabled(false);
        txtNombreCliente.setName("txtNombreCliente"); // NOI18N
        jPanel2.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 190, -1));

        btnAgregarNuevoCliente.setIcon(resourceMap.getIcon("btnAgregarNuevoCliente.icon")); // NOI18N
        btnAgregarNuevoCliente.setText(resourceMap.getString("btnAgregarNuevoCliente.text")); // NOI18N
        btnAgregarNuevoCliente.setToolTipText(resourceMap.getString("btnAgregarNuevoCliente.toolTipText")); // NOI18N
        btnAgregarNuevoCliente.setEnabled(false);
        btnAgregarNuevoCliente.setName("btnAgregarNuevoCliente"); // NOI18N
        btnAgregarNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNuevoClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 30, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 340, 55));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigoUsuario.setEnabled(false);
        txtCodigoUsuario.setName("txtCodigoUsuario"); // NOI18N
        jPanel3.add(txtCodigoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, -1));

        btnBuscarUsuario.setIcon(resourceMap.getIcon("btnBuscarUsuario.icon")); // NOI18N
        btnBuscarUsuario.setToolTipText(resourceMap.getString("btnBuscarUsuario.toolTipText")); // NOI18N
        btnBuscarUsuario.setEnabled(false);
        btnBuscarUsuario.setName("btnBuscarUsuario"); // NOI18N
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 30, -1));

        txtNombreUsuario.setEnabled(false);
        txtNombreUsuario.setName("txtNombreUsuario"); // NOI18N
        jPanel3.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 220, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 340, 55));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jrbCodigo.setText(resourceMap.getString("jrbCodigo.text")); // NOI18N
        jrbCodigo.setName("jrbCodigo"); // NOI18N
        jPanel4.add(jrbCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jrbNombreCompleto.setText(resourceMap.getString("jrbNombreCompleto.text")); // NOI18N
        jrbNombreCompleto.setName("jrbNombreCompleto"); // NOI18N
        jPanel4.add(jrbNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        txtBuscador.setText(resourceMap.getString("txtBuscador.text")); // NOI18N
        txtBuscador.setToolTipText(resourceMap.getString("txtBuscador.toolTipText")); // NOI18N
        txtBuscador.setName("txtBuscador"); // NOI18N
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });
        jPanel4.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 210, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre Articulo", "Cantidad Ingreso", "Cantidad Internada", "Cantidad Existente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaArticulo.setName("tablaArticulo"); // NOI18N
        tablaArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticuloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaArticulo);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 660, 90));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        txtCantExistArt.setText(resourceMap.getString("txtCantExistArt.text")); // NOI18N
        txtCantExistArt.setEnabled(false);
        txtCantExistArt.setName("txtCantExistArt"); // NOI18N
        jPanel4.add(txtCantExistArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 50, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        txtCodigoArt.setText(resourceMap.getString("txtCodigoArt.text")); // NOI18N
        txtCodigoArt.setEnabled(false);
        txtCodigoArt.setName("txtCodigoArt"); // NOI18N
        jPanel4.add(txtCodigoArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 60, -1));

        txtNombreArt.setText(resourceMap.getString("txtNombreArt.text")); // NOI18N
        txtNombreArt.setEnabled(false);
        txtNombreArt.setName("txtNombreArt"); // NOI18N
        jPanel4.add(txtNombreArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 180, -1));

        txtCantAPrestarArt.setText(resourceMap.getString("txtCantAPrestarArt.text")); // NOI18N
        txtCantAPrestarArt.setEnabled(false);
        txtCantAPrestarArt.setName("txtCantAPrestarArt"); // NOI18N
        txtCantAPrestarArt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantAPrestarArtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantAPrestarArtKeyTyped(evt);
            }
        });
        jPanel4.add(txtCantAPrestarArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 50, -1));

        btnAgregar.setIcon(resourceMap.getIcon("btnAgregar.icon")); // NOI18N
        btnAgregar.setText(resourceMap.getString("btnAgregar.text")); // NOI18N
        btnAgregar.setToolTipText(resourceMap.getString("btnAgregar.toolTipText")); // NOI18N
        btnAgregar.setEnabled(false);
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 100, -1));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setText(resourceMap.getString("btnEliminar.text")); // NOI18N
        btnEliminar.setToolTipText(resourceMap.getString("btnEliminar.toolTipText")); // NOI18N
        btnEliminar.setEnabled(false);
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 100, -1));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 100, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtCodigoPedido.setText(resourceMap.getString("txtCodigoPedido.text")); // NOI18N
        txtCodigoPedido.setEnabled(false);
        txtCodigoPedido.setName("txtCodigoPedido"); // NOI18N
        jPanel4.add(txtCodigoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 70, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jdcFechaPrestamo.setDateFormatString(resourceMap.getString("jdcFechaPrestamo.dateFormatString")); // NOI18N
        jdcFechaPrestamo.setEnabled(false);
        jdcFechaPrestamo.setName("jdcFechaPrestamo"); // NOI18N
        jPanel4.add(jdcFechaPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 100, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 680, 220));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel5.border.titleColor"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaDetalleArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre de Articulo", "Cantidad a Prestar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalleArticulo.setName("tablaDetalleArticulo"); // NOI18N
        jScrollPane2.setViewportView(tablaDetalleArticulo);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 660, 90));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 295, 680, 120));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel6.border.titleColor"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setEnabled(false);
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel6.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 100, -1));

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel6.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 100, -1));

        btnLimpiar.setIcon(resourceMap.getIcon("btnLimpiar.icon")); // NOI18N
        btnLimpiar.setText(resourceMap.getString("btnLimpiar.text")); // NOI18N
        btnLimpiar.setEnabled(false);
        btnLimpiar.setName("btnLimpiar"); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel6.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 100, -1));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setText(resourceMap.getString("btnCerrar.text")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel6.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 100, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 415, 680, 60));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 485));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNuevoClienteActionPerformed
        if(this.jif_unidad_buscar==null||jif_unidad_buscar.isClosed()){//si esta cerrado
           Jpn_Cliente_Casual unidadbuscar=new Jpn_Cliente_Casual();
           jif_unidad_buscar=Utiles_Panel.creaVentanaInterna_Panel((FrameView)this.getRefDep(), unidadbuscar, "Cliente Casual");
           unidadbuscar.setRefDep((FrameView)this.getRefDep());//la referencia de quien depende
           unidadbuscar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_unidad_buscar);
       }
    }//GEN-LAST:event_btnAgregarNuevoClienteActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        Jpn_Gestion_Prestamo_BuscarCliente pn_buscarcliente=new Jpn_Gestion_Prestamo_BuscarCliente();
        buscarcliente=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscarcliente, "Buscar Cliente");
        pn_buscarcliente.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscarcliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarcliente);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        Jpn_Gestion_Prestamo_BuscarUsuario pn_buscarusuario=new Jpn_Gestion_Prestamo_BuscarUsuario();
        buscarusuario=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscarusuario, "Buscar Usuario");
        pn_buscarusuario.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscarusuario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarusuario);
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        CodPedidoPrestamo=Cls_DaoPedidoPrestamo.GenerarCodigoPedPrestamo();
        txtCodigoPedido.setText(CodPedidoPrestamo);
        this.Limpiar();
        this.HabilitarTextos(true);
        this.HabilitarBotones(false,true,true,true,true,true,true,true,true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        this.ActualizarBusqueda();
    }//GEN-LAST:event_txtBuscadorKeyReleased

    private void tablaArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArticuloMouseClicked
        String ccodigo=(String)this.tablaArticulo.getValueAt(this.tablaArticulo.getSelectedRow(),0);
        String cnombre=(String)this.tablaArticulo.getValueAt(this.tablaArticulo.getSelectedRow(),1);
        String ccantExistente=(String)this.tablaArticulo.getValueAt(this.tablaArticulo.getSelectedRow(),4);
        this.txtCodigoArt.setText(ccodigo);
        this.txtNombreArt.setText(cnombre);
        this.ActualizarCantidadProducto(ccodigo,ccantExistente);
        this.txtCantAPrestarArt.setText("");
        this.txtCantAPrestarArt.requestFocus();
    }//GEN-LAST:event_tablaArticuloMouseClicked

    private void txtCantAPrestarArtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantAPrestarArtKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantAPrestarArt, evt);
    }//GEN-LAST:event_txtCantAPrestarArtKeyTyped

    private void txtCantAPrestarArtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantAPrestarArtKeyReleased
        try{
            this.ValidarCantidadIngresarPresar();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantAPrestarArtKeyReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(this.txtCodigoArt.getText().trim().isEmpty() || this.txtNombreArt.getText().trim().isEmpty() || this.txtCantAPrestarArt.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "No a seleccionado ningun articulo ó falta ingresar cantidad a prestar");
        }else{
            if(VerificarArticuloRegistradoTabla()==false){
               String fila[];
               DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
               fila=new String[3];
               fila[0]=this.txtCodigoArt.getText().trim();
               fila[1]=this.txtNombreArt.getText().trim();
               fila[2]=this.txtCantAPrestarArt.getText().trim();
               modelo.addRow(fila);
               this.Limpiar();
           }else{
                int respuesta=JOptionPane.showConfirmDialog(null,"¿ARTICULO YA EXISTE EN LA LISTA DETALLE, SI ACEPTA SE AUMENTARA LA CANTIDAD A PRESTAR?","MENSAJE",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    ActualizarCantidadProdTabla(this.txtCodigoArt.getText().trim(),this.txtCantAPrestarArt.getText().trim());
                    this.Limpiar();
                    JOptionPane.showMessageDialog(null, "Se aumento la cantidad a prestar");
                }else{
                    JOptionPane.showMessageDialog(null, "Operacion cancelada, no surgio ningun aumento de la cantidad de prestamo");
                }
           }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleArticulo.getModel();
        if(this.tablaDetalleArticulo.getRowCount()>0){
            int fila = this.tablaDetalleArticulo.getSelectedRow();
            if (fila >= 0) {
                int respuesta=JOptionPane.showConfirmDialog(null,"¿SEGURO DE ELIMINAR EL ARTICULO DE LA LISTA DETALLE?","MENSAJE",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    modelo.removeRow(fila);
                    this.Limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Operacion cancelada");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se encuentra ningun articulo seleccionado en la lista de detalle para eliminar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay articulos en la lista detalle");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.GenerarPrestamo();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.Limpiar();
        this.LimpiarGeneral();
        this.HabilitarTextos(false);
        this.HabilitarBotones(true,false,false,false,false,false,false,false,false);
    }//GEN-LAST:event_btnLimpiarActionPerformed
    public void ValidarCantidadIngresarPresar(){
        if(this.txtCantExistArt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Seleccionar un articulo de la lista");
            this.txtCantAPrestarArt.setText("");
        }else{
            if(this.txtCantAPrestarArt.getText().trim().equals("")){
                this.txtCantAPrestarArt.setText("");
            }else{
                int cantExisArt=Integer.parseInt(this.txtCantExistArt.getText().trim());
                int cantAPrestarArt=Integer.parseInt(this.txtCantAPrestarArt.getText().trim());
                if(cantAPrestarArt>cantExisArt){
                    JOptionPane.showMessageDialog(this, "No hay cantidad sufiente de articulos para prestar");
                    this.txtCantAPrestarArt.setText("");
                }else{
                    if(cantAPrestarArt==0){
                        JOptionPane.showMessageDialog(this, "Como minimo debe prestar 1 articulo");
                        this.txtCantAPrestarArt.setText("");
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarNuevoCliente;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFechaPrestamo;
    private javax.swing.JRadioButton jrbCodigo;
    private javax.swing.JRadioButton jrbNombreCompleto;
    private javax.swing.JTable tablaArticulo;
    private javax.swing.JTable tablaDetalleArticulo;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCantAPrestarArt;
    private javax.swing.JTextField txtCantExistArt;
    private javax.swing.JTextField txtCodigoArt;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoPedido;
    private javax.swing.JTextField txtCodigoUsuario;
    private javax.swing.JTextField txtNombreArt;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables

}
