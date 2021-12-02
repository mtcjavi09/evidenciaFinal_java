/*
    PROGRAMA 02: CONSULTORIO | CLASE main_consultorio
    AUTORA: Maria Tchijov Cruz
    FECHA: 29 de octubre de 2021.
    Consultorio para citas de pacientes
 */

//Se importan librerías necesarias para el funcionamiento de la clase
import javax.swing.*;


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
            //opciones: guardará los tres tipos de usuarios que pueden acceder al sistema, junto con la opción de salir
            String [] usuarios = {"Persona", "Médico", "Paciente", "Salir"};
            //opcionElegida: le pide el tipo de usuario que desea acceder
            String tipoUsuario;
            //Objetos necesarios para acceder a los métodos de las clases
            paciente paciente = new paciente();
            medico medico = new medico();
            persona persona = new persona();
             
            //Acciones del método main
            
            //Bienvenida al usuario
            System.out.println("****BIENVENID@ AL CONSULTORIO MTC****");
            
            //Se agrega una línea para mejor visibilidad
            System.out.println("");

            //Se agregan datos iniciales para cada clase
            persona.agregaDatosIniciales();
            paciente.agregaDatosIniciales();
            medico.agregaDatosIniciales();
            
            //Se agrega una línea para mejor visibilidad
            System.out.println("");
            
            do
            {
                //Se piden las credenciales del usuario y la contraseña
                id_usuario = Integer.parseInt(JOptionPane.showInputDialog("Por favor, escribe tu id de usuario:"));
                contraseña = JOptionPane.showInputDialog("Por favor, escribe tu contraseña:");                
          
                
                //salir: es la variable que ayudará a finalizar el ciclo del ingreso
                int salir = 0;
                
                tipoUsuario = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                        + "tu tipo de usuario", "Ingreso", JOptionPane.DEFAULT_OPTION, 
                        null, usuarios, usuarios[0]);
                
                //Se manda mensaje en consola con el el tipo de usuario que desea ingresar
                System.out.println("Eres: " + tipoUsuario);

                //Se usa un switch ... case para elegir el caso de acuerdo con la opción del usuario

                switch(tipoUsuario)
                {
                    case "Persona": //Es un usuario privilegiado
                    {credenciales = persona.ingresar(id_usuario, contraseña);}
                    
                    case "Médico": //Es un médico
                    {credenciales = medico.ingresar(id_usuario, contraseña);}
                    
                    case "Paciente": //Es un paciente
                    {credenciales = paciente.ingresar(id_usuario, contraseña);}
                }

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
            menu(tipoUsuario, persona, medico, paciente);
            
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
    
    public static void menu(String tipoUsuario, persona persona, medico medico, paciente paciente)
    {       
        //Se especifica el manejo de excepciones try ... catch
        //Se intenta la ejecución de las siguientes instrucciones
        try
        {
            //Se declaran las variables que se usarán en el método
            //salir: es la variable que ayudará a finalizar el ciclo del menú
            int salir = 0;

            //Se comienza el ciclo while hasta que la opción de salir sea 1
            while(salir == 0)
            {
                //Se usa un switch ... case para elegir el caso de acuerdo con la opción del usuario
                switch(tipoUsuario)
                {
                    case "Persona": //Es un usuario privilegiado
                    {
                        //opciones: guardará las opciones disponibles para elegir entre las funciones
                        String [] opciones = {"Cargar usuarios", "Crear nuevo usuario", "Cargar pacientes", "Crear nuevo paciente", 
                            "Cargar médicos","Crear nuevo médico", "Salir"};
                        //opcionElegida: le pide al usuario la opción de la función a realizar
                        String opcionElegida;
                        
                        //Se le pide al usuario la opción que desea realizar
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                                null, opciones, opciones[0]);

                        //Se manda mensaje en consola con la opción que eligió
                        System.out.println("Has elegido: " + opcionElegida);
                        
                        //Se usa un switch ... case para elegir el caso de acuerdo con la opción del usuario

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

                            case "Crear nuevo usuario": //Se eligió crear un nuevo usuario para ingresar
                            {
                                //Se llama al método de creaPersona de la clase persona
                                persona.creaPersona();
                                //Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                //Se termina el switch
                                break;
                            }
                            
                            case "Cargar pacientes": //Se eligió cargar a los pacientes guardados en el archivo JSON
                            {
                                //Se llama al método de cargarJSON de la clase paciente
                                paciente.cargarJSON();
                                //Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                //Se termina el switch
                                break;
                            }  

                            case "Crear nuevo paciente": //Se eligió crear un nuevo paciente para ingresar
                            {
                                //Se llama al método de creaPersona de la clase paciente
                                paciente.creaPersona();
                                //Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                //Se termina el switch
                                break;
                            }
                            
                            case "Cargar médicos": //Se eligió cargar a los médicos guardados en el archivo JSON
                            {
                                //Se llama al método de cargarJSON de la clase medico
                                medico.cargarJSON();
                                //Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                //Se termina el switch
                                break;
                            }
                            
                            case "Crear nuevo médico": //Se eligió crear un nuevo médico para ingresar
                            {
                                //Se llama al método de creaPersona de la clase medico
                                medico.creaPersona();
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
                    
                    case "Médico": //Es un médico
                    {
                        
                    }
                    
                    case "Paciente": //Es un paciente
                    {
                        
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
