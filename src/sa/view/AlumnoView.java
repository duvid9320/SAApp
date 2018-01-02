package sa.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import sa.model.to.AlumnoTO;
import sa.model.to.CarreraTO;
import sa.utils.SAUtils;

public class AlumnoView extends javax.swing.JFrame{

    private AlumnoTO alumno;
    private int x,y;
    
    public AlumnoView() {
        initComponents();
        setLocationRelativeTo(null);
        alumno = new AlumnoTO(); 
        alumno.setHuella(new byte[16]);
    }
    
    public void setSemestre(){
        alumno.setSemestre(jCBSemestre.getSelectedIndex() != -1 ? Integer.parseInt(String.valueOf(jCBSemestre.getSelectedItem())) : 0);
    }
    
    public void setCarrera(CarreraTO carrera){
        alumno.setCarrera(carrera);
    }
    
    public String getSelectedCarrera(){
        return jCBCarrera.getSelectedIndex() != -1 ? String.valueOf(jCBCarrera.getSelectedItem()) : "";
    }
    
    public String getSelectedAlumno(int r){
        return r != -1 ? String.valueOf(jTAAlumnos.getValueAt(r, 0)) : null;
    }
    
    public String getNumeroControl(){
        return jTFNumeroControl.getText().trim();
    }
    
    public void setNumeroControl(){
        alumno.setNumeroControl(jTFNumeroControl.getText().trim());
    }
    
    public void setNombres(){
        alumno.setNombres(jTFNombres.getText().trim());
    }
    
    public void setApellidoMaterno(){
        alumno.setApellidoMaterno(jTFAMaterno.getText().trim());
    }
    
    public void setApellidoPaterno(){
        alumno.setApellidoPaterno(jTFAPaterno.getText().trim());
    }
    
    private HashMap<String, JTextComponent> getConditions(){
        HashMap<String, JTextComponent> conditions = new HashMap<>(6);
        conditions.put("NumeroControl", jTFQNControl);
        conditions.put("Nombres", jTFQNombres);
        conditions.put("ApellidoPaterno", jTFQAPaterno);
        conditions.put("ApellidoMaterno", jTFQAMaterno);
        conditions.put("Semestre", jTFQSemestre);
        conditions.put("CarreraFK", jTFQCarrera);
        return conditions;
    }
    
    private LinkedHashMap<String, String> getFields(){
        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("NumeroControl", "Numero de Control");
        fields.put("Nombres", "");
        fields.put("ApellidoPaterno", "Apellido Paterno");
        fields.put("ApellidoMaterno", "Apellido Materno");
        fields.put("Semestre", "");
        fields.put("Carrera.Nombre", "Carrera");
        return fields;
    }
    
    public String getQueryAlumnos(){
        return SAUtils.getQuery(getFields(), "Alumno INNER JOIN Carrera ON Carrera.IdCarrera = Alumno.CarreraFk", getConditions());
    }
    
    public void resetView(){
        jTFAMaterno.setText("");
        jTFAPaterno.setText("");
        jTFNombres.setText("");
        jTFNumeroControl.setText("");
        jCBCarrera.setSelectedIndex(-1);
        jCBSemestre.setSelectedIndex(-1);
    }

    public AlumnoTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoTO alumno) {
        this.alumno = alumno;
        jTFAMaterno.setText(alumno.getApellidoMaterno());
        jTFAPaterno.setText(alumno.getApellidoPaterno());
        jTFNombres.setText(alumno.getNombres());
        jCBSemestre.setSelectedItem(String.valueOf(alumno.getSemestre()));
        jCBCarrera.setSelectedItem(alumno.getCarrera().getIdCarrera());
        if(jTFNumeroControl.getText().trim().toLowerCase().compareTo(alumno.getNumeroControl().trim().toLowerCase()) != 0)
            jTFNumeroControl.setText(alumno.getNumeroControl());
    }

    public JButton getjBtnEliminarSeleccionados() {
        return jBtnEliminarSeleccionados;
    }

    public JButton getjBtnQuery() {
        return jBtnQuery;
    }

    public JButton getjBtnAdmActividadView() {
        return jBtnAdmActividadView;
    }

    public JButton getjBtnAdmInstructorView() {
        return jBtnAdmInstructorView;
    }

    public JButton getjBtnClose() {
        return jBtnClose;
    }

    public JButton getjBtnEliminar() {
        return jBtnEliminar;
    }

    public JButton getjBtnMaximize() {
        return jBtnMaximize;
    }

    public JButton getjBtnMinimize() {
        return jBtnMinimize;
    }

    public JButton getjBtnModificar() {
        return jBtnModificar;
    }

    public JButton getjBtnRegistrar() {
        return jBtnRegistrar;
    }

    public JComboBox<String> getjCBCarrera() {
        return jCBCarrera;
    }

    public JComboBox<String> getjCBSemestre() {
        return jCBSemestre;
    }

    public JTable getjTAAlumnos() {
        return jTAAlumnos;
    }

    public JTextField getjTFAMaterno() {
        return jTFAMaterno;
    }

    public JTextField getjTFAPaterno() {
        return jTFAPaterno;
    }

    public JTextField getjTFNombres() {
        return jTFNombres;
    }

    public JTextField getjTFNumeroControl() {
        return jTFNumeroControl;
    }
    
    public JTextField getjTFQAMaterno() {
        return jTFQAMaterno;
    }

    public JTextField getjTFQAPaterno() {
        return jTFQAPaterno;
    }

    public JTextField getjTFQCarrera() {
        return jTFQCarrera;
    }

    public JTextField getjTFQNControl() {
        return jTFQNControl;
    }

    public JTextField getjTFQNombres() {
        return jTFQNombres;
    }

    public JTextField getjTFQSemestre() {
        return jTFQSemestre;
    }
//@SuppressWarnings("unchecked")
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
        jTFNumeroControl = new javax.swing.JTextField();
        jTFNombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFAPaterno = new javax.swing.JTextField();
        jTFAMaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCBCarrera = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jCBSemestre = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAAlumnos = new javax.swing.JTable();
        jPQueryControls = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTFQNControl = new javax.swing.JTextField();
        jTFQNombres = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTFQAPaterno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTFQAMaterno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jBtnQuery = new javax.swing.JButton();
        jBtnEliminarSeleccionados = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTFQCarrera = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFQSemestre = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jBtnAdmActividadView = new javax.swing.JButton();
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
        jBtnRegistrar.setEnabled(false);
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
        jBtnModificar.setEnabled(false);
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
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.setFocusable(false);
        jBtnEliminar.setVerifyInputWhenFocusTarget(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("N. CONTROL:");

        jTFNumeroControl.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFNumeroControl.setBorder(null);

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
        jLabel3.setText("APELLIDO PATERNO:");

        jTFAPaterno.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFAPaterno.setBorder(null);

        jTFAMaterno.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTFAMaterno.setBorder(null);
        jTFAMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAMaternoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("APELLIDO MATERNO:");

        jCBCarrera.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCBCarrera.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CARRERA:");

        jCBSemestre.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCBSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jCBSemestre.setBorder(null);
        jCBSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBSemestreActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SEMESTRE:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNumeroControl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jTFNumeroControl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ALUMNOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(null);

        jTAAlumnos.setAutoCreateRowSorter(true);
        jTAAlumnos.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTAAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTAAlumnos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTAAlumnos);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel8.setText("Numero de Control");

        jLabel9.setText("Nombres");

        jLabel10.setText("Apellido Paterno");

        jLabel11.setText("Apellido Materno");

        jBtnQuery.setText("Buscar");

        jBtnEliminarSeleccionados.setText("Eliminar Seleccionados");
        jBtnEliminarSeleccionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarSeleccionadosActionPerformed(evt);
            }
        });

        jLabel12.setText("Carrera");

        jLabel13.setText("Semestre");

        javax.swing.GroupLayout jPQueryControlsLayout = new javax.swing.GroupLayout(jPQueryControls);
        jPQueryControls.setLayout(jPQueryControlsLayout);
        jPQueryControlsLayout.setHorizontalGroup(
            jPQueryControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPQueryControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPQueryControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPQueryControlsLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFQNControl)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFQNombres)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jTFQAPaterno)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTFQAMaterno))
                    .addGroup(jPQueryControlsLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFQCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFQSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                        .addComponent(jBtnEliminarSeleccionados)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnQuery)))
                .addContainerGap())
        );
        jPQueryControlsLayout.setVerticalGroup(
            jPQueryControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPQueryControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPQueryControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFQNControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTFQNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTFQAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTFQAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPQueryControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnQuery)
                    .addComponent(jBtnEliminarSeleccionados)
                    .addComponent(jLabel12)
                    .addComponent(jTFQCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTFQSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.add(jPQueryControls, java.awt.BorderLayout.PAGE_START);

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
        jLabel20.setText("ALUMNOS");

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
        jBtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCloseActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAdmInstructorView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAdmActividadView)
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
                    .addComponent(jBtnAdmInstructorView)
                    .addComponent(jBtnAdmActividadView)
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

    private void jTFAMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAMaternoActionPerformed

    private void jCBSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBSemestreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBSemestreActionPerformed

    private void jBtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnCloseActionPerformed

    private void jBtnEliminarSeleccionadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarSeleccionadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarSeleccionadosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdmActividadView;
    private javax.swing.JButton jBtnAdmInstructorView;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnEliminarSeleccionados;
    private javax.swing.JButton jBtnMaximize;
    private javax.swing.JButton jBtnMinimize;
    private javax.swing.JButton jBtnModificar;
    private javax.swing.JButton jBtnQuery;
    private javax.swing.JButton jBtnRegistrar;
    private javax.swing.JComboBox<String> jCBCarrera;
    private javax.swing.JComboBox<String> jCBSemestre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPQueryControls;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTAAlumnos;
    private javax.swing.JTextField jTFAMaterno;
    private javax.swing.JTextField jTFAPaterno;
    private javax.swing.JTextField jTFNombres;
    private javax.swing.JTextField jTFNumeroControl;
    private javax.swing.JTextField jTFQAMaterno;
    private javax.swing.JTextField jTFQAPaterno;
    private javax.swing.JTextField jTFQCarrera;
    private javax.swing.JTextField jTFQNControl;
    private javax.swing.JTextField jTFQNombres;
    private javax.swing.JTextField jTFQSemestre;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpFondo;
    // End of variables declaration//GEN-END:variables
}
