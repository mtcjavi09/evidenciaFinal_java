/*
    PROGRAMA 02: CONSULTORIO | CLASE cita
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class cita 
{
    //Atributos de la cita
    private int id;
    private String nombre;
    private String fecha;
    private String hora;
    private String motivo;
    private medico medico;
    private paciente paciente;
    
    
    //Lista que guardará todas las citas
    private static List <cita> citas = new ArrayList<>();;
    
    //Archivo donde se guardarán todas las citas
    private static String ARCHIVO = "citas.json";

    //Constructor

    public cita(int id, String nombre, String fecha, String hora, String motivo, medico medico, paciente paciente) 
    {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
    }
    

    //Constructor vacío para su uso en algunos métodos de la clase main
    public cita() {}
    
    //Métodos Get y Set para atributos privados
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}
    public String getHora() {return hora;}
    public void setHora(String hora) {this.hora = hora;}
    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {this.motivo = motivo;}
    public medico getMedico() {return medico;}
    public void setMedico(medico medico) {this.medico = medico;}
    public paciente getPaciente() {return paciente;}
    public void setPaciente(paciente paciente) {this.paciente = paciente;}
    public static List<cita> getCitas() {return citas;}
    public static void setCitas(List<cita> citas) {cita.citas = citas;}

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
            //Se crea el médico con los datos semilla para crear la cita semilla
            medico medico = new medico("Traumatología",1,"Ariana","Horan",32,'F',"1234","arianah@outlook.es");
            //Se crea el paciente con los datos semilla para crear la cita semilla
            paciente paciente = new paciente("Gripa",medico,1,"Nam","Joon",30,'M',"1234","namj@outlook.es");
            //Se crea la cita con los datos semilla
            cita semilla = new cita(1,"Cita 1",LocalDate.now().toString(),LocalTime.now().toString(),"Seguimiento", medico, paciente);
                
            //Si no existe el archivo, se agrega la cita semilla para comenzar con el programa    
            if(file.canExecute() == false)
            {citas.add(semilla);}
            
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
                    cita cita = gson.fromJson(json.toString(), cita.class);
                    citas.add(cita);
                }
                
                //Si la lista tiene algún valor vacío, entonces se agrega el dato semilla para comenzar el programa
                if(citas.isEmpty())
                {citas.add(semilla);}
            }

            System.out.println("Las citas iniciales han sido guardadas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar las citas semilla correctamente por el error: " + e.getMessage());}
    }
    
    //creaCita: guarda una nueva cita
    public void creaCita()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones            
        try
        {
            //Variables necesarias para guardar los atributos de la cita
            int id;
            String nombre,fecha, hora, motivo;


            //Se aumenta un id dependiendo de la cantidad de citas guardadas en la lista citas
            id = citas.size() + 1;
            //Se pide el nombre y el motivo de la cita
            nombre = JOptionPane.showInputDialog("Nombre de la cita:");
            motivo = JOptionPane.showInputDialog("Motivo de la cita:");
            //Se guardan la fecha y hora actuales para la cita
            fecha = LocalDate.now().toString();
            hora = LocalTime.now().toString();
            //Se crea el objeto medico llamando al método busca medico
            medico medico = buscaMedico();
            //Se crea el objeto paciente llamando al método busca paciente
            paciente paciente = buscaPaciente();
            
            //Se crea el objeto persona
            cita cita = new cita(id, nombre, fecha, hora, motivo, medico, paciente);
            
            //Se agrega el objeto cita en la lista citas
            citas.add(cita);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente la cita en la lista citas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar la cita en la lista por el error: " + e.getMessage());}
    }
    
    //buscaMedico: buscará el registro del médico tratante y devuelve nulo si no lo encuentra
    public static medico buscaMedico() 
    {
        //Se crea el objeto médico para guardar los resultados de la búsqueda
        medico medico = null;
            
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se pide el id del médico
            int id_medico = Integer.parseInt(JOptionPane.showInputDialog("ID del médico tratante:"));
            //Se crea el objeto metodos_medico para acceder a los métodos de la clase médico
            medico metodos_medico = new medico();
            //Se verifica que el médico exista
            boolean existe_medico = metodos_medico.getMedicos().stream().anyMatch(x -> x.getId() == id_medico);
            //Si existe, envía un mensaje al usuario de que se encontró el médico y guarda los datos en el 
            if(existe_medico == true)
            {
                //Se indica que el médico fue encontrado
                System.out.println("Médico encontrado en la lista de medicos.");
                //Se guarda el objeto encontrado en el id del médico en el objeto medico
                medico = metodos_medico.getMedicos().get(id_medico);
            }
            //Si no existe, llamará al método para crear un nuevo médico
            else
            {System.out.println("Médico no encontrado en la lista de medicos.");}
        }
        catch (Exception e)
        {System.out.println("No se pudo referenciar al médico por el error: " + e.getMessage());}
   
        return medico;
    }
    
    //buscaPaciente: buscará el registro del paciente y devuelve nulo si no lo encuentra
    public static paciente buscaPaciente()
    {
        //Se crea el objeto médico para guardar los resultados de la búsqueda
        paciente paciente = null;
            
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se pide el id del paciente
            int id_paciente = Integer.parseInt(JOptionPane.showInputDialog("ID del paciente:"));
            //Se crea el objeto metodos_medico para acceder a los métodos de la clase médico
            paciente metodos_paciente = new paciente();
            //Se verifica que el médico exista
            boolean existe_paciente = metodos_paciente.getPacientes().stream().anyMatch(x -> x.getId() == id_paciente);
            //Si existe, envía un mensaje al usuario de que se encontró el médico y guarda los datos en el 
            if(existe_paciente == true)
            {
                //Se indica que el médico fue encontrado
                System.out.println("Paciente encontrado en la lista de pacientes.");
                //Se guarda el objeto encontrado en el id del médico en el objeto medico
                paciente = metodos_paciente.getPacientes().get(id_paciente);
            }
            //Si no existe, llamará al método para crear un nuevo médico
            else
            {System.out.println("Paciente no encontrado en la lista de pacientes.");}
        }
        catch (Exception e)
        {System.out.println("No se pudo referenciar al paciente por el error: " + e.getMessage());}
   
        return paciente;
    }
    
    //guardaCita: guarda los datos de las citas en un archivo json
    public void guardaCita()
    {
        //Se crea el String llamado jsonCita como variable que guardará el formato JSON.
        String jsonCita;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el objeto gson que nos ayudará a pasar el objeto jsonCita a un formato JSON
            Gson gson = new Gson();
                        
            //Se crea el fileWritter para crear el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            //Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            //Se crea un bucle for para guardar cada objeto de la lista en el archivo
            for (int x = 0; x < citas.size(); x++)
            {
                //Se pasa el médico a un formato JSON
                jsonCita = gson.toJson(citas.get(x));
                //Se escribe en el archivo JSON
                printWriter.print(jsonCita);
            }
            
            //Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();
            
            //Se manda mensaje al usuario para que pueda ver el guardado exitoso del paciente
            System.out.println("Las citas han sido guardadas correctamente.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron guardar las citas en el archivo JSON por el error: " + e.getMessage());}
    }
    
    //cargarJSON: leerá y cargará todas las citas que se encuentren en el archivo JSON
    public void cargarJSON()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el archivo con nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
        
            //Se crea el lector para el archivo de medicos.json
            BufferedReader lector = new BufferedReader(new FileReader(file));
            //Se crea el String builder para pasar el formato JSON a un objeto
            StringBuilder json = new StringBuilder();

            //Se crea una variable para ir recorriendo el archivo
            String cadena;

            //Se indicará al usuario que se mostrarán los médicos guardados en el archivo
            System.out.println("Las citas encontrados en el archivo " + ARCHIVO + " son: ");
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
                cita cita = gson.fromJson(json.toString(), cita.class);
                //Se muestra al usuario los datos guardados
                cita.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
    
    //despliega: método que ayudará a mostrar los datos de cada cita
    public void despliega()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            System.out.println("ID de la cita: " + id);
            System.out.println("Nombre de la cita: " + nombre);
            System.out.println("Motivo de la cita: " + motivo);
            System.out.println("Fecha de la cita: " + fecha);
            System.out.println("Hora de la cita: " + hora);
            System.out.println("**** Medico tratante ****");
            medico.despliega();
            System.out.println("**** Paciente a asistir ****");
            paciente.despliega();
        }
        catch (Exception e)
        {System.out.println("No se pudo mostrar la citas por el error: " + e.getMessage());}
    }
    
    //consultaCitas: permite mostrar todos los datos guardados en la lista 
    public static void consultaCitas()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se indica al usuario que se mostrarán todas las citas que se han registrado
            System.out.println("Se han registrado las siguientes citas: ");
            //Se recorre la lista citas para mostrarle al usuario cada cita que se ha registrado
            for (cita x : citas)
            {
                //Se llama al método despliega de la clase cita
                x.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            //Se indica al usuario que se han terminado de desplegar todas las citas
            System.out.println("Se han terminado de mostrar todas las citas registradas.");
        }
        catch (Exception e)
        {System.out.println("No se pudieron desplegar las citas por el error: " + e.getMessage());}
    }
    
    //buscaCita: buscará y mostrará las citas relacionadas con el ID del paciente o del médico
    public static void buscaCita(int id, String usuario)
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Variables necesarias para el funcionamiento del método
            String email = null, mensaje, fecha;
            
            //Se agrega un título para las citas obtenidas
            System.out.println("Se encontraron las siguientes citas para el " + usuario.toLowerCase() + " #" + id);
            //Se usa un switch ... case para elegir el caso de acuerdo con la opción del usuario
            switch(usuario)
            {                
                case "Paciente":
                {
                    //Se buscan las citas que correspondan al paciente y se guarda en una lista
                    List <cita> citasPaciente = citas.stream().filter(paciente -> paciente.getId() == id).collect(Collectors.toList());
                    //Se recorre la lista citasPaciente para mostrarle al usuario cada cita que tiene
                    for (cita x : citasPaciente)
                    {
                        //Se llama al método despliega de la clase cita
                        x.despliega();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");
                    }
                    //Se termina el switch
                    break;
                }
                
                case "Médico":
                {
                    //Se buscan las citas que correspondan al médico
                    List <cita> citasMedico = citas.stream().filter(medico -> medico.getId() == id).collect(Collectors.toList());
                    //Se recorre la lista citasPaciente para mostrarle al usuario cada cita que tiene
                    for (cita x : citasMedico)
                    {
                        //Se llama al método despliega de la clase cita
                        x.despliega();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");                        
                    }
                    //Se termina el switch
                    break;
                }
            }
            
            //Se indica al usuario que se han terminado de desplegar todas las citas
            System.out.println("Se han terminado de mostrar las citas del " + usuario.toLowerCase() + 
                    " #" + id + ".");
        }
        catch (Exception e)
        {System.out.println("No se pudieron encontrar las citas por el error: " + e.getMessage());}
    }
}
