/*
    PROGRAMA 02: CONSULTORIO | CLASE paciente
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class paciente extends persona
{
    //Atributos del paciente
    private String diagnostico;
    //Aquí se indica que tendrá un objeto de la clase médico como atributo
    private medico medico;
    //Lista donde se guardarán todos los pacientes
    private List <paciente> paciente;

    //Constructor
    public paciente(String diagnostico, medico medico, int id, String nombre, String apellido, int edad, char genero, String contraseña, String email) 
    {
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.diagnostico = diagnostico;
        this.medico = medico;
    }

    //Constructor para crear el médico para las recetas y las citas
    //(sin usar todos los atributos)
    public paciente(medico medico, String nombre, String apellido) 
    {
        super(nombre, apellido);
        this.medico = medico;
    }
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public paciente() {}
    
    //Métodos Get y Set para atributos privados
    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}
    public medico getMedico() {return medico;}
    public void setMedico(medico medico) {this.medico = medico;}
    public List<paciente> getPaciente() {return paciente;}
    public void setPaciente(List<paciente> paciente) {this.paciente = paciente;}
    
    //Métodos propios de la clase
    
    //Método creaPaciente para pedir los datos necesarios del paciente
    //  y guardarlos en el archivo JSON
    public static boolean creaPaciente(String archivo) throws Exception
    {
        //Variables necesarias para el correcto funcionamiento del método
        String nombre, apellido, contraseña, email, diagnostico, nombre_medico, apellido_medico, especialidad;
        int id, edad;
        char genero;
        medico medico;
            
        try
        {
            //Se piden los datos del paciente
            id = Integer.parseInt(JOptionPane.showInputDialog("Identificador del paciente:"));
            nombre = JOptionPane.showInputDialog("Nombre del paciente:");
            apellido = JOptionPane.showInputDialog("Apellido del paciente:");
            email = JOptionPane.showInputDialog("Correo electrónico del paciente: ");
            contraseña = JOptionPane.showInputDialog("Contraseña con la que accederá el paciente:");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del paciente:"));
            genero = (JOptionPane.showInputDialog("Indica el género del paciente:\n (Usa F para Femenino y M para masculino)")).charAt(0);
            diagnostico = JOptionPane.showInputDialog("Diagnóstico del paciente:");
            //Se piden los datos del medico del paciente (sin usar todos los atributos, sólo los necesarios para la identificación)
            nombre_medico = JOptionPane.showInputDialog("Nombre del médico:");
            apellido_medico = JOptionPane.showInputDialog("Apellido del médico:");
            especialidad = JOptionPane.showInputDialog("Área a la que pertenece el médico:");
            //Se crea el objeto medico para guardarlo en el objeto paciente        
            medico = new medico(especialidad, nombre_medico, apellido_medico);
            
            //Se crea el objeto paciente
            paciente paciente = new paciente(diagnostico, medico, id, nombre, apellido, edad, genero, contraseña, email);
            //Se guardan los objetos del paciente en el archivo json
            guardaPaciente(paciente, archivo);
            return true;
        }
        
        catch(Exception e)
        {throw new Exception("No se puede guardar el paciente");}
    }
    
    //Método guardaPaciente para guardar los datos del paciente en un archivo json
    public static void guardaPaciente(paciente paciente, String archivo) throws Exception
    {
        String jsonPaciente;
        
        //Se convierte el objeto a JSON.
        Gson gson = new Gson();
        
        jsonPaciente = gson.toJson(paciente);
        System.out.println("Paciente JSON: " + jsonPaciente);
        try
        {
            FileWriter fileWriter = new FileWriter(archivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(jsonPaciente);
            printWriter.close();
        }
        catch (Exception e)
        {throw new Exception("Error de guardado");}
    }
    
    //Método cargaPaciente para leer y cargar el archivo 
    public static void cargaPaciente(String archivo) throws Exception
    {
        try
        {
            File file = new File(archivo);
        
            BufferedReader lector = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();

            String cadena;

            while ((cadena = lector.readLine()) != null)
            {
                System.out.println(cadena);
                //json.append(cadena);
            }

            Gson gson = new Gson();
            paciente paciente = gson.fromJson(json.toString(), paciente.class);

            System.out.println("Paciente cargado");
        }
        
        catch (Exception e)
        {throw new Exception("Error de carga de datos");}
    }
}
