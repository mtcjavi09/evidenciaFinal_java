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
    
    //Lista donde se guardarán todos los médicos
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
    public List<medico> getMedicos() {return medicos;}
    public void setMedicos(List<medico> medicos) {this.medicos = medicos;}
    
    
    //Métodos propios de la clase
    //creaMedico: pide los datos necesarios del médico y los guarda en el archivo JSON
    public static void creaMedico(String ARCHIVO)
    {
        //Variables necesarias para el correcto funcionamiento del método
        String nombre_medico, ingresaGenero, apellido_medico, contraseña_medico, email_medico, especialidad;
        int id_medico = 0, edad_medico;
        char genero_medico;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el arreglo con las opciones de género para evitar error por parte del usuario
            String [] generos = {"F","M"};
            //Se establece un id basado en el último id de registro encontrado en usuarios.
            //Se crea el objeto persona para obtener el último id guardado
            persona persona = new persona();
            //Se aumenta un id dependiendo de la cantidad de personas guardadas en la lista persona
            id_medico = persona.getPersona().size() + 1;
            //Se piden los datos del paciente
            nombre_medico = JOptionPane.showInputDialog("Nombre del médico:");
            apellido_medico = JOptionPane.showInputDialog("Apellido del médico:");
            email_medico = JOptionPane.showInputDialog("Correo electrónico del médico: ");
            contraseña_medico = JOptionPane.showInputDialog("Contraseña con la que accederá el médico:");
            edad_medico = Integer.parseInt(JOptionPane.showInputDialog("Edad del médico:"));
            especialidad = JOptionPane.showInputDialog("Especialidad del médico:");
            //Se elige el género del paciente
            ingresaGenero = (String) JOptionPane.showInputDialog(null,"Indica el género del médico:\n (Usa F para Femenino y M para masculino)\n", 
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            //Se convierte la opción elegida para crear el objeto de forma correcta
            genero_medico = ingresaGenero.charAt(0);

            //Se crea el objeto medico
            medico medico = new medico(especialidad, id_medico, nombre_medico, apellido_medico, edad_medico, genero_medico, contraseña_medico, email_medico);

            //Se guarda el médico en la lista persona
            persona.getPersona().add(medico);

            //Se guarda el médico en la lista medicos
            medicos.add(medico);

            //Se guardan los objetos del paciente en el archivo json
            guardaMedico(medico, ARCHIVO);    

            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el médico en las listas persona y medicos");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar el médico en las listas.");}
    }
    
    //guardaPaciente: guarda los datos del paciente en un archivo json
    public static void guardaMedico(medico medico, String ARCHIVO)
    {
        //Se crea el String llamado jsonPaciente como variable que guardará el formato JSON.
        String jsonMedico;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el objeto gson que nos ayudará a pasar el objeto paciente a un formato JSON
            Gson gson = new Gson();
            //Se pasa el paciente a un formato JSON
            jsonMedico = gson.toJson(medico);
            
            //Se crea el fileWritter para crear el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            //Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);
            //Se escribe en el archivo JSON
            printWriter.print(jsonMedico);
            //Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();
            
            //Se manda mensaje al usuario para que pueda ver el guardado exitoso del paciente
            System.out.println("El paciente ha sido guardado:\n" + jsonMedico);
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo guardar el médico en el archivo JSON.");}
    }
}
