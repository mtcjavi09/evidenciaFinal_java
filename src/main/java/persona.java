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
    private static List <persona> personas = new ArrayList<>();
    
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
    public static List<persona> getPersonas() {return personas;}
    public static void setPersonas(List<persona> personas) {persona.personas = personas;}

       
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
            personas.add(semilla);
            //Se notifica al usuario en consola que los datos semilla han sido guardados
            System.out.println("Los datos iniciales han sido guardados.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar los datos semilla correctamente.");}
    }
    
    //creaPersona: registra a un nuevo usuario para su acceso al sistema
    public void creaPersona()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones            
        try
        {
            //Variables necesarias para guardar los atributos del usuario
            int id, edad;
            String nombre, apellido, ingresaGenero;
            char genero;
            String contraseña, email;
            //Se crea el arreglo con las opciones de género para evitar error por parte del usuario
            String [] generos = {"F","M"};
            
            //Se aumenta un id dependiendo de la cantidad de personas guardadas en la lista personas
            id = personas.size() + 1;
            //Se piden los datos del paciente
            nombre = JOptionPane.showInputDialog("Nombre del usuario:");
            apellido = JOptionPane.showInputDialog("Apellido del usuario:");
            email = JOptionPane.showInputDialog("Correo electrónico del usuario: ");
            contraseña = JOptionPane.showInputDialog("Contraseña con la que accederá el usuario:");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del usuario:"));
            //Se elige el género del paciente
            ingresaGenero = (String) JOptionPane.showInputDialog(null,"Indica el género del usuario:\n (Usa F para Femenino y M para masculino)\n\n", 
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            //Se convierte la opción elegida para crear el objeto de forma correcta
            genero = ingresaGenero.charAt(0);

            //Se crea el objeto persona
            persona persona = new persona(id, nombre, apellido, edad, genero, contraseña, email);
            
            personas.add(persona);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el usuario en la lista personas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar el usuario en las listas por el error: " + e.getMessage());}
    }
    
    //guardaUsuario: guarda los datos del los usuarios en un archivo json
    public static void guardaPersona()
    {
        //Se crea el String llamado jsonPaciente como variable que guardará el formato JSON.
        String jsonUsuario;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el objeto gson que nos ayudará a pasar el objeto paciente a un formato JSON
            Gson gson = new Gson();
                        
            //Se crea el fileWritter para crear el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            //Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            //Se indica al usuario que se guardarán los pacientes
            System.out.println("Los siguientes pacientes serán guardados:");
            //Se crea un bucle for para guardar cada objeto de la lista en el archivo
            for (int x = 0; x < personas.size(); x++)
            {
                //Se pasa el paciente a un formato JSON
                jsonUsuario = gson.toJson(personas.get(x));
                //Se escribe en el archivo JSON
                printWriter.print(jsonUsuario);
                //Se indica qué paciente será guardado
                System.out.println("Usuario guardado: " + jsonUsuario);
            }
            
            //Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();
            
            //Se manda mensaje al usuario para que pueda ver el guardado exitoso del paciente
            System.out.println("Los usuarios han sido guardados correctamente.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron guardar los usuarios en el archivo JSON por el error: " + e.getMessage());}
    }
    
    //cargarPersona: leerá y cargará todos los usuarios que se encuentren en el archivo JSON
    public static void cargarPersona()
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
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
    
    //ingresar: validará si efectivamente el usuario ha sido registrado en el sistema
    public static boolean ingresar(int id, String contraseña) throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se identifica si el usuario ingresado está registrado
            boolean existe = personas.stream().anyMatch(x -> 
                x.getId() == id && x.getContraseña().equals(contraseña));
            //Se regresa el resultado de la búsqueda
            return existe;
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("No se pudo validar al usuario.");}
    }
}
