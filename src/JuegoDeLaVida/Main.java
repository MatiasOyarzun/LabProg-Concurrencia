package JuegoDeLaVida;

import java.util.LinkedList;

/**
 *
 * @author MatiasOyarzun - FAI-1950
 */

public class Main {
    public static void main(String[] args){
        
        // Lista donde se almacenaran los ids de las celulas a crear vivas
        LinkedList<CellID> cells = new LinkedList<>();
        // Seleccion del patron
        int size = Patterns.selectPattern(cells);
        
        // Velocidad de cambio de turno
        double factor = 0.4;
        
        LifeGame lifeGame = new LifeGame(size, cells, factor);
        
        System.out.println("El juego va a comenzar...");
        
        // Comienzo de juego
        lifeGame.startGame();
        
    }
}
