package pck_GUI.pck_Utilidades;

import java.awt.Font;
import pck_utiles.JP_Modelo;

public class Jpn_Hora extends JP_Modelo {

    /****Crear la variable hora de tipo Reloj.Hora como privada***/
    private Reloj.Hora hora;

    public Jpn_Hora() {
        initComponents();
        this.hora();
    }

    public void hora(){
        hora = new Reloj.Hora();/***Inicializar la variable hora***/
        hora.setBounds(10, 10, 300, 60);/*(Ubicacion en eje x, Ubicacion en eje y, Ancho, Alto)*/
        hora.setFont(new Font("Trebuchet MS",java.awt.Font.BOLD, 60));/*Cambiamos la fuente, estilo y tamaño*/
        txtHora.add(hora);/*La agregamos al JFrame*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtHora = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(Jpn_Hora.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHora.setName("txtHora"); // NOI18N
        jPanel2.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 100));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 130));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtHora;
    // End of variables declaration//GEN-END:variables

}
