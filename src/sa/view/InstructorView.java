/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.view;

import sa.controller.InstructorController;
import sa.model.to.InstructorTO;

/**
 *
 * @author dave
 */
public class InstructorView extends javax.swing.JFrame {

    /**
     * Creates new form InstructorView
     */
    public InstructorView(InstructorController controller) {
        initComponents();
        jBtnRegistrar.addActionListener(e -> controller.createInstructor(getInstructorFromView()));
        jBtnModificar.addActionListener(e -> controller.updateInstructor(getInstructorFromView()));
        jBtnEliminar.addActionListener(e -> controller.deleteInstructor(getInstructorFromView()));
        jBtnBuscar.addActionListener(e -> controller.buscarTodos(jTAResults));
        jTAResults.getSelectionModel().addListSelectionListener(e -> setInstructorToView(controller.getInstructor(getSelectedInstructor())));
        setVisible(true);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPFormInstructor = new javax.swing.JPanel();
        jPFormControls = new javax.swing.JPanel();
        jBtnRegistrar = new javax.swing.JButton();
        jBtnModificar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnBuscar = new javax.swing.JButton();
        jPFormFields = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFNombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFApellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAGrado = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jTFId = new javax.swing.JTextField();
        jPQueryResults = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAResults = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPFormInstructor.setLayout(new java.awt.BorderLayout());

        jPFormControls.setLayout(new java.awt.GridLayout(4, 1));

        jBtnRegistrar.setText("Registrar");
        jBtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarActionPerformed(evt);
            }
        });
        jPFormControls.add(jBtnRegistrar);

        jBtnModificar.setText("Modificar");
        jPFormControls.add(jBtnModificar);

        jBtnEliminar.setText("Eliminar");
        jPFormControls.add(jBtnEliminar);

        jBtnBuscar.setText("Buscar");
        jPFormControls.add(jBtnBuscar);

        jPFormInstructor.add(jPFormControls, java.awt.BorderLayout.LINE_END);

        jPFormFields.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Instructor", 2, 1));

        jLabel1.setText("Nombres");

        jTFNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombresActionPerformed(evt);
            }
        });

        jLabel2.setText("Apellidos");

        jLabel3.setText("Grado");

        jTAGrado.setColumns(20);
        jTAGrado.setRows(5);
        jScrollPane1.setViewportView(jTAGrado);

        jLabel4.setText("Id");

        javax.swing.GroupLayout jPFormFieldsLayout = new javax.swing.GroupLayout(jPFormFields);
        jPFormFields.setLayout(jPFormFieldsLayout);
        jPFormFieldsLayout.setHorizontalGroup(
            jPFormFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFormFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFormFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFormFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTFId, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTFNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                    .addGroup(jPFormFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPFormFieldsLayout.setVerticalGroup(
            jPFormFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFormFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFormFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTFId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPFormFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPFormInstructor.add(jPFormFields, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPFormInstructor, java.awt.BorderLayout.NORTH);

        jPQueryResults.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Instructores Registrados", 2, 1));
        jPQueryResults.setLayout(new java.awt.GridLayout(1, 0));

        jTAResults.setAutoCreateRowSorter(true);
        jTAResults.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTAResults);

        jPQueryResults.add(jScrollPane2);

        getContentPane().add(jPQueryResults, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombresActionPerformed

    private void jBtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnModificar;
    private javax.swing.JButton jBtnRegistrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPFormControls;
    private javax.swing.JPanel jPFormFields;
    private javax.swing.JPanel jPFormInstructor;
    private javax.swing.JPanel jPQueryResults;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAGrado;
    private javax.swing.JTable jTAResults;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFId;
    private javax.swing.JTextField jTFNombres;
    // End of variables declaration//GEN-END:variables

}
