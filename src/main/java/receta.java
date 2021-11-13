/*
    PROGRAMA 08: CONSULTORIO | CLASE receta
    AUTORA: Maria Tchijov Cruz
    FECHA: 01 de noviembre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import java.util.*;

public class receta 
{
    //Atributos de la receta
    private int id;
    private Date fecha;
    private paciente paciente;
    private medico medico;
    private String medicamento;
    private String presentacion;
    private String dosis;
    
    //Lista donde se guardarán todos las recetas
    private List <receta> receta;
    
    //Constructor
    public receta(int id, Date fecha, paciente paciente, medico medico, String medicamento, String presentacion, String dosis) 
    {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
        this.medicamento = medicamento;
        this.presentacion = presentacion;
        this.dosis = dosis;
    }
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public receta() {}
    
    //Métodos Get y Set para atributos privados
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}
    public paciente getPaciente() {return paciente;}
    public void setPaciente(paciente paciente) {this.paciente = paciente;}
    public medico getMedico() {return medico;}
    public void setMedico(medico medico) {this.medico = medico;}
    public String getMedicamento() {return medicamento;}
    public void setMedicamento(String medicamento) {this.medicamento = medicamento;}
    public String getPresentacion() {return presentacion;}
    public void setPresentacion(String presentacion) {this.presentacion = presentacion;}
    public String getDosis() {return dosis;}
    public void setDosis(String dosis) {this.dosis = dosis;}
    public List<receta> getReceta() {return receta;}
    public void setReceta(List<receta> receta) {this.receta = receta;}
}
