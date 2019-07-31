package sistemaalmacenejercito;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pck_GUI.pck_AccesoSistema.Jfr_Usuario_Identificacion;
import pck_GUI.pck_Consultas.Jpn_Articulo_Consulta;
import pck_GUI.pck_Consultas.Jpn_Cliente_Consulta;
import pck_GUI.pck_Consultas.Jpn_Devoluciones_Pendientes;
import pck_GUI.pck_Mantenimiento.Jpn_Articulo_Mantenimiento;
import pck_GUI.pck_Mantenimiento.Jpn_Cliente_Mantenimiento;
import pck_GUI.pck_Mantenimiento.Jpn_Unidad_Mantenimiento;
import pck_GUI.pck_Mantenimiento.Jpn_Usuario_Mantenimiento;
import pck_GUI.pck_Procesos.Jpn_Gestion_Devolucion;
import pck_GUI.pck_Procesos.Jpn_Gestion_Prestamo;
import pck_GUI.pck_Utilidades.Jpn_Calendario;
import pck_GUI.pck_Utilidades.Jpn_Hora;
import pck_LOGICA_APLICACION.pck_ConexionBD.BD;
import pck_LOGICA_APLICACION.pck_Dao.Cls_DaoReportes;
import pck_utiles.Cls_DesktopPaneConFondo;
import pck_utiles.Utiles_Panel;

public class SistemaAlmacenEjercitoView extends FrameView {

    JInternalFrame jif_calendario,jif_hora,jif_usuarios,jif_unidad,jif_cliente,jif_articulo,jif_cliente_consultar,jif_articulo_consultar;
    JInternalFrame jif_articulo_prestamo,jif_articulo_devolucion,jif_devpen_consultar;

    public SistemaAlmacenEjercitoView(SingleFrameApplication app,Jfr_Usuario_Identificacion padre) {
        super(app);
        initComponents();
        //Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // calculamos el tamaño de la ventana a partir del ancho de la pantalla
        //int ancho=d.width;
        //int alto=d.height;
        //this.jDesktopPane1.setMaximumSize(d);//.setSize(ancho-300, alto-300);
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = SistemaAlmacenEjercitoApp.getApplication().getMainFrame();
            aboutBox = new SistemaAlmacenEjercitoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        SistemaAlmacenEjercitoApp.getApplication().show(aboutBox);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiUsuario = new javax.swing.JMenuItem();
        jmiArticulo = new javax.swing.JMenuItem();
        jmiUnidad = new javax.swing.JMenuItem();
        jmiCliente = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmiPrestamoArticulo = new javax.swing.JMenuItem();
        jmiDevolucionArticulo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmiConsultaArticulo = new javax.swing.JMenuItem();
        jmiConsultaCliente = new javax.swing.JMenuItem();
        jmiDevolucionesPendientes = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmiReporteArticulos = new javax.swing.JMenuItem();
        jmiReporteClientes = new javax.swing.JMenuItem();
        jmiReporteClientesDebenArt = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiCalendario = new javax.swing.JMenuItem();
        jmiHora = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jToolBar1 = new javax.swing.JToolBar();
        jtiSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jtiCliente = new javax.swing.JButton();
        jtiArticulo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jtiPrestamoArticulo = new javax.swing.JButton();
        jtiDevolucionArticulo = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jtiCalendario = new javax.swing.JButton();
        jtiHora = new javax.swing.JButton();
        jDesktopPane1 = new Cls_DesktopPaneConFondo();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getResourceMap(SistemaAlmacenEjercitoView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        menuBar.add(fileMenu);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jmiUsuario.setIcon(resourceMap.getIcon("jmiUsuario.icon")); // NOI18N
        jmiUsuario.setText(resourceMap.getString("jmiUsuario.text")); // NOI18N
        jmiUsuario.setName("jmiUsuario"); // NOI18N
        jmiUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(jmiUsuario);

        jmiArticulo.setIcon(resourceMap.getIcon("jmiArticulo.icon")); // NOI18N
        jmiArticulo.setText(resourceMap.getString("jmiArticulo.text")); // NOI18N
        jmiArticulo.setName("jmiArticulo"); // NOI18N
        jmiArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiArticuloActionPerformed(evt);
            }
        });
        jMenu2.add(jmiArticulo);

        jmiUnidad.setIcon(resourceMap.getIcon("jmiUnidad.icon")); // NOI18N
        jmiUnidad.setText(resourceMap.getString("jmiUnidad.text")); // NOI18N
        jmiUnidad.setName("jmiUnidad"); // NOI18N
        jmiUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUnidadActionPerformed(evt);
            }
        });
        jMenu2.add(jmiUnidad);

        jmiCliente.setIcon(resourceMap.getIcon("jmiCliente.icon")); // NOI18N
        jmiCliente.setText(resourceMap.getString("jmiCliente.text")); // NOI18N
        jmiCliente.setName("jmiCliente"); // NOI18N
        jmiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClienteActionPerformed(evt);
            }
        });
        jMenu2.add(jmiCliente);

        menuBar.add(jMenu2);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jmiPrestamoArticulo.setIcon(resourceMap.getIcon("jmiPrestamoArticulo.icon")); // NOI18N
        jmiPrestamoArticulo.setText(resourceMap.getString("jmiPrestamoArticulo.text")); // NOI18N
        jmiPrestamoArticulo.setName("jmiPrestamoArticulo"); // NOI18N
        jmiPrestamoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPrestamoArticuloActionPerformed(evt);
            }
        });
        jMenu4.add(jmiPrestamoArticulo);

        jmiDevolucionArticulo.setIcon(resourceMap.getIcon("jmiDevolucionArticulo.icon")); // NOI18N
        jmiDevolucionArticulo.setText(resourceMap.getString("jmiDevolucionArticulo.text")); // NOI18N
        jmiDevolucionArticulo.setName("jmiDevolucionArticulo"); // NOI18N
        jmiDevolucionArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDevolucionArticuloActionPerformed(evt);
            }
        });
        jMenu4.add(jmiDevolucionArticulo);

        menuBar.add(jMenu4);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        jmiConsultaArticulo.setIcon(resourceMap.getIcon("jmiConsultaArticulo.icon")); // NOI18N
        jmiConsultaArticulo.setText(resourceMap.getString("jmiConsultaArticulo.text")); // NOI18N
        jmiConsultaArticulo.setName("jmiConsultaArticulo"); // NOI18N
        jmiConsultaArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsultaArticuloActionPerformed(evt);
            }
        });
        jMenu5.add(jmiConsultaArticulo);

        jmiConsultaCliente.setIcon(resourceMap.getIcon("jmiConsultaCliente.icon")); // NOI18N
        jmiConsultaCliente.setText(resourceMap.getString("jmiConsultaCliente.text")); // NOI18N
        jmiConsultaCliente.setName("jmiConsultaCliente"); // NOI18N
        jmiConsultaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsultaClienteActionPerformed(evt);
            }
        });
        jMenu5.add(jmiConsultaCliente);

        jmiDevolucionesPendientes.setIcon(resourceMap.getIcon("jmiDevolucionesPendientes.icon")); // NOI18N
        jmiDevolucionesPendientes.setText(resourceMap.getString("jmiDevolucionesPendientes.text")); // NOI18N
        jmiDevolucionesPendientes.setName("jmiDevolucionesPendientes"); // NOI18N
        jmiDevolucionesPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDevolucionesPendientesActionPerformed(evt);
            }
        });
        jMenu5.add(jmiDevolucionesPendientes);

        menuBar.add(jMenu5);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jmiReporteArticulos.setIcon(resourceMap.getIcon("jmiReporteArticulos.icon")); // NOI18N
        jmiReporteArticulos.setText(resourceMap.getString("jmiReporteArticulos.text")); // NOI18N
        jmiReporteArticulos.setName("jmiReporteArticulos"); // NOI18N
        jmiReporteArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReporteArticulosActionPerformed(evt);
            }
        });
        jMenu1.add(jmiReporteArticulos);

        jmiReporteClientes.setIcon(resourceMap.getIcon("jmiReporteClientes.icon")); // NOI18N
        jmiReporteClientes.setText(resourceMap.getString("jmiReporteClientes.text")); // NOI18N
        jmiReporteClientes.setName("jmiReporteClientes"); // NOI18N
        jmiReporteClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReporteClientesActionPerformed(evt);
            }
        });
        jMenu1.add(jmiReporteClientes);

        jmiReporteClientesDebenArt.setIcon(resourceMap.getIcon("jmiReporteClientesDebenArt.icon")); // NOI18N
        jmiReporteClientesDebenArt.setText(resourceMap.getString("jmiReporteClientesDebenArt.text")); // NOI18N
        jmiReporteClientesDebenArt.setName("jmiReporteClientesDebenArt"); // NOI18N
        jmiReporteClientesDebenArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReporteClientesDebenArtActionPerformed(evt);
            }
        });
        jMenu1.add(jmiReporteClientesDebenArt);

        menuBar.add(jMenu1);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jmiCalendario.setIcon(resourceMap.getIcon("jmiCalendario.icon")); // NOI18N
        jmiCalendario.setText(resourceMap.getString("jmiCalendario.text")); // NOI18N
        jmiCalendario.setName("jmiCalendario"); // NOI18N
        jmiCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCalendarioActionPerformed(evt);
            }
        });
        jMenu3.add(jmiCalendario);

        jmiHora.setIcon(resourceMap.getIcon("jmiHora.icon")); // NOI18N
        jmiHora.setText(resourceMap.getString("jmiHora.text")); // NOI18N
        jmiHora.setName("jmiHora"); // NOI18N
        jmiHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiHoraActionPerformed(evt);
            }
        });
        jMenu3.add(jmiHora);

        menuBar.add(jMenu3);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(sistemaalmacenejercito.SistemaAlmacenEjercitoApp.class).getContext().getActionMap(SistemaAlmacenEjercitoView.class, this);
        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jtiSalir.setIcon(resourceMap.getIcon("jtiSalir.icon")); // NOI18N
        jtiSalir.setText(resourceMap.getString("jtiSalir.text")); // NOI18N
        jtiSalir.setToolTipText(resourceMap.getString("jtiSalir.toolTipText")); // NOI18N
        jtiSalir.setFocusable(false);
        jtiSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiSalir.setName("jtiSalir"); // NOI18N
        jtiSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiSalir);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        jtiCliente.setIcon(resourceMap.getIcon("jtiCliente.icon")); // NOI18N
        jtiCliente.setText(resourceMap.getString("jtiCliente.text")); // NOI18N
        jtiCliente.setToolTipText(resourceMap.getString("jtiCliente.toolTipText")); // NOI18N
        jtiCliente.setFocusable(false);
        jtiCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiCliente.setName("jtiCliente"); // NOI18N
        jtiCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiCliente);

        jtiArticulo.setIcon(resourceMap.getIcon("jtiArticulo.icon")); // NOI18N
        jtiArticulo.setText(resourceMap.getString("jtiArticulo.text")); // NOI18N
        jtiArticulo.setToolTipText(resourceMap.getString("jtiArticulo.toolTipText")); // NOI18N
        jtiArticulo.setFocusable(false);
        jtiArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiArticulo.setName("jtiArticulo"); // NOI18N
        jtiArticulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiArticuloActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiArticulo);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        jtiPrestamoArticulo.setIcon(resourceMap.getIcon("jtiPrestamoArticulo.icon")); // NOI18N
        jtiPrestamoArticulo.setText(resourceMap.getString("jtiPrestamoArticulo.text")); // NOI18N
        jtiPrestamoArticulo.setToolTipText(resourceMap.getString("jtiPrestamoArticulo.toolTipText")); // NOI18N
        jtiPrestamoArticulo.setFocusable(false);
        jtiPrestamoArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiPrestamoArticulo.setName("jtiPrestamoArticulo"); // NOI18N
        jtiPrestamoArticulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiPrestamoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiPrestamoArticuloActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiPrestamoArticulo);

        jtiDevolucionArticulo.setIcon(resourceMap.getIcon("jtiDevolucionArticulo.icon")); // NOI18N
        jtiDevolucionArticulo.setText(resourceMap.getString("jtiDevolucionArticulo.text")); // NOI18N
        jtiDevolucionArticulo.setToolTipText(resourceMap.getString("jtiDevolucionArticulo.toolTipText")); // NOI18N
        jtiDevolucionArticulo.setFocusable(false);
        jtiDevolucionArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiDevolucionArticulo.setName("jtiDevolucionArticulo"); // NOI18N
        jtiDevolucionArticulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiDevolucionArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiDevolucionArticuloActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiDevolucionArticulo);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        jtiCalendario.setIcon(resourceMap.getIcon("jtiCalendario.icon")); // NOI18N
        jtiCalendario.setText(resourceMap.getString("jtiCalendario.text")); // NOI18N
        jtiCalendario.setToolTipText(resourceMap.getString("jtiCalendario.toolTipText")); // NOI18N
        jtiCalendario.setFocusable(false);
        jtiCalendario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiCalendario.setName("jtiCalendario"); // NOI18N
        jtiCalendario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiCalendarioActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiCalendario);

        jtiHora.setIcon(resourceMap.getIcon("jtiHora.icon")); // NOI18N
        jtiHora.setText(resourceMap.getString("jtiHora.text")); // NOI18N
        jtiHora.setToolTipText(resourceMap.getString("jtiHora.toolTipText")); // NOI18N
        jtiHora.setFocusable(false);
        jtiHora.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtiHora.setName("jtiHora"); // NOI18N
        jtiHora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtiHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtiHoraActionPerformed(evt);
            }
        });
        jToolBar1.add(jtiHora);

        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        ((Cls_DesktopPaneConFondo)jDesktopPane1).setImagen("/pck_imagenes/bandera.jpg");

        setComponent(jDesktopPane1);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(jToolBar1);
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCalendarioActionPerformed
        if(this.jif_calendario==null||jif_calendario.isClosed()){//si esta cerrado
           Jpn_Calendario calendario=new Jpn_Calendario();
           jif_calendario=Utiles_Panel.creaVentanaInterna_Panel(this, calendario, "Calendario");
           calendario.setRefDep(this);//la referencia de quien depende
           calendario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_calendario);
       }
    }//GEN-LAST:event_jmiCalendarioActionPerformed

    private void jmiHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiHoraActionPerformed
        if(this.jif_hora==null||jif_hora.isClosed()){//si esta cerrado
           Jpn_Hora hora=new Jpn_Hora();
           jif_hora=Utiles_Panel.creaVentanaInterna_Panel(this, hora, "Hora");
           hora.setRefDep(this);//la referencia de quien depende
           hora.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_hora);
       }
    }//GEN-LAST:event_jmiHoraActionPerformed

    private void jtiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiSalirActionPerformed
        int respuesta=JOptionPane.showConfirmDialog(null,"¿SEGURO QUE DESEA SALIR DEL SISTEMA?","MENSAJE",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Operacion cancelada");
        }
    }//GEN-LAST:event_jtiSalirActionPerformed

    private void jtiCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiCalendarioActionPerformed
        if(this.jif_calendario==null||jif_calendario.isClosed()){//si esta cerrado
           Jpn_Calendario calendario=new Jpn_Calendario();
           jif_calendario=Utiles_Panel.creaVentanaInterna_Panel(this, calendario, "Calendario");
           calendario.setRefDep(this);//la referencia de quien depende
           calendario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_calendario);
       }
    }//GEN-LAST:event_jtiCalendarioActionPerformed

    private void jtiHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiHoraActionPerformed
        if(this.jif_hora==null||jif_hora.isClosed()){//si esta cerrado
           Jpn_Hora hora=new Jpn_Hora();
           jif_hora=Utiles_Panel.creaVentanaInterna_Panel(this, hora, "Hora");
           hora.setRefDep(this);//la referencia de quien depende
           hora.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_hora);
       }
    }//GEN-LAST:event_jtiHoraActionPerformed

    private void jmiUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuarioActionPerformed
        if(this.jif_usuarios==null||jif_usuarios.isClosed()){//si esta cerrado
           Jpn_Usuario_Mantenimiento usuario=new Jpn_Usuario_Mantenimiento();
           jif_usuarios=Utiles_Panel.creaVentanaInterna_Panel(this, usuario, "Mantenimiento de Usuario");
           usuario.setRefDep(this);//la referencia de quien depende
           usuario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_usuarios);
       }
    }//GEN-LAST:event_jmiUsuarioActionPerformed

    private void jmiArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiArticuloActionPerformed
        if(this.jif_articulo==null||jif_articulo.isClosed()){//si esta cerrado
           Jpn_Articulo_Mantenimiento articulo=new Jpn_Articulo_Mantenimiento();
           jif_articulo=Utiles_Panel.creaVentanaInterna_Panel(this, articulo, "Mantenimiento de Articulo");
           articulo.setRefDep(this);//la referencia de quien depende
           articulo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo);
       }
    }//GEN-LAST:event_jmiArticuloActionPerformed

    private void jmiUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUnidadActionPerformed
        if(this.jif_unidad==null||jif_unidad.isClosed()){//si esta cerrado
           Jpn_Unidad_Mantenimiento unidad=new Jpn_Unidad_Mantenimiento();
           jif_unidad=Utiles_Panel.creaVentanaInterna_Panel(this, unidad, "Mantenimiento de Unidad de Cliente");
           unidad.setRefDep(this);//la referencia de quien depende
           unidad.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_unidad);
       }
    }//GEN-LAST:event_jmiUnidadActionPerformed

    private void jmiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClienteActionPerformed
        if(this.jif_cliente==null||jif_cliente.isClosed()){//si esta cerrado
           Jpn_Cliente_Mantenimiento cliente=new Jpn_Cliente_Mantenimiento();
           jif_cliente=Utiles_Panel.creaVentanaInterna_Panel(this, cliente, "Mantenimiento de Cliente");
           cliente.setRefDep(this);//la referencia de quien depende
           cliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_cliente);
       }
    }//GEN-LAST:event_jmiClienteActionPerformed

    private void jtiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiClienteActionPerformed
           if(this.jif_cliente==null||jif_cliente.isClosed()){//si esta cerrado
           Jpn_Cliente_Mantenimiento cliente=new Jpn_Cliente_Mantenimiento();
           jif_cliente=Utiles_Panel.creaVentanaInterna_Panel(this, cliente, "Mantenimiento de Cliente");
           cliente.setRefDep(this);//la referencia de quien depende
           cliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_cliente);
       }
    }//GEN-LAST:event_jtiClienteActionPerformed

    private void jtiArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiArticuloActionPerformed
        if(this.jif_articulo==null||jif_articulo.isClosed()){//si esta cerrado
           Jpn_Articulo_Mantenimiento articulo=new Jpn_Articulo_Mantenimiento();
           jif_articulo=Utiles_Panel.creaVentanaInterna_Panel(this, articulo, "Mantenimiento de Articulo");
           articulo.setRefDep(this);//la referencia de quien depende
           articulo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo);
       }
    }//GEN-LAST:event_jtiArticuloActionPerformed

    private void jtiPrestamoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiPrestamoArticuloActionPerformed
        if(this.jif_articulo_prestamo==null||jif_articulo_prestamo.isClosed()){//si esta cerrado
           Jpn_Gestion_Prestamo articuloprestamo=new Jpn_Gestion_Prestamo();
           jif_articulo_prestamo=Utiles_Panel.creaVentanaInterna_Panel(this, articuloprestamo, "Gestion de Prestamo de Articulo");
           articuloprestamo.setRefDep(this);//la referencia de quien depende
           articuloprestamo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo_prestamo);
       }
    }//GEN-LAST:event_jtiPrestamoArticuloActionPerformed

    private void jtiDevolucionArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtiDevolucionArticuloActionPerformed
        if(this.jif_articulo_devolucion==null||jif_articulo_devolucion.isClosed()){//si esta cerrado
           Jpn_Gestion_Devolucion articulodevolucion=new Jpn_Gestion_Devolucion();
           jif_articulo_devolucion=Utiles_Panel.creaVentanaInterna_Panel(this, articulodevolucion, "Gestion de Devolucion de Articulo");
           articulodevolucion.setRefDep(this);//la referencia de quien depende
           articulodevolucion.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo_devolucion);
       }
    }//GEN-LAST:event_jtiDevolucionArticuloActionPerformed

    private void jmiPrestamoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrestamoArticuloActionPerformed
        if(this.jif_articulo_prestamo==null||jif_articulo_prestamo.isClosed()){//si esta cerrado
           Jpn_Gestion_Prestamo articuloprestamo=new Jpn_Gestion_Prestamo();
           jif_articulo_prestamo=Utiles_Panel.creaVentanaInterna_Panel(this, articuloprestamo, "Gestion de Prestamo de Articulo");
           articuloprestamo.setRefDep(this);//la referencia de quien depende
           articuloprestamo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo_prestamo);
       }
    }//GEN-LAST:event_jmiPrestamoArticuloActionPerformed

    private void jmiDevolucionArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDevolucionArticuloActionPerformed
        if(this.jif_articulo_devolucion==null||jif_articulo_devolucion.isClosed()){//si esta cerrado
           Jpn_Gestion_Devolucion articulodevolucion=new Jpn_Gestion_Devolucion();
           jif_articulo_devolucion=Utiles_Panel.creaVentanaInterna_Panel(this, articulodevolucion, "Gestion de Devolucion de Articulo");
           articulodevolucion.setRefDep(this);//la referencia de quien depende
           articulodevolucion.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo_devolucion);
       }
    }//GEN-LAST:event_jmiDevolucionArticuloActionPerformed

    private void jmiConsultaArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsultaArticuloActionPerformed
        if(this.jif_articulo_consultar==null||jif_articulo_consultar.isClosed()){//si esta cerrado
           Jpn_Articulo_Consulta articuloconsultar=new Jpn_Articulo_Consulta();
           jif_articulo_consultar=Utiles_Panel.creaVentanaInterna_Panel(this, articuloconsultar, "Consulta de Articulo");
           articuloconsultar.setRefDep(this);//la referencia de quien depende
           articuloconsultar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_articulo_consultar);
       }
    }//GEN-LAST:event_jmiConsultaArticuloActionPerformed

    private void jmiConsultaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsultaClienteActionPerformed
        if(this.jif_cliente_consultar==null||jif_cliente_consultar.isClosed()){//si esta cerrado
           Jpn_Cliente_Consulta clienteconsultar=new Jpn_Cliente_Consulta();
           jif_cliente_consultar=Utiles_Panel.creaVentanaInterna_Panel(this, clienteconsultar, "Consulta de Cliente");
           clienteconsultar.setRefDep(this);//la referencia de quien depende
           clienteconsultar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_cliente_consultar);
       }
    }//GEN-LAST:event_jmiConsultaClienteActionPerformed

    private void jmiDevolucionesPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDevolucionesPendientesActionPerformed
        if(this.jif_devpen_consultar==null||jif_devpen_consultar.isClosed()){//si esta cerrado
           Jpn_Devoluciones_Pendientes DevoPenconsultar=new Jpn_Devoluciones_Pendientes();
           jif_devpen_consultar=Utiles_Panel.creaVentanaInterna_Panel(this, DevoPenconsultar, "Devoluciones Pendientes");
           DevoPenconsultar.setRefDep(this);//la referencia de quien depende
           DevoPenconsultar.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_devpen_consultar);
       }
    }//GEN-LAST:event_jmiDevolucionesPendientesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int respuesta1=JOptionPane.showConfirmDialog(null,"¿SEGURO QUE DESEA SALIR DEL SISTEMA?","MENSAJE",JOptionPane.YES_NO_OPTION);
        if(respuesta1==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Operacion cancelada");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiReporteClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteClientesActionPerformed
        cliente.DaoImprimirReporteClientes();
    }//GEN-LAST:event_jmiReporteClientesActionPerformed

    private void jmiReporteArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteArticulosActionPerformed
        cliente.DaoImprimirReporteArticulos();
    }//GEN-LAST:event_jmiReporteArticulosActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jmiReporteClientesDebenArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteClientesDebenArtActionPerformed
        cliente.DaoImprimirReporteClientequeDeben();
    }//GEN-LAST:event_jmiReporteClientesDebenArtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmiArticulo;
    private javax.swing.JMenuItem jmiCalendario;
    private javax.swing.JMenuItem jmiCliente;
    private javax.swing.JMenuItem jmiConsultaArticulo;
    private javax.swing.JMenuItem jmiConsultaCliente;
    private javax.swing.JMenuItem jmiDevolucionArticulo;
    private javax.swing.JMenuItem jmiDevolucionesPendientes;
    private javax.swing.JMenuItem jmiHora;
    private javax.swing.JMenuItem jmiPrestamoArticulo;
    private javax.swing.JMenuItem jmiReporteArticulos;
    private javax.swing.JMenuItem jmiReporteClientes;
    private javax.swing.JMenuItem jmiReporteClientesDebenArt;
    private javax.swing.JMenuItem jmiUnidad;
    private javax.swing.JMenuItem jmiUsuario;
    private javax.swing.JButton jtiArticulo;
    private javax.swing.JButton jtiCalendario;
    private javax.swing.JButton jtiCliente;
    private javax.swing.JButton jtiDevolucionArticulo;
    private javax.swing.JButton jtiHora;
    private javax.swing.JButton jtiPrestamoArticulo;
    private javax.swing.JButton jtiSalir;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

    Cls_DaoReportes cliente=new Cls_DaoReportes();
}
