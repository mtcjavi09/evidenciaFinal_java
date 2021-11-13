/*
    PROGRAMA 02: CONSULTORIO | CLASE medico
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import java.util.*;

public class medico extends persona
{
    //Atributos del médico
    private String especialidad; 
    
    //Lista donde se guardarán todos los médicos
    private List <medico> medico;
    
    //Constructor para crear el médico usando atributos completos
    public medico(String especialidad, int id, String nombre, String apellido, int edad, char genero, String contraseña, String email) 
    {
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.especialidad = especialidad;
    }
    
    //Constructor para crear el médico para el paciente y las citas
    //(sin usar todos los atributos)
    public medico(String especialidad, String nombre, String apellido) 
    {
        super(nombre, apellido);
        this.especialidad = especialidad;
    }
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public medico() {}
    
    //Métodos Get y Set para atributos privados
    public String getEspecialidad() {return especialidad;}
    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}
    public List<medico> getMedico() {return medico;}
    public void setMedico(List<medico> medico) {this.medico = medico;}
}
