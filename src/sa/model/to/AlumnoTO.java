/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.HashMap;
import sa.model.dao.CarreraDAO;
import sa.utils.SAUtils;

/**
 *
 * @author David Rodríguez
 */
public class AlumnoTO implements GenericTO{
    private String numeroControl;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int semestre;
    private CarreraTO carrera;
    private byte[] huella;

    public AlumnoTO(HashMap<String, Object> data) {
        this(
                String.valueOf(data.get("NumeroControl")), 
                String.valueOf(data.get("Nombres")), 
                String.valueOf(data.get("ApellidoPaterno")), 
                String.valueOf(data.get("ApellidoMaterno")), 
                Integer.parseInt(String.valueOf(data.get("Semestre"))), 
                CarreraDAO.getInstance().getCarrera(String.valueOf(data.get("CarreraFk"))),
                SAUtils.getBytesArrayFromObject(data.get("Huella"))
        );
    }
    
    public AlumnoTO(String numeroControl, String nombres, String apellidoPaterno, String apellidoMaterno, int semestre, CarreraTO carrera, byte[] huella) {
        this.numeroControl = numeroControl;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.semestre = semestre;
        this.carrera = carrera;
        this.huella = huella;
    }
    
    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public CarreraTO getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraTO carrera) {
        this.carrera = carrera;
    }

    public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        this.huella = huella;
    }
    
    public boolean isValid(){
        return SAUtils.stringsAreValid(this.apellidoMaterno, this.apellidoPaterno, this.nombres, this.numeroControl) && semestre != 0 && this.huella != null && carrera.isValid();
    }
    
    @Override
    public String getInsertSQL(){
        return String.format(
                "INSERT INTO Alumno VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s')",
                this.numeroControl,
                this.nombres,
                this.apellidoPaterno, 
                this.apellidoMaterno,
                this.semestre,
                this.carrera.getIdCarrera(),
                this.getHuella()
        );
    }

    @Override
    public String getUpdateSQL() {
        return String.format(
                "UPDATE Alumno SET Nombres = '%s', ApellidoPaterno = '%s', ApellidoMaterno = '%s', Semestre = '%s', CarreraFk = '%s' WHERE NumeroControl = '%s'", 
                nombres,
                apellidoPaterno, 
                apellidoMaterno,
                semestre,
                carrera.getIdCarrera(),
                numeroControl
        );
    }

    @Override
    public String getDeleteSQL() {
        return String.format("DELETE FROM Alumno WHERE NumeroControl = '%s'", numeroControl);
    }

    @Override
    public String getQuerySQL() {
        return String.format("SELECT * FROM Alumno WHERE NumeroControl = '%s'", numeroControl);
    }
}
