package pck_GUI.pck_Consultas;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoArticulo;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoCliente;
import pck_utiles.JP_Modelo;

public class Jpn_Devoluciones_Pendientes extends JP_Modelo {

    String codigo="";
    String nombre="";

    public Jpn_Devoluciones_Pendientes() {
        initComponents();
        this.buttonGroup1.add(this.jrbCodigo);
        this.buttonGroup1.add(this.jrbNombreCompleto);
        this.jrbCodigo.setSelected(true);
        this.ActualizarBusqueda();
        //Cls_Validar_CampoTexto.remplazarMinusculasMayusculas();
    }
    public void ActualizarBusqueda(){
        List lista = null;
        try {
            if(this.jrbCodigo.isSelected())
            {
                codigo=this.txtBuscador.getText().trim();
                if(codigo.compareTo("")==0){
                    lista=Cls_DaoCliente.DaoListarClientesDebenArticulosCodigo("");
                }
                else{
                    lista=Cls_DaoCliente.DaoListarClientesDebenArticulosCodigo(codigo);
                }
            }else if(this.jrbNombreCompleto.isSelected())
            {
                nombre=this.txtBuscador.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=Cls_DaoCliente.DaoListarClientesDebenArticulosNombre("");
                }
                else{
                    lista=Cls_DaoCliente.DaoListarClientesDebenArticulosNombre(nombre);
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
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.tablaCliente.getModel();
            while(this.tablaCliente.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i=i+5){
                fila=new String[3];
                fila[0]=String.valueOf(lista.get(i));
                fila[1]=String.valueOf(lista.get(i+1))+" "+String.valueOf(lista.get(i+2))+" "+String.valueOf(lista.get(i+3));
                fila[2]=String.valueOf(lista.get(i+4));
                modelo.addRow(fila);
            }
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }
    public void RecargarTablaArticulosPendientes(String codigoCliente){
        List lista = null;
        String fila[];
        try {
            lista=Cls_DaoArticulo.DaoListarArticulosDebeCliente(codigoCliente);
            DefaultTableModel modelo=(DefaultTableModel)this.tablaDetalleDevolucionPendiente.getModel();
            while(this.tablaDetalleDevolucionPendiente.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i=i+6){
                fila=new String[5];
                fila[0]=String.valueOf(lista.get(i));
                fila[1]=String.valueOf(lista.get(i+1));
                fila[2]=String.valueOf(lista.get(i+2));
                fila[3]=String.valueOf(lista.get(i+3));
                fila[4]=String.valueOf(lista.get(i+5));
                modelo.addRow(fila);
            }
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jrbCodigo = new javax.swing.JRadioButton();
        jrbNombreCompleto = new javax.swing.JRadioButton();
        txtBuscador = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleDevolucionPendiente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Devoluciones_Pendientes.class);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Cliente", "Nombre Cliente", "Cantidad Falta Devolver"
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
        tablaCliente.setName("tablaCliente"); // NOI18N
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCliente);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 580, 80));

        btnSalir.setIcon(resourceMap.getIcon("btnSalir.icon")); // NOI18N
        btnSalir.setText(resourceMap.getString("btnSalir.text")); // NOI18N
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 100, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jrbCodigo.setText(resourceMap.getString("jrbCodigo.text")); // NOI18N
        jrbCodigo.setName("jrbCodigo"); // NOI18N
        jPanel1.add(jrbCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jrbNombreCompleto.setText(resourceMap.getString("jrbNombreCompleto.text")); // NOI18N
        jrbNombreCompleto.setName("jrbNombreCompleto"); // NOI18N
        jPanel1.add(jrbNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        txtBuscador.setName("txtBuscador"); // NOI18N
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 380, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 580, 50));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaDetalleDevolucionPendiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Pedido", "Fecha Pedido", "Codigo Articulo", "Nombre Articulo", "Cantidad Pendiente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalleDevolucionPendiente.setName("tablaDetalleDevolucionPendiente"); // NOI18N
        jScrollPane2.setViewportView(tablaDetalleDevolucionPendiente);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 560, 150));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtNombreCliente.setText(resourceMap.getString("txtNombreCliente.text")); // NOI18N
        txtNombreCliente.setEnabled(false);
        txtNombreCliente.setName("txtNombreCliente"); // NOI18N
        jPanel3.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 480, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 580, 210));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 430));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        this.ActualizarBusqueda();
    }//GEN-LAST:event_txtBuscadorKeyReleased

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        String ccodigo=(String)this.tablaCliente.getValueAt(this.tablaCliente.getSelectedRow(),0);
        String cnombreCliente=(String)this.tablaCliente.getValueAt(this.tablaCliente.getSelectedRow(),1);
        this.txtNombreCliente.setText("");
        this.txtNombreCliente.setText(cnombreCliente);
        this.RecargarTablaArticulosPendientes(ccodigo);
    }//GEN-LAST:event_tablaClienteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton jrbCodigo;
    private javax.swing.JRadioButton jrbNombreCompleto;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaDetalleDevolucionPendiente;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables

}
