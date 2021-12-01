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
    public List<paciente> getPacientes() {return pacientes;}
    public void setPacientes(List<paciente> pacientes) {this.pacientes = pacientes;}
    
    
    //Métodos propios de la clase
    
    //creaPaciente: pide los datos necesarios del paciente los guarda en el archivo JSON
    public static void creaPaciente(String ARCHIVO) throws Exception
    {
        //Variables necesarias para el correcto funcionamiento del método
        String nombre_paciente, ingresaGenero, apellido_paciente, contraseña_paciente, email_paciente, diagnostico;
        int id_paciente = 0, edad_paciente, id_medico;
        char genero_paciente;
        
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
            id_paciente = persona.getPersona().size() + 1;
            //Se piden los datos del paciente
            nombre_paciente = JOptionPane.showInputDialog("Nombre del paciente:");
            apellido_paciente = JOptionPane.showInputDialog("Apellido del paciente:");
            email_paciente = JOptionPane.showInputDialog("Correo electrónico del paciente: ");
            contraseña_paciente = JOptionPane.showInputDialog("Contraseña con la que accederá el paciente:");
            edad_paciente = Integer.parseInt(JOptionPane.showInputDialog("Edad del paciente:"));
            diagnostico = JOptionPane.showInputDialog("Diagnóstico del paciente:");
            //Se elige el género del paciente
            ingresaGenero = (String) JOptionPane.showInputDialog(null,"Indica el género del paciente:\n (Usa F para Femenino y M para masculino)", 
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            //Se convierte la opción elegida para crear el objeto de forma correcta
            genero_paciente = ingresaGenero.charAt(0);

            //Se pide el Id del médico
            id_medico = Integer.parseInt(JOptionPane.showInputDialog("ID del médico tratante:"));
            //Se crea el objeto metodos_medico para acceder a los métodos de la clase médico
            medico metodos_medico = new medico();
            //Se verifica que el médico exista
            //Si existe, envía un mensaje al usuario de que se encontró el médico
            if(metodos_medico.getMedicos().contains(id_medico))
            {System.out.println("Médico encontrado en la lista de médicos.");}
            //Si no existe, llamará al método para crear un nuevo médico
            else
            {metodos_medico.creaMedico(ARCHIVO);}
            //Se guardan los datos obtenidos en el objeto médico
            String especialidad = metodos_medico.getMedicos().get(id_medico).getEspecialidad();
            int num_medico = metodos_medico.getMedicos().get(id_medico).getId();
            String nombre_medico = metodos_medico.getMedicos().get(id_medico).getNombre();
            String apellido_medico = metodos_medico.getMedicos().get(id_medico).getApellido();
            String email_medico = metodos_medico.getMedicos().get(id_medico).getEmail();
            String contraseña_medico = metodos_medico.getMedicos().get(id_medico).getContraseña();
            int edad_medico = metodos_medico.getMedicos().get(id_medico).getEdad();
            char genero_medico = metodos_medico.getMedicos().get(id_medico).getGenero(); 
            //Se crea el objeto médico para completar el objeto paciente
            medico medico = new medico(especialidad, num_medico, nombre_medico, apellido_medico, edad_medico, genero_medico, contraseña_medico, email_medico);

            //Se crea el objeto paciente
            paciente paciente = new paciente(diagnostico, medico, id_paciente, nombre_paciente, apellido_paciente, edad_paciente, genero_paciente, contraseña_paciente, email_paciente);

            //Se guarda el paciente en la lista persona
            persona.getPersona().add(paciente);

            //Se guarda el paciente en la lista paciente
            pacientes.add(paciente);

            //Se guardan los objetos del paciente en el archivo json
            guardaPaciente(paciente, ARCHIVO);    

            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el paciente en las listas persona y paciente");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("No se pudo guardar el paciente en las listas.");}
    }
    
    //guardaPaciente: guarda los datos del paciente en un archivo json
    public static void guardaPaciente(paciente paciente, String ARCHIVO) throws Exception
    {
        //Se crea el String llamado jsonPaciente como variable que guardará el formato JSON.
        String jsonPaciente;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el objeto gson que nos ayudará a pasar el objeto paciente a un formato JSON
            Gson gson = new Gson();
            //Se pasa el paciente a un formato JSON
            jsonPaciente = gson.toJson(paciente);
            
            //Se crea el fileWritter para crear el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            //Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);
            //Se escribe en el archivo JSON
            printWriter.print(jsonPaciente);
            //Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();
            
            //Se manda mensaje al usuario para que pueda ver el guardado exitoso del paciente
            System.out.println("El paciente ha sido guardado:\n" + jsonPaciente);
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {throw new Exception("No se pudo guardar el paciente en el archivo JSON.");}
    }
    
    //Método cargaPaciente para leer y cargar el archivo 
    public static void cargaPaciente(String ARCHIVO) throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            File file = new File(ARCHIVO);
        
            BufferedReader lector = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();

            String cadena;

            while ((cadena = lector.readLine()) != null)
            {
                System.out.println(cadena);
                json.append(cadena);
            }

            Gson gson = new Gson();
            paciente paciente = gson.fromJson(json.toString(), paciente.class);

            System.out.println("Paciente cargado.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos.");}
    }
}
