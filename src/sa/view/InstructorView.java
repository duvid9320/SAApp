package sa.view;

import sa.controller.InstructorController;
import sa.controller.impl.ActividadControllerImpl;
import sa.controller.impl.AlumnoControllerImpl;
import sa.model.to.InstructorTO;

public class InstructorView extends javax.swing.JFrame {

    public InstructorView(InstructorController controller) {
        initComponents();
        setLocationRelativeTo(null);
        controller.buscarTodos(jTAResults);
        jBtnClose.addActionListener(e -> this.dispose());
        jBtnRegistrar.addActionListener(e -> {controller.createInstructor(getInstructorFromView());controller.buscarTodos(jTAResults);});
        jBtnModificar.addActionListener(e -> {controller.updateInstructor(getInstructorFromView());controller.buscarTodos(jTAResults);});
        jBtnEliminar.addActionListener(e -> {controller.deleteInstructor(getInstructorFromView());controller.buscarTodos(jTAResults);});
        jTAResults.getSelectionModel().addListSelectionListener(e -> setInstructorToView(controller.getInstructor(getSelectedInstructor())));
        jBtnAdmAlumnoView.addActionListener(e -> {new AlumnoControllerImpl();this.dispose();});
        jBtnAdmActividadView.addActionListener(e -> {new ActividadControllerImpl();this.dispose();});
    }
    private void setInstructorToView(InstructorTO instructor) {
        if(instructor == null)
            return;
        jTFId.setText(instructor.getIdInstructor());
        jTFNombres.setText(instructor.getNombres());
        jTFApellidos.setText(instructor.getApellidos());
        jTAGrado.setText(instructor.getGrado());
    }
    
    private String getSelectedInstructor(){
        int selectedRow = jTAResults.getSelectedRow();
        return selectedRow != -1 ? jTAResults.getValueAt(selectedRow, 0).toString() : null;
    }
    
    private InstructorTO getInstructorFromView(){
        return new InstructorTO(
                jTFId.getText().trim(), 
                jTFNombres.getText().trim(), 
                jTFApellidos.getText().trim(), 
                jTAGrado.getText().trim()
        );
    }
    
    //posción del mouse
    int x,y;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jpContenido = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jBtnRegistrar = new javax.swing.JButton();
        jBtnModificar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFId = new javax.swing.JTextField();
        jTFNombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFApellidos = new javax.swing.JTextField();
        jTAGrado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAResults = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jBtnAdmActividadView = new javax.swing.JButton();
        jBtnAdmAlumnoView = new javax.swing.JButton();
        jBtnClose = new javax.swing.JButton();
        jBtnMaximize = new javax.swing.JButton();
        jBtnMinimize = new javax.swing.JButton();

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

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ADMINISTRACIÓN SEMANA ACADÉMICA");

        jpContenido.setBackground(new java.awt.Color(0, 0, 153));
        jpContenido.setMaximumSize(new java.awt.Dimension(830, 545));
        jpContenido.setMinimumSize(new java.awt.Dimension(830, 545));
        jpContenido.setPreferredSize(new java.awt.Dimension(830, 545));

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

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

        jBtnModificar.setBackground(new java.awt.Color(0, 0, 153));
        jBtnModificar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnModificar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/UPDATE.png"))); // NOI18N
        jBtnModificar.setToolTipText("modificar");
        jBtnModificar.setBorder(null);
        jBtnModificar.setBorderPainted(false);
        jBtnModificar.setContentAreaFilled(false);
        jBtnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnModificar.setFocusable(false);
        jBtnModificar.setVerifyInputWhenFocusTarget(false);
        jBtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarActionPerformed(evt);
            }
        });

        jBtnEliminar.setBackground(new java.awt.Color(0, 0, 153));
        jBtnEliminar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/DELETE.png"))); // NOI18N
        jBtnEliminar.setToolTipText("eliminar");
        jBtnEliminar.setBorder(null);
        jBtnEliminar.setBorderPainted(false);
        jBtnEliminar.setContentAreaFilled(false);
        jBtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminar.setFocusable(false);
        jBtnEliminar.setVerifyInputWhenFocusTarget(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        jTFId.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFId.setBorder(null);

        jTFNombres.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFNombres.setBorder(null);
        jTFNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombresActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE(S):");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("APELLIDOS:");

        jTFApellidos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFApellidos.setBorder(null);

        jTAGrado.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTAGrado.setBorder(null);
        jTAGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTAGradoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("GRADO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTAGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnRegistrar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnModificar, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSTRUCTORES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTAResults.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTAResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTAResults);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContenidoLayout = new javax.swing.GroupLayout(jpContenido);
        jpContenido.setLayout(jpContenidoLayout);
        jpContenidoLayout.setHorizontalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContenidoLayout.setVerticalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContenidoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("INSTRUCTORES");

        jBtnAdmActividadView.setBackground(new java.awt.Color(0, 0, 153));
        jBtnAdmActividadView.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jBtnAdmActividadView.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAdmActividadView.setText("ACTIVIDADES");
        jBtnAdmActividadView.setBorder(null);
        jBtnAdmActividadView.setBorderPainted(false);
        jBtnAdmActividadView.setContentAreaFilled(false);
        jBtnAdmActividadView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAdmActividadView.setFocusPainted(false);
        jBtnAdmActividadView.setFocusable(false);
        jBtnAdmActividadView.setRequestFocusEnabled(false);
        jBtnAdmActividadView.setVerifyInputWhenFocusTarget(false);
        jBtnAdmActividadView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAdmActividadViewActionPerformed(evt);
            }
        });

        jBtnAdmAlumnoView.setBackground(new java.awt.Color(0, 0, 153));
        jBtnAdmAlumnoView.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jBtnAdmAlumnoView.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAdmAlumnoView.setText("ALUMNOS");
        jBtnAdmAlumnoView.setToolTipText("");
        jBtnAdmAlumnoView.setBorder(null);
        jBtnAdmAlumnoView.setBorderPainted(false);
        jBtnAdmAlumnoView.setContentAreaFilled(false);
        jBtnAdmAlumnoView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAdmAlumnoView.setFocusPainted(false);
        jBtnAdmAlumnoView.setFocusable(false);
        jBtnAdmAlumnoView.setRequestFocusEnabled(false);
        jBtnAdmAlumnoView.setVerifyInputWhenFocusTarget(false);

        jBtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/CLOSE.png"))); // NOI18N
        jBtnClose.setBorder(null);
        jBtnClose.setBorderPainted(false);
        jBtnClose.setContentAreaFilled(false);
        jBtnClose.setFocusPainted(false);
        jBtnClose.setFocusable(false);
        jBtnClose.setRequestFocusEnabled(false);
        jBtnClose.setVerifyInputWhenFocusTarget(false);

        jBtnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/MAXIMIZE.png"))); // NOI18N
        jBtnMaximize.setBorder(null);
        jBtnMaximize.setBorderPainted(false);
        jBtnMaximize.setContentAreaFilled(false);
        jBtnMaximize.setFocusPainted(false);
        jBtnMaximize.setFocusable(false);
        jBtnMaximize.setRequestFocusEnabled(false);
        jBtnMaximize.setVerifyInputWhenFocusTarget(false);

        jBtnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/MINIMIZE.png"))); // NOI18N
        jBtnMinimize.setBorder(null);
        jBtnMinimize.setBorderPainted(false);
        jBtnMinimize.setContentAreaFilled(false);
        jBtnMinimize.setFocusPainted(false);
        jBtnMinimize.setFocusable(false);
        jBtnMinimize.setRequestFocusEnabled(false);
        jBtnMinimize.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnAdmActividadView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnAdmAlumnoView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnMinimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnMaximize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnClose)
                .addContainerGap())
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20)
                    .addComponent(jBtnAdmActividadView)
                    .addComponent(jBtnAdmAlumnoView)
                    .addComponent(jLabel7)
                    .addComponent(jBtnMinimize)
                    .addComponent(jBtnMaximize)
                    .addComponent(jBtnClose))
                .addGap(11, 11, 11)
                .addComponent(jpContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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

    private void jTFNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombresActionPerformed

    private void jBtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnModificarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jTAGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTAGradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTAGradoActionPerformed

    private void jBtnAdmActividadViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAdmActividadViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnAdmActividadViewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdmActividadView;
    private javax.swing.JButton jBtnAdmAlumnoView;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnMaximize;
    private javax.swing.JButton jBtnMinimize;
    private javax.swing.JButton jBtnModificar;
    private javax.swing.JButton jBtnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTAGrado;
    private javax.swing.JTable jTAResults;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFId;
    private javax.swing.JTextField jTFNombres;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    // End of variables declaration//GEN-END:variables
}
