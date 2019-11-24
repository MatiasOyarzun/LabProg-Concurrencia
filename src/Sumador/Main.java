package Sumador;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author MatiasOyarzun - FAI-1950
 */

public class Main {
    
    public static final int TAMARREGLO = 50000;
    public static Random RANDOM = new Random();
    public static int[] ARREGLO = new int[TAMARREGLO];
    public static AtomicInteger CONT = new AtomicInteger(0);
    public static Scanner sc = new Scanner(System.in);
    
    //Carga arreglo con valores random entre 1 y 10
    public static void cargarArregloRandom(){
        for (int i = 0; i < TAMARREGLO; i++) {
            ARREGLO[i] = RANDOM.nextInt(10)+1;
        }
    }
    
    public static int sumaSecuencial(){
        int suma = 0;
        for (int i = 0; i < TAMARREGLO; i++) {
            suma += ARREGLO[i];
        }
        return suma;
    }
    
    //Creacion de tareas que son enviadas al executor
    public static void creacionTareas(int cantASumar, int resto, int cantTareas, ExecutorService executor){
        int inicio = 0;
        int fin = cantASumar;
        for (int j = 0; j < cantTareas; j++) {
            //Agrego resto a la ultima tarea
            if((resto != 0) && (j == (cantTareas-1)) ){
                fin += resto;
            }
            Sumador hiloSumador = new Sumador(inicio, fin, ARREGLO, CONT);
            //Agrega tarea al executor
            executor.execute(hiloSumador);
            inicio = fin;
            fin += cantASumar;
        }
    }
    
    //Metodo para verificar si el resultado de los hilos es correcto
    public static void verificarResultado(){
        boolean verificar = true;
        int opcion;
        System.out.println("\n¿Desea verificar el resultado de los hilos, realizando una suma secuencial?");
        while (verificar) {
            System.out.println("1. Si"
                    + "\n2. No");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    verificar = false;
                    System.out.println("El resultado de la suma secuencial del arreglo es: "+sumaSecuencial());
                    break;
                case 2:
                    verificar = false;
                    break;
                default:
                    System.out.println("No es una opcion valida, ingrese otra: ");
                    break;
            }
        }
    } 
    
    public static void main(String[] args) {

        //Cargo arreglo con valores random entre 1 y 10
        cargarArregloRandom();
        
        System.out.println("¿Cuantos hilos desea utilizar?");
        int cantHilos = sc.nextInt();
        
        System.out.println("¿Cuantas tareas desea tener?");
        int cantTareas = sc.nextInt();
        
        //Indico la cantidad que debe sumar cada hilo
        int cantASumar = TAMARREGLO/cantTareas;
        //Resto de dividir el tamaño del arreglo por la cantidad de tareas, este resto se le agregara a la ultima tarea
        int resto = TAMARREGLO%cantTareas;
        
        //Creo un ExecutorService con una cantidad de hilos predefinidas
        ExecutorService executor = Executors.newFixedThreadPool(cantHilos);
        
        //Creo hilos sumadores
        creacionTareas(cantASumar, resto, cantTareas, executor);
        
        //Inicio de cierre, que permite que las tareas ya enviadas se ejecuten, pero no permite que se agregen nuevas tareas
        executor.shutdown();
        
        try {
            //Hilo del main espera a que todas las tareas se completen
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("El resultado de la suma realizada por los hilos es: "+CONT.get());
        
        //Permite verificar resultado
        verificarResultado();
        
    }
    
}
