/*
    PROGRAMA 08: CONSULTORIO | CLASE receta
    AUTORA: Maria Tchijov Cruz
    FECHA: 01 de noviembre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class receta 
{
    //Atributos de la receta
    private int id;
    private String fecha;
    private paciente paciente;
    private medico medico;
    private String medicamento;
    private String presentacion;
    private String dosis;
    private boolean sello;
    private boolean firma;
    
    //Lista donde se guardarán todos las recetas
    private static List <receta> recetas = new ArrayList<>();
    
    //Archivo donde se guardarán todas las citas
    private static String ARCHIVO = "recetas.json";
    
    //Constructor

    public receta(int id, String fecha, paciente paciente, medico medico, String medicamento, String presentacion, String dosis, boolean sello, boolean firma) 
    {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
        this.medicamento = medicamento;
        this.presentacion = presentacion;
        this.dosis = dosis;
        this.sello = sello;
        this.firma = firma;
    }
    
    
    //Constructor vacío para su uso en algunos métodos de la clase main
    public receta() {}
    
    //Métodos Get y Set para atributos privados
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}
    public paciente getPaciente() {return paciente;}
    public void setPaciente(paciente paciente) {this.paciente = paciente;}
    public medico getMedico() {return medico;}
    public void setMedico(medico medico) {this.medico = medico;}
    public String getMedicamento() {return medicamento;}
    public void setMedicamento(String medicamento) {this.medicamento = medicamento;}
    public String getPresentacion() {return presentacion;}
    public void setPresentacion(String presentacion) {this.presentacion = presentacion;}
    public String getDosis() {return dosis;}
    public void setDosis(String dosis) {this.dosis = dosis;}
    public static List<receta> getRecetas() {return recetas;}
    public static void setRecetas(List<receta> recetas) {receta.recetas = recetas;}
    public boolean isSello() {return sello;}
    public void setSello(boolean sello) {this.sello = sello;}
    public boolean isFirma() {return firma;}
    public void setFirma(boolean firma) {this.firma = firma;}    
    
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
            //Se crea el médico con los datos semilla para crear la receta semilla
            medico medico = new medico("Traumatología",1,"Ariana","Horan",32,'F',"1234","arianah@outlook.es");
            //Se crea el paciente con los datos semilla para crear la receta semilla
            paciente paciente = new paciente("Gripa",medico,1,"Nam","Joon",30,'M',"1234","namj@outlook.es");
            //Se crea la receta con los datos semilla
            receta semilla = new receta(1,LocalDate.now().toString(),paciente,medico,"Next","Tabletas","1 por día", true, true);
                
            //Si no existe el archivo, se agrega la receta semilla para comenzar con el programa    
            if(file.canExecute() == false)
            {recetas.add(semilla);}
            
            //Si sí existe, se leen las líneas contenidas en el archivo
            else
            {
                
                //Se crea el lector para el archivo de recetas.json
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
                    receta receta = gson.fromJson(json.toString(), receta.class);
                    recetas.add(receta);
                }
                
                //Si la lista está vacía, entonces se agrega el dato semilla para comenzar el programa
                if(recetas.isEmpty())
                {recetas.add(semilla);}
            }

            System.out.println("Las recetas iniciales han sido guardadas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudieron guardar las recetas semilla correctamente por el error: " + e.getMessage());}
    }
    
    //creaReceta: guarda una nueva receta
    public void creaReceta()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones            
        try
        {
            //Se aumenta un id dependiendo de la cantidad de citas guardadas en la lista recetas
            int id = recetas.size() + 1;
            //Se guarda la fecha actual para la receta
            String fecha = LocalDate.now().toString();
            //Se crea el objeto paciente llamando al método busca paciente
            paciente paciente = cita.buscaPaciente();
            if (medico == null)
            {throw new Exception("No existe ningún paciente con tal ID");}
            //Se crea el objeto medico llamando al método busca medico
            medico medico = cita.buscaMedico();
            //Si está nulo, manda una excepción
            if (medico == null)
            {throw new Exception("No existe ningún médico con tal ID");}
            //Se guardan los datos del medicamento
            String medicamento = JOptionPane.showInputDialog("Ingresa el nombre del medicamento: ");
            String presentacion = JOptionPane.showInputDialog("Ingresa la presentación del medicamento: ");
            String dosis = JOptionPane.showInputDialog("Ingresa la dosis del medicamento: ");
            //Se guardan los valores false para el sello y firma de la receta
            boolean sello = false;
            boolean firma = false;
            
            //Se crea el objeto receta
            receta receta = new receta(id,fecha,paciente,medico,medicamento,presentacion,dosis,sello,firma);
            
            //Se agrega el objeto receta en la lista recetas
            recetas.add(receta);
            
            //Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente la receta en la lista recetas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch(Exception e)
        {System.out.println("No se pudo guardar la receta en la lista por el error: " + e.getMessage());}
    }
    
    //cargarJSON: leerá y cargará todas las recetas que se encuentren en el archivo JSON
    public void cargarJSON()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se crea el archivo con nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
        
            //Se crea el lector para el archivo de recetas.json
            BufferedReader lector = new BufferedReader(new FileReader(file));
            //Se crea el String builder para pasar el formato JSON a un objeto
            StringBuilder json = new StringBuilder();

            //Se crea una variable para ir recorriendo el archivo
            String cadena;

            //Se indicará al usuario que se mostrarán las recetas guardadas en el archivo
            System.out.println("Las recetas encontradas en el archivo " + ARCHIVO + " son: ");
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
                receta receta = gson.fromJson(json.toString(), receta.class);
                //Se muestra al usuario los datos guardados
                receta.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
    
    //despliega: método que ayudará a mostrar los datos de cada receta
    public void despliega()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            System.out.println("ID de la receta: " + id);
            System.out.println("Fecha de la receta: " + fecha);
            System.out.println("**** Paciente de la receta ****");
            paciente.despliega();
            System.out.println("**** Medico tratante ****");
            medico.despliega();
            System.out.println("Mecicamento: " + medicamento);
            System.out.println("Presentación del medicamento: " + presentacion);
            System.out.println("Dosis del medicamento: " + dosis);
            System.out.println("Sello: " + sello);
            System.out.println("Firma: " + sello);
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo mostrar la receta por el error: " + e.getMessage());}
    }
    
    //consultaRecetas: permite mostrar todos los datos guardados en la lista 
    public static void consultaCitas()
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se indica al usuario que se mostrarán todas las recetas que se han registrado
            System.out.println("Se han registrado las siguientes recetas: ");
            //Se recorre la lista recetas para mostrarle al usuario cada receta que se ha registrado
            for (receta x : recetas)
            {
                //Se llama al método despliega de la clase receta
                x.despliega();
                //Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            //Se indica al usuario que se han terminado de desplegar todas las citas
            System.out.println("Se han terminado de mostrar todas las recetas registradas.");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron desplegar las recetas por el error: " + e.getMessage());}
    }
    
    //buscaReceta: buscará y mostrará las recetas relacionadas con el ID del paciente o del médico
    public static void buscaReceta(int id, String usuario)
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Se agrega un título para las citas obtenidas
            System.out.println("Se encontraron las siguientes recetas para el " + usuario.toLowerCase() + " #" + id);
            //Se usa un switch ... case para elegir el caso de acuerdo con la opción del usuario
            switch(usuario)
            {                
                case "Paciente":
                {
                    //Se buscan las recetas que correspondan al paciente y se guarda en una lista
                    List <receta> recetasPaciente = recetas.stream().filter(paciente -> paciente.getId() == id).collect(Collectors.toList());
                    //Se recorre la lista recetasPaciente para mostrarle al usuario cada receta que tiene
                    for (receta x : recetasPaciente)
                    {
                        //Se llama al método despliega de la clase receta
                        x.despliega();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");
                    }
                    //Se termina el switch
                    break;
                }
                
                case "Médico":
                {
                    //Se buscan las recetas que correspondan al médico y se guarda en una lista
                    List <receta> recetasMedico = recetas.stream().filter(medico -> medico.getId() == id).collect(Collectors.toList());
                    //Se recorre la lista citasPaciente para mostrarle al usuario cada cita que tiene
                    for (receta x : recetasMedico)
                    {
                        //Se llama al método despliega de la clase receta
                        x.despliega();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");                        
                    }
                    //Se termina el switch
                    break;
                }
            }
            
            //Se indica al usuario que se han terminado de desplegar todas las recetas
            System.out.println("Se han terminado de mostrar las recetas del " + usuario.toLowerCase() + 
                    " #" + id + ".");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudieron encontrar las recetas por el error: " + e.getMessage());}
    }
    
    //sellarReceta: regresará un boolean que indica que la receta ha sido sellada
    public static void sellarReceta() throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Pregunta al usuario el número de receta a sellar
            int id =Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id de la receta a sellar:"));
            //Se busca la receta en la lista recetas
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            //Si no existe la receta, se lanza una excepción
            if (existe == false)
            {throw new Exception("No existe una receta con dicho ID.");}
            //Se le indica al usuario que se sellará la receta
            System.out.println("Sellando receta ... ");
            //Se cambia el sello de false a true
            recetas.get(id).setSello(true);
            //Se le indica al usuario que la receta fue sellada
            System.out.println("La receta ha sido sellada: " + recetas.get(id).isSello());
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo sellar la receta por el error: " + e.getMessage());}        
    }
    
    //firmarReceta: regresará un boolean que indica que la receta ha sido firmada
    public static void firmarReceta() throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Pregunta al usuario el número de receta a firmar
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id de la receta a firmar:"));
            //Se busca la receta en la lista recetas
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            //Si no existe la receta, se lanza una excepción
            if (existe == false)
            {throw new Exception("No existe una receta con dicho ID.");}
            //Se le indica al usuario que se firmará la receta
            System.out.println("Firmando receta ... ");
            //Se cambia la firma de false a true
            recetas.get(id).setFirma(true);
            //Se le indica al usuario que la receta fue firmada
            System.out.println("La receta ha sido firmada: " + recetas.get(id).isFirma());
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo firmar la receta por el error: " + e.getMessage());}        
    }
    
    //surtirReceta: regresará el medicamento, su presentación y dosis, además de un boolean indicando que se terminó el proceso
    public static void surtirReceta() throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones 
        try
        {
            //Pregunta al usuario el número de receta a surtir
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id de la receta a firmar:"));
            //Se busca la receta en la lista recetas
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            //Si no existe la receta, se lanza una excepción
            if (existe == false)
            {throw new Exception("No existe una receta con dicho ID.");}
            //Se busca si la receta está sellada y firmada
            boolean cumple = recetas.get(id).isSello() == false && recetas.get(id).isFirma()== false;
            //Si la receta está firmada y sellada, se continúa
            if(cumple)
            {
                //Se indica que se surtirá la receta
                System.out.println("Surtiendo la receta #" + id);
                //Se muestran los datos del medicamento
                System.out.println("Medicamento: " + recetas.get(id).getMedicamento());
                System.out.println("Presentación: " + recetas.get(id).getPresentacion());
                System.out.println("Recuerda tomarlo: " + recetas.get(id).getDosis());
                //Se crea un boolean para indicar que el surtido fue exitoso
                boolean surtido = true;
                //Se indica al usuario el resultado del surtido de la receta
                System.out.println("Receta surtida: " + surtido);
            }
            //Si no, se lanza la excepción
            else
            {throw new Exception("La receta no tiene sello y/o firma.");}
            
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {System.out.println("No se pudo surtir la receta por el error: " + e.getMessage());}
    }
}
