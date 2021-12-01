/*
    PROGRAMA 02: CONSULTORIO | CLASE medico
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JOptionPane;

public class medico extends persona
{
    //Atributos del médico
    private String especialidad; 

    //Lista donde se guardarán todos los pacientes
    private static List <medico> medicos = new ArrayList<>();
    
    
    //Constructor para crear el médico usando atributos completos
    public medico(String especialidad, int id, String nombre, String apellido, int edad, char genero, String contraseña, String email) 
    {
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.especialidad = especialidad;
    }
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public medico() {}
    
    //Métodos Get y Set para atributos privados
    public String getEspecialidad() {return especialidad;}
    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

    //Métodos propios de la clase
    @Override
    //creaPersona: registra a un nuevo paciente para su acceso al sistema
    public void creaPersona()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones            
        try
        {
            //Variables necesarias para guardar los atributos del medico
            int id, edad;
            String nombre, apellido, ingresaGenero;
            char genero;
            String contraseña, email, especialidad;
            //Se crea el arreglo con las opciones de género para evitar error por parte del usuario
            String [] generos = {"F","M"};
            
            //Se aumenta un id dependiendo de la cantidad de personas guardadas en la lista personas
            id = getPersonas().size() + 1;
            //Se piden los datos del paciente
            nombre = JOptionPane.showInputDialog("Nombre del médico:");
            apellido = JOptionPane.showInputDialog("Apellido del médico:");
            email = JOptionPane.showInputDialog("Correo electrónico del médico: ");
            contraseña = JOptionPane.showInputDialog("Contraseña con la que accederá el médico:");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del médico:"));
            especialidad = JOptionPane.showInputDialog("Diagnóstico del médico:");
            //Se elige el género del paciente
            ingresaGenero = (String) JOptionPane.showInputDialog(null,"Indica el género del médico:\n (Usa F para Femenino y M para masculino)\n\n", 
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            //Se convierte la opción elegida para crear el objeto de forma correcta
            genero = ingresaGenero.charAt(0);
            
            //Se crea el objeto medico
            medico medico = new medico(especialidad, id, nombre, apellido, edad, genero, contraseña, email);
            
            //Se guarda el objeto en la lista personas para que sea guardado en el archivo JSON
            getPersonas().add(medico);
            
            //Se guarda el objeto en la lista medicos para poder permitir filtrar por médicos
            medicos.add(medico);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el médico en la lista personas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar el médico en la lista por el error: " + e.getMessage());}
    }
}
