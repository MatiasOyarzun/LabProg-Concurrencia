package JuegoDeLaVida;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author MatiasOyarzun - FAI-1950
 */

public class TurnManager implements Runnable{
    
    // Representa segundos
    private static final int SECONDS = 1000;
    // Representa el juego de la vida
    private final LifeGame lifeGame;
    // Representa el factor, para la velocidad de cada turno
    private final double turnTimer;
    // Representa el turno actual
    private int turn;
    
    // Constructor del controlador de turnos
    public TurnManager(LifeGame lifeGame, double turnTimer){
        this.lifeGame = lifeGame;
        this.turnTimer = turnTimer;
        this.turn = 1;
    }
    
    @Override
    public void run(){
        while (true) {
            System.out.println("Current Turn: "+this.turn+"\nWorld: \n\n");
            System.out.println(this.lifeGame.toString());
            
            try {
                Thread.sleep((long) (this.turnTimer*SECONDS));
            } catch (InterruptedException ex) {
                Logger.getLogger(TurnManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.lifeGame.prepareTurn();
            this.lifeGame.passTurn();
            
            this.turn++;
        }
    }
    
}
