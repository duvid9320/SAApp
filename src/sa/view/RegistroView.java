package sa.view;


public class RegistroView extends javax.swing.JFrame {

    public RegistroView() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    //posción del mouse
    int x,y;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jpContenido = new javax.swing.JPanel();
        jspContenido = new javax.swing.JSplitPane();
        jpActividades = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTActividades = new javax.swing.JTable();
        jBtnRegistrar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEliminarRegistro = new javax.swing.JButton();
        jBtnBuscar = new javax.swing.JButton();
        jTFBuscar = new javax.swing.JTextField();
        jpUsuario = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTRegistro = new javax.swing.JTable();
        jpPerfil = new javax.swing.JPanel();
        jLNcontrol = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLCarrera = new javax.swing.JLabel();
        jLSemestre = new javax.swing.JLabel();
        jLHoras = new javax.swing.JLabel();
        jBtnMinimize = new javax.swing.JButton();
        jBtnMaximize = new javax.swing.JButton();
        jBtnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(830, 680));
        setUndecorated(true);
        setResizable(false);

        jpFondo.setBackground(new java.awt.Color(0, 0, 102));
        jpFondo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpFondoMouseDragged(evt);
            }
        });
        jpFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpFondoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpFondoMouseReleased(evt);
            }
        });

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/LOGO.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 27)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("REGISTRO DE ACTIVIDADES");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SEMANA ACADÉMICA");

        jpContenido.setBackground(new java.awt.Color(0, 0, 153));
        jpContenido.setMaximumSize(new java.awt.Dimension(830, 545));
        jpContenido.setMinimumSize(new java.awt.Dimension(830, 545));
        jpContenido.setPreferredSize(new java.awt.Dimension(830, 545));

        jspContenido.setDividerLocation(300);
        jspContenido.setDividerSize(2);
        jspContenido.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jspContenido.setPreferredSize(jpContenido.getPreferredSize());

        jpActividades.setBackground(new java.awt.Color(0, 0, 153));

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACTIVIDADES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTActividades);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
        );

        jBtnRegistrar.setBackground(new java.awt.Color(0, 0, 153));
        jBtnRegistrar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/ADD.png"))); // NOI18N
        jBtnRegistrar.setToolTipText("insertar");
        jBtnRegistrar.setBorder(null);
        jBtnRegistrar.setBorderPainted(false);
        jBtnRegistrar.setContentAreaFilled(false);
        jBtnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistrar.setFocusPainted(false);
        jBtnRegistrar.setFocusable(false);
        jBtnRegistrar.setRequestFocusEnabled(false);
        jBtnRegistrar.setVerifyInputWhenFocusTarget(false);

        jBtnGuardar.setBackground(new java.awt.Color(0, 0, 153));
        jBtnGuardar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/SAVE.png"))); // NOI18N
        jBtnGuardar.setToolTipText("modificar");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setBorderPainted(false);
        jBtnGuardar.setContentAreaFilled(false);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.setFocusable(false);
        jBtnGuardar.setVerifyInputWhenFocusTarget(false);
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEliminarRegistro.setBackground(new java.awt.Color(0, 0, 153));
        jBtnEliminarRegistro.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnEliminarRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/DELETE.png"))); // NOI18N
        jBtnEliminarRegistro.setToolTipText("eliminar");
        jBtnEliminarRegistro.setBorder(null);
        jBtnEliminarRegistro.setBorderPainted(false);
        jBtnEliminarRegistro.setContentAreaFilled(false);
        jBtnEliminarRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminarRegistro.setFocusable(false);
        jBtnEliminarRegistro.setVerifyInputWhenFocusTarget(false);
        jBtnEliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarRegistroActionPerformed(evt);
            }
        });

        jBtnBuscar.setBackground(new java.awt.Color(0, 0, 153));
        jBtnBuscar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/SEARCH.png"))); // NOI18N
        jBtnBuscar.setToolTipText("eliminar");
        jBtnBuscar.setBorder(null);
        jBtnBuscar.setBorderPainted(false);
        jBtnBuscar.setContentAreaFilled(false);
        jBtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscar.setFocusable(false);
        jBtnBuscar.setVerifyInputWhenFocusTarget(false);
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });

        jTFBuscar.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jTFBuscar.setBorder(null);

        javax.swing.GroupLayout jpActividadesLayout = new javax.swing.GroupLayout(jpActividades);
        jpActividades.setLayout(jpActividadesLayout);
        jpActividadesLayout.setHorizontalGroup(
            jpActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActividadesLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEliminarRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jpActividadesLayout.setVerticalGroup(
            jpActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActividadesLayout.createSequentialGroup()
                .addGroup(jpActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminarRegistro)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnRegistrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jspContenido.setLeftComponent(jpActividades);

        jpUsuario.setBackground(new java.awt.Color(0, 0, 153));

        jSplitPane1.setDividerLocation(200);

        jTRegistro.setBackground(new java.awt.Color(0, 0, 153));
        jTRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTRegistro);

        jSplitPane1.setRightComponent(jScrollPane3);

        jpPerfil.setBackground(new java.awt.Color(0, 0, 153));

        jLNcontrol.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLNcontrol.setForeground(new java.awt.Color(255, 255, 255));
        jLNcontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNcontrol.setText("<N. CONTROL>");

        jLNombre.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNombre.setText("<NOMBRE COMPLETO>");

        jLCarrera.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLCarrera.setForeground(new java.awt.Color(255, 255, 255));
        jLCarrera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLCarrera.setText("<CARRERA>");

        jLSemestre.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLSemestre.setForeground(new java.awt.Color(255, 255, 255));
        jLSemestre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLSemestre.setText("<SEMESTRE>");

        jLHoras.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLHoras.setForeground(new java.awt.Color(255, 255, 255));
        jLHoras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLHoras.setText("<TOTAL HORAS>");

        javax.swing.GroupLayout jpPerfilLayout = new javax.swing.GroupLayout(jpPerfil);
        jpPerfil.setLayout(jpPerfilLayout);
        jpPerfilLayout.setHorizontalGroup(
            jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNcontrol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jLCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLSemestre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLHoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPerfilLayout.setVerticalGroup(
            jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLNcontrol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLCarrera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLSemestre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLHoras)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jpPerfil);

        javax.swing.GroupLayout jpUsuarioLayout = new javax.swing.GroupLayout(jpUsuario);
        jpUsuario.setLayout(jpUsuarioLayout);
        jpUsuarioLayout.setHorizontalGroup(
            jpUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        jpUsuarioLayout.setVerticalGroup(
            jpUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );

        jspContenido.setRightComponent(jpUsuario);

        javax.swing.GroupLayout jpContenidoLayout = new javax.swing.GroupLayout(jpContenido);
        jpContenido.setLayout(jpContenidoLayout);
        jpContenidoLayout.setHorizontalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jpContenidoLayout.setVerticalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jBtnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/MINIMIZE.png"))); // NOI18N
        jBtnMinimize.setBorder(null);
        jBtnMinimize.setBorderPainted(false);
        jBtnMinimize.setContentAreaFilled(false);
        jBtnMinimize.setFocusPainted(false);
        jBtnMinimize.setFocusable(false);
        jBtnMinimize.setRequestFocusEnabled(false);
        jBtnMinimize.setVerifyInputWhenFocusTarget(false);

        jBtnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/MAXIMIZE.png"))); // NOI18N
        jBtnMaximize.setBorder(null);
        jBtnMaximize.setBorderPainted(false);
        jBtnMaximize.setContentAreaFilled(false);
        jBtnMaximize.setFocusPainted(false);
        jBtnMaximize.setFocusable(false);
        jBtnMaximize.setRequestFocusEnabled(false);
        jBtnMaximize.setVerifyInputWhenFocusTarget(false);

        jBtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/CLOSE.png"))); // NOI18N
        jBtnClose.setBorder(null);
        jBtnClose.setBorderPainted(false);
        jBtnClose.setContentAreaFilled(false);
        jBtnClose.setFocusPainted(false);
        jBtnClose.setFocusable(false);
        jBtnClose.setRequestFocusEnabled(false);
        jBtnClose.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo)
                .addGap(18, 18, 18)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnMinimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnMaximize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnClose)
                .addContainerGap())
            .addComponent(jpContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Logo)
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jBtnMinimize)
                        .addComponent(jBtnMaximize)
                        .addComponent(jBtnClose)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpFondoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFondoMouseReleased
        setOpacity((float)1.0);
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jpFondoMouseReleased

    private void jpFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFondoMousePressed
        setOpacity((float)0.8);
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jpFondoMousePressed

    private void jpFondoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFondoMouseDragged
        this.setLocation(evt.getXOnScreen()-x,evt.getYOnScreen()-y);
    }//GEN-LAST:event_jpFondoMouseDragged

    private void jBtnEliminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarRegistroActionPerformed

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnBuscarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnEliminarRegistro;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnMaximize;
    private javax.swing.JButton jBtnMinimize;
    private javax.swing.JButton jBtnRegistrar;
    private javax.swing.JLabel jLCarrera;
    private javax.swing.JLabel jLHoras;
    private javax.swing.JLabel jLNcontrol;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLSemestre;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTActividades;
    private javax.swing.JTextField jTFBuscar;
    private javax.swing.JTable jTRegistro;
    private javax.swing.JPanel jpActividades;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    private javax.swing.JPanel jpPerfil;
    private javax.swing.JPanel jpUsuario;
    private javax.swing.JSplitPane jspContenido;
    // End of variables declaration//GEN-END:variables
}
