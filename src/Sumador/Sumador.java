package Sumador;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author MatiasOyarzun - FAI-1950
 */

public class Sumador implements Runnable{
    
    private final int inicio;
    private final int fin;
    private final int[] arreglo;
    private final AtomicInteger contador;
    
    public Sumador(int ini, int fin, int[] a, AtomicInteger cont){
        this.inicio = ini;
        this.fin = fin;
        this.arreglo = a;
        this.contador = cont;
    }

    @Override
    public void run() {
        //Variable que mantiene la suma de los elementos del arreglo
        int sum = 0;
        for (int i = this.inicio; i < this.fin; i++) {
            sum += this.arreglo[i];
        }
        //Sumo al contador la suma de los elementos del arreglo
        this.contador.addAndGet(sum);
    }
}
