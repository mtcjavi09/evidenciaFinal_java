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

       
    //Métodos propios de la clase
    
    //agregaDatosIniciales: añadirá un dato semilla para acceder a las funciones 
    public void agregaDatosIniciales()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el archivo con nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
            //Se crea el usuario con los datos semilla
            persona semilla = new persona(1,"Maria","Tchijov",19,'F',"1234","mashjov13@outlook.es");
                
            //Si no existe el archivo, se agrega el usuario semilla para comenzar con el programa    
            if(file.canExecute() == false)
            {personas.add(semilla);}
            
            //Si sí existe, se leen las líneas contenidas en el archivo
            else
            {
                
                //Se crea el lector para el archivo de personas.json
                BufferedReader lector = new BufferedReader(new FileReader(file));
                //Se crea el String builder para pasar el formato JSON a un objeto
                StringBuilder json = new StringBuilder();

                //Se crea una variable para ir recorriendo el archivo
                String cadena;

                //Se crea el ciclo para recorrer el archivo JSON
                while ((cadena = lector.readLine()) != null)
                {
                    //Se guarda la línea
                    json.append(cadena);
                    //Se crea el objeto gson para pasar del formato JSON a un objeto Java
                    Gson gson = new Gson();
                    //Se convierte el objeto
                    persona persona = gson.fromJson(json.toString(), persona.class);
                    personas.add(persona);
                }
                
                //Si la lista tiene algún valor vacío, entonces se agrega el dato semilla para comenzar el programa
                if(personas.isEmpty())
                {personas.add(semilla);}
            }

            System.out.println("Los usuarios iniciales han sido guardados.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar los usuarios semilla correctamente.");}
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
    
    //guardaPersona: guarda los datos de los usuarios en un archivo json
    public void guardaPersona()
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

            
            //Se crea un bucle for para guardar cada objeto de la lista en el archivo
            for (int x = 0; x < personas.size(); x++)
            {
                //Se pasa el paciente a un formato JSON
                jsonUsuario = gson.toJson(personas.get(x));
                //Se escribe en el archivo JSON
                printWriter.print(jsonUsuario);
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

    //ingresar: validará si efectivamente el usuario ha sido registrado en el sistema
    public boolean ingresar(int id, String contraseña) throws Exception
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
    
    //cargarJSON: leerá y cargará todos los usuarios que se encuentren en el archivo JSON
    public void cargarJSON()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el archivo con nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
        
            //Se crea el lector para el archivo de personas.json
            BufferedReader lector = new BufferedReader(new FileReader(file));
            //Se crea el String builder para pasar el formato JSON a un objeto
            StringBuilder json = new StringBuilder();

            //Se crea una variable para ir recorriendo el archivo
            String cadena;

            //Se indicará al usuario que se mostrarán los médicos guardados en el archivo
            System.out.println("Los usuarios encontrados en el archivo son: ");
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
            
            //Se crea el ciclo para recorrer el archivo JSON
            while ((cadena = lector.readLine()) != null)
            {
                //Se guarda la línea
                json.append(cadena);
                //Se crea el objeto gson para pasar del formato JSON a un objeto Java
                Gson gson = new Gson();
                //Se convierte el objeto
                persona persona = gson.fromJson(json.toString(), persona.class);
                //Se muestra al usuario los datos guardados
                System.out.println("ID del usuario: " + persona.getId());
                System.out.println("Nombre del usuario: " + persona.getNombre());
                System.out.println("Apellido del usuario: " + persona.getApellido());
                System.out.println("Edad del usuario: " + persona.getEdad());
                System.out.println("Género del usuario: " + persona.getGenero());
                System.out.println("Correo del usuario: " + persona.getEmail());
                System.out.println("Contraseña del usuario: " + persona.getContraseña());
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
}
