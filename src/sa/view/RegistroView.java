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

import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import sa.model.to.ActividadTO;
import sa.model.to.AlumnoTO;
import sa.model.to.RegistroTO;
import sa.utils.SAUtils;


public class RegistroView extends javax.swing.JFrame {
    
    public static final String NC = "<N. CONTROL>";
    public static final String NOM = "<NOMBRE COMPLETO>";
    public static final String CAR = "<CARRERA>";
    public static final String SEM = "<SEMESTRE>";
    public static final String ACT = "<ACTIVIDAD>";
    public static final String HRS = "<HORAS>";
    
    private RegistroTO registro;
    private int x,y;
    
    public RegistroView() {
        initComponents();
        setLocationRelativeTo(null);
        registro = new RegistroTO();
    }
    
    public String getSelectedRegistroActividad(int row){
        return String.valueOf(row != -1 ? jTRegistro.getValueAt(row, 0) : "");
    }
    
    public String getSelectedRegistroAlumno(int row){
        return String.valueOf(row != -1 ? jTRegistro.getValueAt(row, 1) : "");
    }
    
    public void resetView(){
        jTFQNControl.setText("");
        jTActividades.getSelectionModel().clearSelection();
    }
    
    public void setActividad(ActividadTO actividad){
        if(actividad != null){
            registro.setActividadFk(actividad);
            jLActividad.setText(actividad.getNombre().toUpperCase());
            jLHoras.setText(String.valueOf(actividad.getHoras()));
        }else{
            jLActividad.setText(ACT);
            jLHoras.setText(HRS);
        }
    }
    
    public String getSelectedActividad(int row){
        return String.valueOf(row != -1 ? jTActividades.getValueAt(row, 0) : ""); 
    }
    
    public void resetActividad(){
         jLActividad.setText(ACT);
         jLHoras.setText(HRS);
    }
    
    public String getQueryActividad(){
        return getExceptActividadQuery(
                SAUtils.getQuery(getActividadFields(), "Actividad AS a INNER JOIN Instructor AS i ON a.InstructorFk = i.IdInstructor ", getActividadConditions())
        );
    }
    
    public String getExceptActividadQuery(String query){
        return query += String.format(" %s IdActividad NOT IN (%s)", query.contains("WHERE") ? " AND " : " WHERE ",registro.getIdActividadesRegistradas());
    }
    
    public HashMap<String, JTextComponent> getActividadConditions(){
        HashMap<String, JTextComponent> conditions = new HashMap<>();
        conditions.put("IdActividad", jTFQIdActividad);
        conditions.put("Nombre", jTFQNActividad);
        conditions.put("Tipo", jTFQTActividad);
        conditions.put("Horas", jTFQHActividad);
        return conditions;
    }
    
    public LinkedHashMap<String, String> getActividadFields(){
        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("IdActividad", "Id");
        fields.put("Nombre", "Actividad");
        fields.put("Tipo", "");
        fields.put("Horas", "");
        fields.put("CONCAT(i.Nombres, ' ', i.Apellidos)", "Instructor");
        return fields;
    }
    
    public void setAlumno(AlumnoTO alumno){
        if(alumno == null){
            jLNcontrol.setText(NC);
            jLNombre.setText(NOM);
            jLCarrera.setText(CAR);
            jLSemestre.setText(SEM);
        }else{
            registro.setAlumnoFk(alumno);
            jLNcontrol.setText(alumno.getNumeroControl().toUpperCase());
            jLNombre.setText((alumno.getNombres()+" "+alumno.getApellidoPaterno()+" "+alumno.getApellidoMaterno()).toUpperCase());
            jLCarrera.setText(alumno.getCarrera().getNombre().toUpperCase());
            jLSemestre.setText(String.valueOf(alumno.getSemestre()));
        }
    }
    
    public String getNumeroControl(){
        return jTFQNControl.getText().trim();
    }

    public RegistroTO getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroTO registro) {
        this.registro = registro;
    }

    public JButton getjBtnBuscar() {
        return jBtnBuscar;
    }

    public JLabel getjLActividad() {
        return jLActividad;
    }

    public JLabel getjLHoras() {
        return jLHoras;
    }
    
    public JButton getjBtnClose() {
        return jBtnClose;
    }

    public JButton getjBtnEliminarRegistro() {
        return jBtnEliminarRegistro;
    }

    public JButton getjBtnMaximize() {
        return jBtnMaximize;
    }

    public JButton getjBtnMinimize() {
        return jBtnMinimize;
    }

    public JButton getjBtnQActividad() {
        return jBtnQActividad;
    }

    public JButton getjBtnQAlumno() {
        return jBtnBuscar;
    }

    public JButton getjBtnRegistrar() {
        return jBtnRegistrar;
    }

    public JLabel getjLCarrera() {
        return jLCarrera;
    }

    public JLabel getjLNcontrol() {
        return jLNcontrol;
    }

    public JLabel getjLNombre() {
        return jLNombre;
    }

    public JLabel getjLSemestre() {
        return jLSemestre;
    }

    public JTable getjTActividades() {
        return jTActividades;
    }

    public JTextField getjTFQHActividad() {
        return jTFQHActividad;
    }

    public JTextField getjTFQIdActividad() {
        return jTFQIdActividad;
    }

    public JTextField getjTFQNActividad() {
        return jTFQNActividad;
    }

    public JTextField getjTFQNControl() {
        return jTFQNControl;
    }

    public JTextField getjTFQTActividad() {
        return jTFQTActividad;
    }

    public JTable getjTRegistro() {
        return jTRegistro;
    }
    
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFQIdActividad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFQNActividad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFQTActividad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFQHActividad = new javax.swing.JTextField();
        jBtnQActividad = new javax.swing.JButton();
        jBtnRegistrar = new javax.swing.JButton();
        jBtnEliminarRegistro = new javax.swing.JButton();
        jBtnBuscar = new javax.swing.JButton();
        jTFQNControl = new javax.swing.JTextField();
        jpUsuario = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTRegistro = new javax.swing.JTable();
        jpPerfil = new javax.swing.JPanel();
        jLNcontrol = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLCarrera = new javax.swing.JLabel();
        jLSemestre = new javax.swing.JLabel();
        jLActividad = new javax.swing.JLabel();
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
        jPanel1.setLayout(new java.awt.BorderLayout());

        jTActividades.setAutoCreateRowSorter(true);
        jTActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTActividades.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTActividades);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Id");

        jTFQIdActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFQIdActividadActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jTFQNActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFQNActividadActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo");

        jTFQTActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFQTActividadActionPerformed(evt);
            }
        });

        jLabel4.setText("Horas");

        jTFQHActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFQHActividadActionPerformed(evt);
            }
        });

        jBtnQActividad.setText("Buscar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQIdActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQNActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQTActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQHActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnQActividad)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFQIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFQNActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFQTActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTFQHActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnQActividad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jBtnRegistrar.setBackground(new java.awt.Color(0, 0, 153));
        jBtnRegistrar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/ADD.png"))); // NOI18N
        jBtnRegistrar.setToolTipText("insertar");
        jBtnRegistrar.setBorder(null);
        jBtnRegistrar.setBorderPainted(false);
        jBtnRegistrar.setContentAreaFilled(false);
        jBtnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistrar.setEnabled(false);
        jBtnRegistrar.setFocusPainted(false);
        jBtnRegistrar.setFocusable(false);
        jBtnRegistrar.setRequestFocusEnabled(false);
        jBtnRegistrar.setVerifyInputWhenFocusTarget(false);

        jBtnEliminarRegistro.setBackground(new java.awt.Color(0, 0, 153));
        jBtnEliminarRegistro.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnEliminarRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/DELETE.png"))); // NOI18N
        jBtnEliminarRegistro.setToolTipText("eliminar");
        jBtnEliminarRegistro.setBorder(null);
        jBtnEliminarRegistro.setBorderPainted(false);
        jBtnEliminarRegistro.setContentAreaFilled(false);
        jBtnEliminarRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminarRegistro.setEnabled(false);
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

        jTFQNControl.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jTFQNControl.setBorder(null);

        javax.swing.GroupLayout jpActividadesLayout = new javax.swing.GroupLayout(jpActividades);
        jpActividades.setLayout(jpActividadesLayout);
        jpActividadesLayout.setHorizontalGroup(
            jpActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEliminarRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQNControl, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpActividadesLayout.setVerticalGroup(
            jpActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActividadesLayout.createSequentialGroup()
                .addGroup(jpActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTFQNControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminarRegistro)
                    .addComponent(jBtnRegistrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
        );

        jspContenido.setLeftComponent(jpActividades);

        jpUsuario.setBackground(new java.awt.Color(0, 0, 153));

        jSplitPane1.setDividerLocation(300);

        jTRegistro.setAutoCreateRowSorter(true);
        jTRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTRegistro.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTRegistro);

        jSplitPane1.setRightComponent(jScrollPane3);

        jpPerfil.setBackground(new java.awt.Color(0, 0, 153));
        jpPerfil.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO REGISTRO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLNcontrol.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLNcontrol.setForeground(new java.awt.Color(255, 255, 255));
        jLNcontrol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNcontrol.setText("<N. CONTROL>");

        jLNombre.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNombre.setText("<NOMBRE COMPLETO>");

        jLCarrera.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLCarrera.setForeground(new java.awt.Color(255, 255, 255));
        jLCarrera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLCarrera.setText("<CARRERA>");

        jLSemestre.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLSemestre.setForeground(new java.awt.Color(255, 255, 255));
        jLSemestre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSemestre.setText("<SEMESTRE>");

        jLActividad.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLActividad.setForeground(new java.awt.Color(255, 255, 255));
        jLActividad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLActividad.setText("<ACTIVIDAD>");

        jLHoras.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLHoras.setForeground(new java.awt.Color(255, 255, 255));
        jLHoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLHoras.setText("<HORAS>");

        javax.swing.GroupLayout jpPerfilLayout = new javax.swing.GroupLayout(jpPerfil);
        jpPerfil.setLayout(jpPerfilLayout);
        jpPerfilLayout.setHorizontalGroup(
            jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNcontrol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(jLCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLSemestre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLActividad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLHoras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPerfilLayout.setVerticalGroup(
            jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLNcontrol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLCarrera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLSemestre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLActividad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLHoras)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jpPerfilLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLActividad, jLCarrera, jLHoras, jLNcontrol, jLNombre, jLSemestre});

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
            .addComponent(jspContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContenidoLayout.setVerticalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jpContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jTFQIdActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFQIdActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFQIdActividadActionPerformed

    private void jTFQNActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFQNActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFQNActividadActionPerformed

    private void jTFQTActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFQTActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFQTActividadActionPerformed

    private void jTFQHActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFQHActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFQHActividadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnEliminarRegistro;
    private javax.swing.JButton jBtnMaximize;
    private javax.swing.JButton jBtnMinimize;
    private javax.swing.JButton jBtnQActividad;
    private javax.swing.JButton jBtnRegistrar;
    private javax.swing.JLabel jLActividad;
    private javax.swing.JLabel jLCarrera;
    private javax.swing.JLabel jLHoras;
    private javax.swing.JLabel jLNcontrol;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLSemestre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTActividades;
    private javax.swing.JTextField jTFQHActividad;
    private javax.swing.JTextField jTFQIdActividad;
    private javax.swing.JTextField jTFQNActividad;
    private javax.swing.JTextField jTFQNControl;
    private javax.swing.JTextField jTFQTActividad;
    private javax.swing.JTable jTRegistro;
    private javax.swing.JPanel jpActividades;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    private javax.swing.JPanel jpPerfil;
    private javax.swing.JPanel jpUsuario;
    private javax.swing.JSplitPane jspContenido;
    // End of variables declaration//GEN-END:variables
}
