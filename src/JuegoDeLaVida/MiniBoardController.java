package JuegoDeLaVida;

/**
 *
 * @author MatiasOyarzun - FAI-1950
 */

public class MiniBoardController implements Runnable {
    
    // Representa tamaño del tablero
    private static int SIZEMINIBOARD = 3;
    // Representan donde comienza su dominio dentro de la matriz completa
    private int xStart;
    private int yStart;
    // Representan donde finaliza su dominio dentro de la matriz completa
    private int xEnd;
    private int yEnd;
    // Representa matriz completa de celulas del juego
    private Cell[][] board;
    // Representa el juego
    private LifeGame game;
    
    // Constructor del tablero controlador
    public MiniBoardController(int x, int y, Cell[][] board, LifeGame game){
        this.xStart = x;
        this.yStart = y;
        this.xEnd = x + SIZEMINIBOARD;
        this.yEnd = y + SIZEMINIBOARD;
        this.board = board;
        this.game = game;
    }
    
    /** Permite verificar en una celula segun su posicion, cada celula vecina para verificar
        la cantidad de celulas vivas vecinas **/
    public int searchNeighborsCells(int posX, int posY){
        int x, y, cellCounter = 0;
        int tam = (this.board.length-1);
        
        for (Direction direction: Direction.values()){
            
            x = posX + direction.dirX;
            y = posY + direction.dirY;
            if (x == -1) {
                x = tam;
            }
            if (x == (tam+1)) {
                x = 0;
            }
            if (y == -1) {
                y = tam;
            }
            if (y == (tam+1)) {
                y = 0;
            }
            if (this.board[x][y].isAlive()) {
                cellCounter++;
            }
        }
        
        return cellCounter;
    }
    
    // Metodo de ejecucion de turno, invocado por el run de este mismo
    private void turnExecution(){
        prepareCellsToEvolve();
        evolveCells();
    }
    
    // Metodo para evolucionar cada celula dentro del dominio del tablero
    private void evolveCells(){
        for (int i = this.xStart; i < this.xEnd; i++) {
            for (int j = this.yStart; j < this.yEnd; j++) {
                this.board[i][j].evolve();
            }
        }
        this.game.passTurn();
    }
    
    // Metodo para preparar la evolucion de cada celula dentro del dominio del tablero
    private void prepareCellsToEvolve(){
        for (int i = this.xStart; i < this.xEnd; i++) {
            for (int j = this.yStart; j < this.yEnd; j++) {
                this.board[i][j].prepareToEvolve();
            }
        }
        this.game.prepareTurn();
    }
    
    @Override
    public void run(){
        while (true) {
            turnExecution();
        }
    }
}
