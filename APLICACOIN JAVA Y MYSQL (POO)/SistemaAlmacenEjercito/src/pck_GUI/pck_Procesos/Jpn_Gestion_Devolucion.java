package pck_GUI.pck_Procesos;

import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.FrameView;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoArticulo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoCliente;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoDetalleDevolucion;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoDetallePrestamo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoPedidoDevolucion;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoPedidoPrestamo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUsuario;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Articulo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Cliente;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_DetalleDevolucion;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoDevolucion;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_PedidoPrestamo;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Usuario;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;
import pck_utiles.Utiles_Panel;

public class Jpn_Gestion_Devolucion extends JP_Modelo {

    JInternalFrame buscarcliente,buscarusuario;
    String vcodCliente,vcodUsuario,vcodDevolucion;
    String Codigo=" ";

    public Jpn_Gestion_Devolucion() {
        initComponents();
        this.HabilitarTextos(false);
        this.HabilitarBotones(true,false,false,false,false,false,false,false);
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void Limpiar(){
        this.txtCodigoPrestamo.setText("");
        this.txtCodigoArticulo.setText("");
        this.txtNombreArt.setText("");
        this.txtCantidadFaltaDevol.setText("");
        this.txtCantidadADevol.setText("");
    }
    public void LimpiarGeneral(){
        this.txtCodigoCliente.setText("");
        this.txtNombreCliente.setText("");
        this.txtCodigoUsuario.setText("");
        this.txtNombreUsuario.setText("");
        this.txtCodigoDevolucion.setText("");
        this.jdcFechaDevol.setDate(null);
        if(this.tablaArticulosPrestados.getRowCount()>0){
            int limite1 = this.tablaArticulosPrestados.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
            DefaultTableModel modelo1=(DefaultTableModel)this.tablaArticulosPrestados.getModel();
            for (int f1 = 0; f1 < limite1; f1++) {
                modelo1.removeRow(0);
            }
        }
        if(this.tablaDetalleDevolucion.getRowCount()>0){
            int limite2 = this.tablaDetalleDevolucion.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
            DefaultTableModel modelo2=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
            for (int f2 = 0; f2 < limite2; f2++) {
                modelo2.removeRow(0);
            }
        }
    }
    public void HabilitarBotones(boolean a,boolean b,boolean c,boolean d,boolean f,boolean g,boolean h,boolean l){
        this.btnNuevo.setEnabled(a);
        this.btnGuardar.setEnabled(b);
        this.btnLimpiar.setEnabled(c);
        this.btnBuscarCliente.setEnabled(d);
        this.btnBuscarUsuario.setEnabled(f);
        this.btnAgregar.setEnabled(g);
        this.btnEliminar.setEnabled(h);
        this.btnCancelar.setEnabled(l);
    }
    public void HabilitarTextos(boolean a){
        this.jdcFechaDevol.setEnabled(a);
        this.txtCantidadADevol.setEnabled(a);
    }

    public void RecargarTablaArticulosPrestados(String codigoCliente){
        List lista = null;
        String fila[];
        try {
            lista=Cls_DaoArticulo.DaoListarArticulosDebeCliente(codigoCliente);
            DefaultTableModel modelo=(DefaultTableModel)this.tablaArticulosPrestados.getModel();
            while(this.tablaArticulosPrestados.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i=i+6){
                fila=new String[5];
                fila[0]=String.valueOf(lista.get(i));
               // fila[1]=String.valueOf(lista.get(i+1));
                fila[1]=String.valueOf(lista.get(i+2));
                fila[2]=String.valueOf(lista.get(i+3));
                fila[3]=String.valueOf(lista.get(i+4));
                fila[4]=String.valueOf(lista.get(i+5));
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
        if(this.tablaDetalleDevolucion.getRowCount()>0){
            int fila = this.tablaDetalleDevolucion.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
            for (int f = 0; f < fila; f++) {
                if (this.txtCodigoPrestamo.getText().trim().equals(String.valueOf(modelo.getValueAt(f,0))) && this.txtCodigoArticulo.getText().trim().equals(String.valueOf(modelo.getValueAt(f,1)))){
                    valor = true;
                }
            }
        }
        return valor;
    }
    public void ActualizarCantidadFaltaDevolver(String codigoPedido,String codigoArticulo,String cantidadExistente) {
        if(this.tablaDetalleDevolucion.getRowCount()>0){
            int sumaCantidad=0;
            int restaCantidad=0;
            boolean respuesta=false;
            int fila = this.tablaDetalleDevolucion.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
            for (int f = 0; f < fila; f++) {
                if (codigoPedido.equals(String.valueOf(modelo.getValueAt(f,0))) && codigoArticulo.equals(String.valueOf(modelo.getValueAt(f,1)))){
                    sumaCantidad=sumaCantidad+Integer.parseInt(String.valueOf(modelo.getValueAt(f,3)));
                    respuesta=true;
                }
            }
            if(respuesta=true){
                restaCantidad=Integer.parseInt(cantidadExistente)- sumaCantidad;
                this.txtCantidadFaltaDevol.setText(String.valueOf(restaCantidad));
            }else{
                 this.txtCantidadFaltaDevol.setText(cantidadExistente);
            }
        }else{
            this.txtCantidadFaltaDevol.setText(cantidadExistente);
        }
    }
    public void ActualizarCantidadArtDevolTabla(String codigoPedido,String codigoArticulo,String cantADevolver) {
        int sumaCantTabla=0;
        int fila = this.tablaDetalleDevolucion.getRowCount();
        DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
        for (int f = 0; f < fila; f++) {
           if (codigoPedido.equals(String.valueOf(modelo.getValueAt(f,0))) && codigoArticulo.equals(String.valueOf(modelo.getValueAt(f,1)))){
               sumaCantTabla=Integer.parseInt(String.valueOf(cantADevolver))+Integer.parseInt(String.valueOf(modelo.getValueAt(f,3)));
               modelo.setValueAt(String.valueOf(sumaCantTabla),f,3);
           }
        }
    }
    public void AsignarCasillas(){
        vcodCliente=this.txtCodigoCliente.getText().trim();
        vcodUsuario=this.txtCodigoUsuario.getText().trim();
        vcodDevolucion=this.txtCodigoDevolucion.getText().trim();
    }
    public void GenerarDevolucion(){
        this.AsignarCasillas();
        if(vcodCliente.isEmpty() || vcodUsuario.isEmpty() || vcodDevolucion.isEmpty() || this.jdcFechaDevol.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios");
        }else{
            if(this.tablaDetalleDevolucion.getRowCount()>0){
                int respuesta=JOptionPane.showConfirmDialog(null,"¿ESTA SEGURO DE EFECTUAR LA DEVOLUCION?","DEVOLUCION",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    boolean respuesta1=false;
                    boolean respuesta2=false;
                    boolean respuesta3=false;
                    boolean respuesta4=false;
                    Cls_PedidoPrestamo pedido=null;
                    //Cls_DetallePrestamo detallePrestamo=null;
                    Cls_PedidoDevolucion devolucion=null;
                    Cls_DetalleDevolucion detalleDevolucion=null;
                    Cls_Cliente cliente=null;
                    Cls_Usuario usuario=null;
                    Cls_Articulo articulo=null;
                    try {
                        //PARA LA TABLA PEDIDO DEVOLUCION
                        devolucion=new Cls_PedidoDevolucion();
                        devolucion.setCodigo_devol(this.txtCodigoDevolucion.getText().trim());
                        cliente=Cls_DaoCliente.DaoObtenerClientePorCodigo(this.txtCodigoCliente.getText().trim());
                        devolucion.setCliente(cliente);
                        usuario=Cls_DaoUsuario.DaoObtenerUsuarioPorCodigo(this.txtCodigoUsuario.getText().trim());
                        devolucion.setUsuario(usuario);
                        devolucion.setFechadevolucion_devol(this.jdcFechaDevol.getDate());
                        if(Cls_DaoPedidoDevolucion.DaoAgregarPedidoDevolucion(devolucion)==true){
                            respuesta1=true;
                        }else{
                            JOptionPane.showMessageDialog(this, "Error al registrar devolucion");
                        }
                        //PARA LA TABLA DETALLE DEVOLUCION
                        detalleDevolucion=new Cls_DetalleDevolucion();
                        articulo=new Cls_Articulo();
                        int limite = this.tablaDetalleDevolucion.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
                        DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
                        for (int f = 0; f < limite; f++) {  //RACORREMOS LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ, PARA IR REGISTRANDO UNO POR UNO AL TABLA DETALLEPEDIDO DE LA BD
                            pedido=Cls_DaoPedidoPrestamo.DaoObtenerPedidoPrestamoPorCodigo(String.valueOf(modelo.getValueAt(f,0)));
                            detalleDevolucion.setPrestamo(pedido);
                            articulo=Cls_DaoArticulo.DaoObtenerArticuloPorCodigo(String.valueOf(modelo.getValueAt(f,1)));
                            detalleDevolucion.setArticulo(articulo);
                            devolucion=Cls_DaoPedidoDevolucion.DaoObtenerPedidoDevolucionPorCodigo(this.txtCodigoDevolucion.getText().trim());
                            detalleDevolucion.setDevolucion(devolucion);
                            detalleDevolucion.setCantidad_devuelta_devol(Integer.parseInt(String.valueOf(modelo.getValueAt(f,3))));
                            if(Cls_DaoDetalleDevolucion.DaoAgregarDetallePedidoDevolucion(detalleDevolucion)==true){
                                respuesta2=true;
                            }else{
                                JOptionPane.showMessageDialog(this, "Error al registrar detalle devolucion");
                            }
                            //POR ARTICULO DEVUELTO SE DEBE REALIZAR UNA ACTUALIZACION DE LA CANTIDAD EXISTEN EN AL TABLA ARTICULO DE LA BD
                            if(Cls_DaoArticulo.ActualizarCantidadExistenteDevolucion(String.valueOf(modelo.getValueAt(f,1)),Integer.parseInt(String.valueOf(modelo.getValueAt(f,3))))==true){
                                respuesta3=true;
                            }else{
                                JOptionPane.showMessageDialog(this, "Error al actualizar cantidad existente articulo");
                            }
                            //
                            if(Cls_DaoDetallePrestamo.ActualizarCantidadDevueltaDevolucion(String.valueOf(modelo.getValueAt(f,1)),String.valueOf(modelo.getValueAt(f,0)),Integer.parseInt(String.valueOf(modelo.getValueAt(f,3))))==true){
                                respuesta4=true;
                            }else{
                                JOptionPane.showMessageDialog(this, "Error al actualizar cantidad devuelta");
                            }
                        }
                        if(respuesta1==true && respuesta2==true && respuesta3==true && respuesta4==true){
                            JOptionPane.showMessageDialog(this, "La devolucion se efectuo con exito");
                        }else{
                            JOptionPane.showMessageDialog(this, "Error devolucion no efectuada");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }
                    this.Limpiar();
                    this.LimpiarGeneral();
                    this.HabilitarTextos(false);
                    this.HabilitarBotones(true, false, false,false,false,false,false,false);
                }else{
                    JOptionPane.showMessageDialog(null, "Operacion cancelada");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay articulos en la lista detalle");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCantidadFaltaDevol = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoPrestamo = new javax.swing.JTextField();
        txtCantidadADevol = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoDevolucion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jdcFechaDevol = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaArticulosPrestados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoArticulo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombreArt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtCodigoCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        txtNombreCliente = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtCodigoUsuario = new javax.swing.JTextField();
        btnBuscarUsuario = new javax.swing.JButton();
        txtNombreUsuario = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalleDevolucion = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar1 = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Gestion_Devolucion.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        txtCantidadFaltaDevol.setEnabled(false);
        txtCantidadFaltaDevol.setName("txtCantidadFaltaDevol"); // NOI18N
        jPanel4.add(txtCantidadFaltaDevol, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 100, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        txtCodigoPrestamo.setEnabled(false);
        txtCodigoPrestamo.setName("txtCodigoPrestamo"); // NOI18N
        jPanel4.add(txtCodigoPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 60, -1));

        txtCantidadADevol.setEnabled(false);
        txtCantidadADevol.setName("txtCantidadADevol"); // NOI18N
        txtCantidadADevol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadADevolKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadADevolKeyTyped(evt);
            }
        });
        jPanel4.add(txtCantidadADevol, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 110, -1));

        btnAgregar.setIcon(resourceMap.getIcon("btnAgregar.icon")); // NOI18N
        btnAgregar.setText(resourceMap.getString("btnAgregar.text")); // NOI18N
        btnAgregar.setEnabled(false);
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 100, -1));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setText(resourceMap.getString("btnEliminar.text")); // NOI18N
        btnEliminar.setEnabled(false);
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 100, -1));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 100, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtCodigoDevolucion.setEnabled(false);
        txtCodigoDevolucion.setName("txtCodigoDevolucion"); // NOI18N
        jPanel4.add(txtCodigoDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 70, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jdcFechaDevol.setDateFormatString(resourceMap.getString("jdcFechaDevol.dateFormatString")); // NOI18N
        jdcFechaDevol.setEnabled(false);
        jdcFechaDevol.setName("jdcFechaDevol"); // NOI18N
        jPanel4.add(jdcFechaDevol, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 100, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaArticulosPrestados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Prestamo", "Codigo Articulo", "Nombre Articulo", "Cantidad Prestada", "Cantidad Falta Devolver"
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
        tablaArticulosPrestados.setName("tablaArticulosPrestados"); // NOI18N
        tablaArticulosPrestados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticulosPrestadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaArticulosPrestados);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 640, 80));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 660, 110));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        txtCodigoArticulo.setText(resourceMap.getString("txtCodigoArticulo.text")); // NOI18N
        txtCodigoArticulo.setEnabled(false);
        txtCodigoArticulo.setName("txtCodigoArticulo"); // NOI18N
        jPanel4.add(txtCodigoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 80, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        txtNombreArt.setText(resourceMap.getString("txtNombreArt.text")); // NOI18N
        txtNombreArt.setEnabled(false);
        txtNombreArt.setName("txtNombreArt"); // NOI18N
        jPanel4.add(txtNombreArt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 290, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 680, 270));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigoCliente.setEnabled(false);
        txtCodigoCliente.setName("txtCodigoCliente"); // NOI18N
        jPanel3.add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, -1));

        btnBuscarCliente.setIcon(resourceMap.getIcon("btnBuscarCliente.icon")); // NOI18N
        btnBuscarCliente.setToolTipText(resourceMap.getString("btnBuscarCliente.toolTipText")); // NOI18N
        btnBuscarCliente.setEnabled(false);
        btnBuscarCliente.setName("btnBuscarCliente"); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 30, -1));

        txtNombreCliente.setEnabled(false);
        txtNombreCliente.setName("txtNombreCliente"); // NOI18N
        jPanel3.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 220, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 340, 55));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel5.border.titleColor"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigoUsuario.setEnabled(false);
        txtCodigoUsuario.setName("txtCodigoUsuario"); // NOI18N
        jPanel5.add(txtCodigoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, -1));

        btnBuscarUsuario.setIcon(resourceMap.getIcon("btnBuscarUsuario.icon")); // NOI18N
        btnBuscarUsuario.setToolTipText(resourceMap.getString("btnBuscarUsuario.toolTipText")); // NOI18N
        btnBuscarUsuario.setEnabled(false);
        btnBuscarUsuario.setName("btnBuscarUsuario"); // NOI18N
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 30, -1));

        txtNombreUsuario.setEnabled(false);
        txtNombreUsuario.setName("txtNombreUsuario"); // NOI18N
        jPanel5.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 220, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 340, 55));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel6.border.titleColor"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaDetalleDevolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Prestamo", "Codigo Articulo", "Nombre Articulo", "Cantidad a Devolver"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalleDevolucion.setName("tablaDetalleDevolucion"); // NOI18N
        jScrollPane1.setViewportView(tablaDetalleDevolucion);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 660, 80));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 345, 680, 110));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel8.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel8.border.titleColor"))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setEnabled(false);
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel8.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 100, -1));

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel8.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 100, -1));

        btnLimpiar.setIcon(resourceMap.getIcon("btnLimpiar.icon")); // NOI18N
        btnLimpiar.setText(resourceMap.getString("btnLimpiar.text")); // NOI18N
        btnLimpiar.setEnabled(false);
        btnLimpiar.setName("btnLimpiar"); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel8.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 100, -1));

        btnCerrar1.setIcon(resourceMap.getIcon("btnCerrar1.icon")); // NOI18N
        btnCerrar1.setText(resourceMap.getString("btnCerrar1.text")); // NOI18N
        btnCerrar1.setName("btnCerrar1"); // NOI18N
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });
        jPanel8.add(btnCerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 100, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 455, 680, 60));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 525));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        Jpn_Gestion_Devolucion_BuscarCliente pn_buscarcliente=new Jpn_Gestion_Devolucion_BuscarCliente();
        buscarcliente=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscarcliente, "Buscar Cliente");
        pn_buscarcliente.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscarcliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarcliente);
}//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        Jpn_Gestion_Devolucion_BuscarUsuario pn_buscarusuario=new Jpn_Gestion_Devolucion_BuscarUsuario();
        buscarusuario=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), pn_buscarusuario, "Buscar Usuario");
        pn_buscarusuario.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        pn_buscarusuario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarusuario);
}//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        //this.cerrarPadre();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnCerrar1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Codigo=Cls_DaoPedidoDevolucion.GenerarCodigoPedDevolucion();
        txtCodigoDevolucion.setText(Codigo);
        this.Limpiar();
        this.HabilitarTextos(true);
        this.HabilitarBotones(false,true,true,true,true,true,true,true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tablaArticulosPrestadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArticulosPrestadosMouseClicked
        String ccodigoPedido=(String)this.tablaArticulosPrestados.getValueAt(this.tablaArticulosPrestados.getSelectedRow(),0);
        String ccodigoArticulo=(String)this.tablaArticulosPrestados.getValueAt(this.tablaArticulosPrestados.getSelectedRow(),1);
        String cnombreArticulo=(String)this.tablaArticulosPrestados.getValueAt(this.tablaArticulosPrestados.getSelectedRow(),2);
        String ccantFaltaDevolver=(String)this.tablaArticulosPrestados.getValueAt(this.tablaArticulosPrestados.getSelectedRow(),4);
        this.txtCodigoPrestamo.setText(ccodigoPedido);
        this.txtCodigoArticulo.setText(ccodigoArticulo);
        this.txtNombreArt.setText(cnombreArticulo);
        this.ActualizarCantidadFaltaDevolver(ccodigoPedido,ccodigoArticulo,ccantFaltaDevolver);
        this.txtCantidadADevol.setText("");
        this.txtCantidadADevol.requestFocus();
    }//GEN-LAST:event_tablaArticulosPrestadosMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(this.txtCodigoPrestamo.getText().trim().isEmpty() || this.txtCodigoArticulo.getText().trim().isEmpty() || this.txtNombreArt.getText().trim().isEmpty() || this.txtCantidadADevol.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "No a seleccionado ningun articulo ó falta ingresar cantidad a devolver");
        }else{
            if(VerificarArticuloRegistradoTabla()==false){
               String fila[];
               DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
               fila=new String[4];
               fila[0]=this.txtCodigoPrestamo.getText().trim();
               fila[1]=this.txtCodigoArticulo.getText().trim();
               fila[2]=this.txtNombreArt.getText().trim();
               fila[3]=this.txtCantidadADevol.getText().trim();
               modelo.addRow(fila);
               this.Limpiar();
           }else{
                int respuesta=JOptionPane.showConfirmDialog(null,"¿ARTICULO YA EXISTE EN LA LISTA DETALLE, SI ACEPTA SE AUMENTARA LA CANTIDAD A PRESTAR?","MENSAJE",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                   ActualizarCantidadArtDevolTabla(this.txtCodigoPrestamo.getText().trim(),this.txtCodigoArticulo.getText().trim(),this.txtCantidadADevol.getText().trim());
                    this.Limpiar();
                    JOptionPane.showMessageDialog(null, "Se aumento la cantidad a devolver");
                }else{
                    JOptionPane.showMessageDialog(null, "Operacion cancelada, no surgio ningun aumento de la cantidad a devolver");
                }
           }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucion.getModel();
        if(this.tablaDetalleDevolucion.getRowCount()>0){
            int fila = this.tablaDetalleDevolucion.getSelectedRow();
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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.Limpiar();
        this.LimpiarGeneral();
        this.HabilitarTextos(false);
        this.HabilitarBotones(true,false,false,false,false,false,false,false);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.GenerarDevolucion();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCantidadADevolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadADevolKeyReleased
        try{
            this.ValidarCantidadIngresarDevolver();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantidadADevolKeyReleased

    private void txtCantidadADevolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadADevolKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumeros(txtCantidadADevol, evt);
    }//GEN-LAST:event_txtCantidadADevolKeyTyped
    public void ValidarCantidadIngresarDevolver(){
        if(this.txtCantidadFaltaDevol.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Seleccionar un articulo de la lista");
            this.txtCantidadADevol.setText("");
        }else{
            if(this.txtCantidadADevol.getText().trim().equals("")){
                this.txtCantidadADevol.setText("");
            }else{
                int cantExisArt=Integer.parseInt(this.txtCantidadFaltaDevol.getText().trim());
                int cantAPrestarArt=Integer.parseInt(this.txtCantidadADevol.getText().trim());
                if(cantAPrestarArt>cantExisArt){
                    JOptionPane.showMessageDialog(this, "No puede exeder a la cantidad sobrante a devolver");
                    this.txtCantidadADevol.setText("");
                }else{
                    if(cantAPrestarArt==0){
                        JOptionPane.showMessageDialog(this, "Como minimo debe devolver 1 articulo");
                        this.txtCantidadADevol.setText("");
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFechaDevol;
    private javax.swing.JTable tablaArticulosPrestados;
    private javax.swing.JTable tablaDetalleDevolucion;
    private javax.swing.JTextField txtCantidadADevol;
    private javax.swing.JTextField txtCantidadFaltaDevol;
    private javax.swing.JTextField txtCodigoArticulo;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoDevolucion;
    private javax.swing.JTextField txtCodigoPrestamo;
    private javax.swing.JTextField txtCodigoUsuario;
    private javax.swing.JTextField txtNombreArt;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables

}
