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
            //id_usuario: guardará el id del usuario para acceder al menú
            int id_usuario;
            //contraseña: guardará la contraseña del usuario para acceder al menú
            String contraseña;            
            //intentos: le dará al usuario tres intentos para ingresar al menú
            int intentos = 3;
            //credenciales: ayudará a terminar el ciclo cuando las credenciales hayan sido válidas
            boolean credenciales = false;
            
            //Acciones del método main
            
            //Bienvenida al usuario
            System.out.println("****BIENVENID@ AL CONSULTORIO MTC****");
            
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
            
            //Se crea el objeto persona para ingresar los datos semilla
            persona persona = new persona();
            //Se llama al método para agregar al usuario inicial
            persona.agregaDatosIniciales();
            
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
            
            do
            {
                //Se piden las credenciales del usuario y la contraseña
                id_usuario = Integer.parseInt(JOptionPane.showInputDialog("Por favor, escribe tu id de usuario:"));
                contraseña = JOptionPane.showInputDialog("Por favor, escribe tu contraseña:");                
                
                //Se llama al método valida credenciales
                credenciales = persona.ingresar(id_usuario,contraseña);
                
                //Si es incorrecto, se restará un intento y se le notificará al usuario que no se ha encontrado
                if (credenciales == false)
                {
                    //Primero se restará el intento
                    intentos -= 1;
                    //Luego se le notifica al usuario del error
                     JOptionPane.showMessageDialog(null, "Ingresa nuevamente tu id y contraseña", 
                             "Registro no encontrado", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
            //Si no se acabó los intentos y llenó el campo, se finaliza el ciclo
            while(intentos != 0 && credenciales != true); 
            
            //Si el usuario se acabó sus intentos disponibles, lanza una excepción
            if (intentos == 0)
            {throw new Exception("Se han terminado los intentos disponibles");}
            
            //Muestra al usuario el menú de opciones
            menu();
            
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
                        
            //Despedida al usuario por correcta ejecución del programa
            System.out.println("El programa se ha terminado con éxito. Nos vemos pronto :)\n");
        }
        //Capta cualquier excepción que surga durante la ejecución
        catch (Exception e)
        {
            //Muestra al usuario que ha ocurrido un error y que se terminará el programa
            JOptionPane.showMessageDialog(null, "Ha ocurrido el error: " + e.getMessage() + "," + "\nFinalizando programa..."
                    , "El programa será finalizado", JOptionPane.ERROR_MESSAGE);
            //Se muestra en consola el error que causó la finalización del programa
            System.out.println("Error: " + e.getMessage());
            //Se muestra en consola que el programa ha concluido
            System.out.println("\nPrograma finalizado. Ejecuta nuevamente el programa "
                    + "y vuelve a intentarlo.\n");
            
        }
    }
    
    public static void menu()
    {       
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se declaran las variables que se usarán en el método
            //opciones: guardará las opciones disponibles para elegir entre las funciones
            String [] opciones = {"Cargar usuarios", "Crear nuevo usuario", "Crear nuevo paciente", "Salir"};
            //opcionElegida: le pide al usuario la opción de la función a realizar
            String opcionElegida;
            //salir: es la variable que ayudará a finalizar el ciclo del menú
            int salir = 0;
            //Objetos necesarios para acceder a los métodos de las clases
            paciente paciente = new paciente();
            medico medico = new medico();
            persona persona = new persona();
            
            //Se comienza el ciclo while hasta que la opción de salir sea 1
            while(salir == 0)
            {
                //Se le pide al usuario la opción que desea realizar
                opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                        + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                        null, opciones, opciones[0]);

                //Se manda mensaje en consola con la opción que eligió
                System.out.println("Has elegido: " + opcionElegida);

                switch(opcionElegida)
                {
                    case "Cargar usuarios": //Se eligió cargar a los usuarios guardados en el archivo JSON
                    {
                        //Se llama al método de cargarJSON de la clase persona
                        persona.cargarJSON();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");
                        //Se termina el switch
                        break;
                    }                        
                    
                    case "Crear nuevo usuario":
                    {
                        //Se llama al método de creaPersona de la clase persona
                        persona.creaPersona();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");
                        //Se termina el switch
                        break;
                    }
                    
                    case "Crear nuevo paciente":
                    {
                        //Se llama al método de creaPersona de la clase paciente
                        paciente.creaPersona();
                        //Se agrega una línea para mejor visibilidad
                        System.out.println("");
                        //Se termina el switch
                        break;
                    }
                    
                    case "Salir": //Se eligió salir del menú
                    {
                        //Se cambia el valor de salir para que finalice el ciclo
                        salir = 1;
                        //Se termina el switch
                        break;
                    }
                }
            }
            
            //Se indica que se va a salir del menú
            System.out.println("Saliendo del menú...");
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
            //Se guardan los objetos de todos los usuarios en el archivo JSON
            persona.guardaPersona();
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
            //Se muestran todos los usuarios guardados en el archivo JSON
            persona.cargarJSON();
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
        }
        
        catch(Exception e)
        {e.getMessage();}
    }
}
