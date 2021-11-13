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

public class main_consultorio 
{
    //Método main
    public static void main (String[] args) throws Exception
    {
        try
        {
            //Variables necesarias para el funcionamiento del método
            paciente paciente = new paciente();
            //Acciones del método main
            //Bienvenida y guardado del nombre del archivo
            System.out.println("****BIENVENID@ AL CONSULTORIO MTC****");
            String archivo = "pacientes.json";

            System.out.println("Creando el paciente..." + paciente.creaPaciente(archivo));
            paciente.cargaPaciente(archivo);
        }
        catch(Exception e)
        {
            System.out.println("Programa finalizado por una error de ejecución");
        }
    }
    
    public static boolean validaCredenciales(int id, String contraseña)
    {
        persona persona = new persona();
        boolean existe = persona.getPersona().stream().anyMatch(x -> 
                x.getId() == id && x.getContraseña().equals(contraseña));
        return existe;
    }
}
