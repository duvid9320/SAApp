/* 
 * The MIT License
 *
 * Copyright 2018 David Rodríguez <duvid9320@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package sa.view;

import com.toedter.calendar.JDateChooser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import sa.model.to.ActividadTO;
import sa.model.to.HorarioTO;
import sa.model.to.InstructorTO;
import sa.utils.SAUtils;

public class ActividadView extends javax.swing.JFrame {
    private int x,y;
    private ActividadTO actividad;
    private HorarioTO horario;

    public ActividadView() {
        initComponents();
        setLocationRelativeTo(null);
        actividad = new ActividadTO();
        horario = new HorarioTO();
    }
    
    public void resetHorarioView(){
        jTFIdHorario.setText("");
        jSHInicio.setValue("");
        jSHFin.setValue("");
        jTFLugarHorario.setText("");
        jDCFechaHorario.setDate(null);
    }
    
    public int getSelectedHorario(int row){
        return Integer.parseInt(String.valueOf(row != -1 ? jTQHActividad.getValueAt(row, 0) : 0));
    }
    
    public void setHoraInicioHorario(){
        horario.setHoraInicio(String.valueOf(jSHInicio.getValue()));
    }
    
    public void setHoraFinHorario(){
        horario.setHoraFin(String.valueOf(jSHFin.getValue()));
    }
    
    public void setFechaHorario(){
        horario.setFecha(jDCFechaHorario.getDate());
    }
    
    public void setLugarHorario(){
        horario.setLugar(jTFLugarHorario.getText().trim());
    }
    
    public void resetActividadView(){
        jTFIdActividad.setText("");
        jTFNombreActividad.setText("");
        jCBTipoActividad.setSelectedIndex(-1);
        jCBHorasActividad.setSelectedIndex(-1);
        jTFIdInstructorActividad.setText("");
        jTADescripcionActividad.setText("");
    }
    
    public String getSelectedActividad(int row){
        return String.valueOf(row != -1 ? jTQActividades.getValueAt(row, 0) : "");
    }
    
    public void setInstructorActividad(InstructorTO instructor){
        if(instructor == null)
            return;
        actividad.setInstructorFk(instructor);
        jTFIdInstructorActividad.setText(instructor.getIdInstructor());
    }
    
    public String getSelectedInstructor(int row){
        return String.valueOf(row != -1 ? jTRInstructores.getValueAt(row, 0) : "");
    }
    
    public String getIdActividad(){
        return jTFIdActividad.getText().trim();
    }
    
    public String getActividadQuery(){
        return SAUtils.getQuery(getActividadFields(), "Actividad INNER JOIN Instructor ON Instructor.IdInstructor = Actividad.InstructorFk", getActividadConditions());
    }
    
    private HashMap<String, JTextComponent> getActividadConditions(){
        HashMap<String, JTextComponent> conditions = new HashMap<>();
        conditions.put("Nombre", jTFQNActividad);
        conditions.put("Tipo", jTFQTActividad);
        conditions.put("Horas", jTFQHActividad);
        conditions.put("Instructor.Nombre", jTFQIActividad);
        return conditions;
    }
    
    private LinkedHashMap<String, String> getActividadFields(){
        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("IdActividad", "");
        fields.put("Nombre", "");
        fields.put("Descripcion", "");
        fields.put("Horas", "");
        fields.put("Instructor.Nombres", "N. Instructor");
        fields.put("Instructor.Apellidos", "A. Instructor");
        return fields;
    }
    
    public String getInstructorQuery(){
        return SAUtils.getQuery(null, "Instructor", getInstructorConditions());
    }
    
    private HashMap<String, JTextComponent> getInstructorConditions(){
        HashMap<String, JTextComponent> conditions = new HashMap<>();
        conditions.put("Nombres", jTFQNInstructor);
        conditions.put("Apellidos", jTFQAInstructor);
        conditions.put("Grado", jTFQGInstructor);
        return conditions;
    }
    
    public void setNombreActividad(){
        actividad.setNombre(jTFNombreActividad.getText().trim());
    }
    
    public String getSelectedInstructor(){
        return jTFIdInstructorActividad.getText().trim();
    }
    
    public void setDescripcion(){
        actividad.setDescripcion(jTADescripcionActividad.getText().trim());
    }
    
    public void setIdActividad(){
        actividad.setIdActividad(jTFIdActividad.getText().trim());
    }
    
    public void setHorasActividad(){
        actividad.setHoras(jCBHorasActividad.getSelectedIndex() != -1 ? Integer.parseInt(String.valueOf(jCBHorasActividad.getSelectedItem())) : 0);
    }
    
    public void setTipoActividad(){
        actividad.setTipo(jCBTipoActividad.getSelectedIndex() != -1 ? String.valueOf(jCBTipoActividad.getSelectedItem()) : "");
    }

    public ActividadTO getActividad() {
        return actividad;
    }

    public void setActividad(ActividadTO actividad) {
        if(actividad == null)
            return;
        this.actividad = actividad;
        if(jTFIdActividad.getText().toLowerCase().trim().compareTo(actividad.getIdActividad().toLowerCase().trim()) != 0)
            jTFIdActividad.setText(actividad.getIdActividad());
        jTFNombreActividad.setText(actividad.getNombre());
        jTFIdInstructorActividad.setText(actividad.getInstructorFk().getIdInstructor());
        jTADescripcionActividad.setText(actividad.getDescripcion());
        jCBTipoActividad.setSelectedItem(actividad.getTipo());
        jCBHorasActividad.setSelectedItem(String.valueOf(actividad.getHoras()));
    }

    public HorarioTO getHorario() {
        return horario;
    }

    public void setHorario(HorarioTO horario) {
        if(horario == null)
            return;
        this.horario = horario;
        jTFIdHorario.setText(String.valueOf(horario.getIdHorario()));
        jSHInicio.setValue(SAUtils.getTimeFromString(horario.getHoraInicio()));
        jSHFin.setValue(SAUtils.getTimeFromString(horario.getHoraFin()));
        jTFLugarHorario.setText(horario.getLugar());
        jDCFechaHorario.setDate(horario.getFecha());
    }

    public JButton getjBtnAdmAlumnoView() {
        return jBtnAdmAlumnoView;
    }

    public JButton getjBtnAdmInstructorView() {
        return jBtnAdmInstructorView;
    }

    public JButton getjBtnClose() {
        return jBtnClose;
    }

    public JButton getjBtnEliminarActividad() {
        return jBtnEliminarActividad;
    }

    public JButton getjBtnEliminarHorario() {
        return jBtnEliminarHorario;
    }

    public JButton getjBtnMaximize() {
        return jBtnMaximize;
    }

    public JButton getjBtnMinimize() {
        return jBtnMinimize;
    }

    public JButton getjBtnModificarActividad() {
        return jBtnModificarActividad;
    }

    public JButton getjBtnModificarHorario() {
        return jBtnModificarHorario;
    }

    public JButton getjBtnRegistrarActividad() {
        return jBtnRegistrarActividad;
    }

    public JButton getjBtnRegistrarHorario() {
        return jBtnRegistrarHorario;
    }

    public JComboBox<String> getjCBHorasActividad() {
        return jCBHorasActividad;
    }

    public JComboBox<String> getjCBTipoActividad() {
        return jCBTipoActividad;
    }

    public JDateChooser getjDCFechaHorario() {
        return jDCFechaHorario;
    }

    public JSpinner getjSHFin() {
        return jSHFin;
    }

    public JSpinner getjSHInicio() {
        return jSHInicio;
    }

    public JTextArea getjTADescripcionActividad() {
        return jTADescripcionActividad;
    }

    public JTextField getjTFIdActividad() {
        return jTFIdActividad;
    }

    public JTextField getjTFIdHorario() {
        return jTFIdHorario;
    }

    public JTextField getjTFIdInstructorActividad() {
        return jTFIdInstructorActividad;
    }

    public JTextField getjTFLugarHorario() {
        return jTFLugarHorario;
    }

    public JTextField getjTFNombreActividad() {
        return jTFNombreActividad;
    }

    public JTable getjTQActividades() {
        return jTQActividades;
    }

    public JTable getjTQHActividad() {
        return jTQHActividad;
    }

    public JTable getjTRInstructores() {
        return jTRInstructores;
    }

    public JButton getjBtnQueryActividad() {
        return jBtnQueryActividad;
    }

    public JButton getjBtnQueryInstructores() {
        return jBtnQueryInstructores;
    }

    public JTextField getjTFQAInstructor() {
        return jTFQAInstructor;
    }

    public JTextField getjTFQGInstructor() {
        return jTFQGInstructor;
    }

    public JTextField getjTFQHActividad() {
        return jTFQHActividad;
    }

    public JTextField getjTFQIActividad() {
        return jTFQIActividad;
    }

    public JTextField getjTFQNActividad() {
        return jTFQNActividad;
    }

    public JTextField getjTFQNInstructor() {
        return jTFQNInstructor;
    }

    public JTextField getjTFQTActividad() {
        return jTFQTActividad;
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
        jTFNombreActividad = new javax.swing.JTextField();
        jCBHorasActividad = new javax.swing.JComboBox<>();
        jCBTipoActividad = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescripcionActividad = new javax.swing.JTextArea();
        jTFIdInstructorActividad = new javax.swing.JTextField();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFQNInstructor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFQAInstructor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFQGInstructor = new javax.swing.JTextField();
        jBtnQueryInstructores = new javax.swing.JButton();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTFQNActividad = new javax.swing.JTextField();
        jTFQTActividad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFQHActividad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFQIActividad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jBtnQueryActividad = new javax.swing.JButton();
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

        jTFNombreActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFNombreActividad.setBorder(null);
        jTFNombreActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombreActividadActionPerformed(evt);
            }
        });

        jCBHorasActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCBHorasActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jCBHorasActividad.setSelectedIndex(-1);
        jCBHorasActividad.setBorder(null);
        jCBHorasActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBHorasActividadActionPerformed(evt);
            }
        });

        jCBTipoActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCBTipoActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Taller", "Conferencia" }));
        jCBTipoActividad.setSelectedIndex(-1);
        jCBTipoActividad.setBorder(null);
        jCBTipoActividad.setFocusable(false);
        jCBTipoActividad.setLightWeightPopupEnabled(false);
        jCBTipoActividad.setRequestFocusEnabled(false);
        jCBTipoActividad.setVerifyInputWhenFocusTarget(false);

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID:");

        jTADescripcionActividad.setColumns(10);
        jTADescripcionActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTADescripcionActividad.setRows(2);
        jTADescripcionActividad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DESCRIPCIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 10))); // NOI18N
        jScrollPane1.setViewportView(jTADescripcionActividad);

        jTFIdInstructorActividad.setEditable(false);
        jTFIdInstructorActividad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFIdInstructorActividad.setBorder(null);
        jTFIdInstructorActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIdInstructorActividadActionPerformed(evt);
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
        jBtnRegistrarActividad.setEnabled(false);
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
        jBtnModificarActividad.setEnabled(false);
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
        jBtnEliminarActividad.setEnabled(false);
        jBtnEliminarActividad.setFocusable(false);
        jBtnEliminarActividad.setVerifyInputWhenFocusTarget(false);
        jBtnEliminarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActividadActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSTRUCTORES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jTRInstructores.setAutoCreateRowSorter(true);
        jTRInstructores.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTRInstructores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTRInstructores.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTRInstructores);

        jPanel1.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Nombres");

        jLabel2.setText("Apellidos");

        jLabel3.setText("Grado");

        jBtnQueryInstructores.setText("Buscar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQNInstructor, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQAInstructor, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQGInstructor, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnQueryInstructores)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFQNInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFQAInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFQGInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnQueryInstructores))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBHorasActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIdInstructorActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(jTFNombreActividad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTFIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBHorasActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFIdInstructorActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        jPanel3.setLayout(new java.awt.BorderLayout());

        jTQHActividad.setAutoCreateRowSorter(true);
        jTQHActividad.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTQHActividad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTQHActividad.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTQHActividad);

        jPanel3.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jBtnRegistrarHorario.setBackground(new java.awt.Color(0, 0, 153));
        jBtnRegistrarHorario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnRegistrarHorario.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRegistrarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/ADD.png"))); // NOI18N
        jBtnRegistrarHorario.setToolTipText("insertar");
        jBtnRegistrarHorario.setBorder(null);
        jBtnRegistrarHorario.setBorderPainted(false);
        jBtnRegistrarHorario.setContentAreaFilled(false);
        jBtnRegistrarHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistrarHorario.setEnabled(false);
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
        jBtnModificarHorario.setEnabled(false);
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
        jBtnEliminarHorario.setEnabled(false);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jPActividades.setLayout(new java.awt.BorderLayout());

        jTQActividades.setAutoCreateRowSorter(true);
        jTQActividades.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTQActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTQActividades.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTQActividades);

        jPActividades.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel4.setText("Nombre");

        jLabel5.setText("Tipo");

        jLabel6.setText("Horas");

        jLabel18.setText("N. Instructor");

        jBtnQueryActividad.setText("Buscar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQNActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQTActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQHActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQIActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnQueryActividad)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFQNActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTFQTActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTFQHActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTFQIActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnQueryActividad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPActividades.add(jPanel6, java.awt.BorderLayout.PAGE_START);

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

    private void jTFIdInstructorActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIdInstructorActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIdInstructorActividadActionPerformed

    private void jCBHorasActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBHorasActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBHorasActividadActionPerformed

    private void jTFNombreActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombreActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombreActividadActionPerformed

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
    private javax.swing.JButton jBtnQueryActividad;
    private javax.swing.JButton jBtnQueryInstructores;
    private javax.swing.JButton jBtnRegistrarActividad;
    private javax.swing.JButton jBtnRegistrarHorario;
    private javax.swing.JComboBox<String> jCBHorasActividad;
    private javax.swing.JComboBox<String> jCBTipoActividad;
    private com.toedter.calendar.JDateChooser jDCFechaHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPActividades;
    private javax.swing.JPanel jPMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSpinner jSHFin;
    private javax.swing.JSpinner jSHInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTADescripcionActividad;
    private javax.swing.JTextField jTFIdActividad;
    private javax.swing.JTextField jTFIdHorario;
    private javax.swing.JTextField jTFIdInstructorActividad;
    private javax.swing.JTextField jTFLugarHorario;
    private javax.swing.JTextField jTFNombreActividad;
    private javax.swing.JTextField jTFQAInstructor;
    private javax.swing.JTextField jTFQGInstructor;
    private javax.swing.JTextField jTFQHActividad;
    private javax.swing.JTextField jTFQIActividad;
    private javax.swing.JTextField jTFQNActividad;
    private javax.swing.JTextField jTFQNInstructor;
    private javax.swing.JTextField jTFQTActividad;
    private javax.swing.JTable jTQActividades;
    private javax.swing.JTable jTQHActividad;
    private javax.swing.JTable jTRInstructores;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    private javax.swing.JSplitPane jspContenido;
    // End of variables declaration//GEN-END:variables
}
