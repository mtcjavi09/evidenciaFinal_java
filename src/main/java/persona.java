/*
    PROGRAMA 08: CONSULTORIO | CLASE persona
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import javax.swing.*;
import java.io.*;
import java.util.*;

public class persona
{
    //Atributos de la persona
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String contraseña;
    private String email;
    
    //Lista para guardar todos los usuarios(personas)
    private static List <persona> persona;
    
    //Constructor persona usando todos los atributos
    public persona(int id, String nombre, String apellido, int edad, char genero, String contraseña, String email) 
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.contraseña = contraseña;
        this.email = email;
    }
    
    //Constructor persona usado para creación de médico para paciente (sin usar todos los atributos)
    public persona(String nombre, String apellido) 
    {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public persona() {}
    
    //Métodos Get y Set para atributos privados
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public char getGenero() {return genero;}
    public void setGenero(char genero) {this.genero = genero;}
    public String getContraseña() {return contraseña;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}   
    public List<persona> getPersona() {return persona;}
    public void setPersona(List<persona> persona) {this.persona = persona;}
       
    //Métodos propios de la clase
    public void registrarUsuario()
    {
        
        if (persona == null)
        {
            //Se reserva memoria para guardar los usuarios (personas)
            persona = new ArrayList<>();
        }
        
        else
        {
            //Ingreso de dato semilla para el correcto uso del programa
            persona.add(new persona(id+=1,"Maria","Tchijov",19,'F',"m123",
                    "mashjov13@outlook.es")); 
            
            
        }
    }
}
