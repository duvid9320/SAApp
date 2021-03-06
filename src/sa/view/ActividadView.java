/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author dave
 */
public class ActividadView extends javax.swing.JFrame {

    /**
     * Creates new form ActividadView
     */
    public ActividadView(ActividadController controller) {
        initComponents();
        jBtnQueryInstructores.addActionListener(e -> controller.getAllInstructores(jTRInstructores));
        jTRInstructores.getSelectionModel().addListSelectionListener(e -> selectInstructor(controller.getInstructor(getSelectedInstructor())));
        jBtnRegistrarActividad.addActionListener(e -> controller.createActividad(getActividadFromView()));
        jBtnModificarActividad.addActionListener(e -> controller.updateActividad(getActividadFromView()));
        jBtnEliminarActividad.addActionListener(e -> controller.deleteActividad(getActividadFromView()));
        jBtnQueryActividades.addActionListener(e -> controller.showAllActividades(jTQActividades));
        jTQActividades.getSelectionModel().addListSelectionListener(e -> setActividadToView(controller.getActividad(getSelectedActividad())));
        jBtnRegistrarHorario.addActionListener(e -> controller.createHorario(getHorarioFromView()));
        jBtnQHorarios.addActionListener(e -> controller.showAllHorarios(getActividadFromView(), jTQHActividad));
        jTQHActividad.getSelectionModel().addListSelectionListener(e -> setHorarioToView(controller.getHorario(getSelectedHorario())));
        jBtnEliminarHorario.addActionListener(e -> controller.deleteHorario(getHorarioFromView()));
        jBtnModificarHorario.addActionListener(e -> controller.updateHorario(getHorarioFromView()));
        init();
    }
    
    private void init(){
        jSHFin.setModel(getHourModel());
        jSHFin.setEditor(new JSpinner.DateEditor(jSHFin, "HH:mm:ss"));
        jSHInicio.setModel(getHourModel());
        jSHInicio.setEditor(new JSpinner.DateEditor(jSHInicio, "HH:mm:ss"));
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
        return new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTPActividadViews = new javax.swing.JTabbedPane();
        jPCreateForm = new javax.swing.JPanel();
        jPFormFields = new javax.swing.JPanel();
        jPCFFields = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFIdActividad = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jCBTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jCBHoras = new javax.swing.JComboBox<>();
        jTFInstructor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jBtnHorario = new javax.swing.JButton();
        jPSelectInstructor = new javax.swing.JPanel();
        jPCFControls = new javax.swing.JPanel();
        jBtnRegistrarActividad = new javax.swing.JButton();
        jBtnQueryInstructores = new javax.swing.JButton();
        jBtnModificarActividad = new javax.swing.JButton();
        jBtnEliminarActividad = new javax.swing.JButton();
        jBtnQueryActividades = new javax.swing.JButton();
        jPQueryResults = new javax.swing.JPanel();
        jPTRInstructores = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTRInstructores = new javax.swing.JTable();
        jPQueryActividades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTQActividades = new javax.swing.JTable();
        jPHorarioActividad = new javax.swing.JPanel();
        jPNuevoHorario = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTFIdHorario = new javax.swing.JTextField();
        jDCFechaHorario = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTFLugarHorario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSHInicio = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jSHFin = new javax.swing.JSpinner();
        jPHorariosActividad = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTQHActividad = new javax.swing.JTable();
        jPHAControls = new javax.swing.JPanel();
        jBtnQHorarios = new javax.swing.JButton();
        jBtnRegistrarHorario = new javax.swing.JButton();
        jBtnEliminarHorario = new javax.swing.JButton();
        jBtnModificarHorario = new javax.swing.JButton();
        jPUpdateForm = new javax.swing.JPanel();
        jPQueryform = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPCreateForm.setLayout(new java.awt.BorderLayout());

        jPFormFields.setLayout(new java.awt.BorderLayout());

        jPCFFields.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Actividad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        jLabel1.setText("Id");

        jTFIdActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIdActividadActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Descripcion");

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jScrollPane1.setViewportView(jTADescripcion);

        jLabel4.setText("Tipo");

        jCBTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Taller", "Conferencia" }));
        jCBTipo.setSelectedIndex(1);

        jLabel5.setText("Horas");

        jCBHoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        jTFInstructor.setEditable(false);
        jTFInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFInstructorActionPerformed(evt);
            }
        });

        jLabel6.setText("Instructor");

        jBtnHorario.setText("Establecer Horario");

        javax.swing.GroupLayout jPCFFieldsLayout = new javax.swing.GroupLayout(jPCFFields);
        jPCFFields.setLayout(jPCFFieldsLayout);
        jPCFFieldsLayout.setHorizontalGroup(
            jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCFFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCFFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTFIdActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTFNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jCBHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPCFFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPCFFieldsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTFInstructor))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCFFieldsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBtnHorario)))))
                .addContainerGap())
        );
        jPCFFieldsLayout.setVerticalGroup(
            jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCFFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jCBHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPCFFieldsLayout.createSequentialGroup()
                        .addGroup(jPCFFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTFInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jBtnHorario)))
                .addContainerGap())
        );

        jPFormFields.add(jPCFFields, java.awt.BorderLayout.CENTER);

        jPCreateForm.add(jPFormFields, java.awt.BorderLayout.PAGE_START);

        jPSelectInstructor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Instructor de la Actividad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPSelectInstructor.setPreferredSize(new java.awt.Dimension(819, 400));
        jPSelectInstructor.setLayout(new java.awt.BorderLayout());

        jBtnRegistrarActividad.setText("Registrar");

        jBtnQueryInstructores.setText("Ver Instructores");

        jBtnModificarActividad.setText("Modificar");
        jBtnModificarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarActividadActionPerformed(evt);
            }
        });

        jBtnEliminarActividad.setText("Eliminar");

        jBtnQueryActividades.setText("Ver Actividades");

        javax.swing.GroupLayout jPCFControlsLayout = new javax.swing.GroupLayout(jPCFControls);
        jPCFControls.setLayout(jPCFControlsLayout);
        jPCFControlsLayout.setHorizontalGroup(
            jPCFControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCFControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrarActividad)
                .addGap(18, 18, 18)
                .addComponent(jBtnModificarActividad)
                .addGap(18, 18, 18)
                .addComponent(jBtnEliminarActividad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addComponent(jBtnQueryActividades)
                .addGap(18, 18, 18)
                .addComponent(jBtnQueryInstructores)
                .addContainerGap())
        );
        jPCFControlsLayout.setVerticalGroup(
            jPCFControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCFControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCFControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnRegistrarActividad)
                    .addComponent(jBtnQueryInstructores)
                    .addComponent(jBtnModificarActividad)
                    .addComponent(jBtnEliminarActividad)
                    .addComponent(jBtnQueryActividades))
                .addContainerGap())
        );

        jPSelectInstructor.add(jPCFControls, java.awt.BorderLayout.PAGE_END);

        jPQueryResults.setLayout(new java.awt.GridLayout(2, 1));

        jScrollPane3.setViewportView(jTRInstructores);

        javax.swing.GroupLayout jPTRInstructoresLayout = new javax.swing.GroupLayout(jPTRInstructores);
        jPTRInstructores.setLayout(jPTRInstructoresLayout);
        jPTRInstructoresLayout.setHorizontalGroup(
            jPTRInstructoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTRInstructoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPTRInstructoresLayout.setVerticalGroup(
            jPTRInstructoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTRInstructoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPQueryResults.add(jPTRInstructores);

        jPQueryActividades.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actividades Registradas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPQueryActividades.setLayout(new java.awt.GridLayout(1, 0));

        jTQActividades.setAutoCreateRowSorter(true);
        jTQActividades.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTQActividades);

        jPQueryActividades.add(jScrollPane2);

        jPQueryResults.add(jPQueryActividades);

        jPSelectInstructor.add(jPQueryResults, java.awt.BorderLayout.CENTER);

        jPCreateForm.add(jPSelectInstructor, java.awt.BorderLayout.CENTER);

        jTPActividadViews.addTab("Crear", jPCreateForm);

        jPHorarioActividad.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Id");

        jTFIdHorario.setEditable(false);

        jLabel9.setText("Fecha");

        jLabel11.setText("Lugar");

        jTFLugarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFLugarHorarioActionPerformed(evt);
            }
        });

        jLabel12.setText("Hora Inicio");

        jLabel13.setText("Hora Fin");

        javax.swing.GroupLayout jPNuevoHorarioLayout = new javax.swing.GroupLayout(jPNuevoHorario);
        jPNuevoHorario.setLayout(jPNuevoHorarioLayout);
        jPNuevoHorarioLayout.setHorizontalGroup(
            jPNuevoHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNuevoHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIdHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSHInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSHFin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFLugarHorario, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDCFechaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPNuevoHorarioLayout.setVerticalGroup(
            jPNuevoHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNuevoHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPNuevoHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPNuevoHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jTFLugarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPNuevoHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTFIdHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jSHInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jSHFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDCFechaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jPNuevoHorarioLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDCFechaHorario, jLabel11, jLabel12, jLabel13, jLabel7, jLabel9, jSHFin, jSHInicio, jTFIdHorario, jTFLugarHorario});

        jPHorarioActividad.add(jPNuevoHorario, java.awt.BorderLayout.PAGE_START);

        jPHorariosActividad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Horarios de la Actividad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        jTQHActividad.setAutoCreateRowSorter(true);
        jTQHActividad.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTQHActividad);

        javax.swing.GroupLayout jPHorariosActividadLayout = new javax.swing.GroupLayout(jPHorariosActividad);
        jPHorariosActividad.setLayout(jPHorariosActividadLayout);
        jPHorariosActividadLayout.setHorizontalGroup(
            jPHorariosActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPHorariosActividadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPHorariosActividadLayout.setVerticalGroup(
            jPHorariosActividadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPHorariosActividadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPHorarioActividad.add(jPHorariosActividad, java.awt.BorderLayout.CENTER);

        jBtnQHorarios.setText("Ver Horarios");

        jBtnRegistrarHorario.setText("Registrar");
        jBtnRegistrarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarHorarioActionPerformed(evt);
            }
        });

        jBtnEliminarHorario.setText("Eliminar");

        jBtnModificarHorario.setText("Modificar");

        javax.swing.GroupLayout jPHAControlsLayout = new javax.swing.GroupLayout(jPHAControls);
        jPHAControls.setLayout(jPHAControlsLayout);
        jPHAControlsLayout.setHorizontalGroup(
            jPHAControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPHAControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnRegistrarHorario)
                .addGap(18, 18, 18)
                .addComponent(jBtnModificarHorario)
                .addGap(18, 18, 18)
                .addComponent(jBtnEliminarHorario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                .addComponent(jBtnQHorarios)
                .addContainerGap())
        );
        jPHAControlsLayout.setVerticalGroup(
            jPHAControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPHAControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPHAControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnQHorarios)
                    .addComponent(jBtnRegistrarHorario)
                    .addComponent(jBtnEliminarHorario)
                    .addComponent(jBtnModificarHorario))
                .addContainerGap())
        );

        jPHorarioActividad.add(jPHAControls, java.awt.BorderLayout.PAGE_END);

        jTPActividadViews.addTab("Horario Actividad", jPHorarioActividad);

        javax.swing.GroupLayout jPUpdateFormLayout = new javax.swing.GroupLayout(jPUpdateForm);
        jPUpdateForm.setLayout(jPUpdateFormLayout);
        jPUpdateFormLayout.setHorizontalGroup(
            jPUpdateFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 819, Short.MAX_VALUE)
        );
        jPUpdateFormLayout.setVerticalGroup(
            jPUpdateFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jTPActividadViews.addTab("Modificar/Eliminar", jPUpdateForm);

        javax.swing.GroupLayout jPQueryformLayout = new javax.swing.GroupLayout(jPQueryform);
        jPQueryform.setLayout(jPQueryformLayout);
        jPQueryformLayout.setHorizontalGroup(
            jPQueryformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 819, Short.MAX_VALUE)
        );
        jPQueryformLayout.setVerticalGroup(
            jPQueryformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jTPActividadViews.addTab("Consultar", jPQueryform);

        getContentPane().add(jTPActividadViews);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFInstructorActionPerformed

    private void jTFIdActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIdActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIdActividadActionPerformed

    private void jTFLugarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFLugarHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFLugarHorarioActionPerformed

    private void jBtnRegistrarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnRegistrarHorarioActionPerformed

    private void jBtnModificarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnModificarActividadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEliminarActividad;
    private javax.swing.JButton jBtnEliminarHorario;
    private javax.swing.JButton jBtnHorario;
    private javax.swing.JButton jBtnModificarActividad;
    private javax.swing.JButton jBtnModificarHorario;
    private javax.swing.JButton jBtnQHorarios;
    private javax.swing.JButton jBtnQueryActividades;
    private javax.swing.JButton jBtnQueryInstructores;
    private javax.swing.JButton jBtnRegistrarActividad;
    private javax.swing.JButton jBtnRegistrarHorario;
    private javax.swing.JComboBox<String> jCBHoras;
    private javax.swing.JComboBox<String> jCBTipo;
    private com.toedter.calendar.JDateChooser jDCFechaHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPCFControls;
    private javax.swing.JPanel jPCFFields;
    private javax.swing.JPanel jPCreateForm;
    private javax.swing.JPanel jPFormFields;
    private javax.swing.JPanel jPHAControls;
    private javax.swing.JPanel jPHorarioActividad;
    private javax.swing.JPanel jPHorariosActividad;
    private javax.swing.JPanel jPNuevoHorario;
    private javax.swing.JPanel jPQueryActividades;
    private javax.swing.JPanel jPQueryResults;
    private javax.swing.JPanel jPQueryform;
    private javax.swing.JPanel jPSelectInstructor;
    private javax.swing.JPanel jPTRInstructores;
    private javax.swing.JPanel jPUpdateForm;
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
    private javax.swing.JTabbedPane jTPActividadViews;
    private javax.swing.JTable jTQActividades;
    private javax.swing.JTable jTQHActividad;
    private javax.swing.JTable jTRInstructores;
    // End of variables declaration//GEN-END:variables

}
