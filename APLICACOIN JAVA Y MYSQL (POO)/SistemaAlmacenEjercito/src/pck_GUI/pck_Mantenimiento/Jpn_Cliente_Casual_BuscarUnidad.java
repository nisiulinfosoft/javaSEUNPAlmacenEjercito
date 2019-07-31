package pck_GUI.pck_Mantenimiento;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoUnidad;
import pck_LOGICA_APLICACION.pck_Pojos.Cls_Unidad;
import pck_utiles.Cls_Validar_CampoTexto;
import pck_utiles.JP_Modelo;

public class Jpn_Cliente_Casual_BuscarUnidad extends JP_Modelo {

    String codigo="";
    String nombre="";

    public Jpn_Cliente_Casual_BuscarUnidad() {
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
                    lista=Cls_DaoUnidad.DaoListarUnidadCodigo("");
                }
                else{
                    lista=Cls_DaoUnidad.DaoListarUnidadCodigo(codigo);
                }
            }else if(this.jrbNombreCompleto.isSelected())
            {
                nombre=this.txtBuscador.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=Cls_DaoUnidad.DaoListarUnidadNombreCompleto("");
                }
                else{
                    lista=Cls_DaoUnidad.DaoListarUnidadNombreCompleto(nombre);
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
        Cls_Unidad unidad=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.tablaUnidad.getModel();
            while(this.tablaUnidad.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                unidad=(Cls_Unidad)lista.get(i);
                fila=new String[2];
                fila[0]=unidad.getCodigo_uni();
                fila[1]=unidad.getNombre_uni();
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
        jrbCodigo = new javax.swing.JRadioButton();
        jrbNombreCompleto = new javax.swing.JRadioButton();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUnidad = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Cliente_Casual_BuscarUnidad.class);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jrbCodigo.setText(resourceMap.getString("jrbCodigo.text")); // NOI18N
        jrbCodigo.setName("jrbCodigo"); // NOI18N
        jPanel2.add(jrbCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jrbNombreCompleto.setText(resourceMap.getString("jrbNombreCompleto.text")); // NOI18N
        jrbNombreCompleto.setName("jrbNombreCompleto"); // NOI18N
        jPanel2.add(jrbNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        txtBuscador.setName("txtBuscador"); // NOI18N
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 280, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaUnidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUnidad.setName("tablaUnidad"); // NOI18N
        tablaUnidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUnidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUnidad);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, 230));

        btnSalir.setIcon(resourceMap.getIcon("btnSalir.icon")); // NOI18N
        btnSalir.setText(resourceMap.getString("btnSalir.text")); // NOI18N
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 100, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.cerrarPadre();
}//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        this.ActualizarBusqueda();
    }//GEN-LAST:event_txtBuscadorKeyReleased

    private void tablaUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUnidadMouseClicked
        String ccodigo=(String)this.tablaUnidad.getValueAt(this.tablaUnidad.getSelectedRow(), 0);
        //Cerrar ventatna
        if(this.getRefDep() instanceof Jpn_Cliente_Casual)//para ver quien fue quien lo llamo
        {
            ((Jpn_Cliente_Casual)this.getRefDep()).obtenerUnidad(ccodigo);
        }
        this.cerrarPadre();
    }//GEN-LAST:event_tablaUnidadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrbCodigo;
    private javax.swing.JRadioButton jrbNombreCompleto;
    private javax.swing.JTable tablaUnidad;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables

}
