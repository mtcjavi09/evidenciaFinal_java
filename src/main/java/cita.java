/*
    PROGRAMA 02: CONSULTORIO | CLASE cita
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import java.util.*;

public class cita 
{
    //Atributos de la cita
    private int id;
    private String nombre;
    private Date fecha;
    private String hora;
    private String motivo;
    private medico medico;
    private paciente paciente;
    //Lista que guardará todas las citas
    private List <cita> cita;

    //Constructor
    public cita(int id, Date fecha, String hora, String motivo, medico medico, paciente paciente, List<cita> cita) 
    {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
        this.cita = cita;
    }

    //Constructor vacío para su uso en algunos métodos de la clase main
    public cita() {}
    
    //Métodos Get y Set para atributos privados
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}
    public String getHora() {return hora;}
    public void setHora(String hora) {this.hora = hora;}
    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public medico getMedico() {return medico;}
    public void setMedico(medico medico) {this.medico = medico;}
    public paciente getPaciente() {return paciente;}
    public void setPaciente(paciente paciente) {this.paciente = paciente;}
    public List<cita> getCita() {return cita;}
    public void setCita(List<cita> cita) {this.cita = cita;}
}
