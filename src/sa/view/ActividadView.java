package sa.view;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import sa.controller.ActividadController;
import sa.model.to.ActividadTO;
import sa.model.to.HorarioTO;
import sa.model.to.InstructorTO;
import sa.utils.SAUtils;
import sa.controller.impl.AlumnoControllerImpl;
import sa.controller.impl.InstructorControllerImpl;

public class ActividadView extends javax.swing.JFrame {

    public ActividadView(ActividadController controller) {
        initComponents();
        setLocationRelativeTo(null);
        controller.getAllInstructores(jTRInstructores);
        controller.showAllActividades(jTQActividades);
        jBtnClose.addActionListener(e -> System.exit(0));
        jTRInstructores.getSelectionModel().addListSelectionListener(e -> selectInstructor(controller.getInstructor(getSelectedInstructor())));
        jBtnRegistrarActividad.addActionListener(e -> {controller.createActividad(getActividadFromView());controller.showAllActividades(jTQActividades);});
        jBtnModificarActividad.addActionListener(e -> {controller.updateActividad(getActividadFromView());controller.showAllActividades(jTQActividades);});
        jBtnEliminarActividad.addActionListener(e -> {controller.deleteActividad(getActividadFromView());controller.showAllActividades(jTQActividades);});
        jTQActividades.getSelectionModel().addListSelectionListener(e -> {setActividadToView(controller.getActividad(getSelectedActividad()));controller.showAllHorarios(getActividadFromView(), jTQHActividad);});
        jBtnRegistrarHorario.addActionListener(e -> {controller.createHorario(getHorarioFromView());controller.showAllHorarios(getActividadFromView(), jTQHActividad);});
        jTQHActividad.getSelectionModel().addListSelectionListener(e -> setHorarioToView(controller.getHorario(getSelectedHorario())));
        jBtnEliminarHorario.addActionListener(e -> {controller.deleteHorario(getHorarioFromView());controller.showAllHorarios(getActividadFromView(), jTQHActividad);});
        jBtnModificarHorario.addActionListener(e -> {controller.updateHorario(getHorarioFromView());controller.showAllHorarios(getActividadFromView(), jTQHActividad);});
        jBtnAdmAlumnoView.addActionListener(e -> {new AlumnoControllerImpl();this.dispose();});
        jBtnAdmInstructorView.addActionListener(e -> {new InstructorControllerImpl();this.dispose();});
        init();
    }
    
    //posción del mouse
    int x,y;
    
    private void init(){
        jSHFin.setModel(getHourModel());
        jSHFin.setEditor(new JSpinner.DateEditor(jSHFin, "HH:mm"));
        jSHInicio.setModel(getHourModel());
        jSHInicio.setEditor(new JSpinner.DateEditor(jSHInicio, "HH:mm"));
    }
    
    private String getSelectedHorario(){
        int selectedRow = jTQHActividad.getSelectedRow();
        return selectedRow != -1 ? jTQHActividad.getValueAt(selectedRow, 0).toString() : null;
    }
    
    private void setHorarioToView(HorarioTO horario){
        if(horario == null)
            return;
        jTFIdHorario.setText(String.valueOf(horario.getIdHorario()));
        jSHInicio.setValue(SAUtils.getHourDate(horario.getHoraInicio()));
        jSHFin.setValue(SAUtils.getHourDate(horario.getHoraFin()));
        jTFLugarHorario.setText(horario.getLugar());
        jDCFechaHorario.setDate(horario.getFecha());
    }
    
    private HorarioTO getHorarioFromView(){
        String id = jTFIdHorario.getText().trim();
        return new HorarioTO(
                SAUtils.isValidString(id) ? Integer.parseInt(id) : 0, 
                new ActividadTO(jTFIdActividad.getText().trim(), null, null, null, 0, null), 
                jDCFechaHorario.getDate(), 
                SAUtils.getFormattedTime(new Date(String.valueOf(jSHInicio.getValue()))), 
                SAUtils.getFormattedTime(new Date(String.valueOf(jSHFin.getValue()))), 
                jTFLugarHorario.getText().trim()
        );
    }
    
    private void setActividadToView(ActividadTO actividad){
        if(actividad == null)
            return;
        jTFIdActividad.setText(actividad.getIdActividad());
        jTFNombre.setText(actividad.getNombre());
        jTFInstructor.setText(actividad.getInstructorFk().getIdInstructor());
        jTADescripcion.setText(actividad.getDescripcion());
        jCBTipo.setSelectedItem(actividad.getTipo());
        jCBHoras.setSelectedItem(String.valueOf(actividad.getHoras()));
    }

    private ActividadTO getActividadFromView() {
        return new ActividadTO(
                jTFIdActividad.getText().trim(), 
                jTFNombre.getText().trim(), 
                String.valueOf(jCBTipo.getSelectedItem()), 
                jTADescripcion.getText().trim(), 
                Integer.parseInt(String.valueOf(jCBHoras.getSelectedItem())), 
                new InstructorTO(jTFInstructor.getText().trim(), null, null, null)
        );
    }

    private String getSelectedActividad(){
        int selectedRow = jTQActividades.getSelectedRow();
        return selectedRow != -1 ? jTQActividades.getValueAt(selectedRow, 0).toString() : null;
    }
    
    private String getSelectedInstructor() {
        int selectedRow = jTRInstructores.getSelectedRow();
        return selectedRow != -1 ? jTRInstructores.getValueAt(selectedRow, 0).toString() : null;
    }

    private void selectInstructor(InstructorTO instructor) {
            jTFInstructor.setText(instructor != null ? instructor.getIdInstructor() : "");
    }

    private SpinnerDateModel getHourModel(){
        return new SpinnerDateModel(new Date(),null, null, Calendar.HOUR);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jpContenido = new javax.swing.JPanel();
        jspContenido = new javax.swing.JSplitPane();
        jPMenu = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTFIdActividad = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jCBHoras = new javax.swing.JComboBox<>();
        jCBTipo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jTFInstructor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBtnRegistrarActividad = new javax.swing.JButton();
        jBtnModificarActividad = new javax.swing.JButton();
        jBtnEliminarActividad = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTRInstructores = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTQHActividad = new javax.swing.JTable();
        jBtnRegistrarHorario = new javax.swing.JButton();
        jBtnModificarHorario = new javax.swing.JButton();
        jBtnEliminarHorario = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTFIdHorario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSHInicio = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jSHFin = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jTFLugarHorario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDCFechaHorario = new com.toedter.calendar.JDateChooser();
        jPActividades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTQActividades = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jBtnAdmAlumnoView = new javax.swing.JButton();
        jBtnAdmInstructorView = new javax.swing.JButton();
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

        jspContenido.setDividerLocation(350);
        jspContenido.setDividerSize(2);
        jspContenido.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jspContenido.setPreferredSize(jpContenido.getPreferredSize());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(828, 256));

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));
        jPanel2.setMaximumSize(new java.awt.Dimension(823, 1321));
        jPanel2.setMinimumSize(new java.awt.Dimension(823, 321));

        jTFIdActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFIdActividad.setBorder(null);
        jTFIdActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIdActividadActionPerformed(evt);
            }
        });

        jTFNombre.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFNombre.setBorder(null);
        jTFNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombreActionPerformed(evt);
            }
        });

        jCBHoras.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCBHoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jCBHoras.setBorder(null);
        jCBHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBHorasActionPerformed(evt);
            }
        });

        jCBTipo.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCBTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Taller", "Conferencia" }));
        jCBTipo.setSelectedIndex(1);
        jCBTipo.setBorder(null);
        jCBTipo.setFocusable(false);
        jCBTipo.setLightWeightPopupEnabled(false);
        jCBTipo.setRequestFocusEnabled(false);
        jCBTipo.setVerifyInputWhenFocusTarget(false);

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID:");

        jTADescripcion.setColumns(10);
        jTADescripcion.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTADescripcion.setRows(2);
        jTADescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DESCRIPCIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 10))); // NOI18N
        jScrollPane1.setViewportView(jTADescripcion);

        jTFInstructor.setEditable(false);
        jTFInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFInstructor.setBorder(null);
        jTFInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFInstructorActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("NOMBRE:");

        jLabel15.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TIPO:");

        jLabel16.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("HORAS:");

        jLabel17.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("INSTRUCTOR:");

        jBtnRegistrarActividad.setBackground(new java.awt.Color(0, 0, 153));
        jBtnRegistrarActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnRegistrarActividad.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRegistrarActividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/ADD.png"))); // NOI18N
        jBtnRegistrarActividad.setToolTipText("insertar");
        jBtnRegistrarActividad.setBorder(null);
        jBtnRegistrarActividad.setBorderPainted(false);
        jBtnRegistrarActividad.setContentAreaFilled(false);
        jBtnRegistrarActividad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistrarActividad.setFocusPainted(false);
        jBtnRegistrarActividad.setFocusable(false);
        jBtnRegistrarActividad.setRequestFocusEnabled(false);
        jBtnRegistrarActividad.setVerifyInputWhenFocusTarget(false);

        jBtnModificarActividad.setBackground(new java.awt.Color(0, 0, 153));
        jBtnModificarActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnModificarActividad.setForeground(new java.awt.Color(255, 255, 255));
        jBtnModificarActividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/UPDATE.png"))); // NOI18N
        jBtnModificarActividad.setToolTipText("modificar");
        jBtnModificarActividad.setBorder(null);
        jBtnModificarActividad.setBorderPainted(false);
        jBtnModificarActividad.setContentAreaFilled(false);
        jBtnModificarActividad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnModificarActividad.setFocusable(false);
        jBtnModificarActividad.setVerifyInputWhenFocusTarget(false);
        jBtnModificarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarActividadActionPerformed(evt);
            }
        });

        jBtnEliminarActividad.setBackground(new java.awt.Color(0, 0, 153));
        jBtnEliminarActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnEliminarActividad.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminarActividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/DELETE.png"))); // NOI18N
        jBtnEliminarActividad.setToolTipText("eliminar");
        jBtnEliminarActividad.setBorder(null);
        jBtnEliminarActividad.setBorderPainted(false);
        jBtnEliminarActividad.setContentAreaFilled(false);
        jBtnEliminarActividad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminarActividad.setFocusable(false);
        jBtnEliminarActividad.setVerifyInputWhenFocusTarget(false);
        jBtnEliminarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActividadActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSTRUCTORES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTRInstructores.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTRInstructores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTRInstructores);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnModificarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEliminarActividad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnRegistrarActividad)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTFNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTFIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnEliminarActividad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnModificarActividad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ACTIVIDAD", jPanel2);

        jPanel5.setBackground(new java.awt.Color(0, 0, 153));

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HORARIOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTQHActividad.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTQHActividad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTQHActividad);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );

        jBtnRegistrarHorario.setBackground(new java.awt.Color(0, 0, 153));
        jBtnRegistrarHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnRegistrarHorario.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRegistrarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/ADD.png"))); // NOI18N
        jBtnRegistrarHorario.setToolTipText("insertar");
        jBtnRegistrarHorario.setBorder(null);
        jBtnRegistrarHorario.setBorderPainted(false);
        jBtnRegistrarHorario.setContentAreaFilled(false);
        jBtnRegistrarHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistrarHorario.setFocusPainted(false);
        jBtnRegistrarHorario.setFocusable(false);
        jBtnRegistrarHorario.setRequestFocusEnabled(false);
        jBtnRegistrarHorario.setVerifyInputWhenFocusTarget(false);

        jBtnModificarHorario.setBackground(new java.awt.Color(0, 0, 153));
        jBtnModificarHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnModificarHorario.setForeground(new java.awt.Color(255, 255, 255));
        jBtnModificarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/UPDATE.png"))); // NOI18N
        jBtnModificarHorario.setToolTipText("modificar");
        jBtnModificarHorario.setBorder(null);
        jBtnModificarHorario.setBorderPainted(false);
        jBtnModificarHorario.setContentAreaFilled(false);
        jBtnModificarHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnModificarHorario.setFocusPainted(false);
        jBtnModificarHorario.setFocusable(false);
        jBtnModificarHorario.setRequestFocusEnabled(false);
        jBtnModificarHorario.setVerifyInputWhenFocusTarget(false);
        jBtnModificarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarHorarioActionPerformed(evt);
            }
        });

        jBtnEliminarHorario.setBackground(new java.awt.Color(0, 0, 153));
        jBtnEliminarHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnEliminarHorario.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/DELETE.png"))); // NOI18N
        jBtnEliminarHorario.setToolTipText("eliminar");
        jBtnEliminarHorario.setBorder(null);
        jBtnEliminarHorario.setBorderPainted(false);
        jBtnEliminarHorario.setContentAreaFilled(false);
        jBtnEliminarHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminarHorario.setFocusPainted(false);
        jBtnEliminarHorario.setFocusable(false);
        jBtnEliminarHorario.setRequestFocusEnabled(false);
        jBtnEliminarHorario.setVerifyInputWhenFocusTarget(false);

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID:");

        jTFIdHorario.setEditable(false);
        jTFIdHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFIdHorario.setBorder(null);

        jLabel12.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("HORA INICIO:");

        jSHInicio.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jSHInicio.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("HORA FIN:");

        jSHFin.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jSHFin.setBorder(null);

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("LUGAR:");

        jTFLugarHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFLugarHorario.setBorder(null);
        jTFLugarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFLugarHorarioActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("FECHA:");

        jDCFechaHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrarHorario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnModificarHorario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEliminarHorario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIdHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSHInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSHFin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFLugarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDCFechaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnRegistrarHorario)
                    .addComponent(jBtnEliminarHorario)
                    .addComponent(jBtnModificarHorario)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFLugarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFIdHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSHInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSHFin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDCFechaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("HORARIO", jPanel5);

        javax.swing.GroupLayout jPMenuLayout = new javax.swing.GroupLayout(jPMenu);
        jPMenu.setLayout(jPMenuLayout);
        jPMenuLayout.setHorizontalGroup(
            jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPMenuLayout.setVerticalGroup(
            jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );

        jspContenido.setLeftComponent(jPMenu);

        jPActividades.setBackground(new java.awt.Color(0, 0, 153));
        jPActividades.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACTIVIDADES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTQActividades.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTQActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTQActividades);

        javax.swing.GroupLayout jPActividadesLayout = new javax.swing.GroupLayout(jPActividades);
        jPActividades.setLayout(jPActividadesLayout);
        jPActividadesLayout.setHorizontalGroup(
            jPActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );
        jPActividadesLayout.setVerticalGroup(
            jPActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        jspContenido.setRightComponent(jPActividades);

        javax.swing.GroupLayout jpContenidoLayout = new javax.swing.GroupLayout(jpContenido);
        jpContenido.setLayout(jpContenidoLayout);
        jpContenidoLayout.setHorizontalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jpContenidoLayout.setVerticalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ACTIVIDADES");

        jBtnAdmAlumnoView.setBackground(new java.awt.Color(0, 0, 153));
        jBtnAdmAlumnoView.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jBtnAdmAlumnoView.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAdmAlumnoView.setText("ALUMNOS");
        jBtnAdmAlumnoView.setBorder(null);
        jBtnAdmAlumnoView.setBorderPainted(false);
        jBtnAdmAlumnoView.setContentAreaFilled(false);
        jBtnAdmAlumnoView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAdmAlumnoView.setFocusPainted(false);
        jBtnAdmAlumnoView.setFocusable(false);
        jBtnAdmAlumnoView.setRequestFocusEnabled(false);
        jBtnAdmAlumnoView.setVerifyInputWhenFocusTarget(false);

        jBtnAdmInstructorView.setBackground(new java.awt.Color(0, 0, 153));
        jBtnAdmInstructorView.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jBtnAdmInstructorView.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAdmInstructorView.setText("INSTRUCTORES");
        jBtnAdmInstructorView.setToolTipText("");
        jBtnAdmInstructorView.setBorder(null);
        jBtnAdmInstructorView.setBorderPainted(false);
        jBtnAdmInstructorView.setContentAreaFilled(false);
        jBtnAdmInstructorView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAdmInstructorView.setFocusPainted(false);
        jBtnAdmInstructorView.setFocusable(false);
        jBtnAdmInstructorView.setRequestFocusEnabled(false);
        jBtnAdmInstructorView.setVerifyInputWhenFocusTarget(false);

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
                .addComponent(jBtnAdmAlumnoView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAdmInstructorView)
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
                    .addComponent(jLabel7)
                    .addComponent(jLabel20)
                    .addComponent(jBtnAdmAlumnoView)
                    .addComponent(jBtnAdmInstructorView)
                    .addComponent(jBtnMinimize)
                    .addComponent(jBtnMaximize)
                    .addComponent(jBtnClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void jTFLugarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFLugarHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFLugarHorarioActionPerformed

    private void jBtnModificarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnModificarHorarioActionPerformed

    private void jBtnEliminarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarActividadActionPerformed

    private void jBtnModificarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnModificarActividadActionPerformed

    private void jTFInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFInstructorActionPerformed

    private void jCBHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBHorasActionPerformed

    private void jTFNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombreActionPerformed

    private void jTFIdActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIdActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIdActividadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdmAlumnoView;
    private javax.swing.JButton jBtnAdmInstructorView;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnEliminarActividad;
    private javax.swing.JButton jBtnEliminarHorario;
    private javax.swing.JButton jBtnMaximize;
    private javax.swing.JButton jBtnMinimize;
    private javax.swing.JButton jBtnModificarActividad;
    private javax.swing.JButton jBtnModificarHorario;
    private javax.swing.JButton jBtnRegistrarActividad;
    private javax.swing.JButton jBtnRegistrarHorario;
    private javax.swing.JComboBox<String> jCBHoras;
    private javax.swing.JComboBox<String> jCBTipo;
    private com.toedter.calendar.JDateChooser jDCFechaHorario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPActividades;
    private javax.swing.JPanel jPMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSpinner jSHFin;
    private javax.swing.JSpinner jSHInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTADescripcion;
    private javax.swing.JTextField jTFIdActividad;
    private javax.swing.JTextField jTFIdHorario;
    private javax.swing.JTextField jTFInstructor;
    private javax.swing.JTextField jTFLugarHorario;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTable jTQActividades;
    private javax.swing.JTable jTQHActividad;
    private javax.swing.JTable jTRInstructores;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    private javax.swing.JSplitPane jspContenido;
    // End of variables declaration//GEN-END:variables
}
