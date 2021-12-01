/*
    PROGRAMA 02: CONSULTORIO | CLASE paciente
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
    private static List <paciente> pacientes = new ArrayList<>();
    
    //Constructor para crear el paciente usando atributos completos
    public paciente(String diagnostico, medico medico, int id, String nombre, String apellido, int edad, char genero, String contraseña, String email) 
    {
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.diagnostico = diagnostico;
        this.medico = medico;
    }
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public paciente() {}
    
    //Métodos Get y Set para atributos privados
    public String getDiagnostico() {return diagnostico;}
    public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}
    public medico getMedico() {return medico;}
    public void setMedico(medico medico) {this.medico = medico;}

    
    
    //Métodos propios de la clase
    
    @Override
    //creaPersona: registra a un nuevo paciente para su acceso al sistema
    public void creaPersona()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones            
        try
        {
            //Se llama al método buscaMedico para conocer si el médico está registrado o no y se guarda en la variable medico
            medico medico = buscaMedico();
            
            //Si está nulo, manda una excepción
            if (medico == null)
            {throw new Exception("No se puede crear el paciente sin un médico, por favor, registra un médico tratante");}
            
            //Si no, crea al nuevo paciente
            //Variables necesarias para guardar los atributos del paciente
            int id, edad;
            String nombre, apellido, ingresaGenero;
            char genero;
            String contraseña, email, diagnostico;
            //Se crea el arreglo con las opciones de género para evitar error por parte del usuario
            String [] generos = {"F","M"};
            
            //Se aumenta un id dependiendo de la cantidad de personas guardadas en la lista personas
            id = getPersonas().size() + 1;
            //Se piden los datos del paciente
            nombre = JOptionPane.showInputDialog("Nombre del paciente:");
            apellido = JOptionPane.showInputDialog("Apellido del paciente:");
            email = JOptionPane.showInputDialog("Correo electrónico del paciente: ");
            contraseña = JOptionPane.showInputDialog("Contraseña con la que accederá el paciente:");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del paciente:"));
            diagnostico = JOptionPane.showInputDialog("Diagnóstico del paciente:");
            //Se elige el género del paciente
            ingresaGenero = (String) JOptionPane.showInputDialog(null,"Indica el género del paciente:\n (Usa F para Femenino y M para masculino)\n\n", 
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            //Se convierte la opción elegida para crear el objeto de forma correcta
            genero = ingresaGenero.charAt(0);
            
            //Se crea el objeto persona
            paciente paciente = new paciente(diagnostico, medico, id, nombre, apellido, edad, genero, contraseña, email);
            
            //Se guarda el objeto en la lista personas para que sea guardado en el archivo JSON
            getPersonas().add(paciente);
            
            //Se guarda el objeto en la lista pacientes para poder permitir filtrar por pacientes
            pacientes.add(paciente);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el paciente en las listas personas y pacientes.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar el paciente en la lista por el error: " + e.getMessage());}
    }
    
    //buscaMedico: buscará el registro del médico tratante, si no lo encuentra, crea un nuevo médico
    public static medico buscaMedico() throws Exception
    {
        //Se crea el objeto médico para guardar los resultados de la búsqueda
        medico medico = null;
            
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se pide el Id del médico
            int id_medico = Integer.parseInt(JOptionPane.showInputDialog("ID del médico tratante:"));
            //Se crea el objeto metodos_medico para acceder a los métodos de la clase médico
            medico metodos_medico = new medico();
            //Se verifica que el médico exista
            boolean existe_medico = metodos_medico.getPersonas().stream().anyMatch(x -> x.getId() == id_medico);
            //Si existe, envía un mensaje al usuario de que se encontró el médico y guarda los datos en el 
            if(existe_medico == true)
            {
                //Se indica que el médico fue encontrado
                System.out.println("Médico encontrado en la lista de personas.");
                //Se guarda el objeto encontrado en el id del médico en el objeto medico
                medico = (medico) getPersonas().get(id_medico);
            }
            //Si no existe, llamará al método para crear un nuevo médico
            else
            {System.out.println("Médico no encontrado en la lista de personas.");}
        }
        catch (Exception e)
        {System.out.println("No se pudo referenciar al médico por el error: " + e.getMessage());}
   
        return medico;
    }
    
    //cargaPaciente: leer y cargar el archivo JSON
    public static void cargaPaciente() throws Exception
    {
        
    }
}
