/*
    PROGRAMA 02: CONSULTORIO | CLASE main_consultorio
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main_consultorio 
{
    //Método main
    public static void main (String[] args) throws Exception
    {
        //Se especifica el manejo de excepciones try ... catch
        
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Variables necesarias para el funcionamiento del método
            //nombreUsuario: guardará el nombre del usuario para tener una mayor personalización
            String nombreUsuario;
            //intentos: le dará al usuario tres intentos para no dejar en blanco su nombre
            int intentos = 3;
            //Acciones del método main
            //Bienvenida y guardado del nombre del archivo
            System.out.println("****BIENVENID@ AL CONSULTORIO MTC****");
            //Se especifica una constante del nombre del archivo donde se guardarán todos los datos
            String ARCHIVO = "pacientes.json";
            
            do
            {
                //Se piden las credenciales del usuario y 
                nombreUsuario = JOptionPane.showInputDialog("Bienvenid@ al consultorio de MTC\n"
                    + "Por favor, escribe tu id de usuario:");
                //Si deja en blanco su nombre, se resta un intento y vuelve a intentar
                
            }
            //Si no se acabó los intentos y llenó el campo, se finaliza el ciclo
            while(intentos != 0 && nombreUsuario.equals("")); 
            
            //Si el usuario se acabó sus intentos disponibles, lanza una excepción
            if (intentos == 0)
            {throw new Exception("Nombre no completado");}
            
            //Muestra al usuario el menú de opciones
            menu(nombreUsuario, ARCHIVO);
            
            //Despedida al usuario por correcta ejecución del programa
            System.out.println("El programa se ha terminado con éxito. Nos vemos pronto"
                    + ", " + nombreUsuario + " :)\n");
        }
        
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {
            //Muestra al usuario que ha ocurrido un error y que se terminará el programa
            JOptionPane.showMessageDialog(null, "Ha ocurrido el error: " + e + "," + "\nFinalizando programa..."
                    , "El programa será finalizado", JOptionPane.ERROR_MESSAGE);
            //Se muestra en consola que el programa ha concluido
            System.out.println("\nPrograma finalizado. Ejecuta nuevamente el programa "
                    + "y vuelve a intentarlo.\n");
        }
    }
    
    public static boolean validaCredenciales(int id, String contraseña)
    {
        persona persona = new persona();
        boolean existe = persona.getPersona().stream().anyMatch(x -> 
                x.getId() == id && x.getContraseña().equals(contraseña));
        return existe;
    }
    
    public static void menu(String nombreUsuario, String archivo) throws Exception
    {
        //Se declaran las variables que se usarán en el método
        //opciones: guardará las opciones disponibles para elegir entre las funciones
        String [] opciones = {"Crear paciente", "Cargar Paciente"};
        //opcionElegido = le pide al usuario la opción de la función a realizar
        String opcionElegida;
        
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            opcionElegida = (String) JOptionPane.showInputDialog(null, "¡Hola! " + nombreUsuario + 
                    " por favor, selecciona la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                    null, opciones, opciones[0]);

            switch(opcionElegida)
            {
                case "Crear paciente":
                {
                     System.out.println("Creando el paciente..." + paciente.creaPaciente(archivo));
                }

                case "Cargar Paciente":
                {
                    paciente.cargaPaciente(archivo);
                }
            }
        }
        
        catch(Exception e)
        {
            throw new Exception("Error en el proceso de ejecución del menú");
        }
    }
}
