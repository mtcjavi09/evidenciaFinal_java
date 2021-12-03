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
import java.time.LocalDate;
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
                
                //Si la lista está vacía, entonces se agrega el dato semilla para comenzar el programa
                if(personas.isEmpty())
                {personas.add(semilla);}
            }

            System.out.println("Los usuarios iniciales han sido guardados.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar los usuarios semilla correctamente por el error: " + e.getMessage());}
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
            //Se piden los datos de la persona
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
            
            //Se agrega el objeto persona en la lista personas
            personas.add(persona);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el usuario en la lista personas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar el usuario en la lista por el error: " + e.getMessage());}
    }
    
    //guardaPersona: guarda los datos de los usuarios en un archivo json
    public void guardaPersona()
    {
        //Se crea el String llamado jsonUsuario como variable que guardará el formato JSON.
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
            System.out.println("Los usuarios encontrados en el archivo " + ARCHIVO + " son: ");
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
                persona.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
    
    //despliega: método que ayudará a mostrar los datos de cada usuario
    public void despliega()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se imprimen en pantalla los datos del usuario
            System.out.println("ID del usuario: " + id);
            System.out.println("Nombre del usuario: " + nombre);
            System.out.println("Apellido del usuario: " + apellido);
            System.out.println("Edad del usuario: " + edad);
            System.out.println("Género del usuario: " + genero);
            System.out.println("Correo del usuario: " + email);
            System.out.println("Contraseña del usuario: " + contraseña);
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo mostrar el usuario por el error: " + e.getMessage());}
    }
    
    //consultaUsuarios: mostrará todos los usuarios guardados en la lista personas
    public void consultaUsuarios()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se indica al usuario que se mostrarán todas los usuarios que se han registrado
            System.out.println("Se han registrado los siguientes usuarios: ");
            //Se recorre la lista citas para mostrarle al usuario cada persona que se ha registrado
            for (persona x : personas)
            {
                //Se llama al método despliega de la clase persona
                x.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            //Se indica al usuario que se han terminado de desplegar todas las personas
            System.out.println("Se han terminado de mostrar todos los usuarios registrados.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron desplegar los usuarios por el error: " + e.getMessage());}
    }
    
    //enviaMensaje: enviará mensaje a una persona, un paciente o un médico
    public static void enviaMensaje()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Variables necesarias para el funcionamiento del método
            String email = null, mensaje, fecha;
            //Se pide al usuario el id de la persona a la que desea enviar el mensaje
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario destino:"));
            //Se crea el arreglo con las opciones de usuario para evitar error por parte del usuario
            String [] usuarios = {"Persona","Paciente","Médico"};
            //Se pregunta qué tipo de usuario corresponde al ID
            String usuario = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                        + "el tipo de que corresponde al id", "Buscando id ...", JOptionPane.DEFAULT_OPTION, 
                        null, usuarios, usuarios[0]);
            
            
            //Se usa un switch ... case para elegir el caso de acuerdo con la opción del usuario
            switch(usuario)
            {
                case "Persona":
                {
                    //Se busca al usuario en la lista personas
                    boolean existe = personas.stream().anyMatch(x -> x.getId() == id);
                    //Si no existe el usuario, se lanza una excepción
                    if (existe == false)
                    {throw new Exception("No existe un usuario con dicho ID.");}
                    //Se busca el email en la lista correspondiente
                    email = personas.get(id).getEmail();
                    //Se termina el switch
                    break;
                }
                
                case "Paciente":
                {
                    //Se busca al paciente en la lista pacientes
                    boolean existe = paciente.getPacientes().stream().anyMatch(x -> x.getId() == id);
                    //Si no existe el paciente, se lanza una excepción
                    if (existe == false)
                    {throw new Exception("No existe un paciente con dicho ID.");}
                    //Se busca el email en la lista correspondiente
                    email = paciente.getPacientes().get(id).getEmail();
                    //Se termina el switch
                    break;
                }
                
                case "Médico":
                {
                    //Se busca al médico en la lista medicos
                    boolean existe = medico.getMedicos().stream().anyMatch(x -> x.getId() == id);
                    //Si no existe el paciente, se lanza una excepción
                    if (existe == false)
                    {throw new Exception("No existe un médico con dicho ID.");}
                    //Se busca el email en la lista correspondiente
                    email = medico.getMedicos().get(id).getEmail();
                    //Se termina el switch
                    break;
                }
            }
            
            //Se pide el contendido del mensaje
            mensaje = JOptionPane.showInputDialog("Ingresa el mensaje que deseas enviar: ");
            //Se guarda la fecha de envío del mensaje, pasándola a formato String
            fecha = LocalDate.now().toString();
            
            //Se muestra al usuario los resultados del mensaje
            System.out.println("Se ha enviado el siguiente mensaje:");
            System.out.println("Destinatario: " + email);
            System.out.println("Mensaje: " + mensaje);
            System.out.println("Fecha de envío: " + fecha);
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo enviar el mensaje por el error: " + e.getMessage());}        
    }
}
