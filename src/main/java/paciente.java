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
    
    //Archivo donde se guardarán todos los datos de los medicos
    private static String ARCHIVO = "pacientes.json";
    
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
    public static List<paciente> getPacientes() {return pacientes;}
    public static void setPacientes(List<paciente> pacientes) {paciente.pacientes = pacientes;}
    
    
    
    //Métodos propios de la clase
    
    @Override
    //agregaDatosIniciales: añadirá un dato semilla para acceder a las funciones 
    public void agregaDatosIniciales()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el archivo con nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
            //Se crea el médico con los datos semilla para crear el paciente semilla
            medico medico = new medico("Traumatología",1,"Ariana","Horan",32,'F',"1234","arianah@outlook.es");
            //Se crea el paciente con los datos semilla
            paciente semilla = new paciente("Gripa",medico,1,"Nam","Joon",30,'M',"1234","namj@outlook.es");
            
            //Si no existe el archivo, se agrega el paciente semilla para comenzar con el programa    
            if(file.canExecute() == false)
            {pacientes.add(semilla);}
            
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
                    paciente paciente = gson.fromJson(json.toString(), paciente.class);
                    pacientes.add(paciente);
                }
                
                //Si la lista tiene algún valor vacío, entonces se agrega el dato semilla para comenzar el programa
                if(pacientes.isEmpty())
                {pacientes.add(semilla);}
            }

            System.out.println("Los pacientes iniciales han sido guardados.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar los pacientes semilla correctamente por el error: " + e.getMessage());}
    }
    
    @Override
    //creaPersona: registra a un nuevo paciente para su acceso al sistema
    public void creaPersona()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones            
        try
        {
            //Se llama al método buscaMedico para conocer si el médico está registrado o no y se guarda en la variable medico
            medico medico = cita.buscaMedico();
            
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
            id = pacientes.size() + 1;
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
            
            //Se agrega el objeto paciente en la lista pacientes
            pacientes.add(paciente);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el paciente en la lista pacientes.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar el paciente en la lista por el error: " + e.getMessage());}
    }
    
    @Override
    //guardaPersona: guarda los datos de los médicos en un archivo json
    public void guardaPersona()
    {
        //Se crea el String llamado jsonPaciente como variable que guardará el formato JSON.
        String jsonPaciente;
        
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
            for (int x = 0; x < pacientes.size(); x++)
            {
                //Se pasa el paciente a un formato JSON
                jsonPaciente = gson.toJson(pacientes.get(x));
                //Se escribe en el archivo JSON
                printWriter.print(jsonPaciente);
                //Se indica qué paciente será guardado
                System.out.println("Paciente guardado: " + jsonPaciente);
            }
            
            //Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();
            
            //Se manda mensaje al usuario para que pueda ver el guardado exitoso del paciente
            System.out.println("Los pacientes han sido guardados correctamente.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron guardar los pacientes en el archivo JSON por el error: " + e.getMessage());}
    }
    
    @Override
    //ingresar: validará si efectivamente el paciente ha sido registrado en el sistema
    public boolean ingresar(int id, String contraseña) throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se identifica si el paciente ingresado está registrado
            boolean existe = pacientes.stream().anyMatch(x -> 
                x.getId() == id && x.getContraseña().equals(contraseña));
            //Se regresa el resultado de la búsqueda
            return existe;
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {throw new Exception("No se pudo validar al médico.");}
    }
    
    
    @Override
    //cargarJSON: leerá y cargará todos los pacientes que se encuentren en el archivo JSON
    public void cargarJSON()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el archivo con nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
        
            //Se crea el lector para el archivo de pacientes.json
            BufferedReader lector = new BufferedReader(new FileReader(file));
            //Se crea el String builder para pasar el formato JSON a un objeto
            StringBuilder json = new StringBuilder();

            //Se crea una variable para ir recorriendo el archivo
            String cadena;

            //Se indicará al usuario que se mostrarán los pacientes guardados en el archivo
            System.out.println("Los pacientes encontrados en el archivo " + ARCHIVO + " son: ");
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
                paciente paciente = gson.fromJson(json.toString(), paciente.class);
                //Se muestra al usuario los datos guardados
                paciente.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
    
    @Override
    //despliega: método que ayudará a mostrar los datos de cada paciente
    public void despliega()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se imprimen en pantalla los datos del paciente
            System.out.println("ID del paciente: " + getId());
            System.out.println("Nombre del paciente: " + getNombre());
            System.out.println("Apellido del paciente: " + getApellido());
            System.out.println("Edad del paciente: " + getEdad());
            System.out.println("Género del paciente: " + getGenero());
            System.out.println("Correo del paciente: " + getEmail());
            System.out.println("Contraseña del paciente: " + getContraseña());
            System.out.println("Diagnóstico del paciente: " + diagnostico);
            System.out.println("**** Medico tratante ****");
            medico.despliega();
        }
        catch (Exception e)
        {System.out.println("No se pudo mostrar el paciente por el error: " + e.getMessage());}
    }
    
    //asistirCita: llamará al método consultaPaciente para cambiar el diagnóstico
    public static void asistirCita(int id)
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se guardan los datos del paciente en un objeto de tipo paciente
            paciente paciente = pacientes.get(id);
            //Se crea el objeto medico para acceder a todos los métodos
            medico medico = new medico();
            //Se llama al método consultaPaciente para el cambio del diagnóstico
            medico.consultaPaciente(paciente);
            //Se le indica al usuario que la consulta fue concluida
            System.out.println("Se ha terminado la consulta con éxito.");
        }
        catch (Exception e)
        {System.out.println("No se pudo asistir a la cita por el error: " + e.getMessage());}
    }
}
