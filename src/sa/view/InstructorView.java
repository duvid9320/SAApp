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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import sa.model.to.InstructorTO;
import sa.utils.SAUtils;

public class InstructorView extends javax.swing.JFrame {
    
    private InstructorTO instructor;
    private int x,y;

    public InstructorView() {
        initComponents();
        setLocationRelativeTo(null);
        instructor = new InstructorTO();
    }
    
    public String getQueryInstructores(){
        HashMap<String, JTextComponent> conditions = new HashMap<>(3);
        conditions.put("Apellidos", jTFQApellidos);
        conditions.put("IdInstructor", jTFQId);
        conditions.put("Nombres", jTFQNombres);
        return SAUtils.getQuery(null, "Instructor", conditions);
    }
    
    public String getSelectedInstructor(){
        int selectedRow = jTAQueryInstructores.getSelectedRow();
        return selectedRow != -1 ? String.valueOf(jTAQueryInstructores.getValueAt(selectedRow, 0)) : null;
    }
    
    public String getSelectedInstructor(int selectedRow){
        return selectedRow != -1 ? String.valueOf(jTAQueryInstructores.getValueAt(selectedRow, 0)) : null;
    }
    
    public void resetView(){
        jTFApellidosInstructor.setText("");
        jTFIdInstructor.setText("");
        jTFNombresInstructor.setText("");
        jTAGradoInstructor.setText("");
    }
    
    public void setNombresInstructor(){
        instructor.setNombres(jTFNombresInstructor.getText().trim());
    }
    
    public void setGradoInstructor(){
        instructor.setGrado(jTAGradoInstructor.getText().trim());
    }
    
    public void setIdInstructor(){
        instructor.setIdInstructor(getIdInstructor());
    }
    
    public String getIdInstructor(){
        return jTFIdInstructor.getText().trim();
    }
    
    public void setApellidosInstructor(){
        instructor.setApellidos(jTFApellidosInstructor.getText().trim());
    }

    public InstructorTO getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorTO instructor) {
        if(instructor == null)
            return;
        this.instructor = instructor;
        jTFApellidosInstructor.setText(instructor.getApellidos());
        jTFNombresInstructor.setText(instructor.getNombres());
        jTAGradoInstructor.setText(instructor.getGrado());
        if(instructor.getIdInstructor().toLowerCase().compareTo(jTFIdInstructor.getText().trim().toLowerCase()) != 0)
            jTFIdInstructor.setText(instructor.getIdInstructor());
    }

    public JButton getjBtnDeleteSelected() {
        return jBtnDeleteSelected;
    }

    public JTextField getjTFQApellidos() {
        return jTFQApellidos;
    }

    public JTextField getjTFQId() {
        return jTFQId;
    }

    public JTextField getjTFQNombres() {
        return jTFQNombres;
    }
    
    public JButton getjBtnQueryInstructores() {
        return jBtnQueryInstructores;
    }

    public JButton getjBtnAdmActividadView() {
        return jBtnAdmActividadView;
    }

    public JButton getjBtnAdmAlumnoView() {
        return jBtnAdmAlumnoView;
    }

    public JButton getjBtnClose() {
        return jBtnClose;
    }

    public JButton getjBtnMaximize() {
        return jBtnMaximize;
    }

    public JButton getjBtnMinimize() {
        return jBtnMinimize;
    }

    public JButton getjBtnEliminarInstructor() {
        return jBtnEliminarInstructor;
    }

    public JButton getjBtnModificarInstructor() {
        return jBtnModificarInstructor;
    }

    public JButton getjBtnRegistrarInstructor() {
        return jBtnRegistrarInstructor;
    }

    public JTextField getjTAGradoInstructor() {
        return jTAGradoInstructor;
    }

    public JTable getjTAQueryInstructores() {
        return jTAQueryInstructores;
    }

    public JTextField getjTFApellidosInstructor() {
        return jTFApellidosInstructor;
    }

    public JTextField getjTFIdInstructor() {
        return jTFIdInstructor;
    }

    public JTextField getjTFNombresInstructor() {
        return jTFNombresInstructor;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jpContenido = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jBtnRegistrarInstructor = new javax.swing.JButton();
        jBtnModificarInstructor = new javax.swing.JButton();
        jBtnEliminarInstructor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFIdInstructor = new javax.swing.JTextField();
        jTFNombresInstructor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFApellidosInstructor = new javax.swing.JTextField();
        jTAGradoInstructor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAQueryInstructores = new javax.swing.JTable();
        jPQueryInstructorControls = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTFQId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFQNombres = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFQApellidos = new javax.swing.JTextField();
        jBtnQueryInstructores = new javax.swing.JButton();
        jBtnDeleteSelected = new javax.swing.JButton();
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

        jBtnRegistrarInstructor.setBackground(new java.awt.Color(0, 0, 153));
        jBtnRegistrarInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnRegistrarInstructor.setForeground(new java.awt.Color(255, 255, 255));
        jBtnRegistrarInstructor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/ADD.png"))); // NOI18N
        jBtnRegistrarInstructor.setToolTipText("insertar");
        jBtnRegistrarInstructor.setBorder(null);
        jBtnRegistrarInstructor.setBorderPainted(false);
        jBtnRegistrarInstructor.setContentAreaFilled(false);
        jBtnRegistrarInstructor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistrarInstructor.setEnabled(false);
        jBtnRegistrarInstructor.setFocusPainted(false);
        jBtnRegistrarInstructor.setFocusable(false);
        jBtnRegistrarInstructor.setRequestFocusEnabled(false);
        jBtnRegistrarInstructor.setVerifyInputWhenFocusTarget(false);

        jBtnModificarInstructor.setBackground(new java.awt.Color(0, 0, 153));
        jBtnModificarInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnModificarInstructor.setForeground(new java.awt.Color(255, 255, 255));
        jBtnModificarInstructor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/UPDATE.png"))); // NOI18N
        jBtnModificarInstructor.setToolTipText("modificar");
        jBtnModificarInstructor.setBorder(null);
        jBtnModificarInstructor.setBorderPainted(false);
        jBtnModificarInstructor.setContentAreaFilled(false);
        jBtnModificarInstructor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnModificarInstructor.setEnabled(false);
        jBtnModificarInstructor.setFocusable(false);
        jBtnModificarInstructor.setVerifyInputWhenFocusTarget(false);
        jBtnModificarInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarInstructorActionPerformed(evt);
            }
        });

        jBtnEliminarInstructor.setBackground(new java.awt.Color(0, 0, 153));
        jBtnEliminarInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jBtnEliminarInstructor.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminarInstructor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/images/DELETE.png"))); // NOI18N
        jBtnEliminarInstructor.setToolTipText("eliminar");
        jBtnEliminarInstructor.setBorder(null);
        jBtnEliminarInstructor.setBorderPainted(false);
        jBtnEliminarInstructor.setContentAreaFilled(false);
        jBtnEliminarInstructor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminarInstructor.setEnabled(false);
        jBtnEliminarInstructor.setFocusable(false);
        jBtnEliminarInstructor.setVerifyInputWhenFocusTarget(false);
        jBtnEliminarInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarInstructorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        jTFIdInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFIdInstructor.setBorder(null);

        jTFNombresInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFNombresInstructor.setBorder(null);
        jTFNombresInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombresInstructorActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE(S):");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("APELLIDOS:");

        jTFApellidosInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFApellidosInstructor.setBorder(null);

        jTAGradoInstructor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTAGradoInstructor.setBorder(null);
        jTAGradoInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTAGradoInstructorActionPerformed(evt);
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
                .addComponent(jBtnRegistrarInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnModificarInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEliminarInstructor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIdInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNombresInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFApellidosInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTAGradoInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnRegistrarInstructor)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnEliminarInstructor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnModificarInstructor, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFIdInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNombresInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFApellidosInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAGradoInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSTRUCTORES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        jTAQueryInstructores.setAutoCreateRowSorter(true);
        jTAQueryInstructores.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTAQueryInstructores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTAQueryInstructores.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTAQueryInstructores);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel5.setText("Identificador");

        jLabel6.setText("Nombres");

        jLabel8.setText("Apellidos");

        jBtnQueryInstructores.setText("Buscar");

        jBtnDeleteSelected.setText("Eliminar Seleccionados");

        javax.swing.GroupLayout jPQueryInstructorControlsLayout = new javax.swing.GroupLayout(jPQueryInstructorControls);
        jPQueryInstructorControls.setLayout(jPQueryInstructorControlsLayout);
        jPQueryInstructorControlsLayout.setHorizontalGroup(
            jPQueryInstructorControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPQueryInstructorControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQId, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnDeleteSelected)
                .addGap(18, 18, 18)
                .addComponent(jBtnQueryInstructores)
                .addContainerGap())
        );
        jPQueryInstructorControlsLayout.setVerticalGroup(
            jPQueryInstructorControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPQueryInstructorControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPQueryInstructorControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFQId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTFQNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTFQApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnQueryInstructores)
                    .addComponent(jBtnDeleteSelected))
                .addContainerGap())
        );

        jPanel2.add(jPQueryInstructorControls, java.awt.BorderLayout.PAGE_START);

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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
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

    private void jTFNombresInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombresInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombresInstructorActionPerformed

    private void jBtnModificarInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnModificarInstructorActionPerformed

    private void jBtnEliminarInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarInstructorActionPerformed

    private void jTAGradoInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTAGradoInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTAGradoInstructorActionPerformed

    private void jBtnAdmActividadViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAdmActividadViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnAdmActividadViewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdmActividadView;
    private javax.swing.JButton jBtnAdmAlumnoView;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnDeleteSelected;
    private javax.swing.JButton jBtnEliminarInstructor;
    private javax.swing.JButton jBtnMaximize;
    private javax.swing.JButton jBtnMinimize;
    private javax.swing.JButton jBtnModificarInstructor;
    private javax.swing.JButton jBtnQueryInstructores;
    private javax.swing.JButton jBtnRegistrarInstructor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPQueryInstructorControls;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTAGradoInstructor;
    private javax.swing.JTable jTAQueryInstructores;
    private javax.swing.JTextField jTFApellidosInstructor;
    private javax.swing.JTextField jTFIdInstructor;
    private javax.swing.JTextField jTFNombresInstructor;
    private javax.swing.JTextField jTFQApellidos;
    private javax.swing.JTextField jTFQId;
    private javax.swing.JTextField jTFQNombres;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    // End of variables declaration//GEN-END:variables
}
