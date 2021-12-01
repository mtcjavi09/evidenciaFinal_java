/*
    PROGRAMA 08: CONSULTORIO | CLASE persona
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
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
    private static List <persona> persona = new ArrayList<>();
    
    //Archivo donde se guardarán todos los datos de la persona
    private static String ARCHIVO = "personas.json";
    
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
    //agregaDatosIniciales: añadirá un dato semilla para acceder a las funciones 
    public void agregaDatosIniciales()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea la persona con los datos semilla
            persona semilla = new persona(1,"Maria","Tchijov",19,'F',"1234","mashjov13@outlook.es");
            //Se agrega el objeto a la lista persona
            persona.add(semilla);
            //Se guarda el usuario semilla en el archivo JSON
            guardaPersona(semilla);
            //Se notifica al usuario en consola que los datos semilla han sido guardados
            System.out.println("Los datos iniciales han sido guardados.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar los datos semilla correctamente.");}
        
    }
    
    //guardaPersona: guarda los datos del usuario en un archivo json
    public static void guardaPersona(persona persona) throws Exception
    {
        //Se crea el String llamado jsonPaciente como variable que guardará el formato JSON.
        String jsonPersona;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el objeto gson que nos ayudará a pasar el objeto paciente a un formato JSON
            Gson gson = new Gson();
            //Se pasa el paciente a un formato JSON
            jsonPersona = gson.toJson(persona);
            
            //Se crea el fileWritter para crear el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            //Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);
            //Se escribe en el archivo JSON
            printWriter.print(jsonPersona);
            //Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();
            
            //Se manda mensaje al usuario para que pueda ver el guardado exitoso del paciente
            System.out.println("El usuario ha sido guardado:\n" + jsonPersona);
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {throw new Exception("No se pudo guardar el usuario en el archivo JSON.");}
    }
    
    //ingresar: validará si efectivamente el usuario ha sido registrado en el sistema
    public static boolean ingresar(int id, String contraseña) throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se identifica si el usuario ingresado está registrado
            boolean existe = persona.stream().anyMatch(x -> 
                x.getId() == id && x.getContraseña().equals(contraseña));
            //Se regresa el resultado de la búsqueda
            return existe;
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("No se pudo validar al usuario.");}
    }
}
